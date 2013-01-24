<%@ page language="java" pageEncoding="GBK"%>
<%@ page
	import="com.jiuqi.dna.ui.wt.adapter.browser2.service.clientresource.ClientResourceService"%>
<%@ page
	import="com.jiuqi.dna.ui.wt.internal.services.ClientScriptService"%>
<%@ taglib uri="dna.tld" prefix="DNA"%>
<%@ include file="checkLogin.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<HEAD>
<META HTTP-EQUIV="pragma" CONTENT="public">
<META HTTP-EQUIV="Cache-Control" CONTENT="max-age=2592000">
<meta http-equiv="Content-Type" contect="text/html"">

<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7">
<script type="text/javascript" src="app/common/datepicker.js"></script>
<script>
	//因为DNA会重写javascript原生的DATE，计算旧历的方法提前提供出来保存为全局变量
	var DateGL = new Date();
	var Weathertext = (DateGL.getMonth() + 1) + "月" + DateGL.getDate()
			+ "日    农历:" + CnMonthofDate(DateGL) + CnDayofDate(DateGL)
			+ SolarTerm(DateGL);
</script>
<!--DNA的js,css导入-->
<%
	ClientResourceService.INSTANCE.initResource();
%>
<link rel="stylesheet" type="text/css"
	href="/dnaserver?serviceId=uiresource&type=css&version=<%=ClientResourceService.INSTANCE
					.getJWT_CSS_Version("filterblue")%>"></link>
<script
	src="/dnaserver?serviceId=uiresource&type=ENGINE_JS&version=<%=ClientResourceService.INSTANCE.getENGINE_JS_Version()%>"></script>
<script
	src="/dnaserver?serviceId=uiresource&type=WIDGET_JS&version=<%=ClientResourceService.INSTANCE.getWIDGET_JS_Version()%>"></script>
<script
	src="/dnaserver?serviceId=uiresource&type=ADAPTER_JS&version=<%=ClientResourceService.INSTANCE.getADAPTER_JS_Version()%>"></script>
<script
	src="/dnaserver?serviceId=uiresource&type=CLIENTEVENT_JS&version=<%=ClientScriptService.INSTANCE.getVersion()%>"></script>

<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript" src="js/easydrag.js"></script>
<script type="text/javascript" src="js/index_dna.js"></script>
<script type="text/javascript" src="js/monitor.js"></script>
<script type="text/javascript" src="js/desktop.js"></script>

<link rel="stylesheet" type="text/css" href="css/index.css" />
<link rel="stylesheet" type="text/css" href="css/monitor.css" />
<link rel="stylesheet" type="text/css" href="css/desktop.css" />

<title>ERP-7号生活馆</title>
</HEAD>
<BODY scroll='no'>

	<DNA:shell>
		<!-- 最上面的标题行 -->
		<div border="0px" cellpadding="0" cellspacing="0"
			class="backgroundDiv">
			<DNA:page id="mainpage" name="mainpage" />
		</div>
		<!-- 主功能区 -->
		<div class="navigation1">
			<div class="navigation1Left">&nbsp;</div>
			<div class="navigation1BeforeCenter"></div>
			<div class="navigation1Center">
				<div class="naviMenuBut"></div>
			</div>
			<div class="navigation1AfterCenter"></div>
			<div class="navigation1Right"></div>
		</div>
		<!-- 放主功能区的图标-->
		<table id="menuPage" cellpadding="0" cellspacing="0" class="menuPage">
			<tr id='menuPageTop'>
				<td class="menutopleft">&nbsp;</td>
				<td class="menutopcenter">&nbsp;</td>
				<td class="menutopright">&nbsp;</td>
			</tr>
			<tr id='menuPageCenter'>
				<td class="menutableft">&nbsp;</td>
				<td class="menutabcenter">
					<div style="white-space: nowrap;">
						<div class='menutabcenterDiv menutabcenterDivTabTextSale'></div>
						<div class="tabfold">
							<div id="tabTextSale" class="tabTextn">销售</div>
						</div>
						<div class='menutabcenterDiv'></div>
						<div class="tabfold">
							<div id="tabTextBuy" class="tabTextn">采购</div>
						</div>
						<div class='menutabcenterDiv'></div>
						<div class="tabfold">
							<div id="tabTextInventory" class="tabTextn">库管</div>
						</div>
						<div class='menutabcenterDiv'></div>
						<div class="tabfold">
							<div id="tabTextFinance" class="tabTextn">财务</div>
						</div>
						<div class='menutabcenterDiv'></div>
						<div class="tabfold">
							<div id="tabTextOther" class="tabTextn">其&nbsp;&nbsp;他</div>
						</div>
					</div>
				</td>
				<td class="menutabright">&nbsp;</td>
			</tr>
			<tr>
				<td class="menubuttomleft">&nbsp;</td>
				<td class="menubuttomcenter" id="menubuttomcenter"></td>
				<td class="menubuttomright">&nbsp;</td>
			</tr>
		</table>
		<!--最下面一行工具栏
    PS:单独提出来,是因为table布局下面,如果不设置绝对定位,z-index这个属性是不能使用的,导致不能实现蜥蜴助手从工具栏下面出来的效果
-->
		<div id="statusArea" class="statusArea">
			<table cellpadding="0" cellspacing="0" class="statusAreaTable">
				<tr>
					<td class="statusAreaLeftTD">
						<div id="statusAreaLeft" class="statusAreaLeft">
							&nbsp;
							<div>
					</td>
					<td class="statusArea1"></td>
					<!--七号生活馆系统不需要这个图标 td id="note" class="note"></td -->
					<td></td>
					<td class="statusArea3">&nbsp;</td>
					<td class="statusAreaCenter">&nbsp;</td>
					<!--右下角蜥蜴工具栏-->
					<td class="statusAreaRight">
						<div class="statusAreaRightCommButton">&nbsp;</div>
					</td>
				</tr>
			</table>
		</div>
		<script>
			mainPage.toolframe("showmessage", 22,
					$(window).height() - 50 - 470, 365, 476, "消息提示",
					"app/theme/common/images/level1/footer/message-n.png",
					"<DNA:page id='showMessage' name='showMessage' />");
			$("#showmessage").css("display", "none");
		</script>
	</DNA:shell>

</BODY>
</html>