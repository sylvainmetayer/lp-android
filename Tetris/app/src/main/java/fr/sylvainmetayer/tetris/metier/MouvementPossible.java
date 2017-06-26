package fr.sylvainmetayer.tetris.metier;

public interface MouvementPossible {

    boolean canRotate(Float angle);

    boolean canGoLeft();

    boolean canGoRight();

    boolean canGoDown(int[][] gameboard);

}
