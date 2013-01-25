<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_STORE_ALLOCATE" ID="D9123C5038F66B98D12448B757948D4D" name-db="SA_STORE_ALLOCATE" category="" title="" author="" description="" packagename="com.spark.psi.storage.store.allocate" name="SA_STORE_ALLOCATE" isneedrebuild="false">
  <fields>
    <field dbtable="SA_STORE_ALLOCATE" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="719E2997E0C8002E5458E1CC630F44A4" />
    <field dbtable="SA_STORE_ALLOCATE" name="allocOrdNo" name-db="allocOrdNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="调拨单号" description="" ID="1223793FACD509959CCC26B191432B1C" />
    <field dbtable="SA_STORE_ALLOCATE" name="applyDate" name-db="applyDate" type="date" primary-key="false" keep-valid="false" author="" title="申请日期" description="" ID="DA264E74A7FA268D4604AABFB946845D" />
    <field dbtable="SA_STORE_ALLOCATE" name="dealState" name-db="dealState" type="char(2)" primary-key="false" keep-valid="false" author="" title="处理状态" description="" ID="43CE7A4EF50E0D260699506C06BBC2D5" />
    <field dbtable="SA_STORE_ALLOCATE" name="allocateOutStoreGuid" name-db="allocateOutStoreGuid" type="guid" primary-key="false" keep-valid="false" author="" title="调出库GUID" description="" ID="EB031FA0A383E077C56B9B43139B4C45" />
    <field dbtable="SA_STORE_ALLOCATE" name="allocateOutStoreName" name-db="allocateOutStoreName" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="调出仓库名称" description="" ID="E10F6C079159CCADD1DA0747EDD32081" />
    <field dbtable="SA_STORE_ALLOCATE" name="allocateInStoreGuid" name-db="allocateInStoreGuid" type="guid" primary-key="false" keep-valid="false" author="" title="调入库GUID" description="" ID="E131163DD74BA9C5258B3019A55006B4" />
    <field dbtable="SA_STORE_ALLOCATE" name="allocateInStoreName" name-db="allocateInStoreName" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="调入仓库名称" description="" ID="A5ECEF06992B84262C83051528A1644F" />
    <field dbtable="SA_STORE_ALLOCATE" name="outExaminePerson" name-db="outExaminePerson" type="guid" primary-key="false" keep-valid="false" author="" title="调出审核人GUID" description="" ID="CD2FB80FE1C553B4E68992556C4D738A" />
    <field dbtable="SA_STORE_ALLOCATE" name="outExaminePersonName" name-db="outExaminePersonName" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="调出审核人名称" description="" ID="4870DC37EBB95BDB6AB322B6BA71A4D5" />
    <field dbtable="SA_STORE_ALLOCATE" name="outExamineDate" name-db="outExamineDate" type="date" primary-key="false" keep-valid="false" author="" title="调出审核日期" description="" ID="9493367CB63488AD82FA9383D67A1C4D" />
    <field dbtable="SA_STORE_ALLOCATE" name="inExaminePerson" name-db="inExaminePerson" type="guid" primary-key="false" keep-valid="false" author="" title="调入审核人GUID" description="" ID="71B1882F2012EBAF0E9788540567FDB6" />
    <field dbtable="SA_STORE_ALLOCATE" name="inExaminePersonName" name-db="inExaminePersonName" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="调入审核人名称" description="" ID="F687EDE839C6308AF13865A48988CABB" />
    <field dbtable="SA_STORE_ALLOCATE" name="inExamineDate" name-db="inExamineDate" type="date" primary-key="false" keep-valid="false" author="" title="调入审核日期" description="" ID="710C1E68C413510C11D3192E25CD7BDD" />
    <field dbtable="SA_STORE_ALLOCATE" name="isOutExamine" name-db="isOutExamine" type="boolean" primary-key="false" keep-valid="false" author="" title="调出审核" description="" ID="436F4EA8FC2838B6DD4588B7BF8934F2" />
    <field dbtable="SA_STORE_ALLOCATE" name="isInExamine" name-db="isInExamine" type="boolean" primary-key="false" keep-valid="false" author="" title="调入审核" description="" ID="36C8EA4A96894BC2D1629F3F32E8F489" />
    <field dbtable="SA_STORE_ALLOCATE" name="allocateOutPerson" name-db="allocateOutPerson" type="guid" primary-key="false" keep-valid="false" author="" title="确认调出人GUID" description="" ID="B54F52A9998E241B35CB23E7DBC45AF2" />
    <field dbtable="SA_STORE_ALLOCATE" name="allocateOutPersonName" name-db="allocateOutPersonName" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="确认调出人名称" description="" ID="BCBBD0951E6945440D884B3E9CF61DA6" />
    <field dbtable="SA_STORE_ALLOCATE" name="allocateOutDate" name-db="allocateOutDate" type="date" primary-key="false" keep-valid="false" author="" title="确认调出日期" description="" ID="E0151060F405575201A7D8308EC4D6E6" />
    <field dbtable="SA_STORE_ALLOCATE" name="allocateInPerson" name-db="allocateInPerson" type="guid" primary-key="false" keep-valid="false" author="" title="确认调入人GUID" description="" ID="3CF335706443D9B825606612121725C9" />
    <field dbtable="SA_STORE_ALLOCATE" name="allocateInPersonName" name-db="allocateInPersonName" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="确认调入人名称" description="" ID="4406E89FBCAA6C7AEE74B1187BB1524F" />
    <field dbtable="SA_STORE_ALLOCATE" name="allocateInDate" name-db="allocateInDate" type="date" primary-key="false" keep-valid="false" author="" title="确认调入日期" description="" ID="E65A84527DED0795787425C482B0F06D" />
    <field dbtable="SA_STORE_ALLOCATE" name="allocateReason" name-db="allocateReason" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="调拨原因" description="" ID="20CD88DD71244889C78D459B03347A44" />
    <field dbtable="SA_STORE_ALLOCATE" name="rejectReason" name-db="rejectReason" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="退回原因" description="" ID="C36E6AF8B5DF41E81DD7F9048A01BA9B" />
    <field dbtable="SA_STORE_ALLOCATE" name="createPerson" name-db="createPerson" type="guid" primary-key="false" keep-valid="false" author="" title="创建人GUID" description="" ID="E3DFF277B9856787B79FC9ABC501F28B" />
    <field dbtable="SA_STORE_ALLOCATE" name="createDepartment" name-db="createDepartment" type="guid" primary-key="false" keep-valid="false" author="" title="所属部门" description="" ID="F52E5F5898E831EBE9AC2C7D8EB9F678" />
    <field dbtable="SA_STORE_ALLOCATE" name="createPersonName" name-db="createPersonName" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="73BEFB41395E515D84B936F2191631F7" />
    <field dbtable="SA_STORE_ALLOCATE" name="createPersonNamePY" name-db="createPersonNamePY" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="创建人拼音" description="" ID="3533A120FC24A948429D04D92668EC36" />
    <field dbtable="SA_STORE_ALLOCATE" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="345D1E8297E1D247B8A16E6017296555" />
  </fields>
  <indexs>
    <index author="" name="index_1" name-db="index_1" title="index_1" unique="false" description="">
      <index-item fieldID="719E2997E0C8002E5458E1CC630F44A4" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="D9123C5038F66B98D12448B757948D4D" field="RECID" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_STORE_ALLOCATE" name-db="SA_STORE_ALLOCATE" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

