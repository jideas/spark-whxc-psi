package com.spark.uac.publish.task;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.jiuqi.dna.core.type.GUID;

/**
 * 
 * <p>�����⻧ID����⻧����ҵLOGO</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Zhoulj
 * @version 2011-7-6
 */ 
@StructClass
public class GetCompanyLogoForTenantsRecidTask extends com.jiuqi.dna.core.invoke.SimpleTask{
	
	@StructField
	private GUID REICD;
	
	@StructField	
	private byte[] logo;
	
	/**
	 * 
	 * @param RECID �⻧���
	 */
	public GetCompanyLogoForTenantsRecidTask(GUID RECID){
		this.REICD = RECID;
	}

	public GUID getREICD(){
    	return REICD;
    }

	public void setREICD(GUID rEICD){
    	REICD = rEICD;
    }

	public byte[] getLogo(){
    	return logo;
    }

	public void setLogo(byte[] logo){
    	this.logo = logo;
    }
	
}
