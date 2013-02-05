<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_GoodsSplit_Goods" ID="38D6BAD35D710D3B20318F113C4D54C5" name-db="PSI_GoodsSplit_Goods" category="业务主体" title="拆分商品明细" author="" description="" packagename="com.spark.psi.storage.store.goodssplit" name="PSI_GoodsSplit_Goods" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_GoodsSplit_Goods" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="9E8F2C4FD63EB652DF9A2BF32E319C2F" />
    <field dbtable="PSI_GoodsSplit_Goods" name="goodsName" name-db="goodsName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="D0810ECD365F0DD87EE3EE8957BB4F71" />
    <field dbtable="PSI_GoodsSplit_Goods" name="spec" name-db="spec" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="规格" description="" ID="1B83A1247EE120BF8A7135BD7A8B7849" />
    <field dbtable="PSI_GoodsSplit_Goods" name="unit" name-db="unit" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="3322BFEB5A8EBF753137578F27A387E6" />
    <field dbtable="PSI_GoodsSplit_Goods" name="gcount" name-db="gcount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="B00E524797BC1769CCF849BAAE512185" />
    <field dbtable="PSI_GoodsSplit_Goods" name="splitSheetId" name-db="splitSheetId" type="guid" primary-key="false" keep-valid="false" author="" title="单据id" description="" ID="CA019CC5E28E77EA95978DDDB12D69AF" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_GoodsSplit_Goods" name-db="PSI_GoodsSplit_Goods" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

