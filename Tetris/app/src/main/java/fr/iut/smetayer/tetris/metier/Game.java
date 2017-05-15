package fr.iut.smetayer.tetris.metier;

import java.util.ArrayList;

public class Game {
    private int[][] gameboard;

    public Game(ArrayList<Piece> pieces, int nb_lines, int nb_columns) {

        gameboard = new int[nb_columns][nb_lines];
        for (Piece piece : pieces) {
            int column = piece.getLine();
            int line = piece.getColumn();
            // TODO Set in gameboard !
        }
    }
}
