package com.boubalos.knightmoves;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableInt;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.boubalos.knightmoves.models.KnightPath;

import java.util.List;


public class MainViewModel extends AndroidViewModel {

    MutableLiveData<String> state = new MutableLiveData<>();
    MutableLiveData<ObservableInt> activePath = new MutableLiveData<>();
    MutableLiveData<List<KnightPath>> pathLiveData = new MutableLiveData<>();

    public MainViewModel(@NonNull Application application) {
        super(application);
    }


    public MutableLiveData<String> getState() {
        return state;
    }

    public  MutableLiveData<List<KnightPath>> getPathLiveData() {
        return pathLiveData;
    }

    public MutableLiveData<ObservableInt> getActivePath() {
        return activePath;
    }

    public void setActivePath(int i){
        activePath.setValue(new ObservableInt(i));
    }

    public void setPaths(List<KnightPath> paths) {
        pathLiveData.setValue(paths);
    }
}
