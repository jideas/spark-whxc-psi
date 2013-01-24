/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.order.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.order.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-6    jiuqi
 * ============================================================*/

package com.spark.psi.publish.order.task;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.PromotionAction;
import com.spark.psi.publish.PsiSimpleTask;

/**
 * <p>�޸Ĵ���״̬</p>
 * ȷ�ϴ���
 * ��������
 * ֹͣ����


 *
 
 * @version 2012-3-6
 */

public class UpdatePromotionStatusTask extends PsiSimpleTask<UpdatePromotionStatusTask.Error>{

	/**
	 * <p>����ö��</p>
	 *
	 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>
	
	 *
	 * @author modi
	 * @version 2012-3-31
	 */
	public enum Error{
		EndDateError("��ǰ��Ʒ�Ѵ���������"),
		BeginDateError("��ʼ���ڲ���С�ڵ�ǰʱ��!");
		private String msg;
		private Error(String msg){
			this.msg = msg;
		}
		public String getMsg() {
			return msg;
		}
	}
	
	private final GUID id;
	
	private final PromotionAction promotionAction;
	
	private String cause;
	
	public UpdatePromotionStatusTask(final GUID id,final PromotionAction promotionAction){
		this.id = id;
		this.promotionAction = promotionAction;
	}
	
	public UpdatePromotionStatusTask(final GUID id,final PromotionAction promotionAction,String cause){
		this.id = id;
		this.promotionAction = promotionAction;
		this.cause = cause;
	}

	public GUID getId(){
    	return id;
    }

	public PromotionAction getPromotionAction(){
    	return promotionAction;
    }

	/**
	 * ԭ��
	 * 
	 * @return String
	 */
	public String getCause(){
    	return cause;
    }
	
	
	
	
}
