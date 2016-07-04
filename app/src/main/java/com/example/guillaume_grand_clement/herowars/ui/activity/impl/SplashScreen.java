package com.example.guillaume_grand_clement.herowars.ui.activity.impl;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.example.guillaume_grand_clement.herowars.R;
import com.example.guillaume_grand_clement.herowars.ui.activity.AbsActivity;

import org.w3c.dom.Text;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Completable;
import rx.functions.Action0;

public class SplashScreen extends AbsActivity{
    //region Constants *****************************************************************************

    //endregion

    //region Fields ********************************************************************************

    @BindView(R.id.splash_ic_loader)
    View loader;
    @BindView(R.id.textViewWelcome)
    TextView mTextViewWelcome;
    @BindView(R.id.splash_loading)
    TextView mTextViewLoading;

    //endregion

    //region Override Methods **********************************************************************

    @Override
    protected void onResume() {
        super.onResume();
        mSubscriptions.add(Completable.timer(5000, TimeUnit.MILLISECONDS).subscribe(new Action0() {
            @Override
            public void call() {
                SplashScreen.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                MainActivity.start(SplashScreen.this);
            }
        }));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.splash_activity;
    }

    @Override
    protected void setupUI(Bundle savedInstanceState, boolean bind) {
        super.setupUI(savedInstanceState, bind);

        //animate the loader
        loader.setBackgroundResource(R.drawable.loader);
        AnimationDrawable frameAnimation = (AnimationDrawable) loader.getBackground();
        frameAnimation.start();

        //Set the wonderfull typeface
        mTextViewWelcome.setTypeface(getMainFont());
        mTextViewLoading.setTypeface(getMainFont());

        //animate the loading text
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(1000);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);
        mTextViewLoading.startAnimation(anim);
    }

    //endregion

    //region Public Methods ************************************************************************

    //endregion

    //region Protected Methods *********************************************************************

    //endregion

    //region Private Methods ***********************************************************************

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
