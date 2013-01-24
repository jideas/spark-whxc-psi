/**============================================================
 * 版权：  版权所有 (c) 2002 - 2012
 * 包： com.spark.lib.character
 * 修改记录：
 * 日期                作者           内容
 * =============================================================
 * 2011-5-6       Administrator        
 * ============================================================*/

package com.spark.psi.mainpage;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;

/**
 * <p>设置页面滚动条</p>
 *
 * <p>Copyright: 版权所有 (c) 2002 - 2008<br>

 *
 * @author Administrator
 * @version 2011-5-6
 */

public class Scroll{
	/**
	 * 设置页面滚动条
	 * 
	 * @param parent
	 * @return Composite
	 */
	public static Composite setScroll(Composite parent){
		//设置页面滚动条
		parent.setLayout(new FillLayout());
		ScrolledPanel panel = new ScrolledPanel(parent , JWT.V_SCROLL);
		Composite comp = panel.getComposite();
		return comp;
	}

}
