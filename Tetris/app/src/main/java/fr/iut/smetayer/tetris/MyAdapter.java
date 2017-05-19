package fr.iut.smetayer.tetris;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import fr.iut.smetayer.tetris.metier.Piece;

public class MyAdapter extends ArrayAdapter<Integer> {

    private Context mContext;
    private LayoutInflater inflater = null;

    public MyAdapter(Context context, int textViewResourceId, Integer[] items) {
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

        int data = getItem(position);
        Log.d("GET_VIEW_ADAPTER", "Position : " + position);
        Log.d("GET_VIEW_ADAPTER", "Data : " + data);
        ImageView imageView = (ImageView) vi.findViewById(R.id.imageView);
        imageView.setImageResource(Piece.getImage(data));
        return vi;
    }

}
