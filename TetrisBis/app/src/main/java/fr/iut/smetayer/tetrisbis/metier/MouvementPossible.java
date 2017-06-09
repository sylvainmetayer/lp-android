package fr.iut.smetayer.tetrisbis.metier;

public interface MouvementPossible {

    boolean canRotate(Float angle);

    boolean canGoLeft();

    boolean canGoRight();

    boolean canGoDown();

}
