/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.goods.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-31    jiuqi
 * ============================================================*/

/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.psi.publish.base.goods.task
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2012-3-31    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.goods.task;

import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.type.GUID;

/**
 * <p>TODO 类描述</p>
 *


 *
 
 * @version 2012-3-31
 */

public class DeleteGoodsCategoryTask extends SimpleTask{

	/**
	 * 分类id
	 */
	private GUID id;

	/**
	 * 构造函数
	 * 
	 * @param id
	 *            分类id
	 */
	public DeleteGoodsCategoryTask(GUID id) {
		this.id = id;
	}

	/**
	 * 
	 * @return
	 */
	public GUID getId() {
		return id;
	}

}
