package com.jemhourmehdi.importbdx.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class Utils {

     public static InputStream toInputStream(byte[] bytes){
        return new ByteArrayInputStream(bytes);
    }
}
