package com.ensimag.dac.ejb.client;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.LoggerFactory;

import com.ensimag.dac.ejb.api.IMathRemote;

public class EJBClient {

	public static void main(String[] args) throws NamingException {

		// init initial context
		Properties props = new Properties();
		props.put("org.omg.CORBA.ORBInitialHost", "localhost");
		props.put("org.omg.CORBA.ORBInitialPort", "3700");

		InitialContext initialContext = new InitialContext(props);

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
				.lookup("com.ensimag.ejb.stateless.MathStateless@Remote");
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
				.lookup("com.ensimag.ejb.stateless.MathStateful@Remote");
		long serviceResult = remoteService.add(2, 4);
		System.out.println("call 1 : " + serviceResult);
		System.out.println();

		return serviceResult;
	}
}
