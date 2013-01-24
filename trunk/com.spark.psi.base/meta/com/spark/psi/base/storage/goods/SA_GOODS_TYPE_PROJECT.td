<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_GOODS_TYPE_PROJECT" ID="567B1F9D3B9C7CFB847ECC55913EE3FB" name-db="SA_GOODS_TYPE_PROJECT" category="" title="商品分类属性项目表 " author="" description="存放商品分类的属性项目" packagename="com.spark.psi.base.storage.goods" name="SA_GOODS_TYPE_PROJECT" isneedrebuild="false">
  <fields>
    <field dbtable="SA_GOODS_TYPE_PROJECT" name="propertyGuid" name-db="propertyGuid" type="guid" primary-key="false" keep-valid="false" author="" title="属性GUID" description="" ID="FEC91490F86845EF526C995A00048C0F" />
    <field dbtable="SA_GOODS_TYPE_PROJECT" name="projectValue" name-db="projectValue" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="项目值" description="" ID="442D355C75920B966E8C913CD1683C10" />
    <field dbtable="SA_GOODS_TYPE_PROJECT" name="projectKey" name-db="projectKey" type="char(2)" primary-key="false" keep-valid="false" author="" title="项目键" description="" ID="0844B80BC228191B73E660B2ED5AF130" />
    <field dbtable="SA_GOODS_TYPE_PROJECT" name="projectOrder" name-db="projectOrder" type="int" primary-key="false" keep-valid="false" author="" title="属性项目显示顺序" description="数据为从1开始的数字" ID="D1B4D53351C65AC0EDC8F0C266F36C70" />
  </fields>
  <indexs>
    <index author="" name="SA_GOODS_TYPE_PROJECT" name-db="SA_GOODS_TYPE_PROJECT" title="SA_GOODS_TYPE_PROJECT" unique="false" description="">
      <index-item fieldID="FEC91490F86845EF526C995A00048C0F" field="propertyGuid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_GOODS_TYPE_PROJECT" name-db="SA_GOODS_TYPE_PROJECT" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

