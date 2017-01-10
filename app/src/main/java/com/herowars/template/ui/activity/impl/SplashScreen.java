package com.herowars.template.ui.activity.impl;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.TextView;

import com.herowars.template.R;
import com.herowars.template.ui.activity.AbsActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import rx.Completable;
import rx.functions.Action0;

public class SplashScreen extends AbsActivity {
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

    @Override
    protected void onResume() {
        super.onResume();
        mSubscriptions.add(Completable.timer(5000, TimeUnit.MILLISECONDS).subscribe(new Action0() {
            @Override
            public void call() {
                SplashScreen.this.overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                startActivity(MainActivity.buildIntent(getApplicationContext()));
            }
        }));
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
