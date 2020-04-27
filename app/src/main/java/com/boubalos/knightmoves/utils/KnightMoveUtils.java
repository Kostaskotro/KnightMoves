package com.boubalos.knightmoves.utils;

import com.boubalos.knightmoves.models.KnightPath;
import com.boubalos.knightmoves.models.Position;

import java.util.ArrayList;
import java.util.List;

import static com.boubalos.knightmoves.Constants.X1;
import static com.boubalos.knightmoves.Constants.X2;
import static com.boubalos.knightmoves.Constants.Y1;
import static com.boubalos.knightmoves.Constants.Y2;
import static com.boubalos.knightmoves.Constants.assosiations1;
import static com.boubalos.knightmoves.Constants.assosiations3;
import static com.boubalos.knightmoves.Constants.assosiations2;
import static com.boubalos.knightmoves.Constants.assosiations4;
import static com.boubalos.knightmoves.utils.SharedPrefsUtils.getBoardSize;

public class KnightMoveUtils {

    //Todo each move has 2 different Paths(maybe)


    /**
     * calculate possible knight positions after 1 move
     */

    public static List<Position> Calc1rstLvLPositions(int x, int y){
        List<Position> pointsList = new ArrayList<>();
        for (int i = 0; i < 8; i++) {
            int mx = x + X1[i];
            int my = y + Y1[i];
       //     if (isInsideBoard(mx, my)) {
            Position position = new Position(mx, my);
                pointsList.add(position);
          //  }

        }
        return pointsList;
    }

    /**
     * calculate possible knight positions after 2 moves
     */
    public static List<Position> Calc2ndLvLPositions(int x, int y) {
        List<Position> pointsList = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            int mx = x + X2[i];
            int my = y + Y2[i];
            if (isInsideBoard(mx, my)) {
                Position position = new Position(mx, my, assosiations1.get(i));
                pointsList.add(position);
            }
        }
        for (int i = 0; i < 8; i++) {
            int mx = x + Y2[i];
            int my = y - X2[i];
            if (isInsideBoard(mx, my)) {
                Position position = new Position(mx, my, assosiations2.get(i));
                pointsList.add(position);
            }
        }
        for (int i = 0; i < 8; i++) {
            int mx = x - X2[i];
            int my = y - Y2[i];
            if (isInsideBoard(mx, my)) {
                Position position = new Position(mx, my, assosiations3.get(i));
                pointsList.add(position);
            }
        }
        for (int i = 0; i < 8; i++) {
            int mx = x - Y2[i];
            int my = y + X2[i];
            if (isInsideBoard(mx, my)) {
                Position position = new Position(mx, my, assosiations4.get(i));
                pointsList.add(position);
            }
        }
        Position position = new Position(x, y, new int[]{1,2,3,4,5,6,7,8});
        pointsList.add(position);
        return pointsList;
    }




   public static boolean isInsideBoard(int x, int y) {
        final int N = getBoardSize();
        return x >= 0 && y >= 0 && x < N && y < N;
    }

    public static List<KnightPath> findPaths(List<Position> mustBePositionsToGetToEnd, List<Position> positionsAfter2moves, Position startingPoint, Position endingPoint) { //Todo move this to Knight Utils
        List<Position> pathPoints = new ArrayList<>();
        List<KnightPath> paths = new ArrayList<>();
        for (Position p1 : mustBePositionsToGetToEnd)
            for (Position p2 : positionsAfter2moves) {
                if (p1.getX() == p2.getX() && p1.getY() == p2.getY()) {
                    pathPoints.add(p2);
                }
            }
        if (pathPoints.size() != 0) {
            List<Position> firstMovePoints = KnightMoveUtils.Calc1rstLvLPositions(startingPoint.getX(), startingPoint.getY());
            for (Position p : pathPoints) {
                for (int i : p.getAssociations()) {
                    Position position = firstMovePoints.get(i - 1);
                    if (KnightMoveUtils.isInsideBoard(position.getX(), position.getY())) {
                        KnightPath path = new KnightPath(startingPoint, position, p, endingPoint); //here we need to validate that the associated points exist!!!SOS
                        paths.add(path);
                    }
                }
            }
        }
        return paths;
    }

}
