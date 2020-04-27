package com.boubalos.knightmoves.models;

import androidx.annotation.NonNull;

public class Position {
    private int m_x;
    private int m_y;
    private int[] m_associations;

    public Position(int x, int y, int[] associations) {
        m_x = x;
        m_y = y;
        m_associations = associations;
    }

    public Position(int x, int y) {
        m_x = x;
        m_y = y;
        m_associations = null;
    }


    public int getX() {
        return m_x;
    }

    public int getY() {
        return m_y;
    }

    public int[] getAssociations() {
        return m_associations;
    }


    @NonNull
    @Override
    public String toString() {
        return "X:" + m_x + "/Y:" + m_y;
    }
}
