package fr.iut.smetayer.tetris.metier;

public interface MouvementPossible {

    boolean canRotate(Float angle);

    boolean canGoLeft();

    boolean canGoRight();

    boolean canGoDown();

}
