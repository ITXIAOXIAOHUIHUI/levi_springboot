package com.springboot.levi.leviweb1.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;

import java.io.IOException;


@Slf4j
public class HttpUtils {
    private static Logger LOGGER = LoggerFactory.getLogger(HttpUtils.class);


    public static String sendHttpPost(String url, String JSONBody) throws Exception {
        log.info("request url:[{}],body :[{}]", url, JSONBody);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type",  MediaType.APPLICATION_JSON_VALUE);
        httpPost.setEntity(new StringEntity(JSONBody));
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(10000)
                .setConnectTimeout(10000)
                .setConnectionRequestTimeout(10000)
                .build();
        httpPost.setConfig(requestConfig);
        CloseableHttpResponse response = httpClient.execute(httpPost);
        HttpEntity entity = response.getEntity();
        String responseContent = EntityUtils.toString(entity, "UTF-8");
        response.close();
        httpClient.close();
        log.info("request url:[{}],request body :[{}] response body:{}", url, JSONBody,responseContent);

        return responseContent;
    }

}
