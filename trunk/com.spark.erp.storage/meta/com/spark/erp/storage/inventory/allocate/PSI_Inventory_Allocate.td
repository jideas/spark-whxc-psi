<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_Allocate" ID="816512EFFF6D7873AABA4406D68F3C35" name-db="PSI_Inventory_Allocate" category="" title="" author="" description="库存调拔" packagename="com.spark.erp.storage.inventory.allocate" name="PSI_Inventory_Allocate" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_Allocate" name="allocSheetNo" name-db="allocSheetNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="调拨单号" description="" ID="833EAA11F59983CFBEA4DC2748F057EC" />
    <field dbtable="PSI_Inventory_Allocate" name="applyDate" name-db="applyDate" type="date" primary-key="false" keep-valid="false" author="" title="申请日期" description="" ID="36AD1B147079C174B49022CFE8734A65" />
    <field dbtable="PSI_Inventory_Allocate" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="处理状态" description="" ID="AADFFF6B710F524EAD98C70F2BF47EAE" />
    <field dbtable="PSI_Inventory_Allocate" name="allocateOutStoreId" name-db="allocateOutStoreId" type="guid" primary-key="false" keep-valid="false" author="" title="调出库GUID" description="" ID="9D6EE8F2C3B7941068FC42E17D53914B" />
    <field dbtable="PSI_Inventory_Allocate" name="allocateOutStoreName" name-db="allocateOutStoreName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="调出仓库名称" description="" ID="DE5D4A5082DA5FF8EAB5541343A6EB2A" />
    <field dbtable="PSI_Inventory_Allocate" name="allocateInStoreId" name-db="allocateInStoreId" type="guid" primary-key="false" keep-valid="false" author="" title="调入库GUID" description="" ID="AA3B96EDAFC2C9100AE7CDE9184784D0" />
    <field dbtable="PSI_Inventory_Allocate" name="allocateInStoreName" name-db="allocateInStoreName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="调入仓库名称" description="" ID="587441638FA00D06C284933445BD114A" />
    <field dbtable="PSI_Inventory_Allocate" name="approvePersonId" name-db="approvePersonId" type="guid" primary-key="false" keep-valid="false" author="" title="审核人id" description="" ID="451F80FF4D07E9794FA07D8D3CCB51F8" />
    <field dbtable="PSI_Inventory_Allocate" name="approvePerson" name-db="approvePerson" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="审核人名称" description="" ID="9EEFC93C31FBF5DAAA045C490969E9E6" />
    <field dbtable="PSI_Inventory_Allocate" name="approveDate" name-db="approveDate" type="date" primary-key="false" keep-valid="false" author="" title="审核日期" description="" ID="B346F10E13D3A0BE2E87777E93AE5A45" />
    <field dbtable="PSI_Inventory_Allocate" name="allocateOutPerson" name-db="allocateOutPerson" type="guid" primary-key="false" keep-valid="false" author="" title="确认调出人GUID" description="" ID="7BB470F513EBE38174F50A13B6130AC7" />
    <field dbtable="PSI_Inventory_Allocate" name="allocateOutPersonName" name-db="allocateOutPersonName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="确认调出人名称" description="" ID="C8C8678ED6E305C4ABFA3447067D65C5" />
    <field dbtable="PSI_Inventory_Allocate" name="allocateOutDate" name-db="allocateOutDate" type="date" primary-key="false" keep-valid="false" author="" title="确认调出日期" description="" ID="7DB4A0F6453A6858A468F4A3438B1916" />
    <field dbtable="PSI_Inventory_Allocate" name="allocateInPerson" name-db="allocateInPerson" type="guid" primary-key="false" keep-valid="false" author="" title="确认调入人GUID" description="" ID="BE515531636E4AA64B1222174AC7FE5C" />
    <field dbtable="PSI_Inventory_Allocate" name="allocateInPersonName" name-db="allocateInPersonName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="确认调入人名称" description="" ID="5A6E73EF2292055EC2DD244802D64B7A" />
    <field dbtable="PSI_Inventory_Allocate" name="allocateInDate" name-db="allocateInDate" type="date" primary-key="false" keep-valid="false" author="" title="确认调入日期" description="" ID="B4460D296C7C8434A4AAC04266840A40" />
    <field dbtable="PSI_Inventory_Allocate" name="allocateReason" name-db="allocateReason" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="调拨原因" description="" ID="59283BB7CBB7DE84E75C04BF1CA6F02D" />
    <field dbtable="PSI_Inventory_Allocate" name="rejectReason" name-db="rejectReason" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="退回原因" description="" ID="02AF6EA455B4CF9A86B741DFDED5D05A" />
    <field dbtable="PSI_Inventory_Allocate" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人GUID" description="" ID="928A04CBF4FE83D1A4A8C800B273D68C" />
    <field dbtable="PSI_Inventory_Allocate" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="所属部门" description="" ID="6294877C0ADF772347741D9522FA45A9" />
    <field dbtable="PSI_Inventory_Allocate" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="9686C244F7BE5BD1D64E95FBF31DD400" />
    <field dbtable="PSI_Inventory_Allocate" name="creatorPY" name-db="creatorPY" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人拼音" description="" ID="B4CE56B4FAEEEA4A1EA53B1CF5C91B35" />
    <field dbtable="PSI_Inventory_Allocate" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="2115EFE3044145E62C0996FDCB69BF80" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_Allocate" name-db="PSI_Inventory_Allocate" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

