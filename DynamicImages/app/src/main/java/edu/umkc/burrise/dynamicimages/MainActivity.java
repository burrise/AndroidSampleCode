package edu.umkc.burrise.dynamicimages;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private boolean localImageIsShowing = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button b = (Button) findViewById(R.id.toggleButton);
        b.setOnClickListener(this);
    }

    @Override
    public void onClick(View arg0) {
        localImageIsShowing = !localImageIsShowing;
        ImageView image = (ImageView) findViewById(R.id.image);
        if (localImageIsShowing) {
            image.setImageResource(R.drawable.squirrelreach);
        }
        else { // load remote image on separate thread
            new DownloadAndDisplayImageTask(image)
                    .execute("http://sce2.umkc.edu/BIT/burrise/cs449/SquirrelNews.jpg");
        }
    }

    private class DownloadAndDisplayImageTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView bmImage;

        public DownloadAndDisplayImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String imageURL = urls[0];
            Bitmap imageBitmap = null;
            try {
                InputStream in = new java.net.URL(imageURL).openStream();
                imageBitmap = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return imageBitmap;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
