<%@ page language="java" pageEncoding="GBK"%>
<%@ page import="com.jiuqi.dna.core.spi.application.*"%>
<%@ page import="com.jiuqi.dna.core.type.*"%>
<%@ page import="com.spark.psi.mainpage.*"%>
<%@page import="com.spark.psi.mainpage.utils.CredentialValidator"%>

<%
	final GUID userId = GUID.tryValueOf(request.getParameter("userId"));
		//创建新的DNA会话
		Session dnaSession = AppUtil.getDefaultApp().newSession(
				DNASessionIniterImpl.INSTANCE,
				new DNASessionInitProxyImpl(request));
%>
<html>
<body onload="fnSubmit();">
<form id="redirectForm" action="index.jsp" method="post">
	<input type="hidden" name="userId" value="<%=userId%>"> 
	<input type="hidden" name="sessionId" value="<%=dnaSession.getID()%>">
	<input type="hidden" name="verificationCode" value="<%=dnaSession.getVerificationCode()%>">
</form>
<script>
	function fnSubmit() {
		document.getElementById('redirectForm').submit();
	}
</script>
</body>
</html>
<%
%>
