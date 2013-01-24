<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_GoodsStoreDet" ID="622FA6510F4CABA272DCB43F642F6D1B" name-db="PSI_Inventory_GoodsStoreDet" category="" title="" author="" description="商品库存明细" packagename="com.spark.erp.storage.inventory" name="PSI_Inventory_GoodsStoreDet" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_GoodsStoreDet" name="id" name-db="id" type="guid" primary-key="false" keep-valid="false" author="" title="记录标识" description="" ID="A421EC231162B4D1C2803A19B8136BDB" />
    <field dbtable="PSI_Inventory_GoodsStoreDet" name="shelfId" name-db="shelfId" type="guid" primary-key="false" keep-valid="false" author="" title="货位标识" description="" ID="7BE958F9B0E354F6DB4C60F8F35F13AA" />
    <field dbtable="PSI_Inventory_GoodsStoreDet" name="shelfNo" name-db="shelfNo" type="int" primary-key="false" keep-valid="false" author="" title="货位编号" description="" ID="2644C897FA672A73FA2A99C77B3824C1" />
    <field dbtable="PSI_Inventory_GoodsStoreDet" name="tiersNo" name-db="tiersNo" type="int" primary-key="false" keep-valid="false" author="" title="商品所在层数" description="" ID="5ABB1470642D717F426A54BAC5A57863" />
    <field dbtable="PSI_Inventory_GoodsStoreDet" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品标识" description="" ID="CF48DDC8C8F1D27507890C6D4BA5B0D7" />
    <field dbtable="PSI_Inventory_GoodsStoreDet" name="goodsCount" name-db="goodsCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="商品数量" description="" ID="7EF7E0921C61B2E73BBA437D8E52783D" />
    <field dbtable="PSI_Inventory_GoodsStoreDet" name="produceDate" name-db="produceDate" type="date" primary-key="false" keep-valid="false" author="" title="生产日期" description="" ID="834F94E8AD0FA27A87041461223B7FC4" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_GoodsStoreDet" name-db="PSI_Inventory_GoodsStoreDet" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

