package com.example.guillaume_grand_clement.herowars.ui.activity.impl;

import android.support.design.widget.Snackbar;
import android.util.Log;

import com.example.guillaume_grand_clement.herowars.R;
import com.example.guillaume_grand_clement.herowars.data.pojo.SamplePojo;
import com.example.guillaume_grand_clement.herowars.data.query.sample.SampleQuery;
import com.example.guillaume_grand_clement.herowars.network.request.impl.SampleRequest;
import com.example.guillaume_grand_clement.herowars.ui.activity.AbsActivity;

import io.realm.RealmResults;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AbsActivity {


    @Override
    protected void onResume() {
        super.onResume();
        mSubscriptions.add(new SampleRequest(this).asObservable()
                .subscribeOn(Schedulers.io())

                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<SamplePojo>() {
                    @Override
                    public void call(SamplePojo samplePojo) {
                        Log.e(getClass().getSimpleName(), "succes");
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(getClass().getSimpleName(), throwable.getMessage());
                    }
                }));
        mSubscriptions.add(SampleQuery.getSample(mRealm).asObservable().filter(new Func1<RealmResults<SamplePojo>, Boolean>() {
            @Override
            public Boolean call(RealmResults<SamplePojo> samplePojos) {
                return samplePojos.size() != 0;
            }
        }).subscribe(new Action1<RealmResults<SamplePojo>>() {
            @Override
            public void call(RealmResults<SamplePojo> samplePojo) {
                Snackbar.make(MainActivity.this.getContentView(), samplePojo.get(0).getName(), Snackbar.LENGTH_LONG).show();
                Log.d(getClass().getSimpleName(),samplePojo.get(0).getName());
            }
        }));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }
}
