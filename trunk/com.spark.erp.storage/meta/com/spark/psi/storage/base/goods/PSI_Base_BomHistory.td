<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Base_BomHistory" ID="D64168DD393F4B22E4821F317BF19311" name-db="PSI_Base_BomHistory" category="基础数据主体" title="Bom生效记录" author="" description="" packagename="com.spark.psi.storage.base.goods" name="PSI_Base_BomHistory" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Base_BomHistory" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品条目id" description="" ID="4F84FEE35BBEB5130B225D068D4868D3" />
    <field dbtable="PSI_Base_BomHistory" name="bomId" name-db="bomId" type="guid" primary-key="false" keep-valid="false" author="" title="bom表id" description="" ID="B555D8B2AD8816C912D3ADBAA7126D16" />
    <field dbtable="PSI_Base_BomHistory" name="bomNo" name-db="bomNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="bom编号" description="" ID="1ED13741A2BB43535D08BB4946A017B8" />
    <field dbtable="PSI_Base_BomHistory" name="bomCreator" name-db="bomCreator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="bom创建人" description="" ID="2DBC801CB633932DCE4A969F72BD5B33" />
    <field dbtable="PSI_Base_BomHistory" name="bomApprovor" name-db="bomApprovor" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="bom审批人" description="" ID="4322A427B7D541C62CB06E4EE7ED2BDA" />
    <field dbtable="PSI_Base_BomHistory" name="ineffector" name-db="ineffector" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="bom生效操作人" description="" ID="5FAE16C7A10201F4742525C8CDAEB666" />
    <field dbtable="PSI_Base_BomHistory" name="outeffector" name-db="outeffector" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="bom失效操作人" description="" ID="821890646A69B0C458D0F234493F79EA" />
    <field dbtable="PSI_Base_BomHistory" name="ineffectDate" name-db="ineffectDate" type="date" primary-key="false" keep-valid="false" author="" title="bom生效日期" description="" ID="2C093E18D92578EEC550390591CDA799" />
    <field dbtable="PSI_Base_BomHistory" name="outeffectDate" name-db="outeffectDate" type="date" primary-key="false" keep-valid="false" author="" title="bom失效日期" description="" ID="DCAFA7948949ED94D444B3EDF6652519" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Base_BomHistory" name-db="PSI_Base_BomHistory" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

