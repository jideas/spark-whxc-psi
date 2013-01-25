<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_STORE_DIRECT" ID="4482FDD30FFC264A9DBB8E2C701D2855" name-db="SA_STORE_DIRECT" category="" title="直供" author="" description="" packagename="com.spark.psi.storage.store.storage" name="SA_STORE_DIRECT" isneedrebuild="false">
  <fields>
    <field dbtable="SA_STORE_DIRECT" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="719AFB66D9D9B96F5765A03196B640E0" />
    <field dbtable="SA_STORE_DIRECT" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品规格GUID" description="" ID="FF062BF5CB8753AF1BC764906099B73D" />
    <field dbtable="SA_STORE_DIRECT" name="goodsNo" name-db="goodsNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="588F4DF1A3EA5DB8B04A5473CC8BFF11" />
    <field dbtable="SA_STORE_DIRECT" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="A25A3181E568A778721FCCAB505FDE35" />
    <field dbtable="SA_STORE_DIRECT" name="goodsAttr" name-db="goodsAttr" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="510DF0609067FAF47911D2A5434D8D17" />
    <field dbtable="SA_STORE_DIRECT" name="goodsCountDigit" name-db="goodsCountDigit" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="01998654263FF02780137D6818EDABCC" />
    <field dbtable="SA_STORE_DIRECT" name="unit" name-db="unit" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="6E32E4D3F48E5AAC4344D867C0ED83CE" />
    <field dbtable="SA_STORE_DIRECT" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="单价" description="" ID="735DB160AFEFEE10906F424C63043260" />
    <field dbtable="SA_STORE_DIRECT" name="buyNum" name-db="buyNum" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="采购数量" description="" ID="3517BFD7E016C93E49726821B13EE516" />
    <field dbtable="SA_STORE_DIRECT" name="storeGuid" name-db="storeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="对应仓库编号" description="" ID="3B97D4B891B10BA2FC97F703DB3692CF" />
    <field dbtable="SA_STORE_DIRECT" name="storeName" name-db="storeName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="B203302D35C87255169FB8445B469889" />
    <field dbtable="SA_STORE_DIRECT" name="createGuid" name-db="createGuid" type="guid" primary-key="false" keep-valid="false" author="" title="制单人GUID" description="" ID="E6E9720DC2C42961D7AF219AE4DC3193" />
    <field dbtable="SA_STORE_DIRECT" name="createPerson" name-db="createPerson" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="制单人" description="" ID="E4C5462A986273229559B6093BBE90C2" />
    <field dbtable="SA_STORE_DIRECT" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="制单日期" description="" ID="D6C5D60B3D632E026FAF7C1D43ABCE6A" />
    <field dbtable="SA_STORE_DIRECT" name="cuspGUid" name-db="cuspGUid" type="guid" primary-key="false" keep-valid="false" author="" title="供应商GUID" description="" ID="2040E0B188E8959A27BC5FE2ECA831A8" />
    <field dbtable="SA_STORE_DIRECT" name="cuspName" name-db="cuspName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="供应商名称" description="" ID="392B044AA1F6C2900CD0570E7FDD0CD6" />
    <field dbtable="SA_STORE_DIRECT" name="cuspFullName" name-db="cuspFullName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="供应商全称" description="" ID="1EE852C6386B770AC7BFAA64244F9752" />
    <field dbtable="SA_STORE_DIRECT" name="cuspFullNamePY" name-db="cuspFullNamePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="供应商全称拼音" description="" ID="0DFEF23C81BDBE2107D563FBB50B7EFC" />
    <field dbtable="SA_STORE_DIRECT" name="cuspFax" name-db="cuspFax" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="供应商传真" description="" ID="9BD5AADC087F35E66EFBF8D73A8D38D0" />
    <field dbtable="SA_STORE_DIRECT" name="linkmanGuid" name-db="linkmanGuid" type="guid" primary-key="false" keep-valid="false" author="" title="联系人GUID" description="" ID="BFCBC3E56BAEBAD87A8B6B08D5705E84" />
    <field dbtable="SA_STORE_DIRECT" name="linkman" name-db="linkman" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="联系人" description="" ID="B07624D8767946AB354053107DD1B264" />
    <field dbtable="SA_STORE_DIRECT" name="linkmanTel" name-db="linkmanTel" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人电话" description="" ID="07CD7AC567D29CDCCF029F8398785746" />
    <field dbtable="SA_STORE_DIRECT" name="consigneeGuid" name-db="consigneeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="收货人Guid" description="" ID="026F07CDEBA49C29AFDC81F1D4E79B74" />
    <field dbtable="SA_STORE_DIRECT" name="consignee" name-db="consignee" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="收货人" description="" ID="8123CE5FA758878429AAF681D290F625" />
    <field dbtable="SA_STORE_DIRECT" name="consigneeTel" name-db="consigneeTel" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="收货人电话" description="" ID="06F8E0BE9F5971C32491146D195A55F6" />
    <field dbtable="SA_STORE_DIRECT" name="address" name-db="address" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="收货地址" description="" ID="69F63FDB7C76920A20EDC8932D24316D" />
    <field dbtable="SA_STORE_DIRECT" name="saleOrdGuid" name-db="saleOrdGuid" type="guid" primary-key="false" keep-valid="false" author="" title="相关销售订单GUID" description="" ID="38CE6A39D99ADCEC34154C567D1CE406" />
    <field dbtable="SA_STORE_DIRECT" name="provPrice" name-db="provPrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="上次采购单价" description="" ID="32BD724E992C67A1979FB43CD7DF6DAA" />
    <field dbtable="SA_STORE_DIRECT" name="payDate" name-db="payDate" type="date" primary-key="false" keep-valid="false" author="" title="交货日期" description="" ID="D93940B71A36770FE46209C9EC7C7A31" />
    <field dbtable="SA_STORE_DIRECT" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="56FAA8AD7F0CD54F326A052437BCD91A" />
    <field dbtable="SA_STORE_DIRECT" name="salePrice" name-db="salePrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="销售价格" description="" ID="B41445E712E13ABBEE330154F72CDA0E" />
    <field dbtable="SA_STORE_DIRECT" name="saleDis" name-db="saleDis" type="numeric(6,5)" primary-key="false" keep-valid="false" author="" title="直供时，对应销" description="" ID="6F5234EF5E47BE91B1BB83EBC41CFE78" />
    <field dbtable="SA_STORE_DIRECT" name="isStoped" name-db="isStoped" type="boolean" primary-key="false" keep-valid="false" author="" title="是否被中止" description="" ID="7EC07AF3B4ACAA386F234EA9D656E09E">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
  </fields>
  <indexs>
    <index author="" name="SA_STORE_DIRECT_STOP" name-db="SA_STORE_DIRECT_STOP" title="SA_STORE_DIRECT_STOP" unique="false" description="">
      <index-item fieldID="719AFB66D9D9B96F5765A03196B640E0" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="38CE6A39D99ADCEC34154C567D1CE406" field="saleOrdGuid" field-author="" desc="false" />
    </index>
    <index author="" name="SA_STORE_DIRECT_INDEX" name-db="SA_STORE_DIRECT_INDEX" title="SA_STORE_DIRECT_INDEX" unique="false" description="">
      <index-item fieldID="719AFB66D9D9B96F5765A03196B640E0" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="7EC07AF3B4ACAA386F234EA9D656E09E" field="isStoped" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_STORE_DIRECT" name-db="SA_STORE_DIRECT" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

