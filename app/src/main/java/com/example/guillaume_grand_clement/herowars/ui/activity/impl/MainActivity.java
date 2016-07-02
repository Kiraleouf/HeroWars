package com.example.guillaume_grand_clement.herowars.ui.activity.impl;

import android.support.design.widget.Snackbar;
import android.util.Log;

import com.example.guillaume_grand_clement.herowars.R;
import com.example.guillaume_grand_clement.herowars.data.pojo.SamplePojo;
import com.example.guillaume_grand_clement.herowars.data.query.sample.SampleQuery;
import com.example.guillaume_grand_clement.herowars.network.request.impl.SampleRequest;
import com.example.guillaume_grand_clement.herowars.ui.activity.AbsActivity;

import io.realm.RealmResults;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

public class MainActivity extends AbsActivity {


    @Override
    protected void onResume() {
        super.onResume();
        mSubscriptions.add(new SampleRequest(this).asObservable()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<SamplePojo>() {
                    @Override
                    public void call(SamplePojo samplePojo) {
                        SampleQuery.copySample(samplePojo);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        throwable.printStackTrace();
                    }
                }));

        mSubscriptions.add(SampleQuery.getSample().asObservable()
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<RealmResults<SamplePojo>>() {
                    @Override
                    public void call(RealmResults<SamplePojo> samplePojo) {
                        Snackbar.make(MainActivity.this.getContentView(), samplePojo.get(0).getName(), Snackbar.LENGTH_LONG).show();
                        Log.d(getClass().getSimpleName(), samplePojo.get(0).getName());
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Log.e(getClass().getSimpleName(), throwable.getMessage());
                    }
                }));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }
}
