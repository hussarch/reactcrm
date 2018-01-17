package com.hussar.dc.scs.frm.cdcache.getter;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.hussar.dc.scs.frm.base.rest.model.ConfDataMetaData;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;


/**
 * @author XiaoYi
 * Created on 2017-07-24 11:24:27
 */
@Profile("distributed.deployment")
@Service("remoteAdapterConfDataGetter")
public class RemoteAdapterConfDataGetter extends AdapterConfDataGetter {
    
    private Logger logger = Logger.getLogger(this.getClass());
    
    @Value("${remote.conf.data.url}")
    private String url;
    
    public ConfDataMetaData get(String pathName) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(url + "?path=" + pathName).build();
        Response response;
        String json = "{}";
        try {
            response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                json = response.body().string();
                Gson gson = new Gson();
                return gson.fromJson(json, ConfDataMetaData.class);
            } else {
                logger.error("Can not get the conf data" + response.toString());
            }
        } catch (IOException e) {
            logger.error("Http request failed", e);
        }
        return null;
    }

}
