package com.herowars.template.request.impl;

import com.herowars.template.data.pojo.SamplePojo;
import com.herowars.template.request.AbsRequest;

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

    public SampleRequest() {
        super();
    }

    //endregion

    //region Protected Methods *********************************************************************

    //endregion

    //region Private Methods ***********************************************************************

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
