package fr.iut.smetayer.tetris.metier;

interface Mouvement {

    void rotate(Float angle);

    void left();

    void right();

    void down();
}
