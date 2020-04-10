package com.example.joshtalksapp.service;

import com.example.joshtalksapp.model.PhotoRecords;
import com.example.joshtalksapp.presenter.MainActivityPresenter;
import com.example.joshtalksapp.util.Constants;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Retrofit2Client implements RestClient {
    public static Retrofit2Client retrofitLibInstance;
    public static Retrofit retrofit;
    Call<PhotoRecords> call;
    APIService apiService;
    MainActivityPresenter mainActivityPresenter;

    private Retrofit2Client() {
    }

    public static synchronized Retrofit2Client getInstance() {
        if (retrofitLibInstance == null) {
            retrofitLibInstance = new Retrofit2Client();
        }
        return retrofitLibInstance;
    }

    Retrofit getRetrofitInstance() {
        return new retrofit2.Retrofit.Builder()
                .baseUrl(APIService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Override
    public void fetchImageRecordsByTag(MainActivityPresenter mainActivityPresenter, String tag) {
        this.mainActivityPresenter = mainActivityPresenter;
        retrofit = getRetrofitInstance();
        apiService = retrofit.create(APIService.class);

        call = apiService.searchImagesByTag(
                "flickr.photos.search",
                Constants.API_KEY,
                tag,
                "json",
                1
        );
        fetchAllImagesByTag(call);
    }

    public void fetchAllImagesByTag(Call<PhotoRecords> call) {
        call.enqueue(new Callback<PhotoRecords>() {
            @Override
            public void onResponse(Call<PhotoRecords> call, Response<PhotoRecords> response) {
                if (response.isSuccessful()) {
                    PhotoRecords imageList = response.body();
                    mainActivityPresenter.onSuccessfulResponse(imageList);
                }
            }
            @Override
            public void onFailure(Call<PhotoRecords> call, Throwable t) {
                if(t instanceof Exception){
                    mainActivityPresenter.onFailure("Some error occured");
                }
            }
        });
    }

}
