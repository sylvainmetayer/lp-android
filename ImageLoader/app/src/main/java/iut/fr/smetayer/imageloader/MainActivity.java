package iut.fr.smetayer.imageloader;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_TO_ACCESS_STORAGE = 1;
    private Button load;
    private String image;
    private ImageView loadInto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.loadInto = (ImageView) findViewById(R.id.image);
        this.load = (Button) findViewById(R.id.load);
        this.image = Environment.getExternalStorageDirectory() + "/DCIM/Camera/IMG_20170513_145659.jpg";

        this.load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("IUT", "Click on load button !");
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSION_TO_ACCESS_STORAGE);
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) throws SecurityException {
        switch (requestCode) {
            case MainActivity.MY_PERMISSION_TO_ACCESS_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Log.i("CALL", "User granted permission");
                    //loadImage();
                    loadPicture(MainActivity.this.loadInto, MainActivity.this.image);
                } else {
                    Log.wtf("CALL", "User denied permissions");
                }
            }
            default: {
                Log.wtf("", "Unknown code : " + requestCode);
            }
        }

    }

    public void loadImage() {
        ImageView imageview = (ImageView) findViewById(R.id.image);

        //loadPicture(imageview, image);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.activity_main, null);
        RelativeLayout rl = (RelativeLayout) view.findViewById(R.id.activity_main);

        ImageView imageView = new ImageView(MainActivity.this);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.BELOW, MainActivity.this.load.getId());

        File imgFile = new File(image);
        if (imgFile.exists()) {
            Log.i("IUT", "It exist !");
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imageView.setImageBitmap(myBitmap);
            imageView.setLayoutParams(params);
            rl.addView(imageView);
        }
    }

    private void loadPicture(ImageView loadInto, String photoUrl) {
        Glide
                .with(MainActivity.this)
                .load(photoUrl)
                .centerCrop()
                .crossFade()
                .diskCacheStrategy(DiskCacheStrategy.SOURCE)
                .into(loadInto);
    }
}
