package com.example.joshtalksapp.service;

import com.example.joshtalksapp.presenter.MainActivityPresenter;

public interface RestClient {
    void fetchImageRecordsByTag(MainActivityPresenter mainActivityPresenter, String tag);
}
