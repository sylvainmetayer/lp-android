package fr.iut.smetayer.tetris;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fr.iut.smetayer.tetris.metier.Piece;
import fr.iut.smetayer.tetris.metier.pieces.Piece_I;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int nb_columns = 10;
        int nb_lines = 9;

        GridView layout = (GridView) findViewById(R.id.grid);

        List<Piece> datas = new ArrayList<Piece>();

        for (int i = 0; i < nb_columns; i++) {
            for (int j = 0; j < nb_lines; j++) {
                int dim1[][] = new int[3][];
                dim1[0] = new int[4];
                dim1[1] = new int[9];
                dim1[2] = new int[2];
                Piece piece_tmp = new Piece_I(2, 2, dim1, 1, 3, 2);
                datas.add(piece_tmp);
            }
        }

        MyAdapter adapter = new MyAdapter(MainActivity.this, R.layout.item, (ArrayList<Piece>) datas);
        layout.setAdapter(adapter);


    }
}
