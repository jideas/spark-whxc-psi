<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Base_BomDetail" ID="B486DD601E769A2FEEECA64D42F1C1AA" name-db="PSI_Base_BomDetail" category="基础数据主体" title="bom明细" author="" description="" packagename="com.spark.psi.storage.base.goods" name="PSI_Base_BomDetail" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Base_BomDetail" name="bomId" name-db="bomId" type="guid" primary-key="false" keep-valid="false" author="" title="BOM标识" description="" ID="A9395A46CBEF0767046BC9E879EDFD46" />
    <field dbtable="PSI_Base_BomDetail" name="materialId" name-db="materialId" type="guid" primary-key="false" keep-valid="false" author="" title="子件id" description="" ID="E23F7A23809CB1F3126E6755166265B9" />
    <field dbtable="PSI_Base_BomDetail" name="baseCount" name-db="baseCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="基本数量" description="" ID="7B54751BA274B22B49DB5F659CF50407" />
    <field dbtable="PSI_Base_BomDetail" name="lossRate" name-db="lossRate" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="子件损耗率" description="" ID="3781FE0F3CDA803132B765A5B895B680" />
    <field dbtable="PSI_Base_BomDetail" name="realCount" name-db="realCount" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="需求数量" description="" ID="13007386CFC000938294139B666F83F4" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Base_BomDetail" name-db="PSI_Base_BomDetail" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

