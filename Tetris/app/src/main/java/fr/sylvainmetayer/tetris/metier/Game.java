package fr.sylvainmetayer.tetris.metier;

import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import fr.sylvainmetayer.tetris.MainActivity;
import fr.sylvainmetayer.tetris.metier.pieces.Piece_I;
import fr.sylvainmetayer.tetris.utils.Utils;

public class Game {
    private int[][] gameboard;
    private ArrayList<Piece> pieces;
    private boolean pause;
    private int score;

    public Game(ArrayList<Piece> pieces, int nb_lines, int nb_columns) {

        this.pieces = pieces;
        gameboard = new int[nb_lines][nb_columns];
        this.pause = true;
        this.score = 0;

        resetGame();

        for (Piece piece : pieces) {
            int column = piece.getStartColumn();
            int line = piece.getStartLine();
            int[][] matrice = piece.getMatrice();
            Log.d("INIT", piece.toString());
            Log.d("INIT", "The piece start at " + Utils.formatPosition(line, column));

            for (int matriceLineIterator = 0; matriceLineIterator < matrice.length; matriceLineIterator++) {
                for (int matriceColumnIterator = 0; matriceColumnIterator < matrice[matriceLineIterator].length; matriceColumnIterator++) {
                    int matriceValue = matrice[matriceLineIterator][matriceColumnIterator];
                    int pieceColumn = matriceColumnIterator + column;
                    int pieceLine = matriceLineIterator + line;
                    Log.d("INIT", "I will affect the value '" + matriceValue + "' at " + Utils.formatPosition(pieceLine, pieceColumn));
                    gameboard[pieceLine][pieceColumn] = matriceValue;
                }
            }
        }
        Log.d("INIT_ENDED", logGameboard());
    }

    private void resetGame() {
        for (int line = 0; line < gameboard.length; line++) {
            for (int column = 0; column < gameboard[line].length; column++) {
                gameboard[line][column] = 0;
            }
        }
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
        return newArray;
    }

    private boolean addPieceToGameBoard(Piece piece) {
        int column = piece.getStartColumn();
        int line = piece.getStartLine();
        int[][] matrice = piece.getMatrice();

        for (int matriceLineIterator = 0; matriceLineIterator < matrice.length; matriceLineIterator++) {
            for (int matriceColumnIterator = 0; matriceColumnIterator < matrice[matriceLineIterator].length; matriceColumnIterator++) {
                int matriceValue = matrice[matriceLineIterator][matriceColumnIterator];
                int pieceColumn = matriceColumnIterator + column;
                int pieceLine = matriceLineIterator + line;
                Log.d("ADD_PIECE", "affect value '" + matriceValue + "' at " + Utils.formatPosition(pieceLine, pieceColumn));

                if (gameboard[pieceLine][pieceColumn] != Piece.getEmptyPieceValue())
                    return false;
                gameboard[pieceLine][pieceColumn] = matriceValue;
            }
        }
        return true;
    }

    public void performAction(final MainActivity activity) {
        Piece lastPiece = this.getLastPiece();

        Log.d("LAST_PIECE", lastPiece.toString());

        if (lastPiece.canGoDown(gameboard, this) && !this.isPause()) {
            // Piece is currently going down
            this.moveDown(lastPiece);
            lastPiece.down();
            Log.d("STATE_AFTER", lastPiece.toString());
        } else
            createNewPiece(activity);
        if (checkLines(lastPiece))
            createNewPiece(activity);
        Log.d("GBOARD", this.logGameboard());
    }

    private void createNewPiece(MainActivity activity) {
        Log.d("PERFORM", "Creation of a new piece");
        // TODO Generate a new random piece
        int[][] matrice =
                {
                        {1, 1, 1, 1, 1, 1, 1, 1, 1}
                };
        Piece start_piece = new Piece_I(matrice, 0, 0, activity);

        if (!addPieceToGameBoard(start_piece)) // Can't add piece, game over
            endGame(activity);
        pieces.add(start_piece);
    }

    /**
     * This method is used to remove full lines
     *
     * @param piece The current piece
     */
    private boolean checkLines(Piece piece) {
        // FIXME TODO
        for (int gameLineIterator = gameboard.length - 1; gameLineIterator >= 0; gameLineIterator--) {
            int cpt = 0;
            for (int gameColumnIterator = 0; gameColumnIterator < gameboard[gameLineIterator].length; gameColumnIterator++) {
                int pieceValue = gameboard[gameLineIterator][gameColumnIterator];
                if (pieceValue != Piece.getEmptyPieceValue())
                    cpt++;
            }

            if (cpt == gameboard[gameLineIterator].length) {
                // Line is full
                for (int gameColumnIterator = 0; gameColumnIterator < gameboard[gameLineIterator].length; gameColumnIterator++) {
                    gameboard[gameLineIterator][gameColumnIterator] = Piece.getEmptyPieceValue();
                    score += cpt;
                }
                moveAllPieceDownAboveThisLine(gameLineIterator);
                if (isPieceNeedToBeReplaced(piece, gameLineIterator))
                    return true;
            }
        }
        return false;
    }

    private boolean isPieceNeedToBeReplaced(Piece piece, int lineThatWasRemoved) {
        int line = piece.getStartLine();
        int[][] matrice = piece.getMatrice();

        for (int matriceLineIterator = 0; matriceLineIterator < matrice.length; matriceLineIterator++) {
            for (int matriceColumnIterator = 0; matriceColumnIterator < matrice[matriceLineIterator].length; matriceColumnIterator++) {
                int pieceLine = matriceLineIterator + line;

                if (lineThatWasRemoved == pieceLine)
                    return true;
            }
        }
        return false;
    }

    private void moveAllPieceDownAboveThisLine(int line) {
        for (int gameLineIterator = 0; gameLineIterator < line; gameLineIterator++) {
            for (int gameColumnIterator = 0; gameColumnIterator < gameboard[gameLineIterator].length; gameColumnIterator++) {
                int pieceValue = gameboard[gameLineIterator][gameColumnIterator];
                gameboard[gameLineIterator][gameColumnIterator] = Piece.getEmptyPieceValue();
                gameboard[gameLineIterator + 1][gameColumnIterator] = pieceValue;
            }
        }
    }

    private void endGame(MainActivity activity) {
        Toast.makeText(activity, "Game over", Toast.LENGTH_LONG).show();
        this.togglePause();
    }

    private Piece getLastPiece() {
        return pieces.get(pieces.size() - 1);
    }

    String logGameboard() {
        StringBuilder sb = new StringBuilder();
        for (int[] aGameboard : gameboard) {
            for (int anAGameboard : aGameboard) {
                sb.append(anAGameboard).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
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

    public void moveLeft() {
        if (this.isPause())
            return;

        Piece piece = getLastPiece();

        if (!piece.canGoLeft())
            return;

        moveColumn(piece, -1);
        piece.left();
        checkLines(piece);
    }

    private void moveDown(Piece piece) {
        int column = piece.getStartColumn();
        int line = piece.getStartLine();
        int[][] matrice = piece.getMatrice();

        for (int matriceLineIterator = matrice.length - 1; matriceLineIterator >= 0; matriceLineIterator--) {
            for (int matriceColumnIterator = 0; matriceColumnIterator < matrice[matriceLineIterator].length; matriceColumnIterator++) {
                int matriceValue = matrice[matriceLineIterator][matriceColumnIterator];
                int pieceColumn = matriceColumnIterator + column;
                int pieceLine = matriceLineIterator + line;

                gameboard[pieceLine][pieceColumn] = Piece.getEmptyPieceValue();
                gameboard[pieceLine + 1][pieceColumn] = matriceValue;
            }
        }
    }

    public void moveRight() {
        if (this.isPause())
            return;

        Piece piece = getLastPiece();

        if (!piece.canGoRight())
            return;

        moveColumn(piece, 1);
        piece.right();
        checkLines(piece);
    }

    private void moveColumn(Piece piece, int columnValue) {
        int column = piece.getStartColumn();
        int line = piece.getStartLine();
        int[][] matrice = piece.getMatrice();

        for (int matriceLineIterator = 0; matriceLineIterator < matrice.length; matriceLineIterator++) {
            for (int matriceColumnIterator = 0; matriceColumnIterator < matrice[matriceLineIterator].length; matriceColumnIterator++) {
                int matriceValue = matrice[matriceLineIterator][matriceColumnIterator];
                int pieceColumn = matriceColumnIterator + column;
                int pieceLine = matriceLineIterator + line;

                gameboard[pieceLine][pieceColumn] = 0;
                gameboard[pieceLine][pieceColumn + columnValue] = matriceValue;
            }
        }
    }
}
