package com.example.joshtalksapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.joshtalksapp.R;
import com.example.joshtalksapp.contract.MainActivityContract;
import com.example.joshtalksapp.model.PhotoRecords;
import com.example.joshtalksapp.presenter.MainActivityPresenter;

public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener, MainActivityContract {
    SearchView searchView;
    RecyclerView recyclerView;
    String tag;
    ProgressBar progressBar;
    TextView errorMsgTV;
    MainActivityPresenter mainActivityPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        searchView.setOnQueryTextListener(this);
    }

    private void initViews() {
        searchView = findViewById(R.id.searchview);
        recyclerView = findViewById(R.id.recyclerview);
        progressBar = findViewById(R.id.progressBar);
        errorMsgTV = findViewById(R.id.errorMsg);
        mainActivityPresenter = new MainActivityPresenter(this);
    }

    public void onSuccessfulResponse(PhotoRecords photoRecords){
        if(photoRecords.getPhotos().getPhoto().size() == 0){
            progressBar.setVisibility(View.INVISIBLE);
            errorMsgTV.setVisibility(View.VISIBLE);
            errorMsgTV.setText("No related photos found");
        }
        else {
            setPhotosInAdapter(photoRecords);
            recyclerView.setVisibility(View.VISIBLE);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    public void onFailureResponse(String errorMsg){
        errorMsgTV.setVisibility(View.VISIBLE);
        errorMsgTV.setText(errorMsg);
    }

    void setVisibilityOfViewsOnSearch(){
        recyclerView.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        if(errorMsgTV.getVisibility()==View.VISIBLE)
            errorMsgTV.setVisibility(View.INVISIBLE);
    }
    void validateTag(String tag){
        boolean isTagValidated = (tag!="") ? (tag!=" ") ? true : false : false;
        if(!isTagValidated) {
            errorMsgTV.setVisibility(View.VISIBLE);
            errorMsgTV.setText("Invalid Input");
        }
    }


    public void setPhotosInAdapter(PhotoRecords photoRecords) {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(3, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager); // set LayoutManager to RecyclerView
        PhotoRecyclerviewAdapter customAdapter = new PhotoRecyclerviewAdapter(photoRecords,this);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        tag = query;
        setVisibilityOfViewsOnSearch();
        validateTag(tag);

        mainActivityPresenter.fetchImageRecordsByTag(tag);
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

}
