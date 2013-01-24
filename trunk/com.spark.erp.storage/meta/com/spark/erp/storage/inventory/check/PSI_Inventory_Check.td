<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Check" ID="327BCA18DB75F34D53523AEB71F0AF93" name-db="PSI_Inventory_Check" category="" title="" author="" description="库存盘点" packagename="com.spark.erp.storage.inventory.check" name="PSI_Inventory_Check" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Check" name="startDate" name-db="startDate" type="date" primary-key="false" keep-valid="false" author="" title="盘点开始时间" description="" ID="C2937DCABA31E1D768A59BABA1DACFC5" />
    <field dbtable="PSI_Inventory_Check" name="endDate" name-db="endDate" type="date" primary-key="false" keep-valid="false" author="" title="盘点结束时间" description="" ID="1207B8CA96F7783CCE7FDD5FE530CE8D" />
    <field dbtable="PSI_Inventory_Check" name="checkOrdNo" name-db="checkOrdNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="盘点单编号（单号）" description="" ID="885C7E40AC8EE89E8245F82291120BDB" />
    <field dbtable="PSI_Inventory_Check" name="checkOrdState" name-db="checkOrdState" type="char(2)" primary-key="false" keep-valid="false" author="" title="盘点单据状态" description="" ID="6BDD241F5499C2462207A52A73EF3911" />
    <field dbtable="PSI_Inventory_Check" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="F2E0053F0B20AAF08672D732C22FA06B" />
    <field dbtable="PSI_Inventory_Check" name="checkObj" name-db="checkObj" type="char(2)" primary-key="false" keep-valid="false" author="" title="盘点对象" description="" ID="BB292798235FAC1248C8506DDD393EF7" />
    <field dbtable="PSI_Inventory_Check" name="checkPerson" name-db="checkPerson" type="nvarchar(255)" primary-key="false" keep-valid="false" author="" title="盘点人" description="" ID="AF789B78AD3E9C61136C2760E9F0D11A" />
    <field dbtable="PSI_Inventory_Check" name="markPerson" name-db="markPerson" type="guid" primary-key="false" keep-valid="false" author="" title="制单人" description="" ID="39567249F5F350F4D5A7B414FF96C7E6" />
    <field dbtable="PSI_Inventory_Check" name="markName" name-db="markName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="制单人名称" description="" ID="658A9BEFB820FFFDD8965A3644CB5C71" />
    <field dbtable="PSI_Inventory_Check" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="部门" description="" ID="D716E7041A512F3C65B1EE61D22BE835" />
    <field dbtable="PSI_Inventory_Check" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库" description="" ID="6532DF741A54983FA3DBC010AFA261B8" />
    <field dbtable="PSI_Inventory_Check" name="storeName" name-db="storeName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="DBD302AED9700225EC70D5CCA2CBB95E" />
    <field dbtable="PSI_Inventory_Check" name="creator" name-db="creator" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="28978FD61238433879BA19C36C87F1CC" />
    <field dbtable="PSI_Inventory_Check" name="storeStatus" name-db="storeStatus" type="char(2)" primary-key="false" keep-valid="false" author="" title="仓库状态" description="" ID="772824E59EB9E819FC84383985D8E1EF" />
    <field dbtable="PSI_Inventory_Check" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="634E99516DC051FC73E6C9175A65FAE6" />
    <field dbtable="PSI_Inventory_Check" name="storePY" name-db="storePY" type="nvarchar(25)" primary-key="false" keep-valid="false" author="" title="仓库名称（拼音）" description="" ID="E1C14990A4C288E7A03D1FFB2BD7739F" />
    <field dbtable="PSI_Inventory_Check" name="checkProfit" name-db="checkProfit" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="盘盈数量" description="" ID="2403FC0C97E6AFB114B9346D6EE8533A" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Check" name-db="PSI_Inventory_Check" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

