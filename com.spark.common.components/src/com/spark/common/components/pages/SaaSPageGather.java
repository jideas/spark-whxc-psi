package com.spark.common.components.pages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.misc.SXElement;
import com.jiuqi.dna.core.spi.publish.BundleToken;
import com.jiuqi.dna.core.spi.publish.NamedFactory;
import com.jiuqi.dna.core.spi.publish.NamedFactoryElement;
import com.jiuqi.dna.ui.wt.widgets.Page;

public class SaaSPageGather extends NamedFactory<Page, PageElement> {

	private final static List<SaaSPageInfo> allPageList = new ArrayList<SaaSPageInfo>();
	private final static Map<String, SaaSPageInfo> allPages = new HashMap<String, SaaSPageInfo>();

	public final static SaaSPageInfo[] getAllPageInfos() {
		return allPageList.toArray(new SaaSPageInfo[allPageList.size()]);
	}

	public final static SaaSPageInfo getPageInfo(String name) {
		return allPages.get(name);
	}

	@Override
	protected Page doNewElement(Context context, PageElement meta,
			Object... adArgs) {
		return null;
	}

	@Override
	protected PageElement parseElement(SXElement element, BundleToken bundle)
			throws Throwable {
		PageElement pageElement = new PageElement(element, bundle);
		String name = pageElement.getName();
		if (name == null || name.trim().length() == 0) {
			return null;
		}
		String title = pageElement.title;
		if (title == null || title.trim().length() == 0) {
			title = name;
		}
		SaaSPageInfo pageInfo = new SaaSPageInfo(pageElement.space, name,
				title, new PageController(pageElement.processorClass,
						pageElement.renderClass));
		allPageList.add(pageInfo);
		allPages.put(name, pageInfo);
		return pageElement;
	}
}

class PageElement extends NamedFactoryElement {
	final Class<PageProcessor> processorClass;
	final Class<PageRender> renderClass;
	final String space;
	final String title;

	public PageElement(SXElement element, BundleToken bundle)
			throws ClassNotFoundException {
		super(element.getAttribute("name"));
		this.processorClass = bundle.loadClass(
				element.getAttribute("processor"), PageProcessor.class);
		this.renderClass = bundle.loadClass(element.getAttribute("render"),
				PageRender.class);
		this.space = element.getAttribute("space");
		this.title = element.getAttribute("title");
	}

}
