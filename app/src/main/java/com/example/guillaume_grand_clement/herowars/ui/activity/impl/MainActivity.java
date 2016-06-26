package com.example.guillaume_grand_clement.herowars.ui.activity.impl;

import com.example.guillaume_grand_clement.herowars.R;
import com.example.guillaume_grand_clement.herowars.data.pojo.SamplePojo;
import com.example.guillaume_grand_clement.herowars.network.request.impl.SampleRequest;
import com.example.guillaume_grand_clement.herowars.ui.activity.AbsActivity;

import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class MainActivity extends AbsActivity {


    @Override
    protected void onResume() {
        super.onResume();
        //Tu fais ca pour lancer une requette :
        mSubscriptions.add(new SampleRequest(this).asObservable()
                //Designe le thread sur lequel la requete sera executé
                .subscribeOn(Schedulers.io())

                //Designe le thread sur lequel la réponse sera interprété
                .observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<SamplePojo>() {
            @Override
            public void onCompleted() {
                //TODO Lorsque la requete est terminée

            }

            @Override
            public void onError(Throwable e) {
                //TODO Lorsque la requete retourne une erreur

            }

            @Override
            public void onNext(SamplePojo samplePojo) {
                //TODO Lorsque la requete retourne quelque chose

            }
        }));
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_main;
    }
}
