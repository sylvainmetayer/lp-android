package fr.iut.smetayer.tetris.metier;

public abstract class Piece implements Mouvement, MouvementPossible {

    private int hauteur;
    private int largeur;
    private int[][] matrice;
    private int pos_i;
    private int pos_j;
    private int color;
    private int INT_CODE;

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

    public int getINT_CODE() {
        return INT_CODE;
    }

    public int getLargeur() {
        return largeur;
    }

    public int[][] getMatrice() {
        return matrice;
    }

    public int getPos_i() {
        return pos_i;
    }

    public int getPos_j() {
        return pos_j;
    }

    public int getColor() {
        return color;
    }

    @Override
    public String toString() {
        return "Hauteur : " + hauteur + ", Largeur : " + largeur + ", pos_i : " + pos_i + ", pos_j : " + pos_j + ", color : " + color;
    }
}
