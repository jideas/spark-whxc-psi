<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="sa_dictionary_manage" ID="6BCDDE3A8E2BE7F9395D7AA79EBB904A" name-db="sa_dictionary_manage" category="" title="数据字典管理表 " author="" description=" 存放所有的数据字典类型" packagename="com.spark.psi.base.storage.base" name="sa_dictionary_manage" isneedrebuild="false">
  <fields>
    <field dbtable="sa_dictionary_manage" name="typeName" name-db="typeName" type="nvarchar(20)" primary-key="true" keep-valid="true" author="" title="数据字典类型名" description="" ID="1A6BF7C245C60B599C5691CEC9427016" />
    <field dbtable="sa_dictionary_manage" name="typeTitle" name-db="typeTitle" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="数据字典标题" description="" ID="3904984CE9A5B3173DC90904DB76799E" />
    <field dbtable="sa_dictionary_manage" name="installDate" name-db="installDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="1B514A30C36B3D90BB140C040787EE70" />
    <field dbtable="sa_dictionary_manage" name="installPerson" name-db="installPerson" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="D68388B84BA800A8228F29249F6190CC" />
    <field dbtable="sa_dictionary_manage" name="parentname" name-db="parentname" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="父级名称" description="" ID="AB96A276FA8E8FE311C6763D89294B7D" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="sa_dictionary_manage" name-db="sa_dictionary_manage" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

