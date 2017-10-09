package com.xu.captcha;

import com.asprise.ocr.Ocr;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.time.Instant;

/**
 * Created by robert.xu on 2017/8/16 0016.
 */
public class AutoRead {

    public static void main(String[] args){
        init();
    }

    public static void init() {
        HttpClient httpClient = new HttpClient();
        //https://dynamic.12306.cn/otsweb/passCodeAction.do?rand=sjrand
        //https://bj.122.gov.cn/captcha1?nocache=1502852950799
        //识别能力极弱。。。。
        GetMethod get = new GetMethod("http://dz.bjjtgl.gov.cn/service/checkCode.do");

        try {
            int statusCode = httpClient.executeMethod(get);
            if (statusCode != HttpStatus.SC_OK){
                System.out.println(" The status is :" + get.getStatusLine());
                return;
            }
            String ima = "f:/img/";
            File imaFile = new File(ima);
            imaFile.mkdirs();
            File imageFileF = new File(ima + Instant.now().toEpochMilli() + ".jpg");

            InputStream is = get.getResponseBodyAsStream();
            OutputStream os = new FileOutputStream(imageFileF);
            IOUtils.copy(is, os);
            os.close();
            Ocr.setUp();
            Ocr ocr = new Ocr();
            ocr.startEngine("eng", Ocr.SPEED_FASTEST );
            String s = ocr.recognize(new File[] {imageFileF},Ocr.RECOGNIZE_TYPE_TEXT, Ocr.OUTPUT_FORMAT_PLAINTEXT);

            System.out.println("Result: " + s);

            System.out.println("图片文字为:" + s.replace(",", "").replace("i", "1").replace(" ", "").replace("'", "").replace("o", "0").replace("O", "0").replace("g", "6").replace("B", "8").replace("s", "5").replace("z", "2"));

            ocr.stopEngine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
