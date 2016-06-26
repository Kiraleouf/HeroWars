package com.example.guillaume_grand_clement.herowars.ui.activity.impl;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.guillaume_grand_clement.herowars.R;
import com.example.guillaume_grand_clement.herowars.data.pojo.SamplePojo;
import com.example.guillaume_grand_clement.herowars.network.request.impl.SampleRequest;
import com.example.guillaume_grand_clement.herowars.ui.activity.AbsActivity;

import rx.functions.Action1;

public class MainActivity extends AbsActivity {


    @Override
    protected void onResume() {
        super.onResume();
        //Tu fais ca pour lancer une requette :
        mSubscriptions.add(new SampleRequest(this).asObservable().subscribe(new Action1<SamplePojo>() {
            @Override
            public void call(SamplePojo samplePojo) {
                //résultat avec ton pojo en parametre
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                //En cas d'erreur
            }
        }));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }
}
