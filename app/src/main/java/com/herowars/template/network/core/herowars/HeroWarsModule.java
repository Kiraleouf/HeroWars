package com.herowars.template.network.core.herowars;

import com.herowars.template.network.pojo.SamplePojo;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

@Module
public class HeroWarsModule {

    public interface HeroWarsApiInterface {
        @GET("/profile")
        Observable<SamplePojo> sampleServiceWithParam(@Query("param1") int currentPage,
                                                      @Query("param2") int pageSize);

        @GET("profile/")
        Observable<SamplePojo> sampleService();

    }

    @Provides
    @HeroWarsScope
    public HeroWarsApiInterface providesHeroWarsApiInterface(Retrofit retrofit) {
        return retrofit.create(HeroWarsApiInterface.class);
    }
}
