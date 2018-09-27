package com.onedatashare.com.onedatashare.apis;

import android.app.Activity;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.onedatashare.com.onedatashare.BuildConfig;
import com.onedatashare.com.onedatashare.utils.NetworkUtils;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * The APIClient class for retrofit client
 */
public class APIClient {

    private static Retrofit retrofit = null;
    private static APIInterface mApiService = null;

    public static Retrofit getClient(Activity activity) {

        if (retrofit == null) {

            Gson gson = new GsonBuilder()
                    .setLenient()
                    .create();

            OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                    .connectTimeout(5, TimeUnit.MINUTES)
                    .readTimeout(5, TimeUnit.MINUTES);

            if (BuildConfig.DEBUG) {

                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);
                httpClient.addInterceptor(logging);
            }

            retrofit = new Retrofit.Builder()
                    .baseUrl(AppConstants.DEBUG_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(httpClient.build())
                    .build();
        }

        return retrofit;
    }

    public static APIInterface getmApiService(Activity activity) {

        if (mApiService == null) {
            mApiService = APIClient.getClient(activity)
                    .create(APIInterface.class);
        }
        return mApiService;
    }

    public static class ConnectivityInterceptor implements Interceptor {

        private Activity activity;

        public ConnectivityInterceptor(Activity activity) {
            this.activity = activity;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {
            if (!NetworkUtils.isOnline(activity)) {

                throw new NoConnectivityException();
            }

            Request.Builder builder = chain.request().newBuilder();
            return chain.proceed(builder.build());
        }
    }

    public static class NoConnectivityException extends IOException {

        @Override
        public String getMessage() {
            return "No connectivity exception";
        }
    }
}
