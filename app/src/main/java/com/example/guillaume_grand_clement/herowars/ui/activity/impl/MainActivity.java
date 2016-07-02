package com.example.guillaume_grand_clement.herowars.ui.activity.impl;

import android.support.design.widget.Snackbar;

import com.example.guillaume_grand_clement.herowars.R;
import com.example.guillaume_grand_clement.herowars.data.pojo.SamplePojo;
import com.example.guillaume_grand_clement.herowars.data.query.sample.SampleQuery;
import com.example.guillaume_grand_clement.herowars.network.request.impl.SampleRequest;
import com.example.guillaume_grand_clement.herowars.ui.activity.AbsActivity;

import butterknife.OnClick;
import hugo.weaving.DebugLog;
import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AbsActivity {

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
                        throwable.printStackTrace();
                    }
                }));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }
}
