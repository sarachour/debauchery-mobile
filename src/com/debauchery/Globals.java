package com.debauchery;

import java.io.File;
import java.io.FileOutputStream;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class Globals {
	static String IMAGE_PATH = "images";
	static String IMAGE_NAME = "buffer.jpg";
	
	public static String saveInternal(Context ctx, String path, String name, Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(ctx);
         // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir(path, Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,name);

        FileOutputStream fos = null;
        try {           

            fos = new FileOutputStream(mypath);

       // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mypath.getAbsolutePath();
    }
	public static Bitmap loadImage(String path){
		Bitmap bmp = BitmapFactory.decodeFile(path);
		return bmp;
	}
}
