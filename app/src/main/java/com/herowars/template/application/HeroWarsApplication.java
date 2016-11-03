package com.herowars.template.application;

import android.app.Application;

import com.herowars.template.BuildConfig;
import com.herowars.template.dagger.base.AppModule;
import com.herowars.template.dagger.base.DaggerNetComponent;
import com.herowars.template.dagger.base.NetComponent;
import com.herowars.template.dagger.base.NetModule;
import com.herowars.template.dagger.herowars.DaggerHeroWarsComponent;
import com.herowars.template.dagger.herowars.HeroWarsComponent;
import com.herowars.template.dagger.herowars.HeroWarsModule;
import com.herowars.template.utils.LogUtils;

import java.util.Date;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class HeroWarsApplication extends Application {

    //region Constants *****************************************************************************

    public static final String TAG = HeroWarsApplication.class.getSimpleName();
    public static final String DATE = "%-40s %s";

    //endregion

    //region Fields ********************************************************************************

    private NetComponent mNetComponent;
    private HeroWarsComponent mHeroWarsComponent;

    //endregion

    //region Override Methods **********************************************************************

    @Override
    public void onCreate() {
        super.onCreate();

        mNetComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BuildConfig.BASE_URL))
                .build();

        mHeroWarsComponent = DaggerHeroWarsComponent.builder()
                .netComponent(mNetComponent)
                .heroWarsModule(new HeroWarsModule())
                .build();

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

//    public NetComponent getNetComponent() {
//        return mNetComponent;
//    }

    public HeroWarsComponent getHeroWarsComponent() {
        return mHeroWarsComponent;
    }

    //endregion

    //region Protected Methods *********************************************************************

    //endregion

    //region Private Methods ***********************************************************************

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
