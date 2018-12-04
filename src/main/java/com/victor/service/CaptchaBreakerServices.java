package com.victor.service;

import com.victor.util.HttpUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.NoRouteToHostException;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaptchaBreakerServices {


    public static String solveCaptcha(String path) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost request = new HttpPost("http://127.0.0.1/gsa_test.gsa");
        File f = new File(path);

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);
        builder.addBinaryBody("file", f, ContentType.DEFAULT_BINARY, f.getName());

        HttpEntity multipart = builder.build();

        CloseableHttpResponse response = null;
        try {
            request.setEntity(multipart);
            response = httpClient.execute(request);
            if (response.getStatusLine().getStatusCode() == 200) {
                String result = HttpUtil.convertStreamToString(response.getEntity().getContent());
                Pattern regexResult = Pattern.compile("((?<=\\::\\ )\\w*)");

                Matcher matcher = regexResult.matcher(result);

                if (matcher.find()) {
                    return matcher.group(0);
                }
            }

        } catch (UnsupportedEncodingException e) {
        } catch (NoRouteToHostException e) {
        } catch (UnknownHostException e) {
        } catch (Exception e) {
        } finally {
            HttpUtil.closeHttpEntity(response);
        }
        return "";
    }


}
