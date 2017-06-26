package fr.sylvainmetayer.tetris.metier.pieces;

import android.content.Context;

import fr.sylvainmetayer.tetris.metier.Piece;

public class Piece_S extends Piece {

    private static int[][] matrice = {
            {0, 1, 1},
            {1, 1}
    };

    public Piece_S(int line, int column, Context context) {
        super(matrice, line, column, 1, context);
        bottomPointsToCheck.add("0,2");
        bottomPointsToCheck.add("1,0");
        bottomPointsToCheck.add("1,1");
    }

    @Override
    public void rotate(Float angle) {
        // TODO Appliquer la rotation à la matrice.
    }

    @Override
    public void left() {
        setStartColumn(getStartColumn() - 1);
    }

    @Override
    public void right() {
        setStartColumn(getStartColumn() + 1);
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
        return true;
    }

}
