<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_REPORT_SCREEN" ID="BBB91ECAC12602B0F6BD2E6AA07564F9" name-db="SA_REPORT_SCREEN" category="报表主体" title="滚动屏数据表" author="" description="滚动屏数据表" packagename="com.spark.psi.report.storage.common" name="SA_REPORT_SCREEN" isneedrebuild="false">
  <fields>
    <field dbtable="SA_REPORT_SCREEN" name="tenantId" name-db="tenantId" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="E1C4BBE42EAFB1D7696404EFBE64B105" />
    <field dbtable="SA_REPORT_SCREEN" name="orderType" name-db="orderType" type="char(2)" primary-key="false" keep-valid="false" author="" title="订单类型" description="" ID="29C9B865F1A3833A846A1A90ED66D667" />
    <field dbtable="SA_REPORT_SCREEN" name="orderAmount" name-db="orderAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="订单金额" description="" ID="06705F7D0A0F631A293395CD05914E3A">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_SCREEN" name="orderPerson" name-db="orderPerson" type="guid" primary-key="false" keep-valid="false" author="" title="订单创建人" description="" ID="C17E5D00B6632E54BA4A1AF545600858" />
    <field dbtable="SA_REPORT_SCREEN" name="orderDate" name-db="orderDate" type="date" primary-key="false" keep-valid="false" author="" title="订单日期" description="" ID="CBBA3024823198046F7146BC8FF230E2" />
    <field dbtable="SA_REPORT_SCREEN" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="订单所属部门GUID" description="" ID="B6891211F14729D7213FB323DD7103AB" />
    <field dbtable="SA_REPORT_SCREEN" name="viewOrder" name-db="viewOrder" type="int" primary-key="false" keep-valid="false" author="" title="在一天中的显示顺序" description="" ID="1D06A5FB3D79414BC3D8C2336AEF1FC7" />
  </fields>
  <indexs>
    <index author="" name="SA_REPORT_SCREEN" name-db="SA_REPORT_SCREEN" title="SA_REPORT_SCREEN" unique="false" description="">
      <index-item fieldID="E1C4BBE42EAFB1D7696404EFBE64B105" field="tenantId" field-author="" desc="false" />
      <index-item fieldID="CBBA3024823198046F7146BC8FF230E2" field="orderDate" field-author="" desc="false" />
      <index-item fieldID="B6891211F14729D7213FB323DD7103AB" field="deptGuid" field-author="" desc="false" />
      <index-item fieldID="C17E5D00B6632E54BA4A1AF545600858" field="orderPerson" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_REPORT_SCREEN" name-db="SA_REPORT_SCREEN" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

