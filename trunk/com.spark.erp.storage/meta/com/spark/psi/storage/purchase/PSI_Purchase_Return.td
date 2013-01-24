<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Purchase_Return" ID="E6382C435DC9A57C2A265CD74C7AAEE9" name-db="PSI_Purchase_Return" category="" title="采购退货单" author="" description="" packagename="com.spark.psi.storage.purchase" name="PSI_Purchase_Return" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Purchase_Return" name="billsNo" name-db="billsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="采购退货单编号" description="" ID="8B622B632CBCFA37E0C432058611FB1A" />
    <field dbtable="PSI_Purchase_Return" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="供应商GUID" description="" ID="D37DCBA66F4D8807D8A0A9F246CBA091" />
    <field dbtable="PSI_Purchase_Return" name="shortName" name-db="shortName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="供应商名称" description="" ID="49B165F14A77D6DA9772CE6A2B54FE3A" />
    <field dbtable="PSI_Purchase_Return" name="partnerName" name-db="partnerName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="供应商全称" description="" ID="0895598F4965FF9A3730CDC68761961E" />
    <field dbtable="PSI_Purchase_Return" name="partnerNamePY" name-db="partnerNamePY" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="供应商名称拼音" description="" ID="8AE660A0BCB96833A1B792B0F22B43C3" />
    <field dbtable="PSI_Purchase_Return" name="consignee" name-db="consignee" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="收货人" description="" ID="21D28664A81C24C59BBC5ED27B320430" />
    <field dbtable="PSI_Purchase_Return" name="linkman" name-db="linkman" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="联系人" description="" ID="01B867A2315F844CB33DCB38B826928A" />
    <field dbtable="PSI_Purchase_Return" name="address" name-db="address" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="收货地址" description="" ID="DD6523934FECDF6FBB4205355BC57231" />
    <field dbtable="PSI_Purchase_Return" name="rejectReason" name-db="rejectReason" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="退回原因" description="" ID="D9FA25F7DF757539BA6B9ADF30B89BCD" />
    <field dbtable="PSI_Purchase_Return" name="stopReason" name-db="stopReason" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="中止原因" description="" ID="A9DAE361584605513F6EFF378421D473" />
    <field dbtable="PSI_Purchase_Return" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="473A576D784ABDDC3375E0629FEDC8B3" />
    <field dbtable="PSI_Purchase_Return" name="totalAmount" name-db="totalAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="总金额" description="" ID="FF2E95336893C6C465341F56FEBBEFC6" />
    <field dbtable="PSI_Purchase_Return" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="制单人GUID" description="" ID="885E021EA3C0E9ABA3F370ED3CF32294" />
    <field dbtable="PSI_Purchase_Return" name="creator" name-db="creator" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="制单人" description="" ID="8E8A0323A05CAE3BC77F84E5ADF73D79" />
    <field dbtable="PSI_Purchase_Return" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="制单日期" description="" ID="F991E754982D7818627376A2CA330EEC" />
    <field dbtable="PSI_Purchase_Return" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="所属部门GUID" description="" ID="C68973943AE7414BBD6FA93CF19D468A" />
    <field dbtable="PSI_Purchase_Return" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="处理情况" description="" ID="9DEE2451D0F4D1746CCB11862AAF2A84" />
    <field dbtable="PSI_Purchase_Return" name="isStoped" name-db="isStoped" type="boolean" primary-key="false" keep-valid="false" author="" title="中止状态" description="" ID="86FD2812EC36E8FC1078952403BB774A" />
    <field dbtable="PSI_Purchase_Return" name="approveStr" name-db="approveStr" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="审核人及日期" description="" ID="5E78BE297CA480130356E5EC8D365D5F" />
    <field dbtable="PSI_Purchase_Return" name="finishedDate" name-db="finishedDate" type="date" primary-key="false" keep-valid="false" author="" title="完成日期" description="" ID="B0DADBDA6E2C7E4F238506803AC005DF" />
    <field dbtable="PSI_Purchase_Return" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库ID" description="" ID="79D7327D48A97CF4F41A5589222AE936" />
    <field dbtable="PSI_Purchase_Return" name="storeName" name-db="storeName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="BBE37B41F963D80151F162E8419664DB" />
    <field dbtable="PSI_Purchase_Return" name="billType" name-db="billType" type="char(2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="541F7C3C90DD12C314C426A10866022A" />
    <field dbtable="PSI_Purchase_Return" name="deliveryDate" name-db="deliveryDate" type="date" primary-key="false" keep-valid="false" author="" title="" description="" ID="F62EC6E9161C1DE9429C53F9A78615B6" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Purchase_Return" name-db="PSI_Purchase_Return" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

