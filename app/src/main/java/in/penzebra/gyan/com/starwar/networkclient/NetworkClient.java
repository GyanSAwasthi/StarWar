package in.penzebra.gyan.com.starwar.networkclient;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkClient {

    // http://keywordmarket.in/
     public static final String BASE_URL = "https://swapi.co/api/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {
try{
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS).build();

        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        int timeOutTime = 60000; // 60 seconds
        httpClient.connectTimeout(timeOutTime, TimeUnit.MILLISECONDS);
        httpClient.readTimeout(timeOutTime, TimeUnit.MILLISECONDS);
        httpClient.addInterceptor(logging);
    if (retrofit == null) {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

    }

    } catch (Exception e) {
        e.printStackTrace();
    }

        return retrofit;

    }


}
