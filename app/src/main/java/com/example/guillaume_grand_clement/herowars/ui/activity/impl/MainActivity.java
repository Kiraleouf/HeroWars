package com.example.guillaume_grand_clement.herowars.ui.activity.impl;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;

import com.example.guillaume_grand_clement.herowars.R;
import com.example.guillaume_grand_clement.herowars.data.pojo.SamplePojo;
import com.example.guillaume_grand_clement.herowars.data.query.sample.SampleQuery;
import com.example.guillaume_grand_clement.herowars.network.request.impl.SampleRequest;
import com.example.guillaume_grand_clement.herowars.ui.activity.AbsActivity;

import butterknife.OnClick;
import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AbsActivity {

    //region Constants *****************************************************************************

    //endregion

    //region Fields ********************************************************************************

    //endregion

    //region Override Methods **********************************************************************

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSubscriptions.add(SampleQuery.getSample(mRealm).filter(new Func1<RealmResults<SamplePojo>, Boolean>() {
            @Override
            public Boolean call(RealmResults<SamplePojo> samplePojos) {
                return samplePojos.size() > 0;
            }
        }).doOnNext(new Action1<RealmResults<SamplePojo>>() {
            @Override
            public void call(RealmResults<SamplePojo> samplePojos) {
                Snackbar.make(MainActivity.this.getContentView(),
                        "Bienvenue " + samplePojos.get(0).getName() + " !",
                        Snackbar.LENGTH_LONG)
                        .show();
            }
        }).subscribe());
    }

    @OnClick(R.id.button)
    protected void onCallClick() {
        mSubscriptions.add(new SampleRequest(this).asObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SamplePojo>() {
                    @Override
                    public void call(SamplePojo samplePojo) {
                        SampleQuery.copySample(mRealm, samplePojo);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Snackbar.make(MainActivity.this.getContentView(),
                                "Serveur HS !",
                                Snackbar.LENGTH_LONG)
                                .show();
                    }
                }));
    }

    //endregion

    //region Public Methods ************************************************************************

    public static Intent buildIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    //endregion

    //region Protected Methods *********************************************************************

    //endregion

    //region Private Methods ***********************************************************************

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion


}
