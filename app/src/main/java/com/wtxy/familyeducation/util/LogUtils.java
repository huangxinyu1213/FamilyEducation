package com.wtxy.familyeducation.util;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;

/**
 * Created by mxh on 1/12/16.
 */
public class LogUtils {
    private static final String path = "/sdcard/familyedu/";

    private static boolean isDebug = true;
    public static  void info(String flag,String info){
        if(isDebug){
            Log.i(flag,info);
        }

    }

    public static String logInfoToDisc(String info) {

        try {
            String fileName = "log.txt";
            if (Environment.getExternalStorageState()
                    .equals(Environment.MEDIA_MOUNTED)) {
                File dir = new File(path);
                if (!dir.exists()) {
                    dir.mkdir();
                }
                FileOutputStream fos = new FileOutputStream(path + fileName);
                fos.write(info.getBytes());
                fos.close();
            }
            return fileName;
        } catch (Exception e) {
            Log.e( "LogUtils", "an error ocuued while writing....", e);
        } finally {

        }
        return null;
    }
}
