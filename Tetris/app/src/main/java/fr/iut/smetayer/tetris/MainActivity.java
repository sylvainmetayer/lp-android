package fr.iut.smetayer.tetris;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import fr.iut.smetayer.tetris.metier.Game;
import fr.iut.smetayer.tetris.metier.Piece;
import fr.iut.smetayer.tetris.metier.pieces.Piece_I;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int nb_columns = 10;
        int nb_lines = 22;

        GridView layout = (GridView) findViewById(R.id.grid);

        List<Piece> datas = new ArrayList<>();

        int[][] matrice =
                {
                        {0, 1, 1},
                        {1, 1}
                };
        Piece piece_tmp = new Piece_I(2, 3, matrice, 0, 0);
        datas.add(piece_tmp);

        Game gameboard = new Game((ArrayList<Piece>) datas, nb_lines, nb_columns);
        MyAdapter adapter = new MyAdapter(MainActivity.this, R.layout.item, gameboard.getGameboard());
        layout.setAdapter(adapter);

    }
}
