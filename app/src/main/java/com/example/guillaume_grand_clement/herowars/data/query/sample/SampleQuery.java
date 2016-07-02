package com.example.guillaume_grand_clement.herowars.data.query.sample;

import com.example.guillaume_grand_clement.herowars.data.pojo.SamplePojo;

import io.realm.Realm;
import io.realm.RealmResults;

public class SampleQuery {

    public static void copySample(final SamplePojo samplePojo){
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(samplePojo);
        realm.commitTransaction();
        realm.close();
    }

    public static RealmResults<SamplePojo> getSample() {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmResults<SamplePojo> result =  realm.where(SamplePojo.class).findAll();
        realm.commitTransaction();
        realm.close();
        return result;
    }
}
