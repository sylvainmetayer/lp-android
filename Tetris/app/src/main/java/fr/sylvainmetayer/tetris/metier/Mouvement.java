package fr.sylvainmetayer.tetris.metier;

interface Mouvement {

    void rotate(Float angle);

    void left();

    void right();

    void down();
}
