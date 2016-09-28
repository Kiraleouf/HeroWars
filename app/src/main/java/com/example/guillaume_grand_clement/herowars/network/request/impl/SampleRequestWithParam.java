package com.example.guillaume_grand_clement.herowars.network.request.impl;

import android.content.Context;

import com.example.guillaume_grand_clement.herowars.data.pojo.SamplePojo;
import com.example.guillaume_grand_clement.herowars.network.client.HeroWarsClient;
import com.example.guillaume_grand_clement.herowars.network.request.AbsRequest;

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
        return HeroWarsClient.getService().sampleServiceWithParam(mParam1, mParam2);
    }

    //endregion

    //region Public Methods ************************************************************************

    public SampleRequestWithParam(Context context, int param1, int param2) {
        super(context);
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
