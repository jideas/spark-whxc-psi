/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.report.intf.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-12-13       ���ɹ�        
 * ============================================================*/

package com.spark.psi.report.key;

import com.jiuqi.dna.core.type.GUID;

/**
 * <p>�����⻧�����ڵõ����µ�һ������������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author ���ɹ�
 * @version 2011-12-13
 */

public class GetScreenDataByDateAndTenantsGuidKey{

	private GUID tenantId; // �⻧GUID
	private long currentDate; // ��������
	/**
	 * 
	 * @param tenantId �⻧GUID
	 * @param currentDate ��������
	 */
	public GetScreenDataByDateAndTenantsGuidKey(GUID tenantId,
	        long currentDate) {
		this.tenantId = tenantId;
		this.currentDate = currentDate;
	}
	/**
     * @return �⻧GUID
     */
    public GUID getTenantsGuid(){
    	return tenantId;
    }
	/**
     * @return ��������
     */
    public long getCurrentDate(){
    	return currentDate;
    }
}
