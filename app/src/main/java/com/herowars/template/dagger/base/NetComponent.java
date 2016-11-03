package com.herowars.template.dagger.base;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Dans cette classe doit être déclaré explicitement
 * les références d'objets déclarés dans les modules
 */
@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    Retrofit retrofit();

    OkHttpClient okHttpClient();
}
