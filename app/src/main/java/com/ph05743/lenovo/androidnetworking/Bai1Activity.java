package com.ph05743.lenovo.androidnetworking;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class Bai1Activity extends AppCompatActivity implements View.OnClickListener {
    private ImageView image;
    private Button btn1;
    private TextView text1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai1);

        image = (ImageView) findViewById(R.id.image);
        btn1 = (Button) findViewById(R.id.btn1);
        text1 = (TextView) findViewById(R.id.text1);
        btn1.setOnClickListener(this);
    }

    private Bitmap loadImageFromNetWork(String link) {
        URL url;
        Bitmap bmp = null;
        try {
            url = new URL(link);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    @Override
    public void onClick(View v) {
        final Thread myThread = new Thread(new Runnable() {
            @Override
            public void run() {
                final Bitmap bitmap = loadImageFromNetWork("https://img.icons8.com/nolan/64/000000/android.png");
                image.post(new Runnable() {
                    @Override
                    public void run() {
                        text1.setText("Image Dowloaded");
                        image.setImageBitmap(bitmap);
                    }
                });
            }
        });
        myThread.start();
    }
}
