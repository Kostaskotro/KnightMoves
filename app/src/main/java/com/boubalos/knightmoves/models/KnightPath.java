package com.boubalos.knightmoves.models;


public class KnightPath {
    private final String move = "-->";
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


    public String Stringify() {
        return ""+NumToLetter(start.getY()) + (start.getX()+1) +
                move + NumToLetter(firstmove.getY()) + (firstmove.getX()+1) +
                move + NumToLetter(secondmove.getY()) + (secondmove.getX()+1) +
                move + NumToLetter(thirdmove.getY()) + (thirdmove.getX()+1);
    }

    private static char NumToLetter(int i) {
        int first = 'A';
        return (char) (first + i);
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
