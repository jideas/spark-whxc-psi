<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_STORE_INFO" ID="15F57A165343BDF430CAE90B8D072089" name-db="SA_STORE_INFO" category="" title="" author="" description="" packagename="com.spark.psi.base.storage.store" name="SA_STORE_INFO" isneedrebuild="false">
  <fields>
    <field dbtable="SA_STORE_INFO" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="F28D20BB0812AB66BB3A099294530EE8" />
    <field dbtable="SA_STORE_INFO" name="name" name-db="name" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="A63AFCAAC617A30559D58A50F728B1BC" />
    <field dbtable="SA_STORE_INFO" name="namePY" name-db="namePY" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="" description="" ID="8696C5A48D58543B7B948BFC67103802" />
    <field dbtable="SA_STORE_INFO" name="storeStatus" name-db="storeStatus" type="char(2)" primary-key="false" keep-valid="false" author="" title="仓库状态" description="" ID="A53E40B7D25C1E7BE0F6D0A48F470B01" />
    <field dbtable="SA_STORE_INFO" name="province" name-db="province" type="varchar(12)" primary-key="false" keep-valid="false" author="" title="省" description="" ID="21C671CEE52CFDB7B70C1F9795934EB0" />
    <field dbtable="SA_STORE_INFO" name="city" name-db="city" type="varchar(12)" primary-key="false" keep-valid="false" author="" title="市" description="" ID="89EDBEA1766EFADF5BEE6E7516EB6221" />
    <field dbtable="SA_STORE_INFO" name="country" name-db="country" type="varchar(12)" primary-key="false" keep-valid="false" author="" title="县" description="" ID="6FCCCA3CF189AF68C73432D7F6D18C93" />
    <field dbtable="SA_STORE_INFO" name="address" name-db="address" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="仓库地址" description="" ID="8D27A13EB25B6DCD314879B3501612AA" />
    <field dbtable="SA_STORE_INFO" name="postCode" name-db="postCode" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="邮政编码" description="" ID="EA9DCD98FD902D999E4B5E2A4B25E8BC" />
    <field dbtable="SA_STORE_INFO" name="phoneNo" name-db="phoneNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="电话号码" description="" ID="94555EF3E7B0B4E1DB0C447A21B5F912" />
    <field dbtable="SA_STORE_INFO" name="mobile" name-db="mobile" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="手机号码" description="" ID="F96818C621F749DA8A84EFF52B26FD39" />
    <field dbtable="SA_STORE_INFO" name="consignee" name-db="consignee" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="收货人" description="" ID="5D85DAFC949AA3F123A747478769DDA9" />
    <field dbtable="SA_STORE_INFO" name="isDirectToCustom" name-db="isDirectToCustom" type="char(2)" primary-key="false" keep-valid="false" author="" title="是否供应商直送" description="" ID="DD57A0C673B253D757CC317EBD2E08D9" />
    <field dbtable="SA_STORE_INFO" name="createPerson" name-db="createPerson" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="234C0C92D677E777825C9452FF9B63B7" />
    <field dbtable="SA_STORE_INFO" name="createdDate" name-db="createdDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="999D313A313EFB4194CD3E0AC839C380" />
    <field dbtable="SA_STORE_INFO" name="createPersonName" name-db="createPersonName" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="930AAB928E273F81BDB64677B98D0240" />
  </fields>
  <indexs>
    <index author="" name="index_storename" name-db="index_storename" title="index_storename" unique="true" description="">
      <index-item fieldID="F28D20BB0812AB66BB3A099294530EE8" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="A63AFCAAC617A30559D58A50F728B1BC" field="name" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_STORE_INFO" name-db="SA_STORE_INFO" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

