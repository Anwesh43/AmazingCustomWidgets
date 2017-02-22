package com.anwesome.app.imageclippe;

import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.RectF;

/**
 * Created by anweshmishra on 22/02/17.
 */
public class ImageClipperElementFactory {
    public static ImageClipperElement newImageClipperElement(Bitmap bitmap,ImageClipperShape imageClipperShape, int w, int h) {
        if(bitmap == null) {
            return null;
        }
        return ImageClipperElement.getInstance(bitmap,decidePath(imageClipperShape,w,h));
    }
    private static Path decidePath(ImageClipperShape imageClipperShape,int w,int h) {
        Path path = new Path();
        switch (imageClipperShape) {
            case CIRCLE_SHAPE:
                path.addCircle(w/2,h/2,Math.min(w,h)/2, Path.Direction.CCW);
                break;
            case RECT_SHAPE:
                path.addRect(new RectF(0,0,w,h), Path.Direction.CCW);
                break;
            case TRIANGLE_SHAPE:
                path.moveTo(0,h);
                path.lineTo(w,h);
                path.lineTo(w/2,0);
                path.lineTo(0,h);
                break;
            default:
                break;
        }
        return path;
    }
}
