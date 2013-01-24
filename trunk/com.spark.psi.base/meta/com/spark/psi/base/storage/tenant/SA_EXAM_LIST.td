<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_EXAM_LIST" ID="DCE48B39A9F00C4C8CEC7A53D7167A25" name-db="SA_EXAM_LIST" category="" title="审批记录  " author="" description="" packagename="com.spark.psi.base.storage.tenant" name="SA_EXAM_LIST" isneedrebuild="false">
  <fields>
    <field dbtable="SA_EXAM_LIST" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="E423173865152C0552179540DB395166" />
    <field dbtable="SA_EXAM_LIST" name="userGuid" name-db="userGuid" type="guid" primary-key="false" keep-valid="false" author="" title="用户编号" description="" ID="7C00644FE5F0E8D7C556B8029061D9B0" />
    <field dbtable="SA_EXAM_LIST" name="examDate" name-db="examDate" type="date" primary-key="false" keep-valid="false" author="" title="审批日期" description="" ID="CC6D93B4BBD24F533B5F75A4931C9B35" />
    <field dbtable="SA_EXAM_LIST" name="billsNo" name-db="billsNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="单据编号" description="" ID="B4E7EB2711D6CD8EA56B49382203E5E4" />
    <field dbtable="SA_EXAM_LIST" name="busType" name-db="busType" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="单据类型" description="" ID="B6B427B6B7636CEE4083AD62E06CEBED" />
    <field dbtable="SA_EXAM_LIST" name="busTypeName" name-db="busTypeName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="单据类型名称" description="" ID="428E7E490978F5FA29B591A09A7E6758" />
    <field dbtable="SA_EXAM_LIST" name="amount" name-db="amount" type="double" primary-key="false" keep-valid="false" author="" title="金额" description="" ID="6F7F81B11FCD2029AB657145004C504D" />
    <field dbtable="SA_EXAM_LIST" name="createPerson" name-db="createPerson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="7943A60E86C6ECD8EC14DF25BEBFDD16" />
    <field dbtable="SA_EXAM_LIST" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="7DAAC825FE97289512F3E407BC9F29A0" />
    <field dbtable="SA_EXAM_LIST" name="state" name-db="state" type="nvarchar(15)" primary-key="false" keep-valid="false" author="" title="单据状态" description="" ID="B53DF977068283DFB18056568A471F9F" />
  </fields>
  <indexs>
    <index author="" name="INDEX_EXAM_LIST_" name-db="INDEX_EXAM_LIST_" title="INDEX_EXAM_LIST_" unique="false" description="">
      <index-item fieldID="E423173865152C0552179540DB395166" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="7C00644FE5F0E8D7C556B8029061D9B0" field="userGuid" field-author="" desc="false" />
      <index-item fieldID="CC6D93B4BBD24F533B5F75A4931C9B35" field="examDate" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_EXAM_LIST" name-db="SA_EXAM_LIST" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

