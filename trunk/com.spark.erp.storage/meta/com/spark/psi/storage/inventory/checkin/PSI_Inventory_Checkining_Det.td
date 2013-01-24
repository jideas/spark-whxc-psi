<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Checkining_Det" ID="BDD5CF9439F690F9D425A356EF0EF71C" name-db="PSI_Inventory_Checkining_Det" category="基础数据主体" title="中间表明细" author="" description="" packagename="com.spark.psi.storage.inventory.checkin" name="PSI_Inventory_Checkining_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Checkining_Det" name="sheetId" name-db="sheetId" type="guid" primary-key="false" keep-valid="false" author="" title="入库单recid" description="" ID="1F28C8624F9DB915FF078EA05311C67C" />
    <field dbtable="PSI_Inventory_Checkining_Det" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品recid" description="" ID="CE26D5CD20F1B00BE294E17D9C265E14" />
    <field dbtable="PSI_Inventory_Checkining_Det" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="E1BFBF32526E87C611A7F5025F3851FE" />
    <field dbtable="PSI_Inventory_Checkining_Det" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="74D37CE4B7913AB5462C1CB8F8C87A5A" />
    <field dbtable="PSI_Inventory_Checkining_Det" name="goodsName" name-db="goodsName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="84CE2F4206CD0AFF06D110D164F80C54" />
    <field dbtable="PSI_Inventory_Checkining_Det" name="goodsSpec" name-db="goodsSpec" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="35633A674E5ACF32E6F67EF622AB9DDF" />
    <field dbtable="PSI_Inventory_Checkining_Det" name="unit" name-db="unit" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="9D173FC09C2A636B4C8C076281744D45" />
    <field dbtable="PSI_Inventory_Checkining_Det" name="scale" name-db="scale" type="int" primary-key="false" keep-valid="false" author="" title="商品数量精度" description="" ID="F975A4C86E84716F8F3BE43BFDC42EAB" />
    <field dbtable="PSI_Inventory_Checkining_Det" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="商品单价" description="" ID="35A7596A506D07729293B21DBF9185CB" />
    <field dbtable="PSI_Inventory_Checkining_Det" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="商品金额" description="" ID="0F914EF08CCF4ADB20521CADDAED9895" />
    <field dbtable="PSI_Inventory_Checkining_Det" name="planCount" name-db="planCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="预计入库数量" description="" ID="3900F5197D3D2221974BBABE586E256F" />
    <field dbtable="PSI_Inventory_Checkining_Det" name="checkinCount" name-db="checkinCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="已入库数量" description="" ID="7CE7EC530A92389B201E1A9A86FD1BB1" />
    <field dbtable="PSI_Inventory_Checkining_Det" name="inspectCount" name-db="inspectCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="待检数量" description="" ID="AFCE04B17FDDEEA42F21167D2F7D3E02" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Checkining_Det" name-db="PSI_Inventory_Checkining_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

