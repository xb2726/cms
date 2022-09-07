package com.serendipity.cms.utils;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Auther bin
 */
public class PdfHasCagrUtil {

    private static final String KEY_WORD = "CAGR";

    public static boolean hasCagr(String urlStr, String fileName, String savePath) throws IOException {
        Path path = downLoadByUrl(urlStr, fileName, savePath);

        return getKeyWordsByPath(Files.readAllBytes(path),path);

    }

    // 判断是否有cagr
    public static boolean getKeyWordsByPath(byte[] bytes,Path path) throws IOException {
        float[] coordinate = null;
        try {
            PdfReader pdfReader = new PdfReader(bytes);
            coordinate = getKeyWords(pdfReader);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Files.delete(path);
        return coordinate != null;
    }

    /**
     * @param pdfReader
     * @return float[]
     * @Author AlphaJunS
     * @Date 18:02 2020/3/7
     * @Description 根据pdfreader获取pdf中关键字的坐标
     */
    private static float[] getKeyWords(PdfReader pdfReader) {
        float[] coordinate = null;
        int page = 0;
        try {
            int pageNum = pdfReader.getNumberOfPages();
            PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(pdfReader);
            CustomRenderListener renderListener = new CustomRenderListener();
            renderListener.setKeyWord(KEY_WORD);
            for (page = 1; page <= pageNum; page++) {
                renderListener.setPage(page);
                pdfReaderContentParser.processContent(page, renderListener);
                coordinate = renderListener.getPcoordinate();
                if (coordinate != null) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return coordinate;
    }

    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param fileName
     * @param savePath
     * @throws IOException
     */
    public static Path downLoadByUrl(String urlStr, String fileName, String savePath) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为3秒
        conn.setConnectTimeout(5 * 1000);
        //防止屏蔽程序抓取而返回403错误
        conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
        //得到输入流
        InputStream inputStream = conn.getInputStream();
        //获取自己数组
        byte[] getData = readInputStream(inputStream);
        Path tempFile = Files.createTempFile(fileName, "");
        return Files.write(tempFile, getData);

    }


    /**
     * 从输入流中获取字节数组
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) throws IOException {
        byte[] buffer = new byte[1024];
        int len = 0;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        while ((len = inputStream.read(buffer)) != -1) {
            bos.write(buffer, 0, len);
        }
        bos.close();
        return bos.toByteArray();
    }
}
