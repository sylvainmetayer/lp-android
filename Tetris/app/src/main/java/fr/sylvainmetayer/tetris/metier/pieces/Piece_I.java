package fr.sylvainmetayer.tetris.metier.pieces;

import android.content.Context;

import fr.sylvainmetayer.tetris.metier.Piece;

public class Piece_I extends Piece {

    private static int[][] matrice =
            {
                    {1},
                    {1},
                    {1}
            };

    public Piece_I(int line, int column, Context context) {
        super(matrice, line, column, 1, context);
        bottomPointsToCheck.add("2,0");
        rightPointsToCheck.add("0,0");
        rightPointsToCheck.add("1,0");
        rightPointsToCheck.add("2,0");
        leftPointsToCheck.add("0,0");
        leftPointsToCheck.add("1,0");
        leftPointsToCheck.add("2,0");
    }

    @Override
    public void rotate() {
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
    public boolean canRotate(int[][] gameboard) {
        return false;
    }
}
