package fr.sylvainmetayer.tetris.metier;

import android.content.Context;
import android.util.Log;

import java.util.Arrays;

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
    public boolean canGoDown() {
        return getStartLine() + getMatrice().length < getContext().getResources().getInteger(R.integer.maxLines);
    }

    public boolean canGoDown(int[][] gameboard, Game game) {

        if (!canGoDown())
            return false;

        int column = this.getStartColumn();
        int line = this.getStartLine();
        int[][] matrice = this.getMatrice();
        int pieceLine = (matrice.length - 1) + line;
        Log.d("CANGODOWN", "START !");
        Log.d("CANGODOWN", this.toString());

        for (int matriceLineIterator = 0; matriceLineIterator < matrice.length; matriceLineIterator++) {
            for (int matriceColumnIterator = 0; matriceColumnIterator < matrice[matriceLineIterator].length; matriceColumnIterator++) {
                int pieceColumn = matriceColumnIterator + column;

                int matriceValue = matrice[matriceLineIterator][matriceColumnIterator];

                if (matriceValue == getEmptyPieceValue()) {
                    continue;
                }

                String logMessage = "We are testing the " + Utils.formatPosition(matriceLineIterator, matriceColumnIterator) + " of the matrice, which is " + matriceValue + ". " +
                        "This matrice position correspond to the " + Utils.formatPosition(pieceLine, pieceColumn) + " of the game";
                Log.d("CANGODOWN", logMessage);

                // Check every last line of matrice of the piece, for every column
                if (pieceLine + 1 >= gameboard.length) {
                    Log.d("CANGODOWN", "Out of boundary, continue");
                    continue;
                }

                if (gameboard[pieceLine + 1][pieceColumn] != getEmptyPieceValue()) {
                    Log.d("CANGODOWN", game.logGameboard());
                    Log.d("CANGODOWN", "The position [" + (pieceLine + 1) + "," + pieceColumn + "] is not empty !");
                    return false;
                }

                Log.d("CANGODOWN", "The position [" + matriceLineIterator + "," + matriceColumnIterator + "] of the matrice can go down");
                Log.d("CANGODOWN", "It means that the position [" + (pieceLine + 1) + "," + pieceColumn + "] is available on the gameboard");

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
