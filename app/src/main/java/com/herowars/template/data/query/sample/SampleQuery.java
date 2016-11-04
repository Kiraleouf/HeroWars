package com.herowars.template.data.query.sample;

import com.herowars.template.data.pojo.SamplePojo;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

public class SampleQuery extends Realm.Transaction.Callback {

    public static void copySample(final Realm realm, final SamplePojo samplePojo) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(SamplePojo.class);
                realm.copyToRealm(samplePojo);
            }
        });
    }

    public static Observable<RealmResults<SamplePojo>> getSample(final Realm realm) {
        return realm.where(SamplePojo.class).findAllAsync().asObservable();
    }

    @Override
    public void onSuccess() {
        super.onSuccess();
    }
}
