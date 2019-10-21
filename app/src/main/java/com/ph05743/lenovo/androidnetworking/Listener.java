package com.ph05743.lenovo.androidnetworking;

import android.graphics.Bitmap;

public interface Listener {
    void onImageLoaded(Bitmap bitmap);

    void onError();
}
