package fr.iut.smetayer.tetris;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Sylvain on 29/05/2017.
 */

public class OnSwipeTouchListenerImpl extends OnSwipeTouchListener {
    private Context context;

    public OnSwipeTouchListenerImpl(Context ctx) {
        super(ctx);
        this.context = ctx;
    }

    public void onSwipeTop() {
        Toast.makeText(this.context, "top", Toast.LENGTH_SHORT).show();
    }

    public void onSwipeRight() {
        Toast.makeText(this.context, "right", Toast.LENGTH_SHORT).show();
    }

    public void onSwipeLeft() {
        Toast.makeText(this.context, "left", Toast.LENGTH_SHORT).show();
    }

    public void onSwipeBottom() {
        Toast.makeText(this.context, "bottom", Toast.LENGTH_SHORT).show();
    }

}
