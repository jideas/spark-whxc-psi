<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Checkout_Det" ID="B187A2692D1E94CD7DA3776089C2B658" name-db="PSI_Inventory_Checkout_Det" category="" title="出库单明细" author="" description="" packagename="com.spark.psi.storage.inventory.checkout" name="PSI_Inventory_Checkout_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Checkout_Det" name="sheetId" name-db="sheetId" type="guid" primary-key="false" keep-valid="false" author="" title="出库单recid" description="" ID="665B84C7FD13B68DA265BD139049C39A" />
    <field dbtable="PSI_Inventory_Checkout_Det" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品recid" description="" ID="59411D6BA303EE65A92B8DA633BE9184" />
    <field dbtable="PSI_Inventory_Checkout_Det" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="6A2F1C35D62314924BB82D8CAFBCCF41" />
    <field dbtable="PSI_Inventory_Checkout_Det" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="4F894DDFB136B37887B9A5E54B093A09" />
    <field dbtable="PSI_Inventory_Checkout_Det" name="goodsName" name-db="goodsName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="36B1AD9B64134417CE961390346E6E9E" />
    <field dbtable="PSI_Inventory_Checkout_Det" name="goodsSpec" name-db="goodsSpec" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="BE9483CF4FF2EEF17FF273F8C106CF41" />
    <field dbtable="PSI_Inventory_Checkout_Det" name="unit" name-db="unit" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="D9B23394BB1DA4531F858FEA8040D4F9" />
    <field dbtable="PSI_Inventory_Checkout_Det" name="scale" name-db="scale" type="int" primary-key="false" keep-valid="false" author="" title="商品数量精度" description="" ID="42705DDF26C6D9C461704BA82B5BDEDB" />
    <field dbtable="PSI_Inventory_Checkout_Det" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="商品单价" description="" ID="FA492937D301BC878CC41B0B87BD93C5" />
    <field dbtable="PSI_Inventory_Checkout_Det" name="avgCost" name-db="avgCost" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="库存成本" description="" ID="41A4E25371EB74E9752C9045C4B28F50" />
    <field dbtable="PSI_Inventory_Checkout_Det" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="商品金额" description="" ID="FEE62842A6C27A3B91A45271EF7BC408" />
    <field dbtable="PSI_Inventory_Checkout_Det" name="realCount" name-db="realCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="出库数量" description="" ID="E6AF11EDC4F36D7186CCAF22AABC3939" />
    <field dbtable="PSI_Inventory_Checkout_Det" name="createPerson" name-db="createPerson" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人名称" description="" ID="C54532DABF5DCF7B129932268A0FEA0F" />
    <field dbtable="PSI_Inventory_Checkout_Det" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="8C5DB8B0338B838023D9191FCC1EF158" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Checkout_Det" name-db="PSI_Inventory_Checkout_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

