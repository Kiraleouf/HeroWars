package com.herowars.template.network.request.impl;

import com.herowars.template.network.core.herowars.HeroWarsModule;
import com.herowars.template.network.pojo.SamplePojo;
import com.herowars.template.network.request.AbsRequest;

import rx.Observable;

public class SampleRequest extends AbsRequest<SamplePojo> {

    //region Constants *****************************************************************************

    //endregion

    //region Fields ********************************************************************************

    //endregion

    //region Override Methods **********************************************************************

    @Override
    public Observable<SamplePojo> asObservable() {
        return mApi.sampleService().asObservable();
    }

    //endregion

    //region Public Methods ************************************************************************

    public SampleRequest(HeroWarsModule.HeroWarsApiInterface api) {
        super(api);
    }

    //endregion

    //region Protected Methods *********************************************************************

    //endregion

    //region Private Methods ***********************************************************************

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
