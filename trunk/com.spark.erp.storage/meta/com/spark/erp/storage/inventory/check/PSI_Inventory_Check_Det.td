<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Check_Det" ID="4F6038EF1E00EB430EF0A9E4D4810849" name-db="PSI_Inventory_Check_Det" category="" title="" author="" description="盘点明细" packagename="com.spark.erp.storage.inventory.check" name="PSI_Inventory_Check_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Check_Det" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="00A46124A448C8905C3CA99C2EDE2F02" />
    <field dbtable="PSI_Inventory_Check_Det" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="说明" description="" ID="0FB922A9C6DB462DB1E6D0D5E0289EE2" />
    <field dbtable="PSI_Inventory_Check_Det" name="checkOrdGuid" name-db="checkOrdGuid" type="guid" primary-key="false" keep-valid="false" author="" title="盘点单编号" description="" ID="D96F1EF7829C1ABDBE6E310262AA0D15" />
    <field dbtable="PSI_Inventory_Check_Det" name="carryCount" name-db="carryCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="账面数量" description="" ID="D4A31FE916FF32A944E7F7F2B2F68B95" />
    <field dbtable="PSI_Inventory_Check_Det" name="realCount" name-db="realCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="实盘数量" description="" ID="03EC047E125A9B90EEBC0A622ECAF12F" />
    <field dbtable="PSI_Inventory_Check_Det" name="realAmount" name-db="realAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="实盘金额" description="" ID="C9B1FA08C0BE2BEFDC52EC8C6339A4B0" />
    <field dbtable="PSI_Inventory_Check_Det" name="createPerson" name-db="createPerson" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="912B079D62D0848D34386DFE42E6CF19" />
    <field dbtable="PSI_Inventory_Check_Det" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="570B970E16B521715C7C16821AC1F92F" />
    <field dbtable="PSI_Inventory_Check_Det" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="47E05CF98D825AA8FF4B0715065967A0" />
    <field dbtable="PSI_Inventory_Check_Det" name="goodsSpec" name-db="goodsSpec" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="82E3CCECD5D85F2D18F0A4CAD595DCA2" />
    <field dbtable="PSI_Inventory_Check_Det" name="unit" name-db="unit" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="5D70BBB585F4F21D9356C660E0CD9911" />
    <field dbtable="PSI_Inventory_Check_Det" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编号" description="" ID="AB3379C8C1CA45C2420AF8FF4AAB8064" />
    <field dbtable="PSI_Inventory_Check_Det" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="53F4BA2140D7A4650479E452052C4476" />
    <field dbtable="PSI_Inventory_Check_Det" name="goodsTypeGuid" name-db="goodsTypeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品分类GUID" description="" ID="46AC6616021B6108C4050A7873A678B7" />
    <field dbtable="PSI_Inventory_Check_Det" name="newGoods" name-db="newGoods" type="char(2)" primary-key="false" keep-valid="false" author="" title="是否为新增商品" description="" ID="1C79E233CD1D2C97782274E9044707FE" />
    <field dbtable="PSI_Inventory_Check_Det" name="goodsScale" name-db="goodsScale" type="int" primary-key="false" keep-valid="false" author="" title="商品精度" description="" ID="09D278FAA07583B1772063992F3A8A3E" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Check_Det" name-db="PSI_Inventory_Check_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

