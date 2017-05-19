package fr.iut.smetayer.tetris.metier;

import android.graphics.drawable.Drawable;

import fr.iut.smetayer.tetris.R;

public abstract class Piece implements Mouvement, MouvementPossible {

    private int hauteur;
    private int largeur;
    private int[][] matrice;
    private int pos_i;
    private int pos_j;
    private int color;

    public Piece(int hauteur, int largeur, int[][] matrice, int pos_i, int pos_j, int color) {
        this.hauteur = hauteur;
        this.largeur = largeur;
        this.matrice = matrice;
        this.pos_i = pos_i;
        this.pos_j = pos_j;
        this.color = color;
    }

    public int getHauteur() {
        return hauteur;
    }


    public int getLargeur() {
        return largeur;
    }

    public int[][] getMatrice() {
        return matrice;
    }

    public int getLine() {
        return pos_i;
    }

    public int getColumn() {
        return pos_j;
    }

    public int getColor() {
        return color;
    }

    public static int getImage(int value) {
        switch (value) {
            case 1:
                return R.drawable.blue_image;
            case 0:
                return R.drawable.white_image;
        }
        return R.drawable.white_image;
    }

    @Override
    public String toString() {
        return "Hauteur : " + hauteur + ", Largeur : " + largeur + ", pos_i : " + pos_i + ", pos_j : " + pos_j + ", color : " + color;
    }
}
