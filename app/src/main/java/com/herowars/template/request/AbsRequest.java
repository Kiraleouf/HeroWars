package com.herowars.template.request;

import com.herowars.template.dagger.herowars.HeroWarsModule;

import rx.Observable;

public abstract class AbsRequest<T> {

    //region Constants *****************************************************************************

    //endregion

    //region Fields ********************************************************************************

    protected HeroWarsModule.HeroWarsApiInterface mApi;

    //endregion

    //region Override Methods **********************************************************************

    //endregion

    //region Public Methods ************************************************************************

    public AbsRequest(HeroWarsModule.HeroWarsApiInterface api) {
        this.mApi = api;
    }

    public abstract Observable<T> asObservable();

    //endregion

    //region Protected Methods *********************************************************************

    //endregion

    //region Private Methods ***********************************************************************

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
