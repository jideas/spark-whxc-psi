<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_SALES_OLRETURN_DET" ID="12A7924A297ED9C7B6CA2CEAC3F4AE75" name-db="PSI_SALES_OLRETURN_DET" category="业务主体" title="退货明细" author="" description="" packagename="com.spark.erp.storage.onlinereturn" name="PSI_SALES_OLRETURN_DET" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_SALES_OLRETURN_DET" name="sheetId" name-db="sheetId" type="guid" primary-key="false" keep-valid="false" author="" title="退货单id" description="" ID="776E2BF251114AA6E66F7238E835EC0B" />
    <field dbtable="PSI_SALES_OLRETURN_DET" name="onlineBillsItemId" name-db="onlineBillsItemId" type="guid" primary-key="false" keep-valid="false" author="" title="网上订单明细id" description="" ID="42DCC5594C47581B29BA3CA18D07EB52" />
    <field dbtable="PSI_SALES_OLRETURN_DET" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品id" description="" ID="004D4D820B2A204EA4454483ADFAB652" />
    <field dbtable="PSI_SALES_OLRETURN_DET" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="F0BAFB9ED816047FBB0C534744D06F2A" />
    <field dbtable="PSI_SALES_OLRETURN_DET" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="393886965ACF34FF0CC47495F91A4B2E" />
    <field dbtable="PSI_SALES_OLRETURN_DET" name="goodsSpec" name-db="goodsSpec" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="" description="" ID="0EDA9C281AAFF136D3412000974BA24C" />
    <field dbtable="PSI_SALES_OLRETURN_DET" name="goodsUnit" name-db="goodsUnit" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="" description="" ID="3300CAB386B254E5C732282879F6C767" />
    <field dbtable="PSI_SALES_OLRETURN_DET" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="" description="" ID="043D1701361D7F44A5D979F31AEEEB03" />
    <field dbtable="PSI_SALES_OLRETURN_DET" name="returnCount" name-db="returnCount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="15BA36515F37ACFBFDF4C898724051DE" />
    <field dbtable="PSI_SALES_OLRETURN_DET" name="billsCount" name-db="billsCount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="C9827662CAA1BE9147F73262EF95E4F9" />
    <field dbtable="PSI_SALES_OLRETURN_DET" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="34D8F47F3FCB11A9EED995BE798D598D" />
    <field dbtable="PSI_SALES_OLRETURN_DET" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="6B024EA3BDBEDDB751A971204BE06965" />
    <field dbtable="PSI_SALES_OLRETURN_DET" name="vantages" name-db="vantages" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="" description="" ID="D8FE25D6CF9CFC0D376E2F47C686B1DC" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_SALES_OLRETURN_DET" name-db="PSI_SALES_OLRETURN_DET" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

