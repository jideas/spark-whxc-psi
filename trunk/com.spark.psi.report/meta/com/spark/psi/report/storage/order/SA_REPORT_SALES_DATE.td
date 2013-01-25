<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_REPORT_SALES_DATE" ID="4463741F6D2CC6C43DEF6DAB3ACB9E8C" name-db="SA_REPORT_SALES_DATE" category="报表主体" title="客户分析数据表（按月份）" author="" description="" packagename="com.spark.psi.report.storage.order" name="SA_REPORT_SALES_DATE" isneedrebuild="false">
  <fields>
    <field dbtable="SA_REPORT_SALES_DATE" name="tenantId" name-db="tenantId" type="guid" primary-key="false" keep-valid="true" author="" title="租户编号" description="" ID="DADC04462C97EF502021BD9F5649148E" />
    <field dbtable="SA_REPORT_SALES_DATE" name="cusProGuid" name-db="cusProGuid" type="guid" primary-key="false" keep-valid="false" author="" title="客户" description="" ID="E8C0F2BEA5DDDFBC4E6DF77D5F38725A" />
    <field dbtable="SA_REPORT_SALES_DATE" name="dateNo" name-db="dateNo" type="int" primary-key="false" keep-valid="false" author="" title="日号" description="" ID="728D079FA7B39F0DC2A072F71D3F5E3E" />
    <field dbtable="SA_REPORT_SALES_DATE" name="monthNo" name-db="monthNo" type="int" primary-key="false" keep-valid="false" author="" title="期号" description="" ID="DDFD8408825BCC8C9E6FD371B26BFB09" />
    <field dbtable="SA_REPORT_SALES_DATE" name="quarter" name-db="quarter" type="int" primary-key="false" keep-valid="false" author="" title="季度" description="" ID="125EF216B3B916D7AAF3B542BFE41526" />
    <field dbtable="SA_REPORT_SALES_DATE" name="yearNo" name-db="yearNo" type="int" primary-key="false" keep-valid="false" author="" title="年份" description="" ID="FDB51FE47E8EBB77CAAA9ACC7EE980B1" />
    <field dbtable="SA_REPORT_SALES_DATE" name="ordAmount" name-db="ordAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="订单金额" description="" ID="A53793555B7F606118D95C55771303FB">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_SALES_DATE" name="outstoAmount" name-db="outstoAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="出库金额" description="" ID="B4B930BEBE7D9C3DDB5851BD07C6D32A">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_SALES_DATE" name="receiptAmount" name-db="receiptAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="采购收款金额" description="" ID="74BBEA9A9BE0964FE3F31CD00023F77B">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_SALES_DATE" name="deductionAmount" name-db="deductionAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="抵减金额" description="" ID="BE49140B6D59D68B13910B40EDA33727">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_SALES_DATE" name="rtnAmount" name-db="rtnAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="退货金额" description="" ID="1267F71E4BA073CEC88119AA31BCCB1F">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_SALES_DATE" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="制单人所属部门" description="" ID="6C83ABFA67A24CA596BCE692C8DCA5C7" />
    <field dbtable="SA_REPORT_SALES_DATE" name="orderPerson" name-db="orderPerson" type="guid" primary-key="false" keep-valid="false" author="" title="制单人" description="" ID="CC7CE30AEB586A46091C0BB12BA758F6" />
    <field dbtable="SA_REPORT_SALES_DATE" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="true" author="" title="创建时间" description="" ID="E93A97BA996C3CD1B0B2CA2ADD84E6FB" />
  </fields>
  <indexs>
    <index author="" name="SA_REPORT_CUSTOMER_MONONE" name-db="SA_REPORT_CUSTOMER_MONONE" title="SA_REPORT_CUSTOMER_MONONE" unique="false" description="">
      <index-item fieldID="DADC04462C97EF502021BD9F5649148E" field="tenantId" field-author="" desc="false" />
      <index-item fieldID="6C83ABFA67A24CA596BCE692C8DCA5C7" field="deptGuid" field-author="" desc="false" />
      <index-item fieldID="CC7CE30AEB586A46091C0BB12BA758F6" field="orderPerson" field-author="" desc="false" />
      <index-item fieldID="DDFD8408825BCC8C9E6FD371B26BFB09" field="monthNo" field-author="" desc="false" />
      <index-item fieldID="E8C0F2BEA5DDDFBC4E6DF77D5F38725A" field="cusProGuid" field-author="" desc="false" />
    </index>
    <index author="" name="SA_REPORT_CUSTOMER_MONTWO" name-db="SA_REPORT_CUSTOMER_MONTWO" title="SA_REPORT_CUSTOMER_MONTWO" unique="false" description="">
      <index-item fieldID="DADC04462C97EF502021BD9F5649148E" field="tenantId" field-author="" desc="false" />
      <index-item fieldID="6C83ABFA67A24CA596BCE692C8DCA5C7" field="deptGuid" field-author="" desc="false" />
      <index-item fieldID="CC7CE30AEB586A46091C0BB12BA758F6" field="orderPerson" field-author="" desc="false" />
      <index-item fieldID="125EF216B3B916D7AAF3B542BFE41526" field="quarter" field-author="" desc="false" />
      <index-item fieldID="E8C0F2BEA5DDDFBC4E6DF77D5F38725A" field="cusProGuid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_REPORT_SALES_DATE" name-db="SA_REPORT_SALES_DATE" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

