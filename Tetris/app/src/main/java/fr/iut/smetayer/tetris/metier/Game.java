package fr.iut.smetayer.tetris.metier;

import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

import fr.iut.smetayer.tetris.MainActivity;
import fr.iut.smetayer.tetris.OnSwipeTouchListenerImpl;
import fr.iut.smetayer.tetris.R;
import fr.iut.smetayer.tetris.metier.pieces.Piece_I;

public class Game {
    private int[][] gameboard;
    private ArrayList<Piece> pieces;
    private boolean pause;
    private int score;

    public Game(ArrayList<Piece> pieces, int nb_lines, int nb_columns) {

        this.pieces = pieces;
        gameboard = new int[nb_lines][nb_columns];
        this.pause = false;
        this.score = 0;

        // Initialize with empty values.
        for (int line = 0; line < gameboard.length; line++) {
            for (int column = 0; column < gameboard[line].length; column++) {
                gameboard[line][column] = 0;
            }
        }

        for (Piece piece : pieces) {
            int column = piece.getStartLine();
            int line = piece.getStartColumn();
            int[][] matrice = piece.getMatrice();
            Log.d("INIT", "The piece start at [C:" + column + ",L:" + line + "]");
            Log.d("INIT", "Matrice " + Arrays.deepToString(matrice));

            for (int matriceLineIterator = 0; matriceLineIterator < matrice.length; matriceLineIterator++) {
                for (int matriceColumnIterator = 0; matriceColumnIterator < matrice[matriceLineIterator].length; matriceColumnIterator++) {
                    int matriceValue = matrice[matriceLineIterator][matriceColumnIterator];
                    int pieceColumn = matriceColumnIterator + column;
                    int pieceLine = matriceLineIterator + line;
                    Log.d("INIT", "I will affect the value '" + matriceValue + "' to the position [" + pieceLine + "," + pieceColumn + "] ");
                    gameboard[pieceLine][pieceColumn] = matriceValue;
                }
            }
        }
        Log.d("INIT", "GameBoard " + Arrays.deepToString(gameboard));
    }

    public Integer[] getGameboard() {

        Integer newArray[] = new Integer[gameboard.length * gameboard[0].length];
        for (int i = 0; i < gameboard.length; i++) {
            int[] row = gameboard[i];
            for (int j = 0; j < row.length; j++) {
                int number = gameboard[i][j];
                newArray[i * row.length + j] = number;
            }
        }
        Log.i("GET_GAME_BOARD", Arrays.toString(newArray));
        return newArray;
    }

    private void removePieceFromGameBoard(int startLine, int startColumn, int[][] matrice) {
        for (int matriceLineIterator = 0; matriceLineIterator < matrice.length; matriceLineIterator++) {
            for (int matriceColumnIterator = 0; matriceColumnIterator < matrice[matriceLineIterator].length; matriceColumnIterator++) {
                int matriceValue = 0;
                int pieceColumn = matriceColumnIterator + startColumn;
                int pieceLine = matriceLineIterator + startLine;
                Log.d("REMOVE_PIECE", "set 0 on  the position [" + pieceLine + "," + pieceColumn + "] ");
                gameboard[pieceLine][pieceColumn] = matriceValue;
            }
        }
    }

    private void addPieceToGameBoard(Piece piece) {
        int column = piece.getStartColumn();
        int line = piece.getStartLine();
        int[][] matrice = piece.getMatrice();

        for (int matriceLineIterator = 0; matriceLineIterator < matrice.length; matriceLineIterator++) {
            for (int matriceColumnIterator = 0; matriceColumnIterator < matrice[matriceLineIterator].length; matriceColumnIterator++) {
                int matriceValue = matrice[matriceLineIterator][matriceColumnIterator];
                int pieceColumn = matriceColumnIterator + column;
                int pieceLine = matriceLineIterator + line;
                // FIXME Error on loop, pieceLine is equals to MaxLines which causes an IndexOutOfBoundException
                Log.d("DETAILS", String.valueOf(matriceLineIterator));
                Log.d("DETAILS", String.valueOf(line));
                Log.d("DETAILS", "------");
                Log.d("REMOVE_PIECE", "affect value '" + matriceValue + "' on  the position [" + pieceLine + "," + pieceColumn + "] ");
                gameboard[pieceLine][pieceColumn] = matriceValue;
            }
        }
    }

    public void loop(final MainActivity activity) {
        Piece lastPiece = this.getLastPiece();

        GridView gridView = (GridView) activity.findViewById(R.id.grid);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(activity, "click", Toast.LENGTH_SHORT).show();
            }
        });

        gridView.setOnTouchListener(new OnSwipeTouchListenerImpl(activity));

        Handler handler = new Handler();

        final Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                activity.refresh();
            }
        };

        while (lastPiece.canGoDown()) {
            if (!this.isPause()) {
                int oldStartLine = lastPiece.getStartLine();
                int oldStartColumn = lastPiece.getStartColumn();
                int[][] oldMatrice = lastPiece.getMatrice();
                Log.d("STATE_BEFORE", lastPiece.toString());

                // Update piece state, gameboard, and UI
                lastPiece.down();
                this.updateGameboard(oldStartLine, oldStartColumn, oldMatrice, lastPiece);
                handler.postDelayed(r, 1000);

                Log.d("STATE_AFTER", lastPiece.toString());
                // TODO Handle user input
            }
        }

        Log.d("GBOARD", this.logGameboard());
        activity.refresh();
        // Création d'une nouvelle piece
        int[][] matrice =
                {
                        {0, 1, 1},
                        {1, 1}
                };
        Piece start_piece = new Piece_I(2, 3, matrice, 0, 0, activity);

        // Ajout de la nouvelle piece sur le plateau et à la liste des pieces.
        addPieceToGameBoard(start_piece);
        pieces.add(start_piece);

        Log.d("GBOARD", this.logGameboard());
        // Créer une nouvelle piece
        // L'ajouter
        // Rappeler loop
    }

    private Piece getLastPiece() {
        return pieces.get(pieces.size() - 1);
    }

    private String logGameboard() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < gameboard.length; i++) {
            for (int j = 0; j < gameboard[i].length; j++) {
                sb.append(gameboard[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    private void updateGameboard(int oldStartLine, int oldStartColumn, int[][] oldMatrice, Piece modifiedPiece) {
        removePieceFromGameBoard(oldStartLine, oldStartColumn, oldMatrice);
        addPieceToGameBoard(modifiedPiece);
    }

    public void togglePause() {
        this.pause = !pause;
    }

    public boolean isPause() {
        return pause;
    }

    public String getScore() {
        return "Score : " + score;
    }
}
