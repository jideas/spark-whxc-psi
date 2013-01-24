<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_SALES_ONLINERETURN" ID="5E4DDEA7AE6A6A5794FAF4015A855C7C" name-db="PSI_SALES_ONLINERETURN" category="基础数据主体" title="网上订单退货" author="" description="" packagename="com.spark.erp.storage.onlinereturn" name="PSI_SALES_ONLINERETURN" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_SALES_ONLINERETURN" name="billsNo" name-db="billsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="编号" description="" ID="1E0B2617230FD657F5028FE7B48ABF7C" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="memberId" name-db="memberId" type="guid" primary-key="false" keep-valid="false" author="" title="会员id" description="" ID="60A086056ECA0BDE7B1A04C8FF63D7DD" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="memberName" name-db="memberName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="会员名称" description="" ID="EA53CDF293913F263218CEC1CD480DAD" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="memberPhone" name-db="memberPhone" type="nvarchar(13)" primary-key="false" keep-valid="false" author="" title="会员手机号" description="" ID="520D9CBA91F67FA72D6F3C1F38D84B29" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="onlineCreateDate" name-db="onlineCreateDate" type="date" primary-key="false" keep-valid="false" author="" title="下单日期" description="" ID="06F930EA757FBED8202C56CDFEB56450" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="salesAmount" name-db="salesAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="销售订单总金额" description="" ID="92CC9F6D441FF508CEB7B1E4780FBAC7" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="onlineBillsId" name-db="onlineBillsId" type="guid" primary-key="false" keep-valid="false" author="" title="关联网上订单id" description="" ID="5C02AB5698A126DEC6561CD390326AA3" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="onlineBillsNo" name-db="onlineBillsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="网上订单编号" description="" ID="8FD7B74890729A1576F71080C10068A5" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="stationId" name-db="stationId" type="guid" primary-key="false" keep-valid="false" author="" title="站点id" description="" ID="CCB3481D1D407584F8AC05CDFBCCEAF4" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="stationName" name-db="stationName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="站点名称" description="" ID="A8B3411A33AE8F236EB67903431698A7" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="总金额" description="" ID="3C30E98ED6B62B954BF3A5BF58DBD803" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="2C4DE6C3CFB8D53F0AA3F374A25ED5E2" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="returnReason" name-db="returnReason" type="nvarchar(500)" primary-key="false" keep-valid="false" author="" title="退货原因" description="" ID="F200C2B2998BD6A52DC1BC64808D40AC" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="stopReason" name-db="stopReason" type="nvarchar(500)" primary-key="false" keep-valid="false" author="" title="终止原因" description="" ID="0F657E822865849114B07E98EF451BF4" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="rejectReason" name-db="rejectReason" type="nvarchar(500)" primary-key="false" keep-valid="false" author="" title="驳回原因" description="" ID="25825A56122CFFEB36C9539A271115D1" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="87789F493862B5449C569E0923131B17" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人名称" description="" ID="663D0BDE9A4E93CF3A82D09948133B35" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="056C79D2C0325B536C7244713608E837" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="approvorId" name-db="approvorId" type="guid" primary-key="false" keep-valid="false" author="" title="审核人id" description="" ID="48B3B64300D347B2784C8084034790C8" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="approvor" name-db="approvor" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="审核人名称" description="" ID="36158AC0E1E98171766AA678300CDE8A" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="approveDate" name-db="approveDate" type="date" primary-key="false" keep-valid="false" author="" title="审核日期" description="" ID="A63DC95349D8EB80FDC31F5787C2A3EF" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="finishPerson" name-db="finishPerson" type="guid" primary-key="false" keep-valid="false" author="" title="确认完成人" description="" ID="F15B1BCA9D8F72CD2D52D07A799FBD80" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="finishPersonName" name-db="finishPersonName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="A870C7E7FC057CFFBDDFFE3D42DB7972" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="finishedDate" name-db="finishedDate" type="date" primary-key="false" keep-valid="false" author="" title="" description="" ID="A47EB5E7BF46A94428F8BAB003A89F55" />
    <field dbtable="PSI_SALES_ONLINERETURN" name="vantages" name-db="vantages" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="" description="" ID="980487AB619C2038C8269A08A2FC5C5B" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_SALES_ONLINERETURN" name-db="PSI_SALES_ONLINERETURN" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

