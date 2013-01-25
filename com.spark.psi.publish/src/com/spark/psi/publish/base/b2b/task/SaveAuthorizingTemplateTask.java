package com.spark.psi.publish.base.b2b.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.spark.psi.publish.base.b2b.entity.AuthorizingGoodsItem;

/**
 * ±£´æÊÚÈ¨Ä£°å
 */
public class SaveAuthorizingTemplateTask extends SimpleTask {

	private AuthorizingGoodsItem[] items;

	public SaveAuthorizingTemplateTask(AuthorizingGoodsItem[] items) {
		super();
		this.items = items;
	}

	/**
	 * @return the items
	 */
	public AuthorizingGoodsItem[] getItems() {
		return items;
	}

}
