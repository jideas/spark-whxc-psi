<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_REPORT_PURCHASE_DATE" ID="562817B823F6C7DE89E01AABA150B63B" name-db="SA_REPORT_PURCHASE_DATE" category="报表主体" title="客户/供应商分析数据表（按月份）" author="" description="客户/供应商分析数据表（按月份）" packagename="com.spark.psi.report.storage.order" name="SA_REPORT_PURCHASE_DATE" isneedrebuild="false">
  <fields>
    <field dbtable="SA_REPORT_PURCHASE_DATE" name="tenantId" name-db="tenantId" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="857380831482D4C189C7EF68E17E8A33" />
    <field dbtable="SA_REPORT_PURCHASE_DATE" name="cusProGuid" name-db="cusProGuid" type="guid" primary-key="false" keep-valid="false" author="" title="供应商GUID" description="" ID="F46E39DB710B5505E50B1348F76E194C" />
    <field dbtable="SA_REPORT_PURCHASE_DATE" name="dateNo" name-db="dateNo" type="int" primary-key="false" keep-valid="false" author="" title="日号" description="" ID="C80AD0D59BFE10196F4A58C72D977BAB" />
    <field dbtable="SA_REPORT_PURCHASE_DATE" name="monthNo" name-db="monthNo" type="int" primary-key="false" keep-valid="false" author="" title="期号" description="" ID="6B9BDC33A08A0BFB66B43043DE215881" />
    <field dbtable="SA_REPORT_PURCHASE_DATE" name="quarter" name-db="quarter" type="int" primary-key="false" keep-valid="false" author="" title="季度" description="" ID="743A5B1D3AC2026E2B64F4580D2D3252" />
    <field dbtable="SA_REPORT_PURCHASE_DATE" name="yearNo" name-db="yearNo" type="int" primary-key="false" keep-valid="false" author="" title="年份" description="" ID="EF595D5142DF8E20EE0BEEB377D6A7E2" />
    <field dbtable="SA_REPORT_PURCHASE_DATE" name="ordAmount" name-db="ordAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="本月订单金额" description="" ID="6146984A71B16098AAE320948E952136">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_PURCHASE_DATE" name="outstoAmount" name-db="outstoAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="本月出库金额" description="" ID="DDB470EDAB996CDEA9BE56C349D132CE">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_PURCHASE_DATE" name="receiptAmount" name-db="receiptAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="采购收款金额" description="" ID="12847F5D4DA981BCF4C24DE684676A90">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_PURCHASE_DATE" name="deductionAmount" name-db="deductionAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="抵减金额" description="" ID="033DF739FCFC12E97C2899EDADFC7BE6">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_PURCHASE_DATE" name="rtnAmount" name-db="rtnAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="本月退货金额" description="" ID="4FA88264667032ECEF7AB31B18C95BC3">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_PURCHASE_DATE" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="true" author="" title="创建时间" description="" ID="E4C8DDE5D1CE7B66354C321869618B83" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_REPORT_PURCHASE_DATE" name-db="SA_REPORT_PURCHASE_DATE" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

