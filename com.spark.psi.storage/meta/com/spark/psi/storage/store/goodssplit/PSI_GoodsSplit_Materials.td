<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_GoodsSplit_Materials" ID="6D13852AFB92E479D49AE6480B1E51D1" name-db="PSI_GoodsSplit_Materials" category="业务主体" title="拆分材料明细" author="" description="" packagename="com.spark.psi.storage.store.goodssplit" name="PSI_GoodsSplit_Materials" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_GoodsSplit_Materials" name="materialsId" name-db="materialsId" type="guid" primary-key="false" keep-valid="false" author="" title="材料GUID" description="" ID="A631C629F2549E5CDB45103BECB9D134" />
    <field dbtable="PSI_GoodsSplit_Materials" name="materialsName" name-db="materialsName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="材料名称" description="" ID="D10827604F231CE21129157D333C6004" />
    <field dbtable="PSI_GoodsSplit_Materials" name="spec" name-db="spec" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="规格" description="" ID="813669F1EB78E26B7CF79047BB042899" />
    <field dbtable="PSI_GoodsSplit_Materials" name="unit" name-db="unit" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="C3B6C6E67FE3532E785C042A19178772" />
    <field dbtable="PSI_GoodsSplit_Materials" name="standardCount" name-db="standardCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="参考数量" description="" ID="B9B27D7B53434817CDC927F7816E08D3" />
    <field dbtable="PSI_GoodsSplit_Materials" name="mcount" name-db="mcount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="AEA71600E6FFFC1C42B6CFC8910C87B3" />
    <field dbtable="PSI_GoodsSplit_Materials" name="splitSheetId" name-db="splitSheetId" type="guid" primary-key="false" keep-valid="false" author="" title="单据id" description="" ID="C863FD9ADC9A1FB2F5C5757BAB60467D" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_GoodsSplit_Materials" name-db="PSI_GoodsSplit_Materials" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

