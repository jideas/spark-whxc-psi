<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_PERSONNEL_DEPT" ID="45E07B2CD02B41F2BC6B431270147FA5" name-db="SA_PERSONNEL_DEPT" category="" title="组织结构表" author="" description="" packagename="com.spark.psi.base.storage.organization" name="SA_PERSONNEL_DEPT" isneedrebuild="false">
  <fields>
    <field dbtable="SA_PERSONNEL_DEPT" name="deptnumber" name-db="deptnumber" type="nvarchar(25)" primary-key="false" keep-valid="false" author="" title="部门编号" description="" ID="80AEDC04DB95F3140B487921DCBFA7AE" />
    <field dbtable="SA_PERSONNEL_DEPT" name="Tenantsrecid" name-db="Tenantsrecid" type="guid" primary-key="false" keep-valid="false" author="" title="租户id" description="" ID="177034025BA67AB87A73F04859243C0D" />
    <field dbtable="SA_PERSONNEL_DEPT" name="parent" name-db="parent" type="guid" primary-key="false" keep-valid="false" author="" title="父节点" description="" ID="E7B947C7CE7DBBBC404E014BE0F8B8EE" />
    <field dbtable="SA_PERSONNEL_DEPT" name="name" name-db="name" type="nvarchar(25)" primary-key="false" keep-valid="false" author="" title="部门名字" description="" ID="1846A84B16B7CFF995FA113080F20B3D" />
    <field dbtable="SA_PERSONNEL_DEPT" name="dtype" name-db="dtype" type="char(1)" primary-key="false" keep-valid="false" author="" title="类型（公司or部门）" description="" ID="1D3643335B4A7838793159756B4975D0" />
    <field dbtable="SA_PERSONNEL_DEPT" name="note" name-db="note" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="" description="" ID="615BA5E973D06077F76C570E7548CC1B" />
    <field dbtable="SA_PERSONNEL_DEPT" name="rtime" name-db="rtime" type="date" primary-key="false" keep-valid="false" author="" title="" description="" ID="C241C017A45CADC382E81449D4E2F16F" />
    <field dbtable="SA_PERSONNEL_DEPT" name="rman" name-db="rman" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="72AA706C7BF2311DCE8DFE4726956964" />
    <field dbtable="SA_PERSONNEL_DEPT" name="vaild" name-db="vaild" type="nchar(1)" primary-key="false" keep-valid="false" author="" title="是否有效（1有效）" description="" ID="1362EBFC63717C94F35E5FCFFCC33EF1" />
  </fields>
  <indexs>
    <index author="" name="LK_TENANTS_RECID" name-db="LK_TENANTS_RECID" title="LK_TENANTS_RECID" unique="false" description="">
      <index-item fieldID="177034025BA67AB87A73F04859243C0D" field="Tenantsrecid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_PERSONNEL_DEPT" name-db="SA_PERSONNEL_DEPT" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

