<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_REPORT_CUSTOMER_COUNT" ID="7D418FF566132C211A8787BD7A95F857" name-db="SA_REPORT_CUSTOMER_COUNT" category="报表主体" title="客户分析" author="" description="客户分析" packagename="com.spark.psi.report.storage.partner" name="SA_REPORT_CUSTOMER_COUNT" isneedrebuild="false">
  <fields>
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="tenantId" name-db="tenantId" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="E0A93F9E9158AD2E2D341A09CA8CE19B" />
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="customerId" name-db="customerId" type="guid" primary-key="false" keep-valid="false" author="" title="客户id" description="" ID="F22C395EB98FDF2F1DD4162D8B04DDC1" />
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="部门GUID" description="" ID="D0BFE2E16689073DFD499214DB9A7DA8" />
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="employeeId" name-db="employeeId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人编号" description="" ID="0DD6B1B8F4C59376F8ED1340E0B5EB0D" />
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="dateNo" name-db="dateNo" type="int" primary-key="false" keep-valid="false" author="" title="日号" description="" ID="F82CCB4C15E6598681490F317B1CC780" />
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="monthNo" name-db="monthNo" type="int" primary-key="false" keep-valid="false" author="" title="期号" description="" ID="492FB95DB7ADCCF3CF05F96780331237" />
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="quarter" name-db="quarter" type="int" primary-key="false" keep-valid="false" author="" title="季度" description="" ID="990456CC1F3B73D8D27048804BC8F790" />
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="yearNo" name-db="yearNo" type="int" primary-key="false" keep-valid="false" author="" title="年份" description="" ID="C72AE342B951E730025C9FAE007CE26B" />
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="A1E68ED57E916A2E064D04C52604F73E" />
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="deptId2" name-db="deptId2" type="guid" primary-key="false" keep-valid="false" author="" title="转正后部门GUID" description="" ID="E1039AEA74A9B2CE0F7471085E939FF4" />
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="employeeId2" name-db="employeeId2" type="guid" primary-key="false" keep-valid="false" author="" title="转正后负责人编号" description="" ID="BDD1C2B9EE981925678C3EC31DA63823" />
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="officalDate" name-db="officalDate" type="date" primary-key="false" keep-valid="false" author="" title="转正日期" description="" ID="E89427923373628A2AC2F385BFC15BD2" />
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="dateNo2" name-db="dateNo2" type="int" primary-key="false" keep-valid="false" author="" title="转正日号" description="" ID="AE94728779F531AC4D9A39AE1364AB3E">
      <default>
        <const type="int" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="monthNo2" name-db="monthNo2" type="int" primary-key="false" keep-valid="false" author="" title="转正期号" description="" ID="AED3F333D7939F08E01AB2F0E27C4EFA">
      <default>
        <const type="int" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="quarter2" name-db="quarter2" type="int" primary-key="false" keep-valid="false" author="" title="转正季度" description="" ID="1A50D302DF6505998E94F3E04FB4BD79">
      <default>
        <const type="int" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_CUSTOMER_COUNT" name="yearNo2" name-db="yearNo2" type="int" primary-key="false" keep-valid="false" author="" title="转正年份" description="" ID="1F98002E32C1319D326ACCBDD0CDD377">
      <default>
        <const type="int" value="0" />
      </default>
    </field>
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_REPORT_CUSTOMER_COUNT" name-db="SA_REPORT_CUSTOMER_COUNT" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

