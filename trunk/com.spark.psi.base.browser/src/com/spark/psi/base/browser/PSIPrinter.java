package com.spark.psi.base.browser;

public class PSIPrinter {

	private PrintContentProvider contentProvider;
	
	private boolean isNeedPreview        = true;
	
	public PSIPrinter(PrintContentProvider contentProvider) {
		this.contentProvider = contentProvider;
	}
	
	
	public String getPrinterContent() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<html><head> \n");
		buffer.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=GBK\"> \n");
		buffer.append("<script language=\"javascript\" src=\"/psi_print/CheckActivX.js\"></script> \n");
		buffer.append("<script language=\"javascript\" src=\"/psi_print/jquery.js\"></script> \n");
		buffer.append("<OBJECT  ID=\"LODOP\" CLASSID=\"clsid:2105C259-1E0C-4534-8141-A753534CB4CA\" WIDTH=0 HEIGHT=0></OBJECT> \n");
		buffer.append("<script language=\"javascript\"> \n");
		
		buffer.append("function checkLodop() { \n");
		buffer.append("var oldVersion=LODOP.Version; \n");
		buffer.append("if (oldVersion==null) { \n");
		buffer.append("document.write(\"<h3><font color='#FF00FF'>打印控件未安装!点击这里<a href='/psi_print/install_lodop.exe'>执行安装</a>,安装后请刷新页面。</font></h3>\"); \n");
		buffer.append(" return false;} \n");
		buffer.append("} \n");
		
		buffer.append("function nodeClick(){ \n");
		// buffer.append("if (!checkLodop())return;");
		// intOrient,intPageWidth,intPageHeight,strPageName
		buffer.append("LODOP.SET_PRINT_PAGESIZE(1, " + contentProvider.getWidth() + ", " + contentProvider.getHeight() + ", ''); \n");
		// intTop,intLeft,intWidth,intHeight,strHtml
//		buffer.append("LODOP.ADD_PRINT_HTML(0,0," + contentProvider.getWidth() + ",90,\"<img border='0' src='/psi_print/print_logo.jpg' width='270' height='74' />\");");
		buffer.append("LODOP.ADD_PRINT_HTM(-20,0," + contentProvider.getWidth() + "," + contentProvider.getHeight() + ",document.getElementById(\"content\").innerHTML); \n");
		
		//buffer.append("LODOP.ADD_PRINT_HTM(30,0," + contentProvider.getWidth() + "," + contentProvider.getHeight() + ",\"" + info.toString() + "\"); \n");

		if(isNeedPreview()) {
			buffer.append("	LODOP.PREVIEW(); \n");
		} else {
			buffer.append("	LODOP.PRINT(); \n");
		}
		buffer.append("} \n");
		buffer.append("</script> \n");
		buffer.append("</head><body onload='nodeClick()' style='padding: 0;'> \n");
		buffer.append("<div id='content' style='margin-top: 0; padding-top:0;'> \n");
		buffer.append(contentProvider.getContentHtml() + " <br/>\n");
		buffer.append("</div> \n");
		buffer.append("<input type=\"button\" value=\"Test\" onclick=\"nodeClick()\" /> \n");
		buffer.append("</body></html>");
		return buffer.toString();
	}

	/**
	 * 是否需要预览
	 * @return
	 */
	public boolean isNeedPreview() {
		return isNeedPreview;
	}


	public void setNeedPreview(boolean isNeedPreview) {
		this.isNeedPreview = isNeedPreview;
	}
}
