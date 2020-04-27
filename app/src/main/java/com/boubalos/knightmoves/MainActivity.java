package com.boubalos.knightmoves;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.boubalos.knightmoves.models.ChessBoard;

public class MainActivity extends AppCompatActivity implements ChessBoard.ChessBoardInterface {

    ChessBoard chessBoard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chessBoard = findViewById(R.id.board);
      chessBoard.setChessBoardInterface(this);
        Button button=findViewById(R.id.reset_btn);
        Button button2=findViewById(R.id.path_btn);
        button.setOnClickListener((v)->{
          chessBoard.resetBoard();
        });
        button2.setOnClickListener((view)->
        {
            chessBoard.renderPaths();
        });
    }


    @Override
    public void noPathsFound() {
        Toast toast = Toast.makeText(this, "Impossible!!!",Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    public void PathsFound(int i) {
        Toast toast = Toast.makeText(this, "Knight can get there in "+i+" Ways !",Toast.LENGTH_SHORT);
        toast.show();
    }


}
