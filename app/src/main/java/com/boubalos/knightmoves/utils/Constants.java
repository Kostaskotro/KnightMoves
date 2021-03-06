package com.boubalos.knightmoves.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {


    public static final String BOARD_PREFS="Board Preferences";
    public static final String BOARD_SIZE="Board Size";

    //positions after 1 move
    public static final int[] X1 = new int[]{-1,1,2, 2, 1,-1,-2,-2};
    public static final int[] Y1 = new int[]{ 2,2,1,-1,-2,-2,-1, 1};

    //1/4 of positions after 2 moves ....  we get all the positions by using this 4 times like so (X,Y)|(Y,-X)|(-X,-Y)|(-Y,X) and then include the starting point
    public static final int[] X2 = new int[]{1, 1, 2, 2, 3, 3, 4, 4};
    public static final int[] Y2 = new int[]{1, 3, 0, 4, 1, 3, 0, 2};

    // associations between positions after 2 moves and after 1 move | every position(after 2 moves) can come from 1 or 2 possible positions(after 1 move)
    public static final List<int[]> associations1 = new ArrayList<int[]>(Arrays.asList(new int[]{4, 1}, new int[]{3, 1}, new int[]{2, 5}, new int[]{2},
            new int[]{2, 4}, new int[]{2, 3}, new int[]{3, 4}, new int[]{3}));
    public static final List<int[]> associations2 = new ArrayList<int[]>(Arrays.asList(new int[]{6, 3}, new int[]{5, 3}, new int[]{4, 7}, new int[]{4},
            new int[]{4, 6}, new int[]{4, 5}, new int[]{5, 6}, new int[]{5}));
    public static final List<int[]> associations3 = new ArrayList<int[]>(Arrays.asList(new int[]{8, 5}, new int[]{7, 5}, new int[]{6, 1}, new int[]{6},
            new int[]{6, 8}, new int[]{6, 7}, new int[]{7, 8}, new int[]{7}));
    public static final List<int[]> associations4 = new ArrayList<int[]>(Arrays.asList(new int[]{2, 7}, new int[]{1, 7}, new int[]{8, 3}, new int[]{8},
            new int[]{8, 2}, new int[]{8, 1}, new int[]{1, 2}, new int[]{1}));

    // associations start from 1 not 0 WARNING








}
