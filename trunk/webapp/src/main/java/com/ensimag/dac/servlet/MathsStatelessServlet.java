package com.ensimag.dac.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ensimag.dac.util.EJBClientUtil;

public class MathsStatelessServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 8204003069337360358L;

    private static Logger s_LOGGER = LoggerFactory.getLogger(MathsStatelessServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        long result = -1;

        try {
            result = EJBClientUtil.callAddOnStateless(new InitialContext());
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        out.println("Result of the stateless call was : " + result);
        out.println("Please do it again and observe !");
        out.close();

        s_LOGGER.info("Maths stateless servlet called.");

    }

}
