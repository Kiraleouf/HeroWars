package com.example.guillaume_grand_clement.herowars.network.client;

import com.example.guillaume_grand_clement.herowars.BuildConfig;
import com.example.guillaume_grand_clement.herowars.network.IHeroWarsService;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import io.realm.RealmObject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

public class HeroWarsClient {

    //region Constants *****************************************************************************

    //endregion

    //region Fields ********************************************************************************

    private static HeroWarsClient sInstance;
    private IHeroWarsService mService;

    //endregion

    //region Override Methods **********************************************************************

    //endregion

    //region Public Methods ************************************************************************

    public static IHeroWarsService getService() {
        return getClient().mService;
    }

    //endregion

    //region Protected Methods *********************************************************************

    //endregion

    //region Private Methods ***********************************************************************

    private static HeroWarsClient getClient() {
        if (sInstance == null) {
            sInstance = new HeroWarsClient();
        }
        return sInstance;
    }

    private HeroWarsClient() {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        final Gson gson = getGson();
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(builder.build())
                .build();
        mService = retrofit.create(IHeroWarsService.class);
    }

    private Gson getGson() {
        return new GsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();
    }

    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    //endregion
}
