package com.herowars.template.network.core;

import android.app.Application;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.RealmObject;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

@Module
public class NetModule {

    //region Constants *****************************************************************************
    //endregion

    //region Fields ********************************************************************************

    private String mBaseUrl;

    //endregion

    //region Override Methods **********************************************************************
    //endregion

    //region Private Methods ***********************************************************************
    //endregion

    //region Public Methods ************************************************************************

    public NetModule(String baseUrl) {
        this.mBaseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Cache provideOkHttpCache(Application application) {
        final int cacheSize = 10 * 1024 * 1024; // 10 MiB
        final Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson provideGson() {
        final GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        gsonBuilder.setExclusionStrategies(mExclusionStrategy);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(Cache cache) {
        final OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.cache(cache);
        builder.readTimeout(2000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(2000, TimeUnit.MILLISECONDS);
        return builder.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Gson gson, OkHttpClient okHttpClient) {
        final Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(mBaseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io()))
                .client(okHttpClient)
                .build();
        return retrofit;
    }
    //endregion

    //region Inner Classes or Interfaces ***********************************************************

    private final ExclusionStrategy mExclusionStrategy = new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return f.getDeclaringClass().equals(RealmObject.class);
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    };


    //endregion
}
