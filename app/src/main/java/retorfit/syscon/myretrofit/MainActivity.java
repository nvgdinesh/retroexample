package retorfit.syscon.myretrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import parser.SimpleParser;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button req_link;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        req_link=(Button)findViewById(R.id.req_link);
        result=(TextView) findViewById(R.id.result);
        req_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ApiInterface firstreq=ApiService.reqService(ApiInterface.class);
                Call<SimpleParser> callservice=firstreq.getResponse();
                callservice.enqueue(new Callback<SimpleParser>() {
                    @Override
                    public void onResponse(Call<SimpleParser> call, Response<SimpleParser> response) {
                        int statusCode = response.code();
                        Log.e("MainActivity", "The Status is "+statusCode);
                        try{
                            SimpleParser sp=response.body();
                            Log.e("MainActivity", "The Status is "+sp.RESPONSECODE);
                            Log.e("MainActivity", "The Status is "+sp.ERRCODE);

                        }catch (Exception e){}
                    }

                    @Override
                    public void onFailure(Call<SimpleParser> call, Throwable t) {

                    }
                });
            }
        });
    }
}
