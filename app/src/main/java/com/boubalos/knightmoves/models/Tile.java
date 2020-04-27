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
            String text = "order : " + order;
            switch (order) {
                case 0:{
                    text="Start";
                    break;
                }
                case 1:
                case 2: {
                    text = "Move :" + order;
                    break;
                }
                case 3:{
                    text="End";
                    break;
                }
            }
            Paint paint2 = new Paint();

            paint2 = new Paint();
            paint2.setColor(Color.BLACK);
            paint2.setTextSize(20);
            paint2.setTextAlign(Paint.Align.LEFT);
            float w = paint2.measureText(text) / 2;
            float textSize = paint2.getTextSize();
            //set text size
            canvas.drawRect(tileRect, squareColor);
            // canvas.drawText("asdasdasdasd "+order,tileRect.left,tileRect.top, squareColor);
            //canvas.drawRect(tileRect.left-w, tileRect.top - textSize, tileRect.right + w, tileRect.bottom, squareColor);
            canvas.drawText(text, tileRect.left + 20, tileRect.top + 20, paint2); //x=300,y=300

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

    void setStartingTile() {
        squareColor.setColor(Color.BLUE);
    }

    void setEndingTile() {
        squareColor.setColor(Color.GREEN);
    }

    public String getColumnString() {
        switch (y) {
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            default:
                return null;
        }
    }

    public String getRowString() {
        // To get the actual row, add 1 since 'row' is 0 indexed.
        return String.valueOf(x + 1);
    }

    public void handleTouch() {
        Log.d(TAG, "handleTouch(): col: " + y);
        Log.d(TAG, "handleTouch(): row: " + x);
    }

    public boolean isDark() {
        return (y + x) % 2 == 0;
    }

    public boolean isTouched(final int x, final int y) {
        return tileRect.contains(x, y);
    }

    public void setTileRect(final Rect tileRect) {
        this.tileRect = tileRect;
    }

    public String toString() {
        final String column = getColumnString();
        final String row = getRowString();
        return "<Tile " + column + row + ">";
    }


}