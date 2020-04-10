package com.example.joshtalksapp.contract;

import com.example.joshtalksapp.model.PhotoRecords;

public interface MainActivityContract {
    void onSuccessfulResponse(PhotoRecords photoRecords);
    void onFailureResponse(String errorMsg);
}
