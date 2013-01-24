<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_GoodsStore" ID="D6BA535B20D8C7AF331AD4DD1287245A" name-db="PSI_Inventory_GoodsStore" category="" title="" author="" description="" packagename="com.spark.erp.storage.inventory" name="PSI_Inventory_GoodsStore" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_GoodsStore" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库标识" description="" ID="FAA848675E26BDF5342A4BA42C1E4639" />
    <field dbtable="PSI_Inventory_GoodsStore" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品标识" description="" ID="94BBEFAA0A85BA245D7867A15386ABD4" />
    <field dbtable="PSI_Inventory_GoodsStore" name="initCount" name-db="initCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="初始化数量" description="" ID="4CF1FF7F083647DE0CE801414B17813F" />
    <field dbtable="PSI_Inventory_GoodsStore" name="initAmount" name-db="initAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="初始化金额" description="" ID="E99045A065619855C42FF2F0EE06718B" />
    <field dbtable="PSI_Inventory_GoodsStore" name="initCost" name-db="initCost" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="初始化单位成本" description="" ID="66691929BC4EFD2888A4C72AE3C41F0B" />
    <field dbtable="PSI_Inventory_GoodsStore" name="goodsName" name-db="goodsName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="17AB12BDC7D6E2F3244A479408B6FF3B" />
    <field dbtable="PSI_Inventory_GoodsStore" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="C6C26391AACE0702AE836F374C4964E8" />
    <field dbtable="PSI_Inventory_GoodsStore" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="EB1ADA746966D9078260A91FD7553EC2" />
    <field dbtable="PSI_Inventory_GoodsStore" name="goodsCount" name-db="goodsCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="库存商品数量" description="" ID="F0CD25BD37934642520913D50CC47EB4" />
    <field dbtable="PSI_Inventory_GoodsStore" name="goodsAmount" name-db="goodsAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="库存商品金额" description="" ID="616EEAC22BF83FC500019EE2B2B20565" />
    <field dbtable="PSI_Inventory_GoodsStore" name="shelfLife" name-db="shelfLife" type="int" primary-key="false" keep-valid="false" author="" title="保质期" description="" ID="6ADAFEA6FF6172169C1B498F29D09FEC" />
    <field dbtable="PSI_Inventory_GoodsStore" name="goodsUnit" name-db="goodsUnit" type="char(2)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="CED8244F28AE173E8145E7171AD8C462" />
    <field dbtable="PSI_Inventory_GoodsStore" name="goodsSpec" name-db="goodsSpec" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="FBC7F0BD6414D769CE8936F5A6E0866B" />
    <field dbtable="PSI_Inventory_GoodsStore" name="upperLimitCount" name-db="upperLimitCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="库存数量上限" description="" ID="9DFA0F9C9C33FDDECF60C8E7AC281272" />
    <field dbtable="PSI_Inventory_GoodsStore" name="lowerLimitCount" name-db="lowerLimitCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="库存数量下限" description="" ID="272DABBF977A103A464CCD271623331D" />
    <field dbtable="PSI_Inventory_GoodsStore" name="upperLimitAmount" name-db="upperLimitAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="库存金额上限" description="" ID="CAD126D4A821BB455739D2D7DDC5F6E5" />
    <field dbtable="PSI_Inventory_GoodsStore" name="inventoryType" name-db="inventoryType" type="char(2)" primary-key="false" keep-valid="false" author="" title="库存类型" description="" ID="C561ADF3169291EEBD85C0BD2C0D2154" />
    <field dbtable="PSI_Inventory_GoodsStore" name="lockedCount" name-db="lockedCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="锁定库存数量" description="" ID="4B3C4ADB26BB906148D94FD515C3C7E7" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_GoodsStore" name-db="PSI_Inventory_GoodsStore" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

