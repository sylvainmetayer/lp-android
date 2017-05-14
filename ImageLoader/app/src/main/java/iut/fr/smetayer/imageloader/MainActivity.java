package iut.fr.smetayer.imageloader;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final int MY_PERMISSION_TO_ACCESS_STORAGE = 1;
    private static final int REQUEST_IMAGE_GET = 2;
    private ImageView loadInto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.loadInto = (ImageView) findViewById(R.id.image);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_image:
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                        MY_PERMISSION_TO_ACCESS_STORAGE);
                loadInto.clearColorFilter();
                return true;
            case R.id.menu_horizontal:
                //add the function to perform here
                loadInto.setRotationY(loadInto.getRotationY() + 180);
                return true;
            case R.id.menu_vertical:
                loadInto.setRotationX(loadInto.getRotationX() + 180);
                return true;
            case R.id.menu_color:
                /**
                 * Color matrix that flips the components (<code>-1.0f * c + 255 = 255 - c</code>)
                 * and keeps the alpha intact.
                 */
                final float[] NEGATIVE = {
                        -1.0f, 0, 0, 0, 255, // red
                        0, -1.0f, 0, 0, 255, // green
                        0, 0, -1.0f, 0, 255, // blue
                        0, 0, 0, 1.0f, 0  // alpha
                };

                loadInto.setColorFilter(new ColorMatrixColorFilter(NEGATIVE));

                return true;
            case R.id.menu_gris:
                ColorMatrix matrix = new ColorMatrix();
                matrix.setSaturation(0);

                ColorMatrixColorFilter filter = new ColorMatrixColorFilter(matrix);
                loadInto.setColorFilter(filter);
                return true;
        }
        return (super.onOptionsItemSelected(item));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) throws SecurityException {
        switch (requestCode) {
            case MainActivity.MY_PERMISSION_TO_ACCESS_STORAGE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    startActivityForResult(
                            Intent.createChooser(
                                    new Intent(Intent.ACTION_GET_CONTENT)
                                            .setType("image/*"), "Choose an image"),
                            MainActivity.REQUEST_IMAGE_GET);
                } else {
                    Log.wtf("", "ERROR");
                }
            }
            default: {
                Log.wtf("", "Unknown code : " + requestCode);
            }
        }
    }

    /**
     * @see "http://codetheory.in/android-pick-select-image-from-gallery-with-intents/"
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == MainActivity.REQUEST_IMAGE_GET && resultCode == RESULT_OK) {
            Uri uri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);

                ImageView imageView = (ImageView) findViewById(R.id.image);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

