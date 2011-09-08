package com.ensimag.dac.ejb.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.ow2.easybeans.component.smartclient.spi.SmartContextFactory;

import com.ensimag.dac.ejb.api.IMathRemote;

public class EJBSmartClient {

    public static void main(String[] args) throws NamingException {

        // init initial context
        Properties props = new Properties();
        props.put(Context.PROVIDER_URL, "smart://localhost:2503");
        props.put(Context.INITIAL_CONTEXT_FACTORY, SmartContextFactory.class
                .getName());

        Context initialContext = new InitialContext(props);

        EJBSmartClient client = new EJBSmartClient();

        // stateless
        client.callStateless(initialContext);

        // statefull
        client.callStatefull(initialContext);

    }

    public long callStateless(Context p_InitialContext) throws NamingException {
        System.out.println("#########################################");
        System.out.println("Call stateless :");
        System.out.println("----------------");
        IMathRemote remoteService = (IMathRemote) p_InitialContext
                .lookup("com.ensimag.dac.ejb.stateless.MathStateless@Remote");
        long serviceResult = remoteService.add(2, 4);
        System.out.println("call 1 : " + serviceResult);
        System.out.println();

        return serviceResult;
    }

    public long callStatefull(Context p_InitialContext) throws NamingException {
        System.out.println("#########################################");
        System.out.println();
        System.out.println("Call stateful :");
        System.out.println("---------------");
        IMathRemote remoteService = (IMathRemote) p_InitialContext
                .lookup("com.ensimag.dac.ejb.stateless.MathStateful@Remote");
        long serviceResult = remoteService.add(2, 4);
        System.out.println("call 1 : " + serviceResult);
        System.out.println();

        return serviceResult;
    }
}
