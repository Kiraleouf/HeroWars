package com.example.guillaume_grand_clement.herowars;

import android.app.Application;

import com.example.guillaume_grand_clement.herowars.utils.LogUtils;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class HeroWarsApplication extends Application {

    //region Constants *****************************************************************************

    public static final String TAG = HeroWarsApplication.class.getSimpleName();
    public static final String DATE = "%-40s %s";

    //endregion

    //region Fields ********************************************************************************

    //endregion

    //region Override Methods **********************************************************************

    @Override
    public void onCreate() {
        super.onCreate();

        Date startDate = new Date();
        // init realm in memory ram mode
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name("herowars.realm")
                .inMemory()
                .build();
        Realm.setDefaultConfiguration(config);
        LogUtils.i(TAG, String.format(DATE, "RealmConfiguration init time: ", (new Date().getTime() - startDate.getTime()) + " ms"));
    }


    //endregion

    //region Public Methods ************************************************************************

    //endregion

    //region Protected Methods *********************************************************************

    //endregion

    //region Private Methods ***********************************************************************

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
