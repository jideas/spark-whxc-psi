<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_ORDER_PROMOTION" ID="4CFC47C9C02F88192C50E776EDE45730" name-db="CMS_ORDER_PROMOTION" category="业务主体" title="整单促销" author="" description="" packagename="com.spark.cms.storage.promotion" name="CMS_ORDER_PROMOTION" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_ORDER_PROMOTION" name="isActiving" name-db="isActiving" type="boolean" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="B5A8B146B6C4E9ACF589E8E3836C38AE" />
    <field dbtable="CMS_ORDER_PROMOTION" name="beginAmount" name-db="beginAmount" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="起始金额" description="" ID="9F4505A0BCCD81AB5714D0FEDFD9DAD2" />
    <field dbtable="CMS_ORDER_PROMOTION" name="endAmount" name-db="endAmount" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="结束金额" description="" ID="211976C191D936A422FE61BF8051BAEF" />
    <field dbtable="CMS_ORDER_PROMOTION" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品id" description="" ID="0D28037EC30FFA389E649BEAD29BDD1E" />
    <field dbtable="CMS_ORDER_PROMOTION" name="goodsCount" name-db="goodsCount" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="0A9C072CD0DD31FAB7110B6E296F4964" />
    <field dbtable="CMS_ORDER_PROMOTION" name="vantages" name-db="vantages" type="numeric(10,0)" primary-key="false" keep-valid="false" author="" title="积分值" description="" ID="A8A0248AA1B8024F85DEC3E0BDB3FCFF" />
    <field dbtable="CMS_ORDER_PROMOTION" name="isFreeDelivery" name-db="isFreeDelivery" type="boolean" primary-key="false" keep-valid="false" author="" title="是否免运费促销" description="" ID="7964BBE2E6C42D544FE03F629AD4338E" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_ORDER_PROMOTION" name-db="CMS_ORDER_PROMOTION" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

