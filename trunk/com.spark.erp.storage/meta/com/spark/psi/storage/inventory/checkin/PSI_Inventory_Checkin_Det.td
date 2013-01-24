<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Checkin_Det" ID="CC6008C2C6054EA9E7C5E4B90865EC5A" name-db="PSI_Inventory_Checkin_Det" category="基础数据主体" title="入库明细" author="" description="" packagename="com.spark.psi.storage.inventory.checkin" name="PSI_Inventory_Checkin_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Checkin_Det" name="sheetId" name-db="sheetId" type="guid" primary-key="false" keep-valid="false" author="" title="入库单recid" description="" ID="9D29E4C44C175D3D168F8A43956117D0" />
    <field dbtable="PSI_Inventory_Checkin_Det" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品recid" description="" ID="1F0A8B5A499B88389ECD5B399450321C" />
    <field dbtable="PSI_Inventory_Checkin_Det" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="CADB4D49DD77CC0618CB936B2377C4DD" />
    <field dbtable="PSI_Inventory_Checkin_Det" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="CFC47436399A7E75E84E24CAADB8B55D" />
    <field dbtable="PSI_Inventory_Checkin_Det" name="goodsName" name-db="goodsName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="BA4B9EFADEB13BC197213D41CF7351F2" />
    <field dbtable="PSI_Inventory_Checkin_Det" name="goodsSpec" name-db="goodsSpec" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="1B2179B736E4D8CDF70D07C49590F02E" />
    <field dbtable="PSI_Inventory_Checkin_Det" name="unit" name-db="unit" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="2A7B8B70C5C53FF29F752F77EEFC8180" />
    <field dbtable="PSI_Inventory_Checkin_Det" name="scale" name-db="scale" type="int" primary-key="false" keep-valid="false" author="" title="商品数量精度" description="" ID="42733C8D6D724CE0E34857AE674566C0" />
    <field dbtable="PSI_Inventory_Checkin_Det" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="商品单价" description="" ID="F9B3381336FA22BC745BA9D8286DE930" />
    <field dbtable="PSI_Inventory_Checkin_Det" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="商品金额" description="" ID="E547F61FFA4CA3910575E32CD81951F7" />
    <field dbtable="PSI_Inventory_Checkin_Det" name="realCount" name-db="realCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="入库数量" description="" ID="1DF8074EC06D796B0137322A189EC846" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Checkin_Det" name-db="PSI_Inventory_Checkin_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

