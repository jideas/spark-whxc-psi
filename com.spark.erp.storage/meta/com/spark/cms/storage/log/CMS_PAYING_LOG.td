<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_PAYING_LOG" ID="5D59B95162BB8EB111BAB93C87307788" name-db="CMS_PAYING_LOG" category="报表主体" title="单笔查询记录" author="" description="" packagename="com.spark.cms.storage.log" name="CMS_PAYING_LOG" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_PAYING_LOG" name="ptype" name-db="ptype" type="char(2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="94CF985E7781F017C05C7D9A189EF519" />
    <field dbtable="CMS_PAYING_LOG" name="amount" name-db="amount" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="" description="" ID="4A10CACD180CF583F278E1539C94216A" />
    <field dbtable="CMS_PAYING_LOG" name="createDate" name-db="createDate" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="" description="" ID="387F79559471F6F6DC60744815416275" />
    <field dbtable="CMS_PAYING_LOG" name="orderId" name-db="orderId" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="" description="" ID="D113CCB392F3FEF4E2F72D4D9E9897A0" />
    <field dbtable="CMS_PAYING_LOG" name="transDate" name-db="transDate" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="" description="" ID="C1CA4DCA3EE36E5A9A370CF99700AC53" />
    <field dbtable="CMS_PAYING_LOG" name="relaBillsId" name-db="relaBillsId" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="" description="" ID="405A4025B8CBB15882782F00F5B41461" />
    <field dbtable="CMS_PAYING_LOG" name="logDate" name-db="logDate" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="" description="" ID="D0DD924011D75A81DAF2727B3E0AF7EF" />
    <field dbtable="CMS_PAYING_LOG" name="resultXml" name-db="resultXml" type="varchar(500)" primary-key="false" keep-valid="false" author="" title="" description="" ID="4E7EC92F26D4796DE0B7A979642AFF01" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_PAYING_LOG" name-db="CMS_PAYING_LOG" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

