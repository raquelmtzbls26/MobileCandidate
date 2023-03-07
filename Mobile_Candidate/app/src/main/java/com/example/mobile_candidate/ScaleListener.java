package com.example.mobile_candidate;

import android.view.ScaleGestureDetector;
import android.widget.ImageView;

public class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
    ImageView imgvPersona;
    private float scale=1f;

    public ScaleListener(ImageView imgvPersona){
        this.imgvPersona = imgvPersona;
    }

    public boolean onScale(ScaleGestureDetector detector){
        scale *= detector.getScaleFactor();
        imgvPersona.setScaleX(scale);
        imgvPersona.setScaleY(scale);
        return true;
    }
}
