package com.herowars.template.ui.activity.impl;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;

import com.herowars.template.R;
import com.herowars.template.data.model.SampleModel;
import com.herowars.template.ui.activity.AbsActivity;
import com.herowars.template.network.pojo.SamplePojo;
import com.herowars.template.data.query.SampleQuery;
import com.herowars.template.network.request.impl.SampleRequest;
import com.herowars.template.utils.LogUtils;

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
        mSubscriptions.add(SampleQuery.getSample(mRealm).filter(new Func1<RealmResults<SampleModel>, Boolean>() {
            @Override
            public Boolean call(RealmResults<SampleModel> sampleModels) {
                return sampleModels.size() > 0;
            }
        }).doOnNext(new Action1<RealmResults<SampleModel>>() {
            @Override
            public void call(RealmResults<SampleModel> sampleModels) {
                Snackbar.make(MainActivity.this.getContentView(),
                        "Bienvenue " + sampleModels.first().getName() + " !",
                        Snackbar.LENGTH_LONG)
                        .show();
            }
        }).doOnError(new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                LogUtils.e(TAG, throwable.getMessage(), throwable);
            }
        }).subscribe());
    }

    @OnClick(R.id.button)
    protected void onCallClick() {
        mSubscriptions.add(new SampleRequest(mApi).asObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnError(new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        LogUtils.e(TAG, throwable.getMessage(), throwable);
                    }
                }).subscribe(new Action1<SamplePojo>() {
                    @Override
                    public void call(SamplePojo samplePojo) {

                        SampleModel sampleModel = mRealm.createObject(SampleModel.class);
                        sampleModel.setName(samplePojo.getName());

                        SampleQuery.copySample(mRealm, sampleModel);
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
