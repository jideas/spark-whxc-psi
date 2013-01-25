<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_AUTHOR_GOODS" ID="4101CDBF1CC04572B983858B37F5DC9B" name-db="SA_AUTHOR_GOODS" category="基础数据主体" title="授权商品明细表" author="" description="" packagename="com.spark.psi.storage.customer" name="SA_AUTHOR_GOODS" isneedrebuild="false">
  <fields>
    <field dbtable="SA_AUTHOR_GOODS" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="DAA58715FF07D4CA52B945D3C5F8D5EC" />
    <field dbtable="SA_AUTHOR_GOODS" name="cusproGuid" name-db="cusproGuid" type="guid" primary-key="false" keep-valid="false" author="" title="客户供应商编号" description="" ID="2AEDE73CC30143FF79EAAF2CE89121EB" />
    <field dbtable="SA_AUTHOR_GOODS" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品属性编号" description="" ID="C971C19B2C32C5110BF47F4B2A1D97BC" />
    <field dbtable="SA_AUTHOR_GOODS" name="goodsName" name-db="goodsName" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="D82E8E5AA98C92DF4DAEE51D31A2F834" />
    <field dbtable="SA_AUTHOR_GOODS" name="goodsAttr" name-db="goodsAttr" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="AA108D66727BB870E10AEF52E536FC43" />
    <field dbtable="SA_AUTHOR_GOODS" name="goodsUnit" name-db="goodsUnit" type="varchar(12)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="7D7541DA89FBF385ADCCCA23C911D03E" />
    <field dbtable="SA_AUTHOR_GOODS" name="authorPrice" name-db="authorPrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="授权价格" description="" ID="DB469A396E31F8219E507BCDA1C76DFB" />
    <field dbtable="SA_AUTHOR_GOODS" name="remark" name-db="remark" type="varchar(400)" primary-key="false" keep-valid="false" author="" title="说明" description="" ID="6A0DD28030D8BD86CAD0D86DFD5CE07B" />
    <field dbtable="SA_AUTHOR_GOODS" name="goodsStatus" name-db="goodsStatus" type="char(2)" primary-key="false" keep-valid="false" author="" title="商品状态" description="" ID="AB43CCAFCF201A8D117C049F383E67E1" />
    <field dbtable="SA_AUTHOR_GOODS" name="goodsNum" name-db="goodsNum" type="int" primary-key="false" keep-valid="false" author="" title="商品数量" description="" ID="F827DBE4F4142DD6D7F96EF8615C21BE" />
    <field dbtable="SA_AUTHOR_GOODS" name="proGoodsNo" name-db="proGoodsNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="授权商品编号" description="" ID="FC5D50003F60A8B3ACD698C3A292768C" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_AUTHOR_GOODS" name-db="SA_AUTHOR_GOODS" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

