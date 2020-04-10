package com.example.joshtalksapp.service;

import com.example.joshtalksapp.presenter.MainActivityPresenter;

public interface RemoteDataSource {
    void fetchImageRecordsByTag(MainActivityPresenter mainActivityPresenter, String tag);
}
