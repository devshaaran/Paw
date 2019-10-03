package com.disrupt.paws.dependency_injection;

import android.app.Application;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.disrupt.paws.R;
import com.disrupt.paws.network.PawsApi;
import com.disrupt.paws.util.Constants;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class AppModule {

    /*
    @Singleton
    @Provides
    static Context provideApplicationContext(Application application) {
        return application.getApplicationContext();
    }
    */

    @Singleton
    @Provides
    static RequestOptions provideRequestOptions() {
        return RequestOptions
                .placeholderOf(R.drawable.ic_mood_black_24dp)
                .error(R.drawable.ic_broken_image_black_24dp);
    }

    @Singleton
    @Provides
    static RequestManager provideGlideInstance(Application application, RequestOptions requestOptions) {
        return Glide.with(application)
                .setDefaultRequestOptions(requestOptions);
    }

    @Singleton
    @Provides
    static Interceptor provideOkHttpClientInterceptor() {
        return new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                Request request = original.newBuilder()
                        .url(original.url().newBuilder()
                                .build())
                        .method(original.method(), original.body())
                        .build();

                return chain.proceed(request);
            }
        };
    }

    @Singleton
    @Provides
    static OkHttpClient provideOkHttpClient(Interceptor interceptor) {
        OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder();
        okHttpClientBuilder.addInterceptor(interceptor);
        return okHttpClientBuilder.build();
    }

    @Singleton
    @Provides
    static Retrofit provideRetrofitInstance(OkHttpClient okHttpClient) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    static PawsApi providePawsApi(Retrofit retrofit) {
        return retrofit.create(PawsApi.class);
    }
}
