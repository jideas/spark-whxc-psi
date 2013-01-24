<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_CORE_TREE" ID="43016467B29504C35E02D3783F0CFDB0" name-db="SA_CORE_TREE" category="" title="级次结构表 " author="" description="" packagename="com.spark.psi.base.storage.base" name="SA_CORE_TREE" isneedrebuild="false">
  <fields>
    <field dbtable="SA_CORE_TREE" name="PATH" name-db="PATH" type="varbinary(544)" primary-key="false" keep-valid="false" author="" title="" description="" ID="8CED5C7D1169001CAEC0BDA31E489062" />
    <field dbtable="SA_CORE_TREE" name="STAUTS" name-db="STAUTS" type="char(1)" primary-key="false" keep-valid="false" author="" title="" description="" ID="15DAA80291AAFAC113939F1CE61C85CF" />
    <field dbtable="SA_CORE_TREE" name="tenantId" name-db="tenantId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="919325F44A36BDF39B16332FFDDAE721" />
  </fields>
  <indexs>
    <index author="" name="INDEX_CORE_TREE" name-db="INDEX_CORE_TREE" title="INDEX_CORE_TREE" unique="true" description="">
      <index-item fieldID="8CED5C7D1169001CAEC0BDA31E489062" field="PATH" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_CORE_TREE" name-db="SA_CORE_TREE" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

