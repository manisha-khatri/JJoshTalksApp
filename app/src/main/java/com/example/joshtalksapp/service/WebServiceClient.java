package com.example.joshtalksapp.service;

import com.example.joshtalksapp.presenter.MainActivityPresenter;

public class WebServiceClient implements RemoteDataSource{
    public static WebServiceClient webServiceClientInstance;
    private WebServiceClient(){}

    public static synchronized WebServiceClient getInstance(){
        if(webServiceClientInstance == null){
            webServiceClientInstance = new WebServiceClient();
        }
        return webServiceClientInstance;
    }

    @Override
    public void fetchImageRecordsByTag(MainActivityPresenter mainActivityPresenter, String tag) {
        RestClient restClient = Retrofit2Client.getInstance();
        restClient.fetchImageRecordsByTag(mainActivityPresenter, tag);
    }

}
