package com.example.joshtalksapp.presenter;

import com.example.joshtalksapp.contract.MainActivityContract;
import com.example.joshtalksapp.model.PhotoRecords;
import com.example.joshtalksapp.service.RemoteDataSource;
import com.example.joshtalksapp.service.WebServiceClient;

public class MainActivityPresenter {
    MainActivityContract mainActivityContract;

    public MainActivityPresenter(MainActivityContract mainActivityContract){
        this.mainActivityContract = mainActivityContract;
    }

    public void fetchImageRecordsByTag(String tag){
        //Remote Data Source can be DB or web service client
        RemoteDataSource dataSourceInstance = WebServiceClient.getInstance();
        dataSourceInstance.fetchImageRecordsByTag(this, tag);
    }

    public void onSuccessfulResponse(PhotoRecords photoRecords){
        mainActivityContract.onSuccessfulResponse(photoRecords);
    }

    public void onFailure(String errorMsg){
        mainActivityContract.onFailureResponse(errorMsg);
    }



}
