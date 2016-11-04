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

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class HeroWarsApplication extends Application {

    //region Constants *****************************************************************************

    //endregion

    //region Fields ********************************************************************************

    private HeroWarsComponent mHeroWarsComponent;

    //endregion

    //region Override Methods **********************************************************************

    @Override
    public void onCreate() {
        super.onCreate();

        // init base dagger
        final NetComponent netComponent = DaggerNetComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(BuildConfig.BASE_URL))
                .build();

        // init final dagger component
        mHeroWarsComponent = DaggerHeroWarsComponent.builder()
                .netComponent(netComponent)
                .heroWarsModule(new HeroWarsModule())
                .build();

        // init realm in memory ram mode
        RealmConfiguration config = new RealmConfiguration.Builder(this)
                .name("herowars.realm")
                .inMemory()
                .build();
        Realm.setDefaultConfiguration(config);
    }

    //endregion

    //region Public Methods ************************************************************************

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
