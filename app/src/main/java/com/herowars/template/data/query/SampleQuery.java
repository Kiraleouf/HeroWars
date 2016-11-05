package com.herowars.template.data.query;

import com.herowars.template.data.model.SampleModel;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

public class SampleQuery extends Realm.Transaction.Callback {

    public static void copySample(final Realm realm, final SampleModel sampleModel) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.delete(SampleModel.class);
                realm.copyToRealm(sampleModel);
            }
        });
    }

    public static Observable<RealmResults<SampleModel>> getSample(final Realm realm) {
        return realm.where(SampleModel.class).findAllAsync().asObservable();
    }

    @Override
    public void onSuccess() {
        super.onSuccess();
    }
}
