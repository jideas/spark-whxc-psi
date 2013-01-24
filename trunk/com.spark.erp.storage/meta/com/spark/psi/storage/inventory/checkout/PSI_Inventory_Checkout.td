<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Checkout" ID="C709FDC3405E860ED4056FB4A62FA259" name-db="PSI_Inventory_Checkout" category="" title="出库单" author="" description="" packagename="com.spark.psi.storage.inventory.checkout" name="PSI_Inventory_Checkout" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Checkout" name="sheetNo" name-db="sheetNo" type="varchar(30)" primary-key="false" keep-valid="false" author="" title="出库单号" description="" ID="E112EA523113F94BDF29488507CD5B4D" />
    <field dbtable="PSI_Inventory_Checkout" name="checkoutType" name-db="checkoutType" type="char(2)" primary-key="false" keep-valid="false" author="" title="出库类型" description="" ID="3C84A65252C0ABDD4A396736ECF22062" />
    <field dbtable="PSI_Inventory_Checkout" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="供应商/客户recid" description="" ID="D9C0EBBD1550AFC9DC263EA6C071F9B6" />
    <field dbtable="PSI_Inventory_Checkout" name="partnerName" name-db="partnerName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="供应商/客户名称" description="" ID="68792B7360FD03F6A81CB605DBDF9D77" />
    <field dbtable="PSI_Inventory_Checkout" name="partnerCode" name-db="partnerCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="C3073A083280DCA59BB6BE45372DE808" />
    <field dbtable="PSI_Inventory_Checkout" name="namePY" name-db="namePY" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="拼音" description="" ID="360B06197357597269BFAED63A086F78" />
    <field dbtable="PSI_Inventory_Checkout" name="partnerShortName" name-db="partnerShortName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="简称" description="" ID="440CDA88946A1A428AC1B208B1749D5A" />
    <field dbtable="PSI_Inventory_Checkout" name="relaBillsId" name-db="relaBillsId" type="guid" primary-key="false" keep-valid="false" author="" title="相关单据recid" description="" ID="C7731C8ADE49E82D8789293CBF4AD8DF" />
    <field dbtable="PSI_Inventory_Checkout" name="relaBillsNo" name-db="relaBillsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="相关单据编号" description="" ID="A32288EDF819C2AEB68E61A199242A8A" />
    <field dbtable="PSI_Inventory_Checkout" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库recid" description="" ID="E361988E1D5823AEEB9E4B3840D802A7" />
    <field dbtable="PSI_Inventory_Checkout" name="storeName" name-db="storeName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="32C83DD65BF530A1E5D98754E9D908CB" />
    <field dbtable="PSI_Inventory_Checkout" name="storeNamePY" name-db="storeNamePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="拼音" description="" ID="2F3B13A8A0FECB1750BE810C7786EF20" />
    <field dbtable="PSI_Inventory_Checkout" name="goodsFrom" name-db="goodsFrom" type="nvarchar(500)" primary-key="false" keep-valid="false" author="" title="物品来源" description="" ID="709C0B46D1BE6F98822B675D47768445" />
    <field dbtable="PSI_Inventory_Checkout" name="goodsUse" name-db="goodsUse" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="物品用途" description="" ID="C0F21218AD26B94BA495E1A59721F8FD" />
    <field dbtable="PSI_Inventory_Checkout" name="takePerson" name-db="takePerson" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="提货人" description="" ID="D64CA339751F6D887DA06C16FEE94EAA" />
    <field dbtable="PSI_Inventory_Checkout" name="takeUnit" name-db="takeUnit" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="提货单位" description="" ID="E65533A907FB7B705212AEC7471388F9" />
    <field dbtable="PSI_Inventory_Checkout" name="vouchersNo" name-db="vouchersNo" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="凭证号" description="" ID="D0501A880A4BD3E5AA66C1EEFFAA1217" />
    <field dbtable="PSI_Inventory_Checkout" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="6BF5558A8A53CC1529609725AFB7C997" />
    <field dbtable="PSI_Inventory_Checkout" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="出库金额" description="" ID="2D867DFCC64314995DE106EFB53DA296" />
    <field dbtable="PSI_Inventory_Checkout" name="receiptedAmount" name-db="receiptedAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="已收款金额" description="" ID="2935128F30495B827724603946C41E74" />
    <field dbtable="PSI_Inventory_Checkout" name="receiptedFlag" name-db="receiptedFlag" type="char(2)" primary-key="false" keep-valid="false" author="" title="收款状态" description="" ID="8C6F444CAB96C075716CE3573B766FEE" />
    <field dbtable="PSI_Inventory_Checkout" name="checkoutDate" name-db="checkoutDate" type="date" primary-key="false" keep-valid="false" author="" title="出库日期" description="" ID="0403F5A7E0B6EFB815ABF28FA7D6CB5E" />
    <field dbtable="PSI_Inventory_Checkout" name="checkoutPerson" name-db="checkoutPerson" type="guid" primary-key="false" keep-valid="false" author="" title="确认出库人" description="" ID="39D1C8DB666E3612C25A756FF72EDD6F" />
    <field dbtable="PSI_Inventory_Checkout" name="checkoutPersonName" name-db="checkoutPersonName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="确认出库人" description="" ID="D00A62A23E20A5471C4AA748ECB2F952" />
    <field dbtable="PSI_Inventory_Checkout" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="部门recid" description="" ID="4B91EA9423A5E9A12F8D9EEF9CA1DFDF" />
    <field dbtable="PSI_Inventory_Checkout" name="deptName" name-db="deptName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="" description="" ID="DC02807C09A39D22E0DEF0A08884C3D4" />
    <field dbtable="PSI_Inventory_Checkout" name="isReceipting" name-db="isReceipting" type="boolean" primary-key="false" keep-valid="false" author="" title="是否关联收款单" description="" ID="676B0497B2547A57FB4538782F2734AC" />
    <field dbtable="PSI_Inventory_Checkout" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="F2C142AAC4464BCEBBF996DC98A76CAD" />
    <field dbtable="PSI_Inventory_Checkout" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="94677B628D0EE2BD6706B976496C33C1" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Checkout" name-db="PSI_Inventory_Checkout" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

