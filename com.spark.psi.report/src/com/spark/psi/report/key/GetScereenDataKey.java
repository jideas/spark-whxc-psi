/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.report.intf.key
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-12-15       ���ɹ�        
 * ============================================================*/

package com.spark.psi.report.key;

import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.report.constant.OldReportEnums.FenXiType;

/**
 * <p>TODO ������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author ���ɹ�
 * @version 2011-12-15
 */

public class GetScereenDataKey{

	private GUID tenantId; // �⻧GUID
	private GUID deptGuid; // ����GUID
	private GUID empGuid; // Ա��GUID
	private FenXiType fenXiType; // �������� 
	public GetScereenDataKey(GUID tenantId) {
		this.tenantId = tenantId;
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
    public FenXiType getFenXiType(){
    	return fenXiType;
    }
	/**
     * @param fenXiType �������� 
     */
    public void setFenXiType(FenXiType fenXiType){
    	this.fenXiType = fenXiType;
    }
	/**
     * @return ����GUID
     */
    public GUID getDeptGuid(){
    	return deptGuid;
    }
	/**
     * @param deptGuid ����GUID
     */
    public void setDeptGuid(GUID deptGuid){
    	this.deptGuid = deptGuid;
    }
    /**
     * @return Ա��GUID
     */
    public GUID getEmpGuid(){
    	return empGuid;
    }
	/**
     * @param empGuid Ա��GUID
     */
    public void setEmpGuid(GUID empGuid){
    	this.empGuid = empGuid;
    }
}
