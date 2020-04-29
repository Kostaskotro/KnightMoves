package com.boubalos.knightmoves.models;


import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

import com.boubalos.knightmoves.R;

public class Tile {

    private static final String TAG = Tile.class.getSimpleName();
    private final int y;
    private final int x;
    private int[] order ;
    private int squareSize = 0;

    private final Paint squareColor;
    private Rect tileRect;
    Drawable KnighticonDrawable;

    public Tile(final int x, final int y) {
        this.y = y;
        this.x = x;
        this.squareColor = new Paint();
        squareColor.setColor(isDark() ? Color.BLACK : Color.WHITE);
        squareColor.setAntiAlias(true);
    }

    public void draw(final Canvas canvas) {

        if (order !=null) {                    //here we check if the tile rendered is in our path
            float x = 0;
            float y = 0;
            String text;
            text=order[0]+"";
            if(order.length>1)
                text=order[0]+" | " +order[1];
            x =  tileRect.left + (squareSize)/2;
            y = tileRect.top +(squareSize/2);
            Paint paint = new Paint();
            paint = new Paint();
            paint.setColor(Color.WHITE);
            paint.setTextSize(squareSize / 3);
            paint.setTextAlign(Paint.Align.CENTER);
            //set text size
            canvas.drawRect(tileRect, squareColor);
            // canvas.drawText("asdasdasdasd "+order,tileRect.left,tileRect.top, squareColor);
            //canvas.drawRect(tileRect.left-w, tileRect.top - textSize, tileRect.right + w, tileRect.bottom, squareColor);
            canvas.drawText(text, x, y, paint);

        } else
        {  canvas.drawRect(tileRect, squareColor);
        if(KnighticonDrawable!=null)
        {
            KnighticonDrawable.setBounds(tileRect);
            KnighticonDrawable.draw(canvas);

        }
        }
        order = null;
    }

    public void setOrder(int i,int colorResource) {
        if(order!=null)
        {
            int x = order[0];
            order=new int[]{x,i};
        }
       else order=new int[]{i};
       squareColor.setColor(colorResource);
    }


    public void setStartingTile(Drawable drawable,int color) {
        KnighticonDrawable=drawable;
        squareColor.setColor(color);
    }

    public void setEndingTile(int color) {
        squareColor.setColor(color);
    }

    public boolean isDark() {
        return (y + x) % 2 == 0;
    }

    public void setTileRect(final Rect tileRect) {
        this.tileRect = tileRect;
    }

    public void setSquareSize(int squareSize) {
        this.squareSize = squareSize;
    }
}