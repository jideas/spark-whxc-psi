/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.lib.character
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-5-6       Administrator        
 * ============================================================*/

package com.spark.psi.mainpage;

import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.layouts.FillLayout;
import com.jiuqi.dna.ui.wt.widgets.Composite;
import com.jiuqi.dna.ui.wt.widgets.ScrolledPanel;

/**
 * <p>����ҳ�������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Administrator
 * @version 2011-5-6
 */

public class Scroll{
	/**
	 * ����ҳ�������
	 * 
	 * @param parent
	 * @return Composite
	 */
	public static Composite setScroll(Composite parent){
		//����ҳ�������
		parent.setLayout(new FillLayout());
		ScrolledPanel panel = new ScrolledPanel(parent , JWT.V_SCROLL);
		Composite comp = panel.getComposite();
		return comp;
	}

}
