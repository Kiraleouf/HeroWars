package com.example.guillaume_grand_clement.herowars.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.guillaume_grand_clement.herowars.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.subscriptions.CompositeSubscription;

public abstract class AbsActivity extends AppCompatActivity {

    //region Constants *****************************************************************************

    //endregion

    //region Fields ********************************************************************************

    protected CompositeSubscription mSubscriptions;
    protected Realm mRealm;

    private boolean mIsBound;
    private Unbinder mUnbinder;

    @BindView(R.id.coordinator_layout) CoordinatorLayout mCoordinatorLayout;

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
//        mRealm.close();
        if (mIsBound) {
            mUnbinder.unbind();
        }
        super.onDestroy();
    }

    //endregion

    //region Public Methods ************************************************************************

    public View getContentView() {
        return findViewById(R.id.coordinator_layout);
    }

    //endregion

    //region Protected Methods *********************************************************************

    protected void setupData(Bundle savedInstanceState) {
        mRealm.setDefaultConfiguration(new RealmConfiguration.Builder(this).build());
        mRealm = Realm.getDefaultInstance();
    }

    protected void setupUI(Bundle savedInstanceState, boolean bind) {
        if (bind) {
            mUnbinder = ButterKnife.bind(this);
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
