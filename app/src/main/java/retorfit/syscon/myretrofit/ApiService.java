package retorfit.syscon.myretrofit;

import android.util.Base64;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by syscon on 17/1/17.
 */

public class ApiService {
    private static  String API_BASE_URL="https://apps.bharatmatrimony.com";

    private static  OkHttpClient.Builder mClient=new OkHttpClient.Builder();

    private  static Retrofit.Builder rmBuilder=new Retrofit.Builder().addConverterFactory(GsonConverterFactory.create()).baseUrl(API_BASE_URL);

    public static <S> S reqService (Class<S> reqClass){
        String credentials = "appsadmin:A7jgPjuK";
        final String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        mClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original=chain.request();

                Request.Builder mRequestBuilder= new Request.Builder().addHeader("Accept","application/json").addHeader("Authorization",basic).method(original.method(),original.body());

                Request request =mRequestBuilder.build();

                return chain.proceed(request);
            }
        }).connectTimeout(20, TimeUnit.SECONDS).readTimeout(20,TimeUnit.SECONDS).writeTimeout(20,TimeUnit.SECONDS);

        Retrofit mRetro=rmBuilder.client(mClient.build()).build();
        return mRetro.create(reqClass);

    }
}
