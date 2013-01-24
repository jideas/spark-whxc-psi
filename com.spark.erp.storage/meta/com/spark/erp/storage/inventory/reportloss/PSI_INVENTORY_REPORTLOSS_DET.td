<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_INVENTORY_REPORTLOSS_DET" ID="4A2F7CAA07BA10F36C1F6ABA63B4E545" name-db="PSI_INVENTORY_REPORTLOSS_DET" category="" title="报损单明细" author="" description="" packagename="com.spark.erp.storage.inventory.reportloss" name="PSI_INVENTORY_REPORTLOSS_DET" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_INVENTORY_REPORTLOSS_DET" name="reportLossSheetId" name-db="reportLossSheetId" type="guid" primary-key="false" keep-valid="false" author="" title="报损单标识" description="" ID="3D7FFF877A27635A494F2F8A80B12661" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_DET" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品标识" description="" ID="3EFE8B436058EDCCAB168FFEB1904748" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_DET" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="C1B68FE28E4F6A8A45611B96217DB047" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_DET" name="goodsNumber" name-db="goodsNumber" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="9A31F9E9F44CC79876CD16744CE69A56" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_DET" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="F732ACF97407C40FED99A1389A462F00" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_DET" name="goodsUnit" name-db="goodsUnit" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="60FEF8E8F973662A0BA08F248744F700" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_DET" name="goodsSpec" name-db="goodsSpec" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="B8DA9A96BD491C668EEA8BA4D39CE15C" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_DET" name="scale" name-db="scale" type="int" primary-key="false" keep-valid="false" author="" title="" description="" ID="4756BA984F001809B5B6D1CBBAD8A8DD" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_DET" name="reportCount" name-db="reportCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="报损数量" description="" ID="4E041830D155958E1D85FBD6EC6FF9A2" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_DET" name="reportReason" name-db="reportReason" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="报损原因" description="" ID="A63F996EA106A33EDF888F5BD98EB689" />
    <field dbtable="PSI_INVENTORY_REPORTLOSS_DET" name="reportType" name-db="reportType" type="char(2)" primary-key="false" keep-valid="false" author="" title="报损类型(商品or材料，目前只支持材料)" description="" ID="4676E51BCF0F257DB21E9ACE9904EC2F" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_INVENTORY_REPORTLOSS_DET" name-db="PSI_INVENTORY_REPORTLOSS_DET" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

