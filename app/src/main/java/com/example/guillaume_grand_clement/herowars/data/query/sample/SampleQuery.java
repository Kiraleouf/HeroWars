package com.example.guillaume_grand_clement.herowars.data.query.sample;
import com.example.guillaume_grand_clement.herowars.data.pojo.SamplePojo;

import io.realm.Realm;
import io.realm.RealmResults;

public class SampleQuery {
    public static RealmResults<SamplePojo> getSample(final Realm realm) {
         return realm.where(SamplePojo.class).findAllAsync();
    }

}
