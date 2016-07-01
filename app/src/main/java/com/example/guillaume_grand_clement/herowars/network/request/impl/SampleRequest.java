package com.example.guillaume_grand_clement.herowars.network.request.impl;

import android.content.Context;
import android.util.Log;

import com.example.guillaume_grand_clement.herowars.data.pojo.SamplePojo;
import com.example.guillaume_grand_clement.herowars.network.client.HeroWarsClient;
import com.example.guillaume_grand_clement.herowars.network.request.AbsRequest;

import io.realm.Realm;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class SampleRequest extends AbsRequest<SamplePojo> {

    //region Constants *****************************************************************************

    //endregion

    //region Fields ********************************************************************************

    //endregion

    //region Override Methods **********************************************************************

    //endregion

    //region Public Methods ************************************************************************

    public SampleRequest(Context context) {
        super(context);
    }

    @Override
    public Observable<SamplePojo> asObservable() {
        return HeroWarsClient.getService(mContext).sampleService()
                .asObservable()
                .doOnNext(new Action1<SamplePojo>() {
                    @Override
                    public void call(final SamplePojo samplePojo) {
                        final Realm realm = Realm.getDefaultInstance();
                        realm.beginTransaction();
                        realm.executeTransaction(new Realm.Transaction() {
                            @Override
                            public void execute(Realm realm) {
                                realm.copyToRealm(samplePojo);
                            }
                        });
                        realm.commitTransaction();
                        realm.close();
                    }
                });
    }
    //endregion

    //region Protected Methods *********************************************************************

    //endregion

    //region Private Methods ***********************************************************************

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
