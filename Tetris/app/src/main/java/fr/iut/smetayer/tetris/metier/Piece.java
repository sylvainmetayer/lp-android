package fr.iut.smetayer.tetris.metier;

import android.content.Context;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

import fr.iut.smetayer.tetris.R;

public abstract class Piece implements Mouvement, MouvementPossible {

    private int hauteur;
    private int largeur;
    private int[][] matrice;
    private int startLine;
    private int startColumn;
    private int color;
    private Context context;

    public Piece(int hauteur, int largeur, int[][] matrice, int line, int column, int color, Context context) {
        this.hauteur = hauteur;
        this.largeur = largeur;
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

    public void setStartLine(int startLine) {
        this.startLine = startLine;
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
}
