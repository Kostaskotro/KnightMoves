package com.boubalos.knightmoves.views;

import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SeekBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.boubalos.knightmoves.MainViewModel;
import com.boubalos.knightmoves.adapters.MyRecyclerAdapter;
import com.boubalos.knightmoves.R;
import com.boubalos.knightmoves.databinding.ActivityMainBinding;
import com.boubalos.knightmoves.models.KnightPath;
import com.boubalos.knightmoves.utils.SharedPrefsUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements ChessBoard.ChessBoardInterface, MyRecyclerAdapter.RecyclerListInterface {

    ChessBoard chessBoard;
    RecyclerView recyclerView;
    MyRecyclerAdapter recyclerAdapter;
    MainViewModel mainViewModel;
    public int boardSize = 6;
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainViewModel = new ViewModelProvider(this).get(MainViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setLifecycleOwner(this);
        binding.setActivity(this);
        binding.setViewmodel(mainViewModel);
        binding.setExplanation(getString(R.string.select_starting_tile));

        boardSize = SharedPrefsUtils.getBoardSize(this);
        binding.setBoardSize(boardSize + "");
        chessBoard = findViewById(R.id.board);

        SeekBar sizeBar = findViewById(R.id.seeker);
        sizeBar.setMax(10);
        sizeBar.setProgress(boardSize - 6);
        sizeBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onStopTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar arg0) {
            }

            @Override
            public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
                boardSize = 6 + arg1;
                SharedPrefsUtils.setBoardSize(boardSize, getApplicationContext());
                binding.setBoardSize(boardSize + "");
                binding.setExplanation(getString(R.string.select_starting_tile));
                chessBoard.resetBoard();
                refreshList(new ArrayList<>());
            }
        });

        mainViewModel.getPathLiveData().observe(this, this::refreshList);

        recyclerView = findViewById(R.id.paths_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerAdapter = new MyRecyclerAdapter(this);
        recyclerView.setAdapter(recyclerAdapter);

        chessBoard.setChessBoardInterface(this);
    }

    private void refreshList(List<KnightPath> knightPaths) {
        recyclerAdapter.setPathList(knightPaths);
    }


    public void resetBoard(View v) {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.rotate);
        v.startAnimation(anim);
        chessBoard.resetBoard();
        mainViewModel.setPaths(new ArrayList<>());
        binding.setExplanation(getString(R.string.select_starting_tile));
    }


    @Override
    public void noPathsFound() {
        Toast toast = Toast.makeText(this, "Impossible!!!", Toast.LENGTH_SHORT);
        toast.show();
        binding.setExplanation(getString(R.string.no_paths_found));
    }

    @Override
    public void StartingPointSelected() {
        binding.setExplanation(getString(R.string.select_ending_tile));
    }

    @Override
    public void PointsAlreadySelected() {
        Toast toast = Toast.makeText(this, getString(R.string.refresh_plz), Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void PathsFound(List<KnightPath> paths) {
        mainViewModel.setPaths(paths);
        binding.setExplanation(getString(R.string.paths_found) +" "+ paths.size() + " ways !");
    }

    @Override
    public void setActivePath(int i) {
        chessBoard.renderPath(i);
    }


}
