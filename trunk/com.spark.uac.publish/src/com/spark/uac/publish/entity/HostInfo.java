package com.spark.uac.publish.entity;

import java.net.URL;

import com.jiuqi.dna.core.def.obja.StructClass;
import com.jiuqi.dna.core.def.obja.StructField;
import com.spark.uac.publish.HostType;
@StructClass
public interface HostInfo{
	
//	/**
//	 * ��֤��������id
//	 */
//	public static final String Uac_Server_Id = "0";
	@StructField
	String URL_TEMPLATE = "http://%s:%d";


	public String getId();
	
	/**
	 * �õ���Ϻ��ip��ַ
	 */
	public URL getURL();
	
	/**
	 * �õ���Ϻ��������ַ
	 */
	public URL getDomainURL();
	
	/**
	 * �����ַ
	 * @return
	 */
	public String getHost();

	/**
	 * �������˿�
	 * @return
	 */
	public int getPort();

	/**
	 * ����������
	 * @return
	 */
	public String getDomain();
	
	/**
	 * ��¼ʱ�Ƿ�����������¼
	 * @return
	 */
	public boolean getDomainEnabled();
	
	/**
	 * ����������
	 * @return
	 */
	public HostType getType();

	/**
	 * ����������
	 */
	public String getTitle();
	
	/**
	 * ��Ʒϵ��
	 * 
	 * @return String
	 */
	public String getProductSerials();
}
