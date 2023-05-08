package com.fileTest;

import org.springframework.util.FileCopyUtils;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ming.li
 * @date 2023/2/28 15:21
 */
public class Demo1 {
    static BASE64Encoder encoder = new sun.misc.BASE64Encoder();
    static BASE64Decoder decoder = new sun.misc.BASE64Decoder();

    public static void main(String[] args) throws Exception {
        //getImageBinary();
        //fileToBinStr(new File("C:\\Users\\ken\\Desktop\\WXL32201025租赁物清单.pdf"));
       // String s = extractFileMagic(new FileInputStream(new File("C:\\Users\\ken\\Desktop\\WXL32201025租赁物清单.pdf")));
        String s = extractFileMagic(new FileInputStream(new File("D:\\李明\\租赁物\\租赁物问题\\设备号--去判重误导了\\16532191002011163073\\WXL32201025租赁物清单.pdf")));
        System.out.println(s);
    }


    /**
     * 文件转为二进制字符串
     *
     * @param file
     * @return
     */
    public static String fileToBinStr(File file) {
        try {
            InputStream fis = new FileInputStream(file);
            byte[] bytes = FileCopyUtils.copyToByteArray(fis);
            System.out.println(bytes);
            System.out.println(new String(bytes, "UTF-8"));
            return new String(bytes, "ISO-8859-1");
        } catch (Exception ex) {
            throw new RuntimeException("transform file into bin String 出错", ex);
        }
    }

    private static String extractFileMagic(InputStream inputStream) throws IOException {
        /**
         * int read() 从此输入流中读取一个数据字节。int read(byte[] b) 从此输入流中将最多 b.length
         * 个字节的数据读入一个 byte 数组中。 int read(byte[] b, int off, int len)
         *从此输入流中将最多 len 个字节的数据读入一个 byte 数组中。
         * 之所以从输入流中读取20个字节数据，是因为不同格式的文件头魔数长度是不一样的，比如 EML("44656C69766572792D646174653A")和GIF("47494638")
         * 为了提高识别精度所以获取的字节数相应地长一点
         */
        byte[] b = new byte[20];
        inputStream.read(b, 0, 20);
        return bytesToHexString(b);
    }

    /**
     * @param
     * @return 16进制字符串
     * @description 第二步：将文件头转换成16进制字符串
     */
    private static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder();
       /* if (ArrayUtils.isEmpty(src)) {
            return null;
        }*/
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString().toUpperCase();
    }

  /*  public boolean findFileType(final String magic, final String extName) {
        final String EXT_NAME = extName.toLowerCase();
        *//*读取数据字典中的文件过滤配置*//*
        boolean flag = false;
        for (GFDict gfDict : gfdicts) {
        String filter = gfDict.getDictName();
        String[] filters = filter.split(regex:";");
        if (gfDict.getDictId().equalsIgnoreCase(EXT NAME)) {
        for (String str : filters) {
            if (magic.startsWith(str)) {
                logger.info("相同的魔法数{}",str);
                flag = true;
            }
        return flag;
      }
    }*/
}
