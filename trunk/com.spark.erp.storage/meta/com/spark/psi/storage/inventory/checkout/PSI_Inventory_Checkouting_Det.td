<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Checkouting_Det" ID="19A8B63A38093179182107BC0C40A0FC" name-db="PSI_Inventory_Checkouting_Det" category="" title="出库单中间表明细" author="" description="" packagename="com.spark.psi.storage.inventory.checkout" name="PSI_Inventory_Checkouting_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Checkouting_Det" name="sheetId" name-db="sheetId" type="guid" primary-key="false" keep-valid="false" author="" title="出库单recid" description="" ID="8A7721E4375EFF130A6E815ACA1DEEF5" />
    <field dbtable="PSI_Inventory_Checkouting_Det" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品recid" description="" ID="31B21E46F7E3A7B9E9140963E0DE0BDC" />
    <field dbtable="PSI_Inventory_Checkouting_Det" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="86647042B0C2CE934D6454F71F7C77FF" />
    <field dbtable="PSI_Inventory_Checkouting_Det" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="57A6B887E74DA66F3865EF39ED2F9796" />
    <field dbtable="PSI_Inventory_Checkouting_Det" name="goodsName" name-db="goodsName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="B7EB3F028EBFC5799FBA8E8CFE7C1CAF" />
    <field dbtable="PSI_Inventory_Checkouting_Det" name="goodsSpec" name-db="goodsSpec" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="4E8816F5A7F386C1A3626C59D48894EE" />
    <field dbtable="PSI_Inventory_Checkouting_Det" name="unit" name-db="unit" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="536A4F77E86B88D7DF8DE262B4CD968A" />
    <field dbtable="PSI_Inventory_Checkouting_Det" name="scale" name-db="scale" type="int" primary-key="false" keep-valid="false" author="" title="商品数量精度" description="" ID="98550D42838E0B35CCEF5B172366DF53" />
    <field dbtable="PSI_Inventory_Checkouting_Det" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="商品单价" description="" ID="C40AA9A2528F9D94C2AF8F1B454F88D0" />
    <field dbtable="PSI_Inventory_Checkouting_Det" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="商品金额" description="" ID="9EEC9830F2383394298B19EF2027C805" />
    <field dbtable="PSI_Inventory_Checkouting_Det" name="planCount" name-db="planCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="预计出库数量" description="" ID="2E36AE1E02B82D4D970704990EE547C6" />
    <field dbtable="PSI_Inventory_Checkouting_Det" name="checkoutCount" name-db="checkoutCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="已出库数量" description="" ID="9DB5C6A6F7E3238DAFF1A709BB7F6B01" />
    <field dbtable="PSI_Inventory_Checkouting_Det" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人名称" description="" ID="946DEE8F8673703CA265111CBD8687AB" />
    <field dbtable="PSI_Inventory_Checkouting_Det" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="006C6E7DC8E4938F67F1C14ADA24D16E" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Checkouting_Det" name-db="PSI_Inventory_Checkouting_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

