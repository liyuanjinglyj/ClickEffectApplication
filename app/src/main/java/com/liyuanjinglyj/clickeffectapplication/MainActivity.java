package com.liyuanjinglyj.clickeffectapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private ImageView imageView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.imageView=(ImageView) findViewById(R.id.imageview);
        Bitmap srcBitmap= BitmapFactory.decodeResource(getResources(),R.drawable.dog);
        Bitmap dstBitmap=srcBitmap.copy(Bitmap.Config.ARGB_8888,true);
        for(int i=0;i<srcBitmap.getHeight();i++){
            for(int j=0;j<srcBitmap.getWidth();j++){
                int pixel=srcBitmap.getPixel(j,i);

                int red= Color.red(pixel);
                int alpha= Color.alpha(pixel);
                int green= Color.green(pixel);
                int blue= Color.blue(pixel);

                if(green<200){
                    green+=50;
                }
                dstBitmap.setPixel(j,i,Color.argb(alpha,red,green,blue));
            }
        }
        this.imageView.setImageBitmap(dstBitmap);

        this.imageView2=(ImageView)findViewById(R.id.imageview2);
        this.imageView2.setImageBitmap(createWaterMarkBitmap(dstBitmap,BitmapFactory.decodeResource(getResources(),R.drawable.water)));
    }

    /***
     * 添加水印
     * @param srcBitmap
     * @param watermark
     * @return
     */
    private Bitmap createWaterMarkBitmap(Bitmap srcBitmap, Bitmap watermark){
        if(srcBitmap==null){
            return null;
        }
        int srcWidth=srcBitmap.getWidth();
        int srcHeight=srcBitmap.getHeight();
        int waterWidth=watermark.getWidth();
        int waterHeight=watermark.getHeight();
        Bitmap bitmap=Bitmap.createBitmap(srcWidth,srcHeight,Bitmap.Config.ARGB_8888);
        Canvas canvas=new Canvas(bitmap);
        canvas.drawBitmap(srcBitmap,0,0,null);
        canvas.drawBitmap(watermark,srcWidth-waterWidth+5,srcHeight-waterHeight+5,null);
        return bitmap;
    }
}
