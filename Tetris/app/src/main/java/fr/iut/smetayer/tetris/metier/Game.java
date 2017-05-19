package fr.iut.smetayer.tetris.metier;

import android.util.Log;
import android.widget.Adapter;
import android.widget.ListAdapter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Game {
    private int[][] gameboard;
    private ArrayList<Piece> pieces;
    private ListAdapter adapters;

    public Game(ArrayList<Piece> pieces, int nb_lines, int nb_columns) {

        this.pieces = pieces;
        gameboard = new int[nb_lines][nb_columns];

        // Initialize with empty values.
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[i].length; j++) {
                gameboard[i][j] = 0;
            }
        }

        for (Piece piece : pieces) {
            int column = piece.getLine();
            int line = piece.getColumn();
            int[][] matrice = piece.getMatrice();
            Log.d("GAMEBOARD_INIT", "The piece start at [C:" + column + ",L:" + line + "]");
            Log.d("GAMEBOARD_INIT", "Matrice " + Arrays.deepToString(matrice));

            for (int i = 0; i < matrice.length; i++) {
                for (int j = 0; j < matrice[i].length; j++) {
                    int matriceValue = matrice[i][j];
                    int pieceI = i + column;
                    int pieceJ = j + line;
                    Log.d("GAMEBOARD_INIT", "I will affect the value '" + matriceValue + "' to the position [" + pieceI + "," + pieceJ + "] of the gameboard");
                    gameboard[pieceI][pieceJ] = matriceValue;
                }
            }
        }
        Log.d("GAMEBOARD_INIT", "GameBoard " + Arrays.deepToString(gameboard));
    }

    public void setAdapters(ListAdapter adapters) {
        this.adapters = adapters;
    }

    public Integer[] getGameboard() {

        Integer newArray[] = new Integer[gameboard.length * gameboard[0].length];
        for (int i = 0; i < gameboard.length; i++) {
            int[] row = gameboard[i];
            for (int j = 0; j < row.length; j++) {
                int number = gameboard[i][j];
                newArray[i * row.length + j] = Integer.valueOf(number);
            }
        }
        Log.i("GAMEBOARD_INIT", Arrays.toString(newArray));
        return newArray;
    }

    public void loop() {
        Piece lastPiece = pieces.get(pieces.size() - 1);
        while (lastPiece.canGoDown()) {
            Piece bck = lastPiece;
            lastPiece.down();
            updateGameboard(bck, lastPiece);
            this.adapters.notifyAll();
            // + modifier le tableau
            // + setModified sur l'adapter

            // TODO Handle user input to rotate piece
        }
        // Créer une nouvelle piece
        // L'ajouter
        // Rappeler loop
    }

    private void updateGameboard(Piece originalPiece, Piece modifiedPiece) {
        // TODO
    }
}
