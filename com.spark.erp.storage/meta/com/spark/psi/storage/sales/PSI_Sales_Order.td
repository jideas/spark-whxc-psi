<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Sales_Order" ID="D45AD8EE96811D369F425CFD558676BD" name-db="PSI_Sales_Order" category="" title="销售订单" author="" description="" packagename="com.spark.psi.storage.sales" name="PSI_Sales_Order" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Sales_Order" name="billsNo" name-db="billsNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="销售订单编号" description="" ID="6B539385C052E3A546624763FB1E92CE" />
    <field dbtable="PSI_Sales_Order" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="客户GUID" description="" ID="044EC65D035C375F1B182B3EA5FAFA41" />
    <field dbtable="PSI_Sales_Order" name="shortName" name-db="shortName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="客户简称" description="" ID="92C29DEC2412FE46C140285D980F1E82" />
    <field dbtable="PSI_Sales_Order" name="partnerName" name-db="partnerName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="客户全称" description="" ID="BAAAF5F83BF0B579847072164339A6AD" />
    <field dbtable="PSI_Sales_Order" name="partnerNamePY" name-db="partnerNamePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="客户拼音" description="" ID="EE7574FEA369372BCAD6D6F994D2F58B" />
    <field dbtable="PSI_Sales_Order" name="fax" name-db="fax" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="客户传真" description="" ID="A5CBACF3A95CC024C71F82AF3774DBA0" />
    <field dbtable="PSI_Sales_Order" name="consignee" name-db="consignee" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="收货人" description="" ID="691B3781B01D8DD9A11DDCA1A188E218" />
    <field dbtable="PSI_Sales_Order" name="linkman" name-db="linkman" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="联系人" description="" ID="6252CC48033C9EC650A77CC0294B2FF5" />
    <field dbtable="PSI_Sales_Order" name="address" name-db="address" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="收货地址" description="" ID="BFF07259CA5DD0F92B50A0C64B40A1D6" />
    <field dbtable="PSI_Sales_Order" name="deliveryDate" name-db="deliveryDate" type="date" primary-key="false" keep-valid="false" author="" title="交货日期" description="" ID="08CA6ADB65CAA90EF2DC9D86E229316E" />
    <field dbtable="PSI_Sales_Order" name="rejectReason" name-db="rejectReason" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="退回原因" description="" ID="25A642AC0475D48E58CB355C599F543F" />
    <field dbtable="PSI_Sales_Order" name="stopReason" name-db="stopReason" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="中止原因" description="" ID="11BF8903CA90A85D9EA3C00819D7C699" />
    <field dbtable="PSI_Sales_Order" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="8B7D8CE9797D2285E4F3AAD9227A158C" />
    <field dbtable="PSI_Sales_Order" name="disAmount" name-db="disAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="整单折扣" description="" ID="F27E86F301DFE79BBF54A5EB15997A41" />
    <field dbtable="PSI_Sales_Order" name="totalAmount" name-db="totalAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="总金额" description="" ID="1BDF47B2ED36F444BFF3042178425ABF" />
    <field dbtable="PSI_Sales_Order" name="billType" name-db="billType" type="char(2)" primary-key="false" keep-valid="false" author="" title="类型" description="" ID="76451C0781147691F775D623B946FE73" />
    <field dbtable="PSI_Sales_Order" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="制单人GUID" description="" ID="798F47DA059DF1B9D26C43368F756848" />
    <field dbtable="PSI_Sales_Order" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="AF48B9A2341FA4A12B9A28DE8124C589" />
    <field dbtable="PSI_Sales_Order" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="制单日期" description="" ID="77053EDACD0C93DBDADC9E3CB8A1F950" />
    <field dbtable="PSI_Sales_Order" name="finishedDate" name-db="finishedDate" type="date" primary-key="false" keep-valid="false" author="" title="完成日期" description="" ID="14A96D8440B1997510F58E7450E283A4" />
    <field dbtable="PSI_Sales_Order" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="40098709B47A1492FCB7586146EDD8CF" />
    <field dbtable="PSI_Sales_Order" name="isStoped" name-db="isStoped" type="boolean" primary-key="false" keep-valid="false" author="" title="" description="" ID="626CEC4D27E5DA7EA3EEAC16B99F275A" />
    <field dbtable="PSI_Sales_Order" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="9878BB3447B7B6DDF895E53CC3176B3A" />
    <field dbtable="PSI_Sales_Order" name="approveStr" name-db="approveStr" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="审核人及日期" description="" ID="11716AE9984B5F47BDAAB25317D3355D" />
    <field dbtable="PSI_Sales_Order" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="冗余" description="" ID="768BE389831F6C10776A14E7D689280E" />
    <field dbtable="PSI_Sales_Order" name="storeName" name-db="storeName" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="冗余" description="" ID="3A09F7683F858BBE9DA4D9A625F56665" />
    <field dbtable="PSI_Sales_Order" name="isAllot" name-db="isAllot" type="boolean" primary-key="false" keep-valid="false" author="" title="是否分配" description="" ID="7BDA46AEF48EFAC1CDB95D850C91E5FF" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Sales_Order" name-db="PSI_Sales_Order" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

