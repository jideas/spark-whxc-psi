/**
 * 
 */
package com.spark.psi.base.phoneMessage.service;

import java.io.IOException;

import org.xml.sax.SAXException;

import com.jiuqi.dna.core.misc.SXElement;
import com.jiuqi.dna.core.misc.SXElementBuilder;
import com.spark.psi.base.phoneMessage.entity.ReturnMessage;

/**
 * xml解析工具
 */
public class XmlParseUtil {

	private XmlParseUtil() {
	}

	private static XmlParseUtil instance;

	/**
	 * 获得解析工具对象(over)
	 */
	public static XmlParseUtil getInstance() {
		if (null == instance) {
			instance = new XmlParseUtil();
		}
		return instance;
	}

	/**
	 * 主调用接口(over)
	 */
	public ReturnMessage parseXml(String xml) {
		SXElementBuilder seb = null;
		try {
			seb = new SXElementBuilder();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		SXElement root = null;
		try {
			root = seb.build(xml).firstChild();
		} catch (IOException e) {
		} catch (SAXException e) {
		}
		return parseRoot(root);
	}

	private ReturnMessage parseRoot(SXElement root) {
		ReturnMessage msg = new ReturnMessage();
		SXElement result = root.firstChild("result");
		if (null != result) {
			msg.setResult(result.getText());
		}
		SXElement spmid = root.firstChild("spmid");
		if (null != spmid) {
			msg.setSpmid(spmid.getText());
		}
		SXElement description = root.firstChild("description");
		if (null != description) {
			msg.setDescription(description.getText());
		}
		SXElement reserve1 = root.firstChild("reserve1");
		if (null != reserve1) {
			msg.setReserve1(reserve1.getText());
		}

		SXElement reserve2 = root.firstChild("reserve2");
		if (null != reserve2) {
			msg.setReserve2(reserve2.getText());
		}
		return msg;
	}

}
