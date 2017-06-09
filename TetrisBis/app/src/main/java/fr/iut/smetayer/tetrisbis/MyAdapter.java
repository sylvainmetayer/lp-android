package fr.iut.smetayer.tetrisbis;

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

import fr.iut.smetayer.tetrisbis.metier.Piece;

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

        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(100, 100));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(0, 0, 0, 0);
        } else {
            imageView = (ImageView) convertView;
        }
        int data = getItem(position);
        imageView.setImageResource(Piece.getImage(data));
        return imageView;

    }

}
