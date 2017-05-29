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

    private Game gameboard;
    private MyAdapter adapter;
    private GridView layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int nb_columns = getResources().getInteger(R.integer.maxColumns);
        int nb_lines = getResources().getInteger(R.integer.maxLines);

        layout = (GridView) findViewById(R.id.grid);
        List<Piece> datas = new ArrayList<>();

        int[][] matrice =
                {
                        {0, 1, 1},
                        {1, 1}
                };
        Piece start_piece = new Piece_I(2, 3, matrice, 0, 0, this);
        datas.add(start_piece);

        gameboard = new Game((ArrayList<Piece>) datas, nb_lines, nb_columns);
        adapter = new MyAdapter(MainActivity.this, R.layout.item, gameboard.getGameboard());
        layout.setAdapter(adapter);

        if (true) // TODO Remove this debug condition
            gameboard.loop(this);
    }

    public void refresh() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //adapter.notifyDataSetChanged();
                //adapter.clear();
                // adapter.addAll(gameboard.getGameboard()); // Crash the app
                layout.setAdapter(new MyAdapter(MainActivity.this, R.layout.item, gameboard.getGameboard()));
            }
        });
    }
}
