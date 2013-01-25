<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_STORE_DISMOUNT" ID="211ADA41C7E676E6F28D6EFC60219EBB" name-db="SA_STORE_DISMOUNT" category="" title="库存拆装" author="" description="库存拆装" packagename="com.spark.psi.storage.store.dismounting" name="SA_STORE_DISMOUNT" isneedrebuild="false">
  <fields>
    <field dbtable="SA_STORE_DISMOUNT" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户ID" description="" ID="962467B477E100429105C81379D9F99B" />
    <field dbtable="SA_STORE_DISMOUNT" name="dismDate" name-db="dismDate" type="date" primary-key="false" keep-valid="false" author="" title="拆装日期" description="" ID="F3490201CDEC1336B9B7E8BE084EE506" />
    <field dbtable="SA_STORE_DISMOUNT" name="dismOrdNo" name-db="dismOrdNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="拆装单编号（单号）" description="" ID="6422E1C77E37E0EAA08F41590234E96B" />
    <field dbtable="SA_STORE_DISMOUNT" name="markPerson" name-db="markPerson" type="guid" primary-key="false" keep-valid="false" author="" title="制单人" description="" ID="A8A20540B821AD4B3A4F1525B3F919BE" />
    <field dbtable="SA_STORE_DISMOUNT" name="markName" name-db="markName" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="制单人名称" description="" ID="A8560F142DAEC371677FA3CAD04AC2B2" />
    <field dbtable="SA_STORE_DISMOUNT" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="部门" description="" ID="8A5DCF9C52438E67C4AB84C84F6A6857" />
    <field dbtable="SA_STORE_DISMOUNT" name="storeGuid" name-db="storeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="仓库" description="" ID="D348E7BCA52A161AB344116E68FCD0F8" />
    <field dbtable="SA_STORE_DISMOUNT" name="storeName" name-db="storeName" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="3570C6AC957ED75D9A09C91F38A1F964" />
    <field dbtable="SA_STORE_DISMOUNT" name="storePY" name-db="storePY" type="varchar(25)" primary-key="false" keep-valid="false" author="" title="仓库名称（拼音）" description="" ID="B29870D5E2E0EAA6F83FB5CA24227AB9" />
    <field dbtable="SA_STORE_DISMOUNT" name="createPerson" name-db="createPerson" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="2B2CEA830C6512622B4E323AF2632779" />
    <field dbtable="SA_STORE_DISMOUNT" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="F566CB1DCAA1954EAB61814C5AEF9A9E" />
  </fields>
  <indexs>
    <index author="" name="dismount_markName" name-db="dismount_markName" title="dismount_markName" unique="false" description="">
      <index-item fieldID="A8560F142DAEC371677FA3CAD04AC2B2" field="markName" field-author="" desc="false" />
      <index-item fieldID="B29870D5E2E0EAA6F83FB5CA24227AB9" field="storePY" field-author="" desc="false" />
      <index-item fieldID="3570C6AC957ED75D9A09C91F38A1F964" field="storeName" field-author="" desc="false" />
      <index-item fieldID="6422E1C77E37E0EAA08F41590234E96B" field="dismOrdNo" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_STORE_DISMOUNT" name-db="SA_STORE_DISMOUNT" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

