<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="cms_floor" ID="8F898AC21D5883823A9FA7CE5A511A17" name-db="cms_floor" category="业务主体" title="楼层管理" author="" description="" packagename="com.spark.cms.storage.manager" name="cms_floor" isneedrebuild="false">
  <fields>
    <field dbtable="cms_floor" name="ordinal" name-db="ordinal" type="int" primary-key="false" keep-valid="false" author="" title="序号" description="" ID="D6A67189FB36B314144ADC58BD241508" />
    <field dbtable="cms_floor" name="title" name-db="title" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="" description="" ID="FFDFAE98AEE9F5874DBF026F8BDD2118" />
    <field dbtable="cms_floor" name="theme" name-db="theme" type="char(2)" primary-key="false" keep-valid="false" author="" title="主题" description="" ID="AE1598B08AFEBAF27F3EC196B1420ECA" />
    <field dbtable="cms_floor" name="floorType" name-db="floorType" type="char(2)" primary-key="false" keep-valid="false" author="" title="类型(广告||商品)" description="" ID="46EB38C2092F6519210CC7E834479248" />
    <field dbtable="cms_floor" name="goodsCategoryId" name-db="goodsCategoryId" type="guid" primary-key="false" keep-valid="false" author="" title="底楼对应的商品二级分类id" description="" ID="45D4DEA9853CEE32578C77128ECFEC71" />
    <field dbtable="cms_floor" name="imageUrl" name-db="imageUrl" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="底楼广告图片地址" description="" ID="528B61B60243215DAFBF525FBBA5111A" />
    <field dbtable="cms_floor" name="url" name-db="url" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="底楼广告链接地址" description="" ID="D3FD29C08C1B64A396C7BE8000EB3B8A" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="cms_floor" name-db="cms_floor" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

