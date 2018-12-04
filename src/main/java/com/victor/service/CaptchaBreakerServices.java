package com.victor.service;

import com.victor.util.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.*;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class CaptchaBreakerServices {

    public static void getCaptcha() throws FileNotFoundException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://127.0.0.1/gsa_test.gsa");
        File f = new File("C:\\captchas\\policiamilitar.sp.gov.br\\kpmc8.png");
        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addBinaryBody("file", f, ContentType.DEFAULT_BINARY, f.getName());
//        builder.addTextBody("file", "C:\\captchas\\policiamilitar.sp.gov.br\\kpmc8.png", ContentType.TEXT_PLAIN);


//        try {
//            builder.addBinaryBody("file", new FileInputStream(f), ContentType.APPLICATION_OCTET_STREAM, f.getName());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//
        HttpEntity multipart = builder.build();


        CloseableHttpResponse response = null;
        try {
            request.setEntity(multipart);
            response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                String result = HttpUtil.convertStreamToString(response.getEntity().getContent());
                System.out.println(result);
            } else {

            }
        } catch (UnsupportedEncodingException e) {
        } catch (NoRouteToHostException e) {
        } catch (UnknownHostException e) {
        } catch (Exception e) {
        } finally {
            HttpUtil.closeHttpEntity(response);
        }

    }


}
