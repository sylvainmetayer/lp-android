package fr.sylvainmetayer.tetris.metier;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fr.sylvainmetayer.tetris.R;
import fr.sylvainmetayer.tetris.utils.Utils;

public abstract class Piece implements Mouvement, MouvementPossible {

    private int hauteur;
    private int largeur;
    private int[][] matrice;
    private int startLine;
    private int startColumn;
    private int color;
    private Context context;
    protected List<String> bottomPointsToCheck;
    protected List<String> leftPointsToCheck;
    protected List<String> rightPointsToCheck;

    public Piece(int[][] matrice, int line, int column, int color, Context context) {
        this.hauteur = matrice.length;
        this.largeur = 0;

        for (int lineIterator = 0; lineIterator < matrice.length; lineIterator++) {
            for (int columnIterator = 0; columnIterator < matrice[lineIterator].length; columnIterator++) {
                int tmpLargeur = matrice[lineIterator][columnIterator];
                if (tmpLargeur > largeur)
                    largeur = tmpLargeur; // Get max largeur
            }
        }

        this.matrice = matrice;
        this.startLine = line;
        this.startColumn = column;
        this.color = color;
        this.context = context;
        this.bottomPointsToCheck = new ArrayList<>();
        this.leftPointsToCheck = new ArrayList<>();
        this.rightPointsToCheck = new ArrayList<>();
    }

    public Context getContext() {
        return context;
    }

    public int[][] getMatrice() {
        return matrice;
    }

    public int getStartLine() {
        return startLine;
    }

    public int getStartColumn() {
        return startColumn;
    }

    public static int getImage(int value) {
        switch (value) {
            case 1:
                return R.drawable.blue_image;
            case 0:
                return R.drawable.black_image;
        }
        return R.drawable.black_image;
    }

    @Override
    public boolean canGoLeft(int[][] gameboard) {
        return checkColumnLimits(gameboard, -1, leftPointsToCheck);
    }

    @Override
    public boolean canGoRight(int[][] gameboard) {
        return checkColumnLimits(gameboard, 1, rightPointsToCheck);
    }

    private boolean checkColumnLimits(int[][] gameboard, int columnValue, List<String> pointsToCheck) {
        boolean isLeft = (columnValue == -1);

        int column = this.getStartColumn();
        int line = this.getStartLine();

        for (String point : pointsToCheck) {
            int pointLine = Integer.parseInt(point.split(",")[0]);
            int pointColumn = Integer.parseInt(point.split(",")[1]);

            int pieceLine = pointLine + line;
            int pieceColumn = pointColumn + column;

            int newPieceColumnPosition = pieceColumn + columnValue;

            if (isLeft) {
                if (newPieceColumnPosition < 0) {
                    Log.i("CANGO" + "LEFT", "Out of boundary, continue");
                    return false;
                }
            } else {
                if (newPieceColumnPosition >= getContext().getResources().getInteger(R.integer.maxColumns)) {
                    Log.i("CANGO" + "RIGHT", "Out of boundary, continue");
                    return false;
                }
            }

            Log.d("CANGO" + (isLeft ? "LEFT" : "RIGHT"), isLeft + "Going to check" + Utils.formatPosition(pieceLine, newPieceColumnPosition));
            if (gameboard[pieceLine][newPieceColumnPosition] != getEmptyPieceValue()) {
                Log.w("CANGO" + (isLeft ? "LEFT" : "RIGHT"), "The position [" + (pieceLine) + "," + newPieceColumnPosition + "] is not empty !");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean canGoDown(int[][] gameboard) {
        if (!(getStartLine() + getMatrice().length < getContext().getResources().getInteger(R.integer.maxLines)))
            return false;

        int column = this.getStartColumn();
        int line = this.getStartLine();

        for (String point : bottomPointsToCheck) {
            int pointLine = Integer.parseInt(point.split(",")[0]);
            int pointColumn = Integer.parseInt(point.split(",")[1]);

            int pieceLine = pointLine + line;
            int pieceColumn = pointColumn + column;

            if (pieceLine + 1 >= gameboard.length) {
                Log.i("CANGODOWN", "Out of boundary, continue");
                continue;
            }

            if (gameboard[pieceLine + 1][pieceColumn] != getEmptyPieceValue()) {
                Log.w("CANGODOWN", "The position [" + (pieceLine + 1) + "," + pieceColumn + "] is not empty !");
                return false;
            }
        }
        return true;
    }

    public void setStartLine(int startLine) {
        this.startLine = startLine;
    }

    public void setStartColumn(int startColumn) {
        this.startColumn = startColumn;
    }

    @Override
    public String toString() {
        StringBuilder sb;
        sb = new StringBuilder();
        sb.append("Hauteur : ").append(hauteur).append(", ");
        sb.append("Largeur : ").append(largeur).append("\n");
        sb.append("Matrice : ").append(Arrays.deepToString(matrice)).append("\n");
        sb.append("startLine : ").append(startLine).append(", ");
        sb.append("startColumn : ").append(startColumn).append(", ");
        sb.append("Color : ").append(color);
        return sb.toString();
    }

    public static int getEmptyPieceValue() {
        return 0;
    }

}
