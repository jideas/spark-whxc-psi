<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_REPORT_DEPT_SALES_DATE" ID="CAE98260468FDA7158E8EB1EB9CD0322" name-db="SA_REPORT_DEPT_SALES_DATE" category="报表主体" title="部门销售数据" author="" description="" packagename="com.spark.psi.report.storage.order" name="SA_REPORT_DEPT_SALES_DATE" isneedrebuild="false">
  <fields>
    <field dbtable="SA_REPORT_DEPT_SALES_DATE" name="tenantId" name-db="tenantId" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="C88343C88301C0813CFA1C81CDEDDF5B" />
    <field dbtable="SA_REPORT_DEPT_SALES_DATE" name="dateNo" name-db="dateNo" type="int" primary-key="false" keep-valid="false" author="" title="日号" description="" ID="D8087C058D8317043C0D35BAA505DB21" />
    <field dbtable="SA_REPORT_DEPT_SALES_DATE" name="monthNo" name-db="monthNo" type="int" primary-key="false" keep-valid="false" author="" title="期号" description="" ID="4054EFDB75F5932331C9835192F99536" />
    <field dbtable="SA_REPORT_DEPT_SALES_DATE" name="quarter" name-db="quarter" type="int" primary-key="false" keep-valid="false" author="" title="季度" description="" ID="F658344EC9E71A0645093A824E7E20BC" />
    <field dbtable="SA_REPORT_DEPT_SALES_DATE" name="yearNo" name-db="yearNo" type="int" primary-key="false" keep-valid="false" author="" title="年份" description="" ID="E72A12057ED60868DB151CBB19CB7D0E" />
    <field dbtable="SA_REPORT_DEPT_SALES_DATE" name="ordAmount" name-db="ordAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="订单金额" description="" ID="B77A933BF849ADDDF0F69F712B35A9D9">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_DEPT_SALES_DATE" name="outstoAmount" name-db="outstoAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="出库金额" description="" ID="064D95264899F8545A61E9C33E4329DF">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_DEPT_SALES_DATE" name="receiptAmount" name-db="receiptAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="采购收款金额" description="" ID="A8024800EB73E927AEA694D3B4B5070B">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_DEPT_SALES_DATE" name="deductionAmount" name-db="deductionAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="抵减金额" description="" ID="4407CFBCE6FC501599E983FDA258F831">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_DEPT_SALES_DATE" name="rtnAmount" name-db="rtnAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="退货金额" description="" ID="89E23EFAD6A3606AAFFBCF036C24B14E">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_DEPT_SALES_DATE" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="true" author="" title="制单人所属部门" description="" ID="EA69FE426852A0AC03F737D2AA93D266" />
    <field dbtable="SA_REPORT_DEPT_SALES_DATE" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="true" author="" title="创建时间" description="" ID="5A8D43D6F78DA7B98FAFFEB169A483C7" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_REPORT_DEPT_SALES_DATE" name-db="SA_REPORT_DEPT_SALES_DATE" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

