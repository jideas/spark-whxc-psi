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

public class MainFunctionGather extends
		NamedFactory<MainFunction, FunctionElement> {

	private final static Map<String, MainFunction> allFunctions = new HashMap<String, MainFunction>();
	private final static Map<String, List<MainFunction>> allFunctionList = new HashMap<String, List<MainFunction>>();

	// private final static List<FunctionInfo> allFunctions = new
	// ArrayList<FunctionInfo>();

	public final static MainFunction[] getAllFunctions(String space) {
		List<MainFunction> functionList = allFunctionList.get(space);
		if (functionList != null) {
			return functionList.toArray(new MainFunction[functionList.size()]);
		}
		return null;
	}

	public final static MainFunction getFunction(String code) {
		return allFunctions.get(code);
	}

	@Override
	protected MainFunction doNewElement(Context context, FunctionElement meta,
			Object... adArgs) {
		return null;
	}

	@Override
	protected FunctionElement parseElement(SXElement element, BundleToken bundle)
			throws Throwable {
		FunctionElement functionElement = new FunctionElement(element, bundle);
		MainFunction function = functionElement.functionClass.newInstance();

		List<MainFunction> functionList = allFunctionList
				.get(functionElement.spaceName);
		if (functionList == null) {
			functionList = new ArrayList<MainFunction>();
			allFunctionList.put(functionElement.spaceName, functionList);
		}
		functionList.add(function);
		allFunctions.put(function.getCode(), function);
		return functionElement;
	}
}

class FunctionElement extends NamedFactoryElement {
	final Class<MainFunction> functionClass;
	final String spaceName;
	final static String xml_attr_class = "class";
	final static String xml_attr_space = "space";

	public FunctionElement(SXElement element, BundleToken bundle)
			throws ClassNotFoundException {
		super(element.getAttribute(xml_attr_class));
		this.functionClass = bundle.loadClass(
				element.getAttribute(xml_attr_class), MainFunction.class);
		this.spaceName = element.getAttribute(xml_attr_space);
	}

}
