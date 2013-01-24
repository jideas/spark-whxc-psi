<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_INVENTORY_REPORTLOSS_SHELF" ID="AFCC9F8DAED61239915B7F49244BC6E1" name-db="PSI_INVENTORY_REPORTLOSS_SHELF" category="基础数据主体" title="报损单货位信息" author="" description="" packagename="com.spark.erp.storage.inventory.reportloss" name="PSI_INVENTORY_REPORTLOSS_SHELF" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_INVENTORY_REPORTLOSS_SHELF" name="reportLossItemId" name-db="reportLossItemId" type="guid" primary-key="false" keep-valid="false" author="" title="报损单条目标识" description="" ID="BDDEDBC4A97BCBB10E84EFE0A7B1B2A0" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_SHELF" name="sheetId" name-db="sheetId" type="guid" primary-key="false" keep-valid="false" author="" title="报损单标识" description="" ID="21E14216EFF3AB911D016EF8425BBA85" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_SHELF" name="stockId" name-db="stockId" type="guid" primary-key="false" keep-valid="false" author="" title="商品标识" description="" ID="A5C4734A76E4EEBC9284A87909DF0C75" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_SHELF" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库标识" description="" ID="4D99E517A1AAE5AFD015EFAB8631DAFB" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_SHELF" name="count" name-db="count" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="报损数量" description="" ID="AB3D8D8778063EF5C9A777B2FF6A0B90" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_SHELF" name="shelfId" name-db="shelfId" type="guid" primary-key="false" keep-valid="false" author="" title="货位标识" description="" ID="4A2462E93A9BCDE1F706C80A02063E5B" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_SHELF" name="shelfNo" name-db="shelfNo" type="int" primary-key="false" keep-valid="false" author="" title="货位编号" description="" ID="A692AE7D0652BC405018BB8BDADBFF7A" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_SHELF" name="tiersNo" name-db="tiersNo" type="int" primary-key="false" keep-valid="false" author="" title="层数" description="" ID="BF93B7E9E2B0CF3D6ED06143A564C799" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_SHELF" name="produceDate" name-db="produceDate" type="date" primary-key="false" keep-valid="false" author="" title="生产日期" description="" ID="A2F21CF58317DF1123E6BC7175B433A0" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_INVENTORY_REPORTLOSS_SHELF" name-db="PSI_INVENTORY_REPORTLOSS_SHELF" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

