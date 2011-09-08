package com.ensimag.dac.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ow2.util.log.Log;
import org.ow2.util.log.LogFactory;

public class HelloServlet extends HttpServlet {

    /**
     *
     */
    private static final long serialVersionUID = 8204003069337360358L;

    private static Log s_LOGGER = LogFactory.getLog(HelloServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        this.doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        PrintWriter out = resp.getWriter();

        out.println("Hello, world!");
        out.close();

        s_LOGGER.info("Hello world servlet called.");

    }

}
