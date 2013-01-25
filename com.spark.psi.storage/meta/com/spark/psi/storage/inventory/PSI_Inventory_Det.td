<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Det" ID="9F76575FE9E8016A38C2A2C72B781CB9" name-db="PSI_Inventory_Det" category="基础数据主体" title="库存明细" author="" description="" packagename="com.spark.psi.storage.inventory" name="PSI_Inventory_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Det" name="shelfId" name-db="shelfId" type="guid" primary-key="false" keep-valid="false" author="" title="货位标识" description="" ID="91FB0924625310092420C02ECBA71440" />
    <field dbtable="PSI_Inventory_Det" name="shelfNo" name-db="shelfNo" type="int" primary-key="false" keep-valid="false" author="" title="货位编号" description="" ID="2D8E770B1878968C98B1B7A66D03B6CE" />
    <field dbtable="PSI_Inventory_Det" name="tiersNo" name-db="tiersNo" type="int" primary-key="false" keep-valid="false" author="" title="存货所在层数" description="" ID="D2E55F1B21316818B3C9446BDAB6B51B" />
    <field dbtable="PSI_Inventory_Det" name="stockId" name-db="stockId" type="guid" primary-key="false" keep-valid="false" author="" title="存货标识" description="" ID="FB65E76F540E21544EDA0D92972EE637" />
    <field dbtable="PSI_Inventory_Det" name="count" name-db="count" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="存货数量" description="" ID="9828FAD5E5AC767D29E74FB07E2DB16A" />
    <field dbtable="PSI_Inventory_Det" name="produceDate" name-db="produceDate" type="date" primary-key="false" keep-valid="false" author="" title="生产日期" description="" ID="E6E130F3DFADBBD7B9B569AE1D7632C9" />
    <field dbtable="PSI_Inventory_Det" name="inventoryId" name-db="inventoryId" type="guid" primary-key="false" keep-valid="false" author="" title="库存id" description="" ID="FE7929E2E30CB2AB48A17C7D955A0D15" />
    <field dbtable="PSI_Inventory_Det" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库id" description="" ID="F86AC43050DEBE4C69A9E839787BA4CA" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Det" name-db="PSI_Inventory_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

