package com.herowars.template.request.impl;

import com.herowars.template.dagger.herowars.HeroWarsModule;
import com.herowars.template.data.pojo.SamplePojo;
import com.herowars.template.request.AbsRequest;

import rx.Observable;

public class SampleRequestWithParam extends AbsRequest<SamplePojo> {

    //region Constants *****************************************************************************

    //endregion

    //region Fields ********************************************************************************

    private final int mParam1;
    private final int mParam2;

    //endregion

    //region Override Methods **********************************************************************

    @Override
    public Observable<SamplePojo> asObservable() {
        return mApi.sampleServiceWithParam(mParam1, mParam2);
    }

    //endregion

    //region Public Methods ************************************************************************

    public SampleRequestWithParam(HeroWarsModule.HeroWarsApiInterface api, int param1, int param2) {
        super(api);
        mParam1 = param1;
        mParam2 = param2;
    }

    //endregion

    //region Protected Methods *********************************************************************

    //endregion

    //region Private Methods ***********************************************************************

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
