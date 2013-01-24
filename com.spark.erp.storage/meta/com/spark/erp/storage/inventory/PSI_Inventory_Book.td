<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Book" ID="DBBDC14CA6CB84A802442478A63931F8" name-db="PSI_Inventory_Book" category="" title="" author="" description="库存台账" packagename="com.spark.erp.storage.inventory" name="PSI_Inventory_Book" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Book" name="storeGuid" name-db="storeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="仓库GUID" description="" ID="12B51E1CF2259F1FA2FAE1CA6E0910C9" />
    <field dbtable="PSI_Inventory_Book" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="D9F014C4598E1D385F5F7EE1F2EACACC" />
    <field dbtable="PSI_Inventory_Book" name="beginStoreCount" name-db="beginStoreCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="期初库存数量" description="" ID="D92EAA3528B3ADCC32A654AED19BDB74" />
    <field dbtable="PSI_Inventory_Book" name="beginStoreMoney" name-db="beginStoreMoney" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="期初库存金额" description="" ID="9DDCD219FAF6DBEBAF3238F0B39B1990" />
    <field dbtable="PSI_Inventory_Book" name="instoCount" name-db="instoCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="入库数量" description="" ID="984B88366E20FC33AC72607CC0292909" />
    <field dbtable="PSI_Inventory_Book" name="instoAmount" name-db="instoAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="入库金额" description="" ID="E9D6DBCBC987FF9C382AF0896CD49921" />
    <field dbtable="PSI_Inventory_Book" name="outstoCount" name-db="outstoCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="出库数量" description="" ID="D8122F2AB1507FA810D2809B61737A29" />
    <field dbtable="PSI_Inventory_Book" name="outstoAmount" name-db="outstoAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="出库金额" description="" ID="01BEDB0BADE4AEC156DA6DC03FFA9473" />
    <field dbtable="PSI_Inventory_Book" name="endStoreCount" name-db="endStoreCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="期末库存数量" description="" ID="CEA43EE8498BD5E64AE235DC62324F33" />
    <field dbtable="PSI_Inventory_Book" name="endStoreMoney" name-db="endStoreMoney" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="期末库存金额" description="" ID="4A64CA016C96B446BD8C430F104080AA" />
    <field dbtable="PSI_Inventory_Book" name="createdDate" name-db="createdDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="896C632A3CE63496829C0CF2CA11830B" />
    <field dbtable="PSI_Inventory_Book" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="762C0F4A0F051E64CAC2B2F08E095E3D" />
    <field dbtable="PSI_Inventory_Book" name="goodsAttr" name-db="goodsAttr" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="6E78D56862D6219D19803B1A0BD85B01" />
    <field dbtable="PSI_Inventory_Book" name="goodsUnit" name-db="goodsUnit" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="82AA1D825950A95430FCE628B7E4B8FA" />
    <field dbtable="PSI_Inventory_Book" name="goodsTypeGuid" name-db="goodsTypeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品分类GUID" description="" ID="07FB49ADC2D6D75CBF9D7E5CBC7FCD36" />
    <field dbtable="PSI_Inventory_Book" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编号" description="" ID="834E0EEE4F838742F7FE5ECA412890CD" />
    <field dbtable="PSI_Inventory_Book" name="goodsScale" name-db="goodsScale" type="int" primary-key="false" keep-valid="false" author="" title="商品精度" description="" ID="33570E9EC2AEED0CC6FE3BBF9C1CDDD6" />
    <field dbtable="PSI_Inventory_Book" name="dateNo" name-db="dateNo" type="int" primary-key="false" keep-valid="false" author="" title="天号" description="" ID="672102D9C345A3C931C043B5DE42613B" />
    <field dbtable="PSI_Inventory_Book" name="monthNo" name-db="monthNo" type="int" primary-key="false" keep-valid="false" author="" title="月号" description="" ID="2708CA3CE5802DF872285CD4F2A7E80F" />
    <field dbtable="PSI_Inventory_Book" name="quarter" name-db="quarter" type="int" primary-key="false" keep-valid="false" author="" title="季号" description="" ID="E7CF80669793C78325871BB0C3B56DDF" />
    <field dbtable="PSI_Inventory_Book" name="yearNo" name-db="yearNo" type="int" primary-key="false" keep-valid="false" author="" title="年号" description="" ID="32208D495C19335CEF2752B91F021BF7" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Book" name-db="PSI_Inventory_Book" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

