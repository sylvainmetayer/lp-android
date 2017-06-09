package fr.iut.smetayer.tetrisbis;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import fr.iut.smetayer.tetrisbis.metier.Game;
import fr.iut.smetayer.tetrisbis.metier.Piece;
import fr.iut.smetayer.tetrisbis.metier.pieces.Piece_I;

public class MainActivity extends AppCompatActivity {

    private Game game_board;
    private GridView layout;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int nb_columns = getResources().getInteger(R.integer.maxColumns);
        int nb_lines = getResources().getInteger(R.integer.maxLines);

        layout = (GridView) findViewById(R.id.grid);
        ArrayList<Piece> datas = new ArrayList<>();

        int[][] matrice =
                {
                        {1},
                        {1},
                        {1}
                };
        Piece start_piece = new Piece_I(matrice, 0, 2, this);
        datas.add(start_piece);

        game_board = new Game(datas, nb_lines, nb_columns);

        final Button pause = (Button) findViewById(R.id.pause);
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                game_board.togglePause();
                if (game_board.isPause()) {
                    pause.setText(getResources().getString(R.string.restart));
                } else {
                    pause.setText(getResources().getString(R.string.pause));
                }
            }
        });

        editText = (EditText) findViewById(R.id.score);

        MyAdapter adapter = new MyAdapter(MainActivity.this, R.layout.item, game_board.getGameboard());
        layout.setAdapter(adapter);


        // Set mouvement detection on grid.
        layout.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, "click", Toast.LENGTH_SHORT).show();
            }
        });
        layout.setOnTouchListener(new OnSwipeTouchListenerImpl(this));

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (!MainActivity.this.game_board.isPause()) {
                    game_board.performAction(MainActivity.this);
                    MainActivity.this.refresh();
                }

            }
        }, 0, 1000);

    }

    public void refresh() {
        this.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                //adapter.notifyDataSetChanged();
                //adapter.clear();
                // adapter.addAll(game_board.getGameboard()); // Crash the app
                layout.setAdapter(new MyAdapter(MainActivity.this, R.layout.item, game_board.getGameboard()));
                editText.setText(game_board.getScore());
            }
        });
    }
}
