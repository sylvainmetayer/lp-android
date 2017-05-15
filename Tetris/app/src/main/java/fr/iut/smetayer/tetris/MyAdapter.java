package fr.iut.smetayer.tetris;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import fr.iut.smetayer.tetris.metier.Piece;

public class MyAdapter extends ArrayAdapter<Piece> {

    private Context mContext;
    private LayoutInflater inflater = null;

    public MyAdapter(Context context, int textViewResourceId, ArrayList<Piece> items) {
        super(context, textViewResourceId, items);
        mContext = context;
        inflater = (LayoutInflater) mContext
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        View vi = convertView;
        if (convertView == null)
            vi = inflater.inflate(R.layout.item, null);

        Piece data = getItem(position);

        ImageView imageView = (ImageView) vi.findViewById(R.id.imageView);

        assert data != null;
        Log.i("IUT", data.toString());

        TextView textView = (TextView) vi.findViewById(R.id.textView);

        textView.setText(data.toString());

        return vi;
    }

}
