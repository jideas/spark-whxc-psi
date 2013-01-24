<%@page import="com.jiuqi.dna.core.spi.application.ContextSPI"%>
<%@page import="com.jiuqi.dna.core.Context"%>
<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="com.jiuqi.dna.core.type.*"%>
<%@ page import="com.spark.psi.mainpage.*"%>
<%@ page import="com.spark.psi.publish.base.config.task.*"%>
<%
	boolean checked = false;
	try {
		final GUID userId = GUID.tryValueOf(request
				.getParameter("userId"));
		final long sessionId = Long.parseLong(request
				.getParameter("sessionId"));
		final long verificationCode = Long.parseLong(request
				.getParameter("verificationCode"));
		DNAContextUtil.execContextRunnable(sessionId, verificationCode,
				new DNAContextUtil.ContextRunnable() {
					public Object runWidthContext(ContextSPI context)
							throws Throwable {
						LoginTask task = new LoginTask(null, userId,
								null);
						context.handle(task);
						return true;
					}
				});
		request.setAttribute("sessionId", sessionId);
		request.setAttribute("verificationCode", verificationCode);
		checked = true;
	} catch (NumberFormatException e) {
	} catch (Throwable t) {
	}
	if (!checked) {
		response.sendRedirect("redirect.jsp");
		return;
	}
%>