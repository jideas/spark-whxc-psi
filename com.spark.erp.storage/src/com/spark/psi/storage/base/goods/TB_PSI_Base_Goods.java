package com.spark.psi.storage.base.goods;

import com.jiuqi.dna.core.def.table.TableDeclarator;
import com.jiuqi.dna.core.type.TypeFactory;
import com.jiuqi.dna.core.def.table.TableFieldDefine;
import com.jiuqi.dna.core.def.table.TableFieldDeclare;

public final class TB_PSI_Base_Goods extends TableDeclarator {

	public static final String TABLE_NAME ="PSI_Base_Goods";

	public final TableFieldDefine f_goodsCode;
	public final TableFieldDefine f_goodsName;
	public final TableFieldDefine f_namePY;
	public final TableFieldDefine f_categoryId;
	public final TableFieldDefine f_salePrice;
	public final TableFieldDefine f_isJointVenture;
	public final TableFieldDefine f_supplierId;
	public final TableFieldDefine f_remark;
	public final TableFieldDefine f_shelfLife;
	public final TableFieldDefine f_warningDay;
	public final TableFieldDefine f_canDelete;
	public final TableFieldDefine f_refFlag;
	public final TableFieldDefine f_inventoryWarningType;
	public final TableFieldDefine f_createDate;
	public final TableFieldDefine f_lastModifyDate;
	public final TableFieldDefine f_lastModifyPerson;
	public final TableFieldDefine f_creatorId;
	public final TableFieldDefine f_creator;
	public final TableFieldDefine f_status;

	public static final String FN_goodsCode ="goodsCode";
	public static final String FN_goodsName ="goodsName";
	public static final String FN_namePY ="namePY";
	public static final String FN_categoryId ="categoryId";
	public static final String FN_salePrice ="salePrice";
	public static final String FN_isJointVenture ="isJointVenture";
	public static final String FN_supplierId ="supplierId";
	public static final String FN_remark ="remark";
	public static final String FN_shelfLife ="shelfLife";
	public static final String FN_warningDay ="warningDay";
	public static final String FN_canDelete ="canDelete";
	public static final String FN_refFlag ="refFlag";
	public static final String FN_inventoryWarningType ="inventoryWarningType";
	public static final String FN_createDate ="createDate";
	public static final String FN_lastModifyDate ="lastModifyDate";
	public static final String FN_lastModifyPerson ="lastModifyPerson";
	public static final String FN_creatorId ="creatorId";
	public static final String FN_creator ="creator";
	public static final String FN_status ="status";

	//���ɵ��øù��췽��.��ǰ��ֻ���ɿ��ʵ����.
	private TB_PSI_Base_Goods() {
		super(TABLE_NAME);
		this.table.setTitle("��Ʒ��");
		this.table.setCategory("������������");
		TableFieldDeclare field;
		this.f_goodsCode = field = this.table.newField(FN_goodsCode, TypeFactory.NVARCHAR(30));
		field.setTitle("��Ʒ����");
		this.f_goodsName = field = this.table.newField(FN_goodsName, TypeFactory.NVARCHAR(100));
		field.setTitle("��Ʒ����");
		this.f_namePY = field = this.table.newField(FN_namePY, TypeFactory.NVARCHAR(30));
		field.setTitle("����ƴ��");
		this.f_categoryId = field = this.table.newField(FN_categoryId, TypeFactory.GUID);
		field.setTitle("��Ʒ����GUID");
		this.f_salePrice = field = this.table.newField(FN_salePrice, TypeFactory.NUMERIC(17,2));
		field.setTitle("ͳһ���ۼ۸�");
		this.f_isJointVenture = field = this.table.newField(FN_isJointVenture, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ���Ӫ��Ʒ");
		this.f_supplierId = field = this.table.newField(FN_supplierId, TypeFactory.GUID);
		field.setTitle("��Ӫ��Ӧ��");
		this.f_remark = field = this.table.newField(FN_remark, TypeFactory.NVARCHAR(1000));
		field.setTitle("��ע");
		this.f_shelfLife = field = this.table.newField(FN_shelfLife, TypeFactory.INT);
		field.setTitle("������");
		this.f_warningDay = field = this.table.newField(FN_warningDay, TypeFactory.INT);
		field.setTitle("Ԥ������");
		this.f_canDelete = field = this.table.newField(FN_canDelete, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ����ɾ��");
		this.f_refFlag = field = this.table.newField(FN_refFlag, TypeFactory.BOOLEAN);
		field.setTitle("�Ƿ����");
		this.f_inventoryWarningType = field = this.table.newField(FN_inventoryWarningType, TypeFactory.CHAR(2));
		field.setTitle("��Ʒ���Ԥ������");
		this.f_createDate = field = this.table.newField(FN_createDate, TypeFactory.DATE);
		field.setTitle("���ݲ���ʱ��");
		this.f_lastModifyDate = field = this.table.newField(FN_lastModifyDate, TypeFactory.DATE);
		field.setTitle("��������޸�ʱ��");
		this.f_lastModifyPerson = field = this.table.newField(FN_lastModifyPerson, TypeFactory.NVARCHAR(10));
		field.setTitle("��������޸���");
		this.f_creatorId = field = this.table.newField(FN_creatorId, TypeFactory.GUID);
		field.setTitle("������Guid");
		this.f_creator = field = this.table.newField(FN_creator, TypeFactory.NVARCHAR(30));
		field.setTitle("������");
		this.f_status = field = this.table.newField(FN_status, TypeFactory.CHAR(2));
		field.setTitle("��Ʒ״̬");
	}

}
