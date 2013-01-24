package com.spark.oms.publish;

/**
 * ϵͳ����/ְ����<
 */
public enum Auth {	
	
	/**
	 * ��ɫ
	 */
	SalesCommissionerRole("JS001","����רԱ"),
	HRAssistantRole("JS002","����(������Դ)"),
	PinTubeCommissionerRole("JS003","����רԱ"),
	FinanceSpecialistRole("JS004","����רԱ"),
	ProductSpecialistRole("JS005","��ƷרԱ"),
	SalesDirectorRole("JS006","�����ܼ�"),
	DivisionLeadersRole("JS007","��ҵ���쵼"),
	
	/**
	 * �ͻ�����
	 */
	CustomerManagement("01","�ͻ�����"),
	CustomerManagement_Query("0101","��ѯ"),
	CustomerManagement_RefundRequest("0102","�˿�����"),
	CustomerManagement_NewTenant("0103","�½��⻧"),
	CustomerManagement_UpdateTenant("0104","�޸��⻧(һ����Ϣ)"),
	CustomerManagement_UpdateTenantCore("0105","�޸��⻧������Ϣ"),
	CustomerManagement_SendMessage("0106","���Ͷ���"),
	CustomerManagement_ConfigTenantSystem("0107","�����⻧ϵͳ"),
	CustomerManagement_CommunicationPrepaidPhone("0108","ͨѶ��ֵ"),
	CustomerManagement_StopRequest("0109","ͣ������"),
	CustomerManagement_ChangeService("0110","�������"),
	CustomerManagement_RenewalService("0111","��ǩ����"),
	CustomerManagement_RecoverService("0112","�ָ�����"),
	
	/**
	 * ��Ʒ����
	 */
	ProductManagement("02","��Ʒ����"),
	ProductManagement_Query("0201","��ѯ"),
	ProductManagement_New("0202","�½�"),
	ProductManagement_Update("0203","�޸�"),
	ProductManagement_Delete("0204","ɾ��"),
	//
	ProductManagement_Stop("0205","ͣ��"),
	
	
	/**
	 * ��������
	 */
	OrderManagement("03","��������"),
	OrderManagement_Query("0301","��ѯ"),
	OrderManagement_New("0302","�½�"),
	OrderManagement_Update("0303","�޸�"),
	OrderManagement_Print("0304","��ӡ"),
	
	/**
	 * 
	 * �տ����
	 */
	CollectionManagement("04","�տ����"),
	CollectionManagement_Query("0401","��ѯ"),
	CollectionManagement_Confirm("0402","ȷ���տ�"),
	CollectionManagement_ConfirmRefund("0403","ȷ���˿�"),
	
	/**
	 * ��Ʊ��¼
	 */
	BillingRecord("05","��Ʊ��¼"),
	BillingRecord_Query("0501","��ѯ"),
	BillingRecord_Billing("0502","��Ʊ"),
	
	/**
	 * ��ά����
	 */
	OperationalManagement("06","��ά����"),
	OperationalManagement_ChannelsSet("0601","ͨ������"),
	OperationalManagement_PrintMessageFlow("0602","��ӡ������ˮ"),
	OperationalManagement_ExportMessageFlow("0603","����������ˮ"),
	OperationalManagement_LookupMessageFlow("0604","�鿴������ˮ"),
	
	/**
	 * ��֧��¼
	 */
	IncomingAndOutgoingsRecord("07","��֧��¼"),
	IncomingAndOutgoingsRecord_Query("0701","��ѯ"),
	IncomingAndOutgoingsRecord_New("0702","�½�"),
	
	/**
	 * �û�����
	 */
	UserManagement("08","�û�����"),
	UserManagement_Query("0801","��ѯ"),
	UserManagement_New("0802","�½�"),
	UserManagement_Update("0803","�޸�"),
	UserManagement_LeaveOffice("0804","��ְ"),
	UserManagement_BlockUp("0805","ͣ��"),
	
	/**
	 * ��ɫ����
	 */
	RoleManagement("09","��ɫ����" ),
	RoleManagement_Query("0901","��ѯ"),
	RoleManagement_New("0902","�½�"),
	RoleManagement_Update("0903","�޸�"),
	RoleManagement_Delete("0904","ɾ��"),
	
	
	/**
	 * ����ͣ��ȷ��
	 */
	OutOfService("10","����ͣ��ȷ��" ),
	OutOfService_Query("1001","��ѯ"),
	OutOfService_Confirm("1002","ȷ��ͣ��");
	
	/**
	 * ����/ְ�����
	 */
	private String code;

	/**
	 * ����/ְ������
	 */
	private String name;

	/**
	 * 
	 * @param code
	 * @param name
	 */
	private Auth(String code, String name) {
		this.code = code;
		this.name = name;
	}

	/**
	 * 
	 * @return
	 */
	public String getCode() {
		return this.code;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return this.name;
	}

	public static Auth getAuthByCode(String code) {
		for (Auth auth : Auth.values()) {
			if (auth.getCode().equals(code)) {
				return auth;
			}
		}
		throw new IllegalArgumentException(code + "����һ����ȷ��Ȩ��ö��");
	}
}