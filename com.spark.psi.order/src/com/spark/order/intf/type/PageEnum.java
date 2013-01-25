package com.spark.order.intf.type;


/**
 * <p>
 * ҳǩö��
 * </p>
 * 
 * <p>
 * Copyright: ��Ȩ���� (c) 2002 - 2008<br>
 * Company: ����
 * </p>
 * 
 * @author Ī��
 * @version 2011-10-21
 */
public enum PageEnum {
	// ----------���б����---------
	/**
	 * ���ύ�����˻ض���
	 */
	NEW("�ɹ�����", "���۶���"),
	/**
	 * �����ɹ��˻�
	 */
	NEW_CANCEL("�ɹ��˻�", "�����˻�"),
	/**
	 * �ɹ�����
	 */
	EXAMINE("�ɹ�����", "��������"),
	/**
	 * �ɹ�����
	 */
	FOLLOW("�ɹ�����", "���۸���"),
	/**
	 * �ɹ���¼
	 */
	LOG("�ɹ���¼", "���ۼ�¼"),
	// -----------�����µ��ͻ�----------
	/**
	 * ���϶���
	 */
	ONE_NEW("���϶���"),
	/**
	 * ������¼
	 */
	ONE_LOG("������¼"),
	// -----------�������-----------
	/**
	 * ������ϸ
	 */
	DETAIL("�ɹ�������ϸ", "���۶�����ϸ"),
//	/**
//	 * ������ϸ�޲���ͼ����
//	 */
//	DETAIL_1("�ɹ�������ϸ", "���۶�����ϸ"),
	// ------------�ɹ������������ҳ��----------
	/**
	 * �ɹ������½�ҳ�棨��Ʒ�嵥��
	 */
	BUY_CREATE("�ɹ�����"),
	/**
	 * �ɹ�Ԥ�������Ʒ
	 */
	GOODS_STORE_WARNING("ѡ��ɹ���Ʒ(��Ԥ�������)"),
	/**
	 * �ɹ�ȫ����Ʒ
	 */
	GOODS_ALL("ѡ��ɹ���Ʒ(��ȫ����Ʒ��)"),
	/**
	 * �ɹ��������
	 */
	BUY_LOOK_STORAGE("���"),
	//-------------�ͻ���Ӧ��----------------
	/**
	 * �ͻ���Ӧ�̵���(δ�꽻�׺ͽ��׼�¼)
	 */
	BILLS_CUSP("��Ӧ��", "�ͻ�");

	private String[] name;

	private PageEnum(String... name) {
		this.name = name;
	}


	// /**
	// * ��ȡҳǩ����(�ɹ�)
	// *
	// * @return String
	// */
	// public String getName() {
	// return name[0];
	// }

	/**
	 * ��ȡҳǩ����
	 * 
	 * @param ord
	 * @return String
	 */
	public String getName(BillsEnum ord) {
		if (BillsEnum.SALE.equals(ord) && name.length > 1) {
			return name[1];
		}
		return name[0];
	}

}
