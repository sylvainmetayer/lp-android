package fr.sylvainmetayer.tetris.metier.pieces;

import android.content.Context;

import fr.sylvainmetayer.tetris.metier.Piece;

public class Piece_I extends Piece {

    public Piece_I(int[][] matrice, int line, int column, Context context) {
        super(matrice, line, column, 1, context);
    }

    @Override
    public void rotate(Float angle) {
        // TODO Appliquer la rotation à la matrice.
    }

    @Override
    public void left() {

    }

    @Override
    public void right() {

    }

    @Override
    public void down() {
        setStartLine(getStartLine() + 1);
    }

    @Override
    public boolean canRotate(Float angle) {
        return false;
    }

    @Override
    public boolean canGoLeft() {
        return false;
    }

    @Override
    public boolean canGoRight() {
        return false;
    }


}
