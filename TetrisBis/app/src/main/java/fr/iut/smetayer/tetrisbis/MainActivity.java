package fr.iut.smetayer.tetrisbis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

import fr.iut.smetayer.tetrisbis.metier.Game;
import fr.iut.smetayer.tetrisbis.metier.Piece;
import fr.iut.smetayer.tetrisbis.metier.pieces.Piece_I;

public class MainActivity extends AppCompatActivity {

    private Game gameboard;
    private MyAdapter adapter;
    private GridView layout;
    private EditText editText;

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

        final Button pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameboard.togglePause();
                if (gameboard.isPause()) {
                    pause.setText(getResources().getString(R.string.pause));
                } else {
                    pause.setText(getResources().getString(R.string.restart));
                }
            }
        });

        editText = (EditText) findViewById(R.id.score);

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
                editText.setText(gameboard.getScore());
            }
        });
    }
}
