package com.example.guillaume_grand_clement.herowars.ui.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.guillaume_grand_clement.herowars.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.realm.Realm;
import rx.subscriptions.CompositeSubscription;

public abstract class AbsActivity extends AppCompatActivity {

    //region Constants *****************************************************************************

    //endregion

    //region Fields ********************************************************************************

    protected CompositeSubscription mSubscriptions;
    protected Realm mRealm;
    protected boolean mIsOffline;

    private Snackbar mSnackbar;
    private boolean mIsBound;
    private Unbinder mUnbinder;

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

    public View getContentView() {
        return findViewById(R.id.coordinator_layout);
    }

    //endregion

    //region Protected Methods *********************************************************************

    protected void setupData(Bundle savedInstanceState) {
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

    private void dismissSnackbarIfNeeded() {
        if (mSnackbar != null) {
            mSnackbar.dismiss();
            mSnackbar = null;
        }
    }

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
