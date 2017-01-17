package retorfit.syscon.myretrofit;

import parser.SimpleParser;
import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by syscon on 17/1/17.
 */

public interface ApiInterface {
    @Headers("Cache-Control: no-cache")
    @FormUrlEncoded
    @POST("/applogin/login.php")
    Call<SimpleParser> getResponse();
}
