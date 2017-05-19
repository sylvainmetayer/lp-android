package fr.iut.smetayer.tetris.metier.pieces;

import fr.iut.smetayer.tetris.metier.Piece;

public class Piece_I extends Piece {

    public Piece_I(int hauteur, int largeur, int[][] matrice, int pos_i, int pos_j) {
        super(hauteur, largeur, matrice, pos_i, pos_j, 1);
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

    @Override
    public boolean canGoDown() {
        return false;
    }
}
