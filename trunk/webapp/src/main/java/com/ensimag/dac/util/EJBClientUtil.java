package com.ensimag.dac.util;

import com.ensimag.dac.ejb.api.IMathRemote;
import com.ensimag.dac.ejb.api.IMathStatelessRemote;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class EJBClientUtil {

    private static int countStateful = 0;

    public static long callAddOnStateless(Context p_InitialContext)
            throws NamingException {
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

    public static long callAddAndSaveOnStateless(Context p_InitialContext)
            throws NamingException {
        System.out.println("#########################################");
        System.out.println("Call stateless :");
        System.out.println("----------------");
        IMathStatelessRemote remoteService = (IMathStatelessRemote) p_InitialContext
                .lookup("com.evangelion.ejb.stateless.MathStateless@Remote");
        long serviceResult = remoteService.addAndSave(2,4);
        System.out.println("call 1 : " + serviceResult);
        System.out.println();

        return serviceResult;
    }

    public static long callAddOnStatefull(Context p_InitialContext, HttpServletRequest request)
            throws NamingException {
        System.out.println("#########################################");
        System.out.println();
        System.out.println("Call stateful :");
        System.out.println("---------------");

        IMathRemote remoteService = (IMathRemote) request.getSession().getAttribute("stateful");

        if (null == request.getSession().getAttribute("stateful")) {
            remoteService = (IMathRemote) p_InitialContext
                    .lookup("com.evangelion.ejb.stateless.MathStateful@Remote");
            request.getSession().setAttribute("stateful", remoteService);
        }

        long serviceResult = remoteService.add(2, 4);
        System.out.println("call " + countStateful++ + " : " + serviceResult);
        System.out.println();

        return serviceResult;
    }
}
