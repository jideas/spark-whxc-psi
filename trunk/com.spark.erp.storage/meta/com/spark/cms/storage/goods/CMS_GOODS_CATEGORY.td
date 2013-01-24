<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_GOODS_CATEGORY" ID="FBCF04D9DFB985E6863A8C8E81718E84" name-db="CMS_GOODS_CATEGORY" category="业务主体" title="网站商品分类" author="" description="" packagename="com.spark.cms.storage.goods" name="CMS_GOODS_CATEGORY" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_GOODS_CATEGORY" name="categoryNo" name-db="categoryNo" type="nvarchar(25)" primary-key="false" keep-valid="false" author="" title="分类编号" description="" ID="60FA9B7FB8AAC3D539C1448CCCA624EF" />
    <field dbtable="CMS_GOODS_CATEGORY" name="categoryName" name-db="categoryName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品分类名称" description="" ID="AE7346D0EF1AB0BCB09A0E5359D39D86" />
    <field dbtable="CMS_GOODS_CATEGORY" name="parentId" name-db="parentId" type="guid" primary-key="false" keep-valid="false" author="" title="父分类GUID" description="" ID="C1B377B126E7C630D3D59FC7803757BB" />
    <field dbtable="CMS_GOODS_CATEGORY" name="path" name-db="path" type="varbinary(544)" primary-key="false" keep-valid="false" author="" title="节点的路径" description="" ID="EB1E8832356A685C7F532A88AC942AE0" />
    <field dbtable="CMS_GOODS_CATEGORY" name="levelNo" name-db="levelNo" type="int" primary-key="false" keep-valid="false" author="" title="节点级数" description="" ID="AB9EA848F25F48AF3CDECBECC182F21C" />
    <field dbtable="CMS_GOODS_CATEGORY" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="数据插入时间" description="" ID="7C227BD9CE9F42D41AE08D3CE5C98778" />
    <field dbtable="CMS_GOODS_CATEGORY" name="lastModifyDate" name-db="lastModifyDate" type="date" primary-key="false" keep-valid="false" author="" title="数据最后修改时间" description="" ID="AB946CA147D6C8A5FD431CAB38F42FAB" />
    <field dbtable="CMS_GOODS_CATEGORY" name="lastModifyPerson" name-db="lastModifyPerson" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="数据最后修改人" description="" ID="028BF7380E62EF4EC405A9603FD37ADC" />
    <field dbtable="CMS_GOODS_CATEGORY" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Guid" description="" ID="9D05DF77142A1EBC284EF6551518524B" />
    <field dbtable="CMS_GOODS_CATEGORY" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="FC7E292ABBA105F3AF1D847309E48525" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_GOODS_CATEGORY" name-db="CMS_GOODS_CATEGORY" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

