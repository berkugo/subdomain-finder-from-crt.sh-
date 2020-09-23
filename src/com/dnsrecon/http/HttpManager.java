package com.dnsrecon.http;

import com.dnsrecon.proxy.ProxyManager;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;

public class HttpManager {

    private String url;
    private HttpURLConnection con;
    private ProxyManager pManager;
    private ArrayList<String> results = new ArrayList<>();


    public HttpManager(String url) throws IOException {
        this.url = url;
        this.pManager = new ProxyManager(this.url);
        this.con = (HttpURLConnection) new URL(url).openConnection(pManager.getProxyInstance());
        this.con.setConnectTimeout(5000);
        this.con.setReadTimeout(5000);
    }

    public void getRequest() throws IOException {
        this.con.setRequestMethod("GET");
        final int response = this.con.getResponseCode();
        BufferedReader reader = new BufferedReader(new InputStreamReader(this.con.getInputStream()));
        if(response == HttpURLConnection.HTTP_OK)
        {
            String output = reader.readLine();
            JsonArray convertedObject = new Gson().fromJson(output, JsonArray.class);
            for(JsonElement iterator : convertedObject)
            {
                String currentResult = iterator.getAsJsonObject().get("name_value").toString();
                if(currentResult.contains("@"))
                {
                    continue;
                }
                else
                {
                    StringBuilder compress = new StringBuilder();
                    if(currentResult.contains("*"))
                    {
                        for(String partial : currentResult.split("."))
                        {
                            if(partial != "*" && partial != null) {
                                compress.append(partial);
                            }
                        }
                    }
                    results.add(compress.toString());

                }

            }
        }
        for(String s : results)
            System.out.println(s);

    }


}
