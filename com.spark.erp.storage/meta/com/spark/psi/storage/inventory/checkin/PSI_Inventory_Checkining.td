<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Checkining" ID="356DAF12C34B495C1E316990F481291A" name-db="PSI_Inventory_Checkining" category="基础数据主体" title="待入库中间表" author="" description="" packagename="com.spark.psi.storage.inventory.checkin" name="PSI_Inventory_Checkining" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Checkining" name="sheetType" name-db="sheetType" type="char(2)" primary-key="false" keep-valid="false" author="" title="入库类型" description="" ID="D5FA3F5EDED80DA8D2C26923EE0A9F24" />
    <field dbtable="PSI_Inventory_Checkining" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="供应商/客户recid" description="" ID="8BC3B385865BFA0982F1D21A297DE7CB" />
    <field dbtable="PSI_Inventory_Checkining" name="partnerCode" name-db="partnerCode" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="" description="" ID="2E7DBD9AED29D2CEA65D32EC6A33F05A" />
    <field dbtable="PSI_Inventory_Checkining" name="partnerName" name-db="partnerName" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="供应商/客户名称" description="" ID="BA925088840DCA795A81FA04299FBBBC" />
    <field dbtable="PSI_Inventory_Checkining" name="namePY" name-db="namePY" type="varchar(30)" primary-key="false" keep-valid="false" author="" title="拼音" description="" ID="A98000273CF2EDF36B326CCE8AD1FD2D" />
    <field dbtable="PSI_Inventory_Checkining" name="partnerShortName" name-db="partnerShortName" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="简称" description="" ID="77862F73F8CB944B3C66DA3568F5A286" />
    <field dbtable="PSI_Inventory_Checkining" name="relaBillsId" name-db="relaBillsId" type="ntext" primary-key="false" keep-valid="false" author="" title="相关单据recid" description="" ID="C1950C95FF5DCFB3C71195941ACB1DC3" />
    <field dbtable="PSI_Inventory_Checkining" name="relaBillsNo" name-db="relaBillsNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="相关单据编号" description="" ID="3B07D3131996473EBCE0989196BB39E1" />
    <field dbtable="PSI_Inventory_Checkining" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库recid" description="" ID="A66F0AAD31FB0E14CAD3B112C1181B44" />
    <field dbtable="PSI_Inventory_Checkining" name="isStoped" name-db="isStoped" type="boolean" primary-key="false" keep-valid="false" author="" title="是否中止" description="" ID="51B99070A2A3F70D57AA2A68ED91E721" />
    <field dbtable="PSI_Inventory_Checkining" name="stopReason" name-db="stopReason" type="nvarchar(500)" primary-key="false" keep-valid="false" author="" title="中止原因" description="" ID="104E5458FDB511C7BD3B24F9F67B11BD" />
    <field dbtable="PSI_Inventory_Checkining" name="storeName" name-db="storeName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="9F75393F0350E0BCAB226710255AEF03" />
    <field dbtable="PSI_Inventory_Checkining" name="storeNamePY" name-db="storeNamePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="拼音" description="" ID="6738E8BC339ABEADBCC2372A982433BD" />
    <field dbtable="PSI_Inventory_Checkining" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="53A5FE18677EAC40D5D19135999415C9" />
    <field dbtable="PSI_Inventory_Checkining" name="billsAmount" name-db="billsAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="订单金额" description="" ID="8E3BED9A01B6CDF0173B1198F6CC280F" />
    <field dbtable="PSI_Inventory_Checkining" name="billsCount" name-db="billsCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="订单总数量" description="" ID="A9F211EA6062619E7690B4E2E0ABA68B" />
    <field dbtable="PSI_Inventory_Checkining" name="checkinDate" name-db="checkinDate" type="date" primary-key="false" keep-valid="false" author="" title="预计入库日期" description="" ID="1DC9F95A61F6B8C0F217BA9E20984186" />
    <field dbtable="PSI_Inventory_Checkining" name="checkinString" name-db="checkinString" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="确认入库字符串" description="" ID="1DBBFC3BB86449113EBC58E50053F93F" />
    <field dbtable="PSI_Inventory_Checkining" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="处理情况" description="" ID="18CFFBA9CC7A2A14E99B0ECC0A0D4D8E" />
    <field dbtable="PSI_Inventory_Checkining" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="13016B21335A56015E8BCA9B0C15920A" />
    <field dbtable="PSI_Inventory_Checkining" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="EA683F6874411CD1B84D3490CCE48EBE" />
    <field dbtable="PSI_Inventory_Checkining" name="creatorDeptId" name-db="creatorDeptId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="1A3CDB561F8DBBE2FE7ECF493420B81C" />
    <field dbtable="PSI_Inventory_Checkining" name="deptName" name-db="deptName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="" description="" ID="F5BC80B856E91698D245773104897CF9" />
    <field dbtable="PSI_Inventory_Checkining" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="0FB9A22BDADD8557CBDFD0EA11505DDE" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Checkining" name-db="PSI_Inventory_Checkining" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

