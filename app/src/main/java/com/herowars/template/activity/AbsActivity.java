package com.herowars.template.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.herowars.template.R;
import com.herowars.template.application.HeroWarsApplication;
import com.herowars.template.dagger.herowars.HeroWarsModule;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.subscriptions.CompositeSubscription;

public abstract class AbsActivity extends AppCompatActivity {

    //region Constants *****************************************************************************

    protected final String TAG = getClass().getSimpleName();

    //endregion

    //region Fields ********************************************************************************

    @Inject
    Retrofit mRetrofit;

    @Inject
    OkHttpClient mOkHttpClient;

    @Inject
    protected HeroWarsModule.HeroWarsApiInterface mApi;

    protected CompositeSubscription mSubscriptions;
    protected Realm mRealm;

    private boolean mIsBound;
    private Typeface mMainFont;
    private Unbinder mUnbinder;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout mCoordinatorLayout;

    //endregion

    //region Override Methods **********************************************************************

    @Override
    protected final void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResId());
        setupData(savedInstanceState);
        setupUI(savedInstanceState, true);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSubscriptions = new CompositeSubscription();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        mRealm.close();
        if (mIsBound) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }

    //endregion

    //region Public Methods ************************************************************************

    protected View getContentView() {
        return findViewById(R.id.coordinator_layout);
    }

    protected Typeface getMainFont() {
        return mMainFont;
    }

    //endregion

    //region Protected Methods *********************************************************************

    protected void setupData(Bundle savedInstanceState) {

        // inject activity
        ((HeroWarsApplication) getApplication()).getHeroWarsComponent().inject(this);

        // Realm
        mRealm = Realm.getDefaultInstance();

        // typeface
        mMainFont = Typeface.createFromAsset(getAssets(), "crusades.ttf");
    }

    protected void setupUI(Bundle savedInstanceState, boolean bind) {
        if (bind) {
            mUnbinder = ButterKnife.bind(this, this.getContentView());
        }
        mIsBound = bind;
    }

    @LayoutRes
    protected abstract int getLayoutResId();

    //endregion

    //region Private Methods ***********************************************************************

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
