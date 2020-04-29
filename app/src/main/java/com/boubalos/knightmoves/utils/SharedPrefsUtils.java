package com.boubalos.knightmoves.utils;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static com.boubalos.knightmoves.utils.Constants.BOARD_PREFS;
import static com.boubalos.knightmoves.utils.Constants.BOARD_SIZE;

public class SharedPrefsUtils {

    public static int getBoardSize(Context context){
        SharedPreferences sharedPref =
                context.getSharedPreferences(BOARD_PREFS, MODE_PRIVATE);
        return sharedPref.getInt(BOARD_SIZE, 6);
    }

    public static void setBoardSize(int size, Context context){
        SharedPreferences sharedPref = context.getSharedPreferences(BOARD_PREFS,
                MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putInt(BOARD_SIZE, size);
        editor.apply();
    }
}
