package com.boubalos.knightmoves.models;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;

public final class Tile {

    private static final String TAG = Tile.class.getSimpleName();
    private final int y;
    private final int x;
    private int order = -1;
    private int squareSize = 0;



    private final Paint squareColor;
    private Rect tileRect;
    boolean selected = false;

    public Tile(final int x, final int y) {
        this.y = y;
        this.x = x;
        this.squareColor = new Paint();
        squareColor.setColor(isDark() ? Color.BLACK : Color.WHITE);
        squareColor.setAntiAlias(true);
    }

    public void draw(final Canvas canvas) {
        if (order != -1) {
            float x = 0;
            float y = 0;
            String text = "order : " + order;
            switch (order) {
                case 0: {
                    x = tileRect.left;
                    y = tileRect.top + (squareSize / 2);
                    text = "Start";
                    break;
                }
                case 1:
                case 2:
                case 3: {
                    x = tileRect.left + (squareSize / 3);
                    y = tileRect.top + (squareSize / 2);
                    text = "" + order;
                    break;
                }


            }
            Paint paint = new Paint();
            paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setTextSize(squareSize / 3);
            paint.setTextAlign(Paint.Align.LEFT);
            //set text size
            canvas.drawRect(tileRect, squareColor);
            // canvas.drawText("asdasdasdasd "+order,tileRect.left,tileRect.top, squareColor);
            //canvas.drawRect(tileRect.left-w, tileRect.top - textSize, tileRect.right + w, tileRect.bottom, squareColor);
            canvas.drawText(text, x, y, paint);

        } else
            canvas.drawRect(tileRect, squareColor);
        order = -1;
    }

    public void setOrder(int i) {
        order = i;
    }

    public void setSelected(boolean selected) {
        if (selected) squareColor.setColor(Color.RED);
        else squareColor.setColor(isDark() ? Color.BLACK : Color.WHITE);

    }

    public void setYellow(boolean selected) {
        squareColor.setColor(Color.YELLOW);
    }

    public void setSelected2(boolean selected) {
        if (squareColor.getColor() == Color.RED) squareColor.setColor(Color.GREEN);
        else squareColor.setColor(Color.YELLOW);
        //  else squareColor.setColor(isDark() ? Color.BLACK : Color.WHITE);
    }

    public void setStartingTile() {
        squareColor.setColor(Color.BLUE);
    }

    public void setEndingTile() {
        squareColor.setColor(Color.GREEN);
    }

//    public String getColumnString() {
//        switch (y) {
//            case 0:
//                return "A";
//            case 1:
//                return "B";
//            case 2:
//                return "C";
//            case 3:
//                return "D";
//            case 4:
//                return "E";
//            case 5:
//                return "F";
//            case 6:
//                return "G";
//            case 7:
//                return "H";
//            default:
//                return null;
//        }
//    }
//
//    public String getRowString() {
//        // To get the actual row, add 1 since 'row' is 0 indexed.
//        return String.valueOf(x + 1);
//    }
//
//    public void handleTouch() {
//        Log.d(TAG, "handleTouch(): col: " + y);
//        Log.d(TAG, "handleTouch(): row: " + x);
//    }

    public boolean isDark() {
        return (y + x) % 2 == 0;
    }

    public boolean isTouched(final int x, final int y) {
        return tileRect.contains(x, y);
    }

    public void setTileRect(final Rect tileRect) {
        this.tileRect = tileRect;
    }

//    public String toString() {
//        final String column = getColumnString();
//        final String row = getRowString();
//        return "<Tile " + column + row + ">";
//    }


    public void setSquareSize(int squareSize) {
        this.squareSize = squareSize;
    }
}