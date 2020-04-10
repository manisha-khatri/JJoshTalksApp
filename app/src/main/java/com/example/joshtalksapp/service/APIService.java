package com.example.joshtalksapp.service;

import com.example.joshtalksapp.model.PhotoRecords;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIService {
    String BASE_URL = "https://www.flickr.com/services/";

    //https://www.flickr.com/services/rest/?method=flickr.photos.search&api_key=1103d35517cc5e24b6828afe6b723e53&tags=cats&format=json&nojsoncallback=1

    @GET("rest/")
    Call<PhotoRecords> searchImagesByTag(
            @Query("method") String method,
            @Query("api_key") String apiKey,
            @Query("tags") String tags,
            @Query("format") String format,
            @Query("nojsoncallback") int nojsoncallback
    );
}
