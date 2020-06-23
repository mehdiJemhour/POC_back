package com.jemhourmehdi.importbdx.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Utils {

     public static InputStream toInputStream(byte[] bytes){
        return new ByteArrayInputStream(bytes);
    }

    public static String getExtension(String filename){
         if(filename == null) throw new RuntimeException("Filename cannot be null");
        String[] splitedFilename = filename.split("\\.");
        return splitedFilename[splitedFilename.length - 1];
    }
}
