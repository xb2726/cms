package com.binx.core.utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Auther X .
 */
public class StreamUtils {

    public static InputStream byte2Input(byte[] buf) {
        return new ByteArrayInputStream(buf);
    }

    public static byte[] input2byte(InputStream inStream) {
        if (inStream == null) {
            return null;
        } else {
            try {
                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                Throwable var2 = null;

                try {
                    byte[] buff = new byte[1024];

                    int rc;
                    while((rc = inStream.read(buff, 0, 1024)) > 0) {
                        swapStream.write(buff, 0, rc);
                    }

                    byte[] var6 = swapStream.toByteArray();
                    return var6;
                } catch (Throwable var16) {
                    var2 = var16;
                    throw var16;
                } finally {
                    if (swapStream != null) {
                        if (var2 != null) {
                            try {
                                swapStream.close();
                            } catch (Throwable var15) {
                                var2.addSuppressed(var15);
                            }
                        } else {
                            swapStream.close();
                        }
                    }

                }
            } catch (IOException var18) {
                var18.printStackTrace();
                return null;
            }
        }
    }
}
