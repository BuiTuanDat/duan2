package com.ph05743.lenovo.androidnetworking;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class Bai3Activity extends AppCompatActivity implements View.OnClickListener, Listener {
    private ImageView image3;
    private TextView text3;
    private Button btn3;

    public static final String IMAGE_URL = "https://img.icons8.com/clouds/100/000000/android-os.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai3);
        image3 = (ImageView) findViewById(R.id.image3);
        text3 = (TextView) findViewById(R.id.text3);
        btn3 = (Button) findViewById(R.id.btn3);
        btn3.setOnClickListener(this);
        // onImageLoaded(null);
        //onError();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn3:
                new LoadImageTask( this,this).execute(IMAGE_URL);
                break;
        }

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }


    public class LoadImageTask extends AsyncTask<String, Void, Bitmap> {
        private Listener mListener;
        private ProgressDialog progressDialog;

        public LoadImageTask(Listener listener, Context context) {
            mListener = listener;
            progressDialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog.setMessage("Downloading image...");
            progressDialog.show();
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            try {
                return BitmapFactory.decodeStream((InputStream) new URL(params[0]).getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Bitmap result) {
            super.onPostExecute(result);
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            if (result != null) {
                mListener.onImageLoaded(result);
            } else {
                mListener.onError();
            }
        }
    }


    public void onImageLoaded(Bitmap bitmap) {
        image3.setImageBitmap(bitmap);
        text3.setText("Image Downloaded");
    }

    public void onError() {
        text3.setText("Error download image");
    }

//    public interface Listener {
//        void onImageLoaded(Bitmap bitmap);
//
//        void onError();
//    }

}
