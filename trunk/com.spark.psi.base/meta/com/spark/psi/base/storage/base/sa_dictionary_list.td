<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="sa_dictionary_list" ID="8D3A7574E67C802134B323C07C52FFED" name-db="sa_dictionary_list" category="" title="数据字典数据存放表  " author="" description="" packagename="com.spark.psi.base.storage.base" name="sa_dictionary_list" isneedrebuild="false">
  <fields>
    <field dbtable="sa_dictionary_list" name="typeName" name-db="typeName" type="nvarchar(20)" primary-key="false" keep-valid="true" author="" title="数据字典类型名" description="" ID="970840A28124257543CD1E7F985579AD" />
    <field dbtable="sa_dictionary_list" name="code" name-db="code" type="char(20)" primary-key="false" keep-valid="true" author="" title="代码值" description="" ID="2741AAC2A4414741D968D94C7F77D445" />
    <field dbtable="sa_dictionary_list" name="codeName" name-db="codeName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="代码名称" description="" ID="955939CBC80BCC0399ADD30FEDC3CDB3" />
    <field dbtable="sa_dictionary_list" name="parentcode" name-db="parentcode" type="char(20)" primary-key="false" keep-valid="false" author="" title="父级代码" description="" ID="A9B97DA645EE86DEFE36072AF59FA3D1" />
    <field dbtable="sa_dictionary_list" name="installDate" name-db="installDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="52326EB83BE74B2C21B946A6DBFD6A01" />
    <field dbtable="sa_dictionary_list" name="installPerson" name-db="installPerson" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="B8FE42F94ADD1E44B1CAD987A101E09B" />
  </fields>
  <indexs>
    <index author="" name="INDEX_SA_DICTIONARY_LIST" name-db="INDEX_SA_DICTIONARY_LIST" title="INDEX_SA_DICTIONARY_LIST" unique="false" description="">
      <index-item fieldID="970840A28124257543CD1E7F985579AD" field="typeName" field-author="" desc="false" />
      <index-item fieldID="2741AAC2A4414741D968D94C7F77D445" field="code" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="sa_dictionary_list" name-db="sa_dictionary_list" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

