package com.dnsrecon.proxy;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

public class ProxyManager {

    private SocketAddress proxyAddress;
    private Proxy pr;
    public ProxyManager(String url)
    {
        this.proxyAddress = new InetSocketAddress("127.0.0.1", 9050);
        this.pr = new Proxy(Proxy.Type.SOCKS, proxyAddress);
    }

    public Proxy getProxyInstance()
    {
        if(this.pr != null)
            return this.pr;
        else return  new Proxy(Proxy.Type.SOCKS, proxyAddress);
    }




}
