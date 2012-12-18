package com.ensimag.dac.ejb.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.ow2.carol.jndi.spi.MultiOrbInitialContextFactory;

import com.ensimag.dac.ejb.api.IMathRemote;

public class EJBClient {

    public static void main(String[] args) throws NamingException {

        // init initial context
        Properties props = new Properties();
        props.put(Context.PROVIDER_URL, "rmi://localhost:1099");
        props.put(Context.INITIAL_CONTEXT_FACTORY,
                MultiOrbInitialContextFactory.class.getName());

        Context initialContext = new InitialContext(props);

        EJBClient client = new EJBClient();

        // stateless
        client.callStateless(initialContext);

        // stateful
        client.callStateful(initialContext);

    }

    public long callStateless(Context p_InitialContext) throws NamingException {
        System.out.println("#########################################");
        System.out.println("Call stateless :");
        System.out.println("----------------");
        IMathRemote remoteService = (IMathRemote) p_InitialContext
                .lookup("com.evangelion.ejb.stateless.MathStateless@Remote");
        long serviceResult = remoteService.add(2, 4);
        System.out.println("call 1 : " + serviceResult);
        System.out.println();

        return serviceResult;
    }

    public long callStateful(Context p_InitialContext) throws NamingException {
        System.out.println("#########################################");
        System.out.println();
        System.out.println("Call stateful :");
        System.out.println("---------------");
        IMathRemote remoteService = (IMathRemote) p_InitialContext
                .lookup("com.evangelion.ejb.stateless.MathStateful@Remote");
        long serviceResult = remoteService.add(2, 4);
        System.out.println("call 1 : " + serviceResult);
        System.out.println();

        return serviceResult;
    }
}
