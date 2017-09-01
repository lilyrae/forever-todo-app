package com.example.lily.forevertodo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;
import java.net.URL;

public class SuccessActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);

        setImage();
    }

    private class ImageDownloader extends AsyncTask<String, Void, Bitmap> {
        ImageView successImageView;

        public ImageDownloader(ImageView successImageView) {
            this.successImageView = successImageView;
        }

        protected Bitmap doInBackground(String... urls) {
            String url = urls[0];
            Bitmap bitmap = null;

            try {
                InputStream inputStream = new URL(url).openStream();
                bitmap = BitmapFactory.decodeStream(inputStream);

            } catch (Exception e) {
                Log.e("FAILED", e.getMessage());
            }
            return bitmap;
        }

        protected void onPostExecute(Bitmap result) {
            successImageView.setImageBitmap(result);
        }
    }

    private void setImage() {
        final ImageView imageView = (ImageView) findViewById(R.id.imageViewSuccess);
        ImageDownloader imageDownLoader = new ImageDownloader(imageView);
        imageDownLoader.execute("https://tse4.mm.bing.net/th?id=OIP.fiPgrigkM-s-M0LwelqlVwEsDh&pid=15.1");
    }
}
