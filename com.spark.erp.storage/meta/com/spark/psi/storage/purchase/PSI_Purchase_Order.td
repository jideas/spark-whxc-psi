<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Purchase_Order" ID="D7EBF398DCF455D50A88C48341ECABDD" name-db="PSI_Purchase_Order" category="" title="采购订单" author="" description="" packagename="com.spark.psi.storage.purchase" name="PSI_Purchase_Order" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Purchase_Order" name="billsNo" name-db="billsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="采购订单编号" description="" ID="F850DA3F4E7EC2929B51D9EBF6550828" />
    <field dbtable="PSI_Purchase_Order" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="供应商GUID" description="" ID="1D71622AB1A7B2AE501DCFE2340C7C4E" />
    <field dbtable="PSI_Purchase_Order" name="partnerName" name-db="partnerName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="供应商名称" description="" ID="C6E0AB319188B3376B5412F2C55AF5CA" />
    <field dbtable="PSI_Purchase_Order" name="partnerNamePY" name-db="partnerNamePY" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="供应商名称拼音" description="" ID="0083E3EAC301D17F5B38FC0F700B6E39" />
    <field dbtable="PSI_Purchase_Order" name="shortName" name-db="shortName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="供应商全称" description="" ID="19F07DB5DC79B1BFB06DB0C37DED2243" />
    <field dbtable="PSI_Purchase_Order" name="billType" name-db="billType" type="char(2)" primary-key="false" keep-valid="false" author="" title="单据类型" description="" ID="4226F34F453F186B5468ACD5A36FFC4B" />
    <field dbtable="PSI_Purchase_Order" name="consignee" name-db="consignee" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="收货人" description="" ID="E0B49B2927DCC4718B2C00CED1A86249" />
    <field dbtable="PSI_Purchase_Order" name="linkman" name-db="linkman" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="联系人" description="" ID="C5B877CE2E3E8B134C3BB3748DE97822" />
    <field dbtable="PSI_Purchase_Order" name="address" name-db="address" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="收货地址" description="" ID="241D3FAAD9A3478315B93BEE4A7696CF" />
    <field dbtable="PSI_Purchase_Order" name="deliveryDate" name-db="deliveryDate" type="date" primary-key="false" keep-valid="false" author="" title="交货日期" description="" ID="0276F109386D7FC7C2714E53A4D1CA9D" />
    <field dbtable="PSI_Purchase_Order" name="rejectReason" name-db="rejectReason" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="退回原因" description="" ID="DC3344594A444D573A55C2798B30BCDC" />
    <field dbtable="PSI_Purchase_Order" name="stopReason" name-db="stopReason" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="中止原因" description="" ID="A9A6FB76994C5D5ACF1C8E03183FAA7C" />
    <field dbtable="PSI_Purchase_Order" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="2404C6690A0E5B4E0E4EB2206A89C964" />
    <field dbtable="PSI_Purchase_Order" name="totalAmount" name-db="totalAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="总金额" description="" ID="CC54D3FBBC7129F77D7FA64D1A32CE5A" />
    <field dbtable="PSI_Purchase_Order" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="制单人GUID" description="" ID="37C96E32138587A0283D7F052FD3A929" />
    <field dbtable="PSI_Purchase_Order" name="creator" name-db="creator" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="制单人" description="" ID="334E3DE5CB2399C1F6882784411A3D77" />
    <field dbtable="PSI_Purchase_Order" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="制单日期" description="" ID="0231A4D8475BA06DC2341631804095D7" />
    <field dbtable="PSI_Purchase_Order" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="处理情况" description="" ID="E5B7E4B211FFCEB8A7DC156C9C75142E" />
    <field dbtable="PSI_Purchase_Order" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="部门GUID" description="" ID="35B91DD606BEE3C5AD3B5E28CDFA36D5" />
    <field dbtable="PSI_Purchase_Order" name="isStoped" name-db="isStoped" type="boolean" primary-key="false" keep-valid="false" author="" title="中止状态" description="" ID="350994E08E0A290D913AEA03FA6ED01E" />
    <field dbtable="PSI_Purchase_Order" name="approveStr" name-db="approveStr" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="审核人及日期" description="" ID="D58F0716B7723823DC6800628AC12803" />
    <field dbtable="PSI_Purchase_Order" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库ID" description="" ID="0BFF5EE560F1673CE1E339DAF3D85AB4" />
    <field dbtable="PSI_Purchase_Order" name="storeName" name-db="storeName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="9928EDA8ED7A12BE2F274B62EC2F73F0" />
    <field dbtable="PSI_Purchase_Order" name="finishedDate" name-db="finishedDate" type="date" primary-key="false" keep-valid="false" author="" title="完成日期" description="" ID="46C7F009E5DDCA25157828E0505BBC51" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Purchase_Order" name-db="PSI_Purchase_Order" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

