<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Log" ID="857BA7002A596A2E073D4BE206AC3DD9" name-db="PSI_Inventory_Log" category="" title="" author="" description="库存流水" packagename="com.spark.erp.storage.inventory" name="PSI_Inventory_Log" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Log" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库GUID" description="" ID="399AC6CB77AF61D3B9173AFF65E80851" />
    <field dbtable="PSI_Inventory_Log" name="stockId" name-db="stockId" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="5E6D6A472C02BAE91D1E47B398DDBEA2" />
    <field dbtable="PSI_Inventory_Log" name="name" name-db="name" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="D5878F3511AF13DFF3E89DA21C72267F" />
    <field dbtable="PSI_Inventory_Log" name="properties" name-db="properties" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="274416DA6A7C80D7F53F1F3739D8C942" />
    <field dbtable="PSI_Inventory_Log" name="unit" name-db="unit" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="0244261C8197670C13D254F6E3203B8D" />
    <field dbtable="PSI_Inventory_Log" name="categoryId" name-db="categoryId" type="guid" primary-key="false" keep-valid="false" author="" title="商品分类GUID" description="" ID="49645550914F15960E76262A4A85FE17" />
    <field dbtable="PSI_Inventory_Log" name="code" name-db="code" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编号" description="" ID="8F4EE479C6CB663A64CB09695C02C28E" />
    <field dbtable="PSI_Inventory_Log" name="stockNo" name-db="stockNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="AE4A729CAC5266D7D350901184BDEAC2" />
    <field dbtable="PSI_Inventory_Log" name="orderId" name-db="orderId" type="guid" primary-key="false" keep-valid="false" author="" title="单据id" description="" ID="4D27419A56555832EBA07CC044C2931E" />
    <field dbtable="PSI_Inventory_Log" name="orderNo" name-db="orderNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="单据编号" description="" ID="AC19DCD7E9D0A1B3C9AFD99AAD344F18" />
    <field dbtable="PSI_Inventory_Log" name="logType" name-db="logType" type="char(2)" primary-key="false" keep-valid="false" author="" title="流水类型" description="" ID="064A81986C28494783CC54D7E704208B" />
    <field dbtable="PSI_Inventory_Log" name="instoCount" name-db="instoCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="入库数量" description="" ID="F9176AC3C0506F50FC38BF0C2AD4603F" />
    <field dbtable="PSI_Inventory_Log" name="instoAmount" name-db="instoAmount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="入库金额" description="" ID="FBFAA4AC9D278439B745A977F4C51A2A" />
    <field dbtable="PSI_Inventory_Log" name="scale" name-db="scale" type="int" primary-key="false" keep-valid="false" author="" title="商品精度" description="" ID="1DC3D84EA1339322B616B5C7BECB0435" />
    <field dbtable="PSI_Inventory_Log" name="outstoCount" name-db="outstoCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="出库数量" description="" ID="0E4D7ADA8506DBA72F169FA4937C617E" />
    <field dbtable="PSI_Inventory_Log" name="outstoAmount" name-db="outstoAmount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="出库金额" description="" ID="1523B92682919AEDCBF3EEC64C750659" />
    <field dbtable="PSI_Inventory_Log" name="createPerson" name-db="createPerson" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="C3D088AAA95BB37A70C47F212BBE0D4F" />
    <field dbtable="PSI_Inventory_Log" name="createdDate" name-db="createdDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="13CE89506099E73446CA665B2CD6A451" />
    <field dbtable="PSI_Inventory_Log" name="inventoryType" name-db="inventoryType" type="char(2)" primary-key="false" keep-valid="false" author="" title="库存类型" description="" ID="B91264273D2A2CA5A3624F31E39AC30B" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Log" name-db="PSI_Inventory_Log" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

