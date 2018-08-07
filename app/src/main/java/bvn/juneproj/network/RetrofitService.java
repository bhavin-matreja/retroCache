package bvn.juneproj.network;

import bvn.juneproj.model.bookcategory.BooksCategoriesResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Bhavin on 6/27/2018.
 */
public interface RetrofitService {

    @GET("lists/names.json")
    Call<BooksCategoriesResponse> getBooksCategories(@Query("api-key") String apiKey);
}
