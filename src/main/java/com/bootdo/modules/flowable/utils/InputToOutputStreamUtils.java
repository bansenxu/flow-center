package com.bootdo.modules.flowable.utils;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class InputToOutputStreamUtils {
    public static void copyPic(InputStream in, OutputStream out) {
        try {
            IOUtils.copy(in, out);
        } catch (IOException e) {
        } finally {
            IOUtils.closeQuietly(out);
            IOUtils.closeQuietly(in);
        }
    }
}
