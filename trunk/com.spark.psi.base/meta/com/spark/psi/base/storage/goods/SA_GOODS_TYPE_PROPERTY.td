<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_GOODS_TYPE_PROPERTY" ID="FD36B37039376E360F3A3048B93370D2" name-db="SA_GOODS_TYPE_PROPERTY" category="" title="商品分类属性表 " author="" description="存放商品分类的属性" packagename="com.spark.psi.base.storage.goods" name="SA_GOODS_TYPE_PROPERTY" isneedrebuild="false">
  <fields>
    <field dbtable="SA_GOODS_TYPE_PROPERTY" name="goodsTypeGuid" name-db="goodsTypeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品分类GUID" description="" ID="EEFC64B0650D0FF049C857D87A5BB95F" />
    <field dbtable="SA_GOODS_TYPE_PROPERTY" name="propertyName" name-db="propertyName" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="商品分类属性名称" description="" ID="2D4A907573F00FA17A33A406096D3ADD" />
    <field dbtable="SA_GOODS_TYPE_PROPERTY" name="propertyInputType" name-db="propertyInputType" type="char(2)" primary-key="false" keep-valid="false" author="" title="属性录入类型" description="枚举（01手动录入、02下拉选择）" ID="3CE88E8696753E5680400AB3606298A4" />
    <field dbtable="SA_GOODS_TYPE_PROPERTY" name="propertyOrder" name-db="propertyOrder" type="int" primary-key="false" keep-valid="false" author="" title="属性显示顺序" description="数据为从1开始的数字" ID="EAB5726514A6474EE2A4CB9FEE33CAC4" />
  </fields>
  <indexs>
    <index author="" name="SA_GOODS_TYPE_TYPEGUID" name-db="SA_GOODS_TYPE_TYPEGUID" title="SA_GOODS_TYPE_TYPEGUID" unique="false" description="">
      <index-item fieldID="EEFC64B0650D0FF049C857D87A5BB95F" field="goodsTypeGuid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_GOODS_TYPE_PROPERTY" name-db="SA_GOODS_TYPE_PROPERTY" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

