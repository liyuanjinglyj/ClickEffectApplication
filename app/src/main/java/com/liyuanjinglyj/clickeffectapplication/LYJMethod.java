package com.liyuanjinglyj.clickeffectapplication;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class LYJMethod {

    public static byte[] getImage(String path) throws Exception {
        URL url=new URL(path);
        HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setReadTimeout(6000);
        InputStream is=null;
        if(httpURLConnection.getResponseCode()==200){
            is=httpURLConnection.getInputStream();
            byte[] result=readStream(is);
            is.close();
            return result;
        }
        return null;
    }

    public static byte[] readStream(InputStream is) throws Exception{
        ByteArrayOutputStream baos=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int len=-1;
        while((len=is.read(buffer))!=-1){
            baos.write(buffer,0,len);
        }
        baos.close();
        is.close();
        return baos.toByteArray();
    }
}
