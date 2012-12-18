package com.ensimag.dac.ws.client;

import org.apache.cxf.interceptor.LoggingInInterceptor;
import org.apache.cxf.interceptor.LoggingOutInterceptor;
import org.apache.cxf.jaxws.JaxWsProxyFactoryBean;

import com.ensimag.dac.service.api.IHelloWS;

public class WSClient {

    /**
     * @param args
     */
    public static void main(String[] args) {
        JaxWsProxyFactoryBean proxy = new JaxWsProxyFactoryBean();
        proxy.setAddress("http://localhost:9000/webapp/HelloWSService");
        proxy.setServiceClass(IHelloWS.class);

        //proxy.getInInterceptors().add(new LoggingInInterceptor());
        //proxy.getOutInterceptors().add(new LoggingOutInterceptor());

        IHelloWS service = (IHelloWS) proxy.create();

        System.out.println(service.sayHello("Guillaume"));

    }

}
