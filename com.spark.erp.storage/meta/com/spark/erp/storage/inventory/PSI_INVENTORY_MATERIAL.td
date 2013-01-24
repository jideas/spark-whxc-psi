<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_INVENTORY_MATERIAL" ID="306F1EE9953B52DE043CEB11B264A2C8" name-db="PSI_INVENTORY_MATERIAL" category="" title="" author="" description="" packagename="com.spark.erp.storage.inventory" name="PSI_INVENTORY_MATERIAL" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_INVENTORY_MATERIAL" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库标识" description="" ID="0DE7B938486EC4EB1729C6AA02CC1BC8" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="materialId" name-db="materialId" type="guid" primary-key="false" keep-valid="false" author="" title="材料标识" description="" ID="E914D523472EE94D125208B49B0E6B97" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="initCount" name-db="initCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="初始化数量" description="" ID="5560C47FB1D175CD860C0F1667ABEB8F" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="initAmount" name-db="initAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="初始化金额" description="" ID="9CB03A34BEE671B5B710340F634A83E5" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="initCost" name-db="initCost" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="初始化单位成本" description="" ID="8C8E079A3FEE5B41D89CDAE26A9DB204" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="materialName" name-db="materialName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="材料名称" description="" ID="DE486B70AACA0ACA53A51D09D7E3DB1A" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="materialCode" name-db="materialCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="材料编码" description="" ID="DA3E64795044D610D3AF13AEDAE15706" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="materialNumber" name-db="materialNumber" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="材料条码" description="" ID="2D90243F123421EBC7759225D66FA5B6" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="materialCount" name-db="materialCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="库存材料数量" description="" ID="84A59FFE74BF91EC4E836346F7B56B95" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="materialAmount" name-db="materialAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="库存材料金额" description="" ID="2008700453B137BA7BB1D2E66973B6D3" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="shelfLife" name-db="shelfLife" type="int" primary-key="false" keep-valid="false" author="" title="保质期" description="" ID="0BC679D70B7036EA2E59613659ECA176" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="materialUnit" name-db="materialUnit" type="char(2)" primary-key="false" keep-valid="false" author="" title="材料单位" description="" ID="FC98E2B9BD62917DB74E0567F5A81B6B" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="materialSpec" name-db="materialSpec" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="材料规格" description="" ID="D09B43F1BD0913AE01804C9F1A140F65" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="onWayCount" name-db="onWayCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="采购在途数量" description="" ID="AD22C8EA08628103080FC5C2F1700479" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="toDeliverCount" name-db="toDeliverCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="发货需求" description="" ID="6100A3FACC985B1605D82B79283163F4" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="upperLimitCount" name-db="upperLimitCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="库存数量上限" description="" ID="87F8F374C3DE79D7A9C6527D7F23CDFE" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="lowerLimitCount" name-db="lowerLimitCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="库存数量下限" description="" ID="72644E31BE5ABD4D0C2FD02C29A7A7BD" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="upperLimitAmount" name-db="upperLimitAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="库存金额上限" description="" ID="F68509FA86C2263C70CABBEBAEE9AE1E" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="inventoryType" name-db="inventoryType" type="char(2)" primary-key="false" keep-valid="false" author="" title="库存类型" description="" ID="BA488EC5AC4C21004F0E6C9581C69EA5" />
    <field dbtable="PSI_INVENTORY_MATERIAL" name="lockedCount" name-db="lockedCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="锁定库存数量" description="" ID="C60210A13340E209997424190827D1F0" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_INVENTORY_MATERIAL" name-db="PSI_INVENTORY_MATERIAL" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

