<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_STORE_CHECK" ID="EDE1593382B9AF16C1CD7B2F93DCC1D7" name-db="SA_STORE_CHECK" category="" title="库存盘点" author="" description="库存盘点" packagename="com.spark.psi.storage.store.check" name="SA_STORE_CHECK" isneedrebuild="false">
  <fields>
    <field dbtable="SA_STORE_CHECK" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户GUID" description="" ID="9C418BBE91C55CFD09D5146B965E353E" />
    <field dbtable="SA_STORE_CHECK" name="startDate" name-db="startDate" type="date" primary-key="false" keep-valid="false" author="" title="盘点开始时间" description="" ID="B3CF067FC5F6C95E19DA12D589CF1E58" />
    <field dbtable="SA_STORE_CHECK" name="endDate" name-db="endDate" type="date" primary-key="false" keep-valid="false" author="" title="盘点结束时间" description="" ID="DA45CACB0FD4149E2ABA4C8FF251F2B8" />
    <field dbtable="SA_STORE_CHECK" name="checkOrdNo" name-db="checkOrdNo" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="盘点单编号（单号）" description="" ID="CE35C62179922E83ECEF30A263354A34" />
    <field dbtable="SA_STORE_CHECK" name="checkOrdState" name-db="checkOrdState" type="char(2)" primary-key="false" keep-valid="false" author="" title="盘点单据状态" description="" ID="E1D68B7379346377924243AD99155D13" />
    <field dbtable="SA_STORE_CHECK" name="remark" name-db="remark" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="38EE7CC52029836E87173258F836B7D1" />
    <field dbtable="SA_STORE_CHECK" name="checkObj" name-db="checkObj" type="char(2)" primary-key="false" keep-valid="false" author="" title="盘点对象" description="" ID="8EDB5963B747BC4C8608AFA737EF99D3" />
    <field dbtable="SA_STORE_CHECK" name="checkPerson" name-db="checkPerson" type="varchar(255)" primary-key="false" keep-valid="false" author="" title="盘点人" description="" ID="0274FCBEA092D2CFC5C161A5B6066A29" />
    <field dbtable="SA_STORE_CHECK" name="markPerson" name-db="markPerson" type="guid" primary-key="false" keep-valid="false" author="" title="制单人" description="" ID="A9404722C093C9087E67324F546BDF15" />
    <field dbtable="SA_STORE_CHECK" name="markName" name-db="markName" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="制单人名称" description="" ID="7D8AC020CCC4C4E0720AC59775DE5AC6" />
    <field dbtable="SA_STORE_CHECK" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="部门" description="" ID="B3C37B42FB33AF63EB8B065A852CB1EC" />
    <field dbtable="SA_STORE_CHECK" name="storeGuid" name-db="storeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="仓库" description="" ID="66AB2C6AAAB3B8640CB4F5DDAF3053B1" />
    <field dbtable="SA_STORE_CHECK" name="storeName" name-db="storeName" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="A8071A3F4F867532A50E03DD482CD60D" />
    <field dbtable="SA_STORE_CHECK" name="createPerson" name-db="createPerson" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="7D22AAEEB15058E7A349CD33B1D3E590" />
    <field dbtable="SA_STORE_CHECK" name="storeState" name-db="storeState" type="char(2)" primary-key="false" keep-valid="false" author="" title="仓库状态" description="" ID="C4820C5E31F23A68FECEC999710F1E60" />
    <field dbtable="SA_STORE_CHECK" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="B0515652F32FAB4D432F516D34FFF0A7" />
    <field dbtable="SA_STORE_CHECK" name="storePY" name-db="storePY" type="varchar(25)" primary-key="false" keep-valid="false" author="" title="仓库名称（拼音）" description="" ID="3BA547533A0E6FC2F6C2FFC0C3F9776A" />
    <field dbtable="SA_STORE_CHECK" name="checkProfit" name-db="checkProfit" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="盘盈数量" description="" ID="698EEB3A636029CE180E1FF8EA437482" />
    <field dbtable="SA_STORE_CHECK" name="checkDeficient" name-db="checkDeficient" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="盘亏数量" description="" ID="28865A4E5C560BC664B6C1A95A240537" />
  </fields>
  <indexs>
    <index author="" name="check_checkOrdNo" name-db="check_checkOrdNo" title="check_checkOrdNo" unique="false" description="">
      <index-item fieldID="CE35C62179922E83ECEF30A263354A34" field="checkOrdNo" field-author="" desc="false" />
      <index-item fieldID="A8071A3F4F867532A50E03DD482CD60D" field="storeName" field-author="" desc="false" />
      <index-item fieldID="3BA547533A0E6FC2F6C2FFC0C3F9776A" field="storePY" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_STORE_CHECK" name-db="SA_STORE_CHECK" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

