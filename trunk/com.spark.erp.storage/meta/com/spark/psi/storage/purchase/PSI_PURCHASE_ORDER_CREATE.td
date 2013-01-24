<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Purchase_Order_CREATE" ID="A3DE30156B8804693846F2096CCCF289" name-db="PSI_Purchase_Order_CREATE" category="业务主体" title="采购清单" author="" description="" packagename="com.spark.psi.storage.purchase" name="PSI_Purchase_Order_CREATE" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Purchase_Order_CREATE" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="FC02FED9A4AC5B61FDCD7F885628766A" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品规格GUID" description="" ID="D705AF0B9206F1094AA80C5FDA658417" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="81A40964243A6B93435B524D2B683385" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="B5C63A1E6C16F0A5F1D7C73DA43F72C8" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="goodsAttr" name-db="goodsAttr" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="5B6B63DF2627828A69B990555C6976DA" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="goodsCountDigit" name-db="goodsCountDigit" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="429332F4ABC4840A6882D20692FA9A90" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="unit" name-db="unit" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="01B7B486DCCCC42762B1301D3BAB319A" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="单价" description="" ID="EF983457EB4F8361EF672E29B01B8E4C" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="buyNum" name-db="buyNum" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="采购数量" description="" ID="77DAB2F3360AB6AC5F59FF4AF619F638" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="storeGuid" name-db="storeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="对应仓库编号" description="" ID="4FE269E1A59BCEECB0D9A98543BCE51C" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="storeName" name-db="storeName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="8DB340D0283434C9C64459AA3BFE0503" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="createGuid" name-db="createGuid" type="guid" primary-key="false" keep-valid="false" author="" title="制单人GUID" description="" ID="0111353CC4FF9A6B6E6831ECE7E601C0" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="createPerson" name-db="createPerson" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="制单人" description="" ID="E2657085399AE85B1799429900923362" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="制单日期" description="" ID="9CCB0E1072089EB9BE14CDE8DB5DB7D5" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="cuspGUid" name-db="cuspGUid" type="guid" primary-key="false" keep-valid="false" author="" title="供应商GUID" description="" ID="E8595751646B5074828CEF6B4D4B2688" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="cuspName" name-db="cuspName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="供应商名称" description="" ID="752B75040355DC6D9718FAC8F0CFAB4E" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="cuspFullName" name-db="cuspFullName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="供应商全称" description="" ID="058AF57C5BA566FE093935A4FFD0DBE9" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="cuspFullNamePY" name-db="cuspFullNamePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="供应商全称拼音" description="" ID="BE5D2128EB3FEE9B9F3CACA9979534F4" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="cuspFax" name-db="cuspFax" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="供应商传真" description="" ID="2F435BA26EAFD02D66BD13EFA314A065" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="linkmanGuid" name-db="linkmanGuid" type="guid" primary-key="false" keep-valid="false" author="" title="联系人GUID" description="" ID="8020DAAC617D5B62D2270B22F10D3135" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="linkman" name-db="linkman" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="联系人" description="" ID="CAB6B06BCEB2A5D0FEDF68F4CA216C07" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="linkmanTel" name-db="linkmanTel" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人电话" description="" ID="075DB8A2630E742492C0CD937D1FBF06" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="consigneeGuid" name-db="consigneeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="收货人Guid" description="" ID="02F9082EB759530F47BEB156902AE6B3" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="consignee" name-db="consignee" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="收货人" description="" ID="590854ADC05F1CDDCF945C0EF20C548B" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="consigneeTel" name-db="consigneeTel" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="收货人电话" description="" ID="3F6B621C44114B625C58DED47FA9D0B8" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="address" name-db="address" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="收货地址" description="" ID="6457873758C5B39CC901012E4C0970E3" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="provPrice" name-db="provPrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="上次采购单价" description="" ID="793EDB5BAB1B531A0B12BC0929F5992D" />
    <field dbtable="PSI_Purchase_Order_CREATE" name="buyCause" name-db="buyCause" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="采购原因" description="" ID="9678E4B441480ED8E3E933BEC1FB41C1" />
  </fields>
  <indexs>
    <index author="" name="SA_BUY_CREATE_INDEX" name-db="SA_BUY_CREATE_INDEX" title="SA_BUY_CREATE_INDEX" unique="false" description="">
      <index-item fieldID="FC02FED9A4AC5B61FDCD7F885628766A" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="0111353CC4FF9A6B6E6831ECE7E601C0" field="createGuid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Purchase_Order_CREATE" name-db="PSI_Purchase_Order_CREATE" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

