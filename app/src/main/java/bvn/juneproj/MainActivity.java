package bvn.juneproj;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.net.HttpURLConnection;

import bvn.juneproj.model.bookcategory.BooksCategoriesResponse;
import bvn.juneproj.network.RetrofitFactory;
import bvn.juneproj.network.RetrofitService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("MAIN Activity", "STARTED");
        RetrofitService service = RetrofitFactory.getAdapter(this).create(RetrofitService.class);
        service.getBooksCategories("aeeddbeacaad46449993e0ff726e3d8b").enqueue(new Callback<BooksCategoriesResponse>() {
            @Override
            public void onResponse(Call<BooksCategoriesResponse> call, Response<BooksCategoriesResponse> response) {
                if (response.isSuccessful()){
                    if (response.raw().cacheResponse() != null) {
                        Log.d("RESPONSE", "FROM CACHE" + response.raw().code());
                    }
                    if (response.raw().networkResponse() != null) {
                        Log.d("RESPONSE", "FROM NETWORK" + response.raw().code());
                    }

                }
            }

            @Override
            public void onFailure(Call<BooksCategoriesResponse> call, Throwable t) {
                Log.d("FAILURE", call.toString());
            }
        });
    }
}
