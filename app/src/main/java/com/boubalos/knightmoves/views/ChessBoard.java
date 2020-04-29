package com.boubalos.knightmoves.views;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.boubalos.knightmoves.models.KnightPath;
import com.boubalos.knightmoves.models.Position;
import com.boubalos.knightmoves.models.Tile;
import com.boubalos.knightmoves.utils.KnightMoveUtils;
import com.boubalos.knightmoves.utils.SharedPrefsUtils;

import java.util.List;

public class ChessBoard extends View {
    private static final String TAG = ChessBoard.class.getSimpleName();
    private static final int IDLE = 0;
    private static final int STARTING_POINT_SELECTED = 1;
    private static final int ENDING_POINT_SELECTED = 2;
    private static final int RENDERING_IN_PROGRESS = 3;
    private int state = IDLE;
    private Rect rect;

    private List<Position> pointsList2;
    private List<Position> pointsList1;

    private Position startingPoint;
    private Position endingPoint;
    private List<KnightPath> paths;
    private Tile[][] mTiles;
    private int x0 = 0;
    private int y0 = 0;
    private int squareSize = 0;
    private boolean flipped = false;
    private int width, height;
    private int size;
    private Context context;

    ChessBoardInterface chessBoardInterface;

    public ChessBoard(final Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        size = SharedPrefsUtils.getBoardSize(context);
        this.mTiles = new Tile[size][size];
        buildTiles();
        rect = new Rect();
    }

    public void setChessBoardInterface(ChessBoardInterface chessBoardInterface) {
        this.chessBoardInterface = chessBoardInterface;
    }

    private void buildTiles() {
        mTiles = new Tile[size][size];
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                mTiles[x][y] = new Tile(x, y);
            }
        }
    }


    @Override
    protected void onDraw(final Canvas canvas) {
        int width = getWidth();
        int height = getHeight();
        squareSize = Math.min(getSquareSizeWidth(width), getSquareSizeHeight(height));
        computeOrigins(width, height);

        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                final int xCoord = getXCoord(x);
                final int yCoord = getYCoord(y);

                mTiles[x][y].setTileRect(rect);

                rect.left = xCoord;
                rect.top = yCoord;
                rect.right = rect.left + squareSize;  // right
                rect.bottom = rect.top + squareSize;
                mTiles[x][y].setSquareSize(squareSize);
                mTiles[x][y].draw(canvas);
            }
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int
            heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int sqSizeW = getSquareSizeWidth(width);
        int sqSizeH = getSquareSizeHeight(height);
        int sqSize = Math.min(sqSizeW, sqSizeH);

        if (height > width) {
            int p = getMaxHeightPercentage();
            height = Math.min(getHeight(sqSize), height * p / 100);
        } else {
            width = Math.min(getWidth(sqSize), width * 65 / 100);
        }
        this.width = width;
        this.height = height;
        setMeasuredDimension(width, height);
    }


    protected int getWidth(int sqSize) {
        return sqSize * size;
    }

    protected int getHeight(int sqSize) {
        return sqSize * size;
    }

    private int getSquareSizeWidth(final int width) {
        return (width) / size;
    }

    private int getSquareSizeHeight(final int height) {
        return (height) / size;
    }

    private int getXCoord(final int x) {
        return x0 + squareSize * (flipped ? (size - 1) - x : x);
    }

    private int getYCoord(final int y) {
        return y0 + squareSize * (flipped ? y : (size - 1) - y);
    }

    private void computeOrigins(final int width, final int height) {
        this.x0 = (width - squareSize * size) / 2;
        this.y0 = (height - squareSize * size) / 2;
    }

    protected int getMaxHeightPercentage() {
        return 100;
    }

    void resetTiles() {
        buildTiles();
        invalidate();
    }

    public void resetBoard() {
        size = SharedPrefsUtils.getBoardSize(context);
        buildTiles();
        state = IDLE;
        invalidate();
    }

    @Override
    @SuppressLint("ClickableViewAccessibility")
    public boolean onTouchEvent(MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float screenX = event.getX();

            float screenY = width - event.getY();
            int selectedX = (int) screenX / getSquareSizeWidth(width);
            int selectedY = (int) screenY / getSquareSizeHeight(height);
            if (selectedX >= size)
                selectedX = size - 1;
            if (selectedY >= size)
                selectedY = size - 1;
            Log.i(TAG, width + "/" + height + "/////" + selectedX + "/" + selectedY);
            switch (state) {
                case IDLE: {
                    mTiles[selectedX][selectedY].setStartingTile();
                    pointsList2 = KnightMoveUtils.Calc2ndLvLPositions(selectedX, selectedY, size);
                    state = STARTING_POINT_SELECTED;
                    startingPoint = new Position(selectedX, selectedY);
                    invalidate();
                    chessBoardInterface.StartingPointSelected();
                    return true;
                }
                case STARTING_POINT_SELECTED: {
                    pointsList1 = KnightMoveUtils.Calc1rstLvLPositions(selectedX, selectedY);
                    mTiles[selectedX][selectedY].setEndingTile();
                    state = ENDING_POINT_SELECTED;
                    endingPoint = new Position(selectedX, selectedY);
                    paths = KnightMoveUtils.findPaths(pointsList1, pointsList2, startingPoint, endingPoint, size);
                    if (paths.size() != 0) chessBoardInterface.PathsFound(paths);
                    else chessBoardInterface.noPathsFound();
                    invalidate();
                    return true;
                }
                case ENDING_POINT_SELECTED: {
                    chessBoardInterface.PointsAlreadySelected();
                    return true;
                }

            }
        }
        return false;
    }

    public void renderPath(int index) {
        if (state == ENDING_POINT_SELECTED) {
            state = RENDERING_IN_PROGRESS;
            if (paths.size() > 0) {
                resetTiles();
                KnightPath path = paths.get(index);
                Log.d(TAG, "RENDERING PATH : " + path.getStart().toString() + "|" + path.getFirstmove().toString() + "|" + path.getSecondmove().toString() + "|" + path.getThirdmove().toString());
                mTiles[path.getStart().getX()][path.getStart().getY()].setStartingTile();
                mTiles[path.getStart().getX()][path.getStart().getY()].setOrder(0);
                mTiles[path.getThirdmove().getX()][path.getThirdmove().getY()].setEndingTile();
                mTiles[path.getThirdmove().getX()][path.getThirdmove().getY()].setOrder(3);
                mTiles[path.getFirstmove().getX()][path.getFirstmove().getY()].setYellow(true);
                mTiles[path.getFirstmove().getX()][path.getFirstmove().getY()].setOrder(1);
                mTiles[path.getSecondmove().getX()][path.getSecondmove().getY()].setSelected(true);
                mTiles[path.getSecondmove().getX()][path.getSecondmove().getY()].setOrder(2);
                invalidate();
                state = ENDING_POINT_SELECTED;
                if (index == paths.size() - 1)
                    index = 0;
                else index++;
            } else {
                chessBoardInterface.noPathsFound();
            }
        }
    }


    public void setBoardSize(int size) {
        this.size = size;
        resetBoard();
    }


    public interface ChessBoardInterface {
        void noPathsFound();

        void StartingPointSelected();

        void PointsAlreadySelected();

        void PathsFound(List<KnightPath> paths);
    }

}