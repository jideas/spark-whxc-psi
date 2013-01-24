package com.spark.psi.base.dictionary;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.xml.sax.SAXException;

import com.jiuqi.dna.core.misc.SXElement;
import com.jiuqi.dna.core.misc.SXElementBuilder;
import com.jiuqi.dna.ui.wt.graphics.TextFileDescriptor;
import com.spark.psi.base.internal.entity.EnumEntityImpl;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.EnumType;

public abstract class CommonConfigUtil {

	public static void parseXml(List<EnumEntity> typeList,
			Map<String, EnumEntity> map, String xmlName) {
		TextFileDescriptor file = TextFileDescriptor.getDescriptor(
				"com.spark.psi.base", "meta/com/spark/psi/base/config/"+xmlName+".xml");
		SXElementBuilder builder = null;
		try {
			builder = new SXElementBuilder();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		SXElement root = null;
		try {
			root = builder.build(file.getContent("UTF-8")).firstChild();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		}
		Iterator<SXElement> it = root.getChildren("item").iterator();
		while (it.hasNext()) {
			SXElement el = it.next();
			String code1 = el.getAttribute("code");
			String name = el.getAttribute("name");
			EnumEntityImpl type = new EnumEntityImpl(EnumType.SupplierType,
					code1, name);
			typeList.add(type);
			map.put(code1, type);
		}
	}
}
