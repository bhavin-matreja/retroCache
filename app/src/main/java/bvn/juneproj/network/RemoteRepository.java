package bvn.juneproj.network;

import retrofit2.Retrofit;

/**
 * Created by Bhavin on 6/27/2018.
 */
public class RemoteRepository {

    RetrofitService service;

    public RemoteRepository(Retrofit retrofit) {
        this.service = retrofit.create(RetrofitService.class);
    }

    public void getBooksCategories(){

    }
}
