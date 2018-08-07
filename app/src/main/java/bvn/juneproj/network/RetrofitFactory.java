package bvn.juneproj.network;

import android.content.Context;

import com.google.gson.GsonBuilder;

import java.io.IOException;

import bvn.juneproj.BuildConfig;
import bvn.juneproj.Utils;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Bhavin on 6/27/2018.
 */
public class RetrofitFactory {

    private static HttpLoggingInterceptor loggingInterceptor;
    private static OkHttpClient.Builder okHttpClientBuilder;
    private final static int CACHE_SIZE_BYTES = 1024 * 1024 * 2;


    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            if (Utils.isNetworkAvailable()) {
                int maxAge = 60; // read from cache for 1 minute
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, max-age=" + maxAge)
                        .build();
            } else {
                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
                return originalResponse.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                        .build();
            }
        }
    };

    public static Retrofit getAdapter(Context context) {
        if (okHttpClientBuilder == null) {
            loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

            Cache cache = new Cache(context.getCacheDir(), CACHE_SIZE_BYTES);

            okHttpClientBuilder = new OkHttpClient.Builder()
                    .addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR)
                    .cache(cache);

            if (BuildConfig.DEBUG) {
                okHttpClientBuilder.addNetworkInterceptor(loggingInterceptor);
            }

        }
        return new Retrofit.Builder()
                .baseUrl("https://api.nytimes.com/svc/books/v3/")
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
