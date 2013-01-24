<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_SHOPPINGCART" ID="A33481A21C26F70E59798647C7647E63" name-db="CMS_SHOPPINGCART" category="业务主体" title="购物车" author="" description="" packagename="com.spark.cms.storage.order" name="CMS_SHOPPINGCART" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_SHOPPINGCART" name="memberId" name-db="memberId" type="guid" primary-key="false" keep-valid="false" author="" title="会员id" description="" ID="91ED45708BABB7DA6951C70153EE04E1" />
    <field dbtable="CMS_SHOPPINGCART" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品id" description="" ID="1D471A4812CC6A6A80A658B801C142B5" />
    <field dbtable="CMS_SHOPPINGCART" name="goodsCount" name-db="goodsCount" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="商品数量" description="" ID="D943585968CEAD654AFB32BEA54FB15E" />
    <field dbtable="CMS_SHOPPINGCART" name="ordinal" name-db="ordinal" type="int" primary-key="false" keep-valid="false" author="" title="顺序号" description="" ID="F18B7539C2624BA95BE934648E2B93B3" />
    <field dbtable="CMS_SHOPPINGCART" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="添加时间" description="" ID="2F017F628149863E49D0DAE37AB22A41" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_SHOPPINGCART" name-db="CMS_SHOPPINGCART" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

