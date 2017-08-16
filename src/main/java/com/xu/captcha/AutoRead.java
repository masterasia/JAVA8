package com.xu.captcha;

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
        GetMethod get = new GetMethod("https://bj.122.gov.cn/captcha1?nocache=1502852950799");

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

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
