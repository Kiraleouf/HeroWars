package com.herowars.template.dagger.base;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    //region Constants *****************************************************************************
    //endregion

    //region Fields ********************************************************************************

    private Application mApplication;

    //endregion

    //region Override Methods **********************************************************************
    //endregion

    //region Private Methods ***********************************************************************
    //endregion

    //region Public Methods ************************************************************************

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
