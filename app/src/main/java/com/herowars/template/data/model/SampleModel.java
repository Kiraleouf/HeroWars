package com.herowars.template.data.model;

import io.realm.RealmObject;

public class SampleModel extends RealmObject {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
