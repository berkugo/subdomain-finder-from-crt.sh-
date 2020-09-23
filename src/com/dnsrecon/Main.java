package com.dnsrecon;

import com.dnsrecon.http.HttpManager;

import java.io.IOException;
import java.net.SocketException;

public class Main {

    public static void main(String[] args) throws IOException {

        try
        {
            HttpManager manager = new HttpManager("https://crt.sh/json?q=yahoo.com");
            manager.getRequest();
        }
        catch(SocketException proxyException)
        {
            System.out.println("Check your TOR service whether it is available and working properly.");
        }

    }
}
