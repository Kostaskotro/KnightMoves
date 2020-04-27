package com.boubalos.knightmoves.models;

public class KnightPath {

    private Position start;
    private Position firstmove;
    private Position secondmove;
    private Position thirdmove;

    public KnightPath(Position start, Position firstmove, Position secondmove, Position thirdmove) {
        this.start = start;
        this.firstmove = firstmove;
        this.secondmove = secondmove;
        this.thirdmove = thirdmove;
    }

    public Position getStart() {
        return start;
    }

    public Position getFirstmove() {
        return firstmove;
    }

    public Position getSecondmove() {
        return secondmove;
    }

    public Position getThirdmove() {
        return thirdmove;
    }
}
