<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Allocate_Det" ID="CB50256ACD677C66A5392703A47459DF" name-db="PSI_Inventory_Allocate_Det" category="" title="" author="" description="库存调拔明细" packagename="com.spark.erp.storage.inventory.allocate" name="PSI_Inventory_Allocate_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Allocate_Det" name="allocateId" name-db="allocateId" type="guid" primary-key="false" keep-valid="false" author="" title="调拨单号" description="" ID="B6D37FD6CB8DB6958509217E3F5900D7" />
    <field dbtable="PSI_Inventory_Allocate_Det" name="stockId" name-db="stockId" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="68D926C87A81792AA1DE3EB9DB39F84D" />
    <field dbtable="PSI_Inventory_Allocate_Det" name="stockName" name-db="stockName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="021269021D96886FEEC6B2134832B04E" />
    <field dbtable="PSI_Inventory_Allocate_Det" name="stockCode" name-db="stockCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编号" description="" ID="D0BBF3F45790B13CE7717429E42C68E1" />
    <field dbtable="PSI_Inventory_Allocate_Det" name="stockNo" name-db="stockNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="0642C9428DEADA93A595050D60EBB30F" />
    <field dbtable="PSI_Inventory_Allocate_Det" name="stockSpec" name-db="stockSpec" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="DA635CAD6007B591CF393F5A58513CE4" />
    <field dbtable="PSI_Inventory_Allocate_Det" name="unit" name-db="unit" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="4843414E99DCA229B5CEA3C48EB05277" />
    <field dbtable="PSI_Inventory_Allocate_Det" name="ableCount" name-db="ableCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="可用库存" description="" ID="2C18A10B48DA52A72FFC73A42262205B" />
    <field dbtable="PSI_Inventory_Allocate_Det" name="allocateCount" name-db="allocateCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="实际调拨数量" description="" ID="8E0B3553307CC1F65DFB03759A89C147" />
    <field dbtable="PSI_Inventory_Allocate_Det" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人ID" description="" ID="9BAAB55FB6AC578953CF8F8B004B7066" />
    <field dbtable="PSI_Inventory_Allocate_Det" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="6381BABCEB3C32E699EC6C9EEF1C5AD4" />
    <field dbtable="PSI_Inventory_Allocate_Det" name="stockScale" name-db="stockScale" type="int" primary-key="false" keep-valid="false" author="" title="精度" description="" ID="A40BE5944354F10BE0400577D1EC8119" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Allocate_Det" name-db="PSI_Inventory_Allocate_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

