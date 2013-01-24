package com.spark.uac.browser;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.spark.common.utils.CredentialRegistry;

public class CredentialValidateServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		doService(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		doService(request, response);
	}

	protected void doService(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String userId = request.getParameter("userId");
		String credential = request.getParameter("credential");
		String result = null;
		try {
			result = CredentialRegistry.validate(credential, userId) ? "1"
					: "0";
		} catch (Throwable t) {
			result = "0";
		}
		response.getWriter().write(result);
	}
}
