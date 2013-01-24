<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Checkouting" ID="F1F27976EEC738F549690A25B936859A" name-db="PSI_Inventory_Checkouting" category="基础数据主体" title="出库单中间表" author="" description="" packagename="com.spark.psi.storage.inventory.checkout" name="PSI_Inventory_Checkouting" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Checkouting" name="sheetType" name-db="sheetType" type="char(2)" primary-key="false" keep-valid="false" author="" title="出库类型" description="" ID="DC9E84388CFF0480EC791CDF2C1E8F52" />
    <field dbtable="PSI_Inventory_Checkouting" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="供应商/客户recid" description="" ID="A27F32FDBDF69DE704C1D58CFEA2C07F" />
    <field dbtable="PSI_Inventory_Checkouting" name="partnerCode" name-db="partnerCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="6C8811BD314045040142E12BFA3591ED" />
    <field dbtable="PSI_Inventory_Checkouting" name="partnerName" name-db="partnerName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="供应商/客户名称" description="" ID="3A48072802213A5CCD1D04EBC0D35783" />
    <field dbtable="PSI_Inventory_Checkouting" name="namePY" name-db="namePY" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="拼音" description="" ID="5E8CCDA891221F95340260A784701222" />
    <field dbtable="PSI_Inventory_Checkouting" name="partnerShortName" name-db="partnerShortName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="简称" description="" ID="7214506068C6FD5960A85BCAF0F937DE" />
    <field dbtable="PSI_Inventory_Checkouting" name="relaBillsId" name-db="relaBillsId" type="guid" primary-key="false" keep-valid="false" author="" title="相关单据recid" description="" ID="245CB711C6A650D849EC182CE22FE146" />
    <field dbtable="PSI_Inventory_Checkouting" name="relaBillsNo" name-db="relaBillsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="相关单据编号" description="" ID="7A151A889E83156B82E642926ABF9607" />
    <field dbtable="PSI_Inventory_Checkouting" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库recid" description="" ID="2814A12B29B2959AA53005D31A911018" />
    <field dbtable="PSI_Inventory_Checkouting" name="isStoped" name-db="isStoped" type="boolean" primary-key="false" keep-valid="false" author="" title="是否中止" description="" ID="FEC2A8FA7C4E6E9F7B986B7447E8885C" />
    <field dbtable="PSI_Inventory_Checkouting" name="stopReason" name-db="stopReason" type="nvarchar(500)" primary-key="false" keep-valid="false" author="" title="中止原因" description="" ID="C1AC797DF8D782572C83A5FDB6BA8858" />
    <field dbtable="PSI_Inventory_Checkouting" name="storeName" name-db="storeName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="7AFEFAB1381F61BB65E2147F1C541A6E" />
    <field dbtable="PSI_Inventory_Checkouting" name="storeNamePY" name-db="storeNamePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="拼音" description="" ID="8A3F970D7E260F3ECC7A23F7177AF94C" />
    <field dbtable="PSI_Inventory_Checkouting" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="D6E3FF1FC6D10FF3D3D04427C2F7A95C" />
    <field dbtable="PSI_Inventory_Checkouting" name="billsAmount" name-db="billsAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="订单金额" description="" ID="86194BE2B604AC8ACAD1555D7D72E4D9" />
    <field dbtable="PSI_Inventory_Checkouting" name="billsCount" name-db="billsCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="订单总商品数" description="" ID="217040F0156DD7AD51244B0C8A5F511B" />
    <field dbtable="PSI_Inventory_Checkouting" name="checkoutDate" name-db="checkoutDate" type="date" primary-key="false" keep-valid="false" author="" title="预计出库日期" description="" ID="040D0F9C98D23078C089A726C7D25689" />
    <field dbtable="PSI_Inventory_Checkouting" name="checkoutString" name-db="checkoutString" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="确认出库字符串" description="" ID="F41201A3108FAFFD4AEA2D70B379315A" />
    <field dbtable="PSI_Inventory_Checkouting" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="处理情况" description="" ID="D5D9005D7549CF2215592F8746DB572D" />
    <field dbtable="PSI_Inventory_Checkouting" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="BCE829DB3D72B4462D9853F56CE486A1" />
    <field dbtable="PSI_Inventory_Checkouting" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="5829276AFE5E7F1F9DB77E04B9E95B50" />
    <field dbtable="PSI_Inventory_Checkouting" name="creatorDeptId" name-db="creatorDeptId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="567C56B44866B67AA28D55BC975D61BD" />
    <field dbtable="PSI_Inventory_Checkouting" name="deptName" name-db="deptName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="" description="" ID="D33FFABE94EF5D9A36B2393175183EF4" />
    <field dbtable="PSI_Inventory_Checkouting" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="4F8128D832F8032C6EF1304AAEDB1BC8" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Checkouting" name-db="PSI_Inventory_Checkouting" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

