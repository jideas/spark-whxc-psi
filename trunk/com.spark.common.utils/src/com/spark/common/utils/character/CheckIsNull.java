/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.lib.character
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-4-30       Administrator        
 * ============================================================*/

package com.spark.common.utils.character;

import java.util.List;
import java.util.Map;

/**
 * <p>����Ƿ�Ϊ�չ�������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author xiangzhongqiu
 * @version 2011-4-30
 */

public class CheckIsNull{

	/**
	 * ��鵱ǰ�����Ƿ�Ϊ��  ����NULL�Ϳ��ַ��� 
	 * �����Ϊ�շ���true
	 * 
	 * @param obj  Ҫ���Ķ���
	 * @return boolean ����true��false
	 */
	public static boolean isNotEmpty(Object obj){
		return !isEmpty(obj);
	}

	/**
	 * ��鵱ǰ�����Ƿ�Ϊ��  ����NULL�Ϳ��ַ���
	 * ���Ϊ�շ���true
	 * @param obj
	 * @return boolean
	 */
	public static boolean isEmpty(Object obj){
		boolean isTrue = false;
		if(null == obj){
			isTrue = true;
		}
		if(obj instanceof String){
			if(((String)obj).trim().length() == 0){
				isTrue = true;
			}
			else{
				isTrue = false;
			}
		}
		if(obj instanceof List<?>){
			if(((List<?>)obj).size() <= 0){
				isTrue = true;
			}
		}
		if(obj instanceof Map<?,?>){
			if(((Map<?,?>)obj).size() <= 0){
				isTrue = true;
			}
		}
		return isTrue;
	}

}
