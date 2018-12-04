package com.victor.service;

import com.victor.util.HttpUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.UnsupportedEncodingException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class CaptchaBreakerServices {

    public static void getCaptcha() {
        CloseableHttpClient httpClient = HttpClients.custom().build();
        HttpPost request = new HttpPost("http://127.0.0.1/gsa_test.gsa");
        CloseableHttpResponse response = null;

        ArrayList<NameValuePair> parameters = new ArrayList<NameValuePair>();
        parameters.add(new BasicNameValuePair("file", "C:\\captchas\\policiamilitar.sp.gov.br\\kpmc8.png"));

        try {
            request.setEntity(new UrlEncodedFormEntity(parameters, "UTF-8"));
            response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {

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
