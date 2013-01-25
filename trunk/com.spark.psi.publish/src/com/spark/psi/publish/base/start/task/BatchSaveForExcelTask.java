/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.start.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-7-9    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.publish.base.start.task
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-7-9    jiuqi
 * ============================================================*/

package com.spark.psi.publish.base.start.task;

import java.io.InputStream;
import java.util.Map;

import com.jiuqi.dna.core.invoke.Task;

/**
 * <p>ͨ��excel������������</p>
 *
  */

public class BatchSaveForExcelTask extends Task<com.spark.psi.publish.base.start.task.BatchSaveForExcelTask.Model>{

	public enum Model{
		Customer,
		Supplier,
		Goods,
		Materials
	}
	
	/**
	 * 
	 * <p>����ֵ��������</p>
	 *
	 */
	public enum ErrType {
		/** ����Ϊ�� */
		IsNull("����Ϊ�գ�"),
		/** ��ʽ���� */
		FormatError("��ʽ����"),
		/** �������� */
		NotIsNumber("�������֣�"),
		/** ���Ȳ��� */
		LengthIsError("���ݳ��Ȳ��ڹ涨��Χ��"),
		/** �ѱ�ռ�� */
		IsExist("�ѱ�ռ�ã�"),
		/** ʡ������XML�ļ�������*/
		AreaXmlNotExist("ʡ������XML�ļ������ڣ��޷�����ƥ�䣡"),
		/** ʡ����������ֵ������ */
		NotFull("ʡ����������ֵ������"),
		/** ʡ�����ز�ƥ�� */
		NotMatch("ʡ�����ز�ƥ��"),
		/** ����ֵ��Ч */
		ValueIsVoid("����ֵ��Ч��"),
		/** �����ı���ʽ���� */
		NotIsText("Excel���и������������ı���ʽ��д��");
		
		String title;
		
		private ErrType(String title){
			this.title = title;
        }

		public String getTitle(){
        	return title;
        }
		
		
	}
	
	/**
	 * ִ����־��Ϣ
	 *  
	 * @return Map<String,ErrType> ���Ϊ����Ϊ����ɹ�
	 */
	private Map<Integer,Map<String,ErrType>> excuteLog;
	
	private InputStream excel;
	
	public BatchSaveForExcelTask(InputStream excel){
	    this.excel = excel;
    }

	public InputStream getExcel(){
    	return excel;
    }
	
	public Integer[] getExcutesNumber(){
		return excuteLog.keySet().toArray(new Integer[0]);
	}
	
	public Map<String,ErrType> getErr(Integer rowNum){
		Map<String,ErrType> err = excuteLog.get(rowNum);
//		if(err==null)throw new IllegalArgumentException(rowNum+"��û�д���");
		return err;
	}

	public void setExceutlog(Map<Integer,Map<String,ErrType>> log){
    	this.excuteLog = log;
    }
	
	
}
