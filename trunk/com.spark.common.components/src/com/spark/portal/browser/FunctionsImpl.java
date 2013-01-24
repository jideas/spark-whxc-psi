package com.spark.portal.browser;

import com.jiuqi.dna.core.Context;
import com.spark.common.components.pages.BaseFunction;
import com.spark.common.components.pages.Functions;

public class FunctionsImpl implements Functions {

	/**
	 * 
	 */
	private BaseFunction[] functions;

	/**
	 * 
	 * @param functions
	 */
	public FunctionsImpl(BaseFunction[] functions) {
		this.functions = functions;
	}

	/**
	 * @return the functions
	 */
	public BaseFunction[] getBaseFunctions(Context context) {
		return functions;
	}

}
