package com.ensimag.dac.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloServlet extends HttpServlet {

	/**
     *
     */
	private static final long serialVersionUID = 8204003069337360358L;

	private static Logger s_LOGGER = LoggerFactory
			.getLogger(HelloServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		this.doGet(req, resp);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		s_LOGGER.info("Connected remote user is {}", req.getRemoteUser());

		PrintWriter out = resp.getWriter();

		out.println("Hello, world!");
		out.close();

		s_LOGGER.info("Hello world servlet called.");

	}

}
