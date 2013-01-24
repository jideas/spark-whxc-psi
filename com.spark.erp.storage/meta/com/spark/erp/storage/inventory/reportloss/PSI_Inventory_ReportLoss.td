<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Inventory_ReportLoss" ID="7F988D972FEFAB1D67F6DB1A29537974" name-db="PSI_Inventory_ReportLoss" category="" title="" author="" description="库存报损" packagename="com.spark.erp.storage.inventory.reportloss" name="PSI_Inventory_ReportLoss" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Inventory_ReportLoss" name="id" name-db="id" type="guid" primary-key="false" keep-valid="false" author="" title="记录标识" description="" ID="2C2FF1D3A118DCC1B2124A90ECB71482" />
    <field dbtable="PSI_Inventory_ReportLoss" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="仓库标识" description="" ID="726A7E887F3145CBADC5C06D1D6413FE" />
    <field dbtable="PSI_Inventory_ReportLoss" name="storeName" name-db="storeName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="仓库名称" description="" ID="D28506077E0DC204F1234B99F027A0D2" />
    <field dbtable="PSI_Inventory_ReportLoss" name="sheetNo" name-db="sheetNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="报损单号" description="" ID="3F93CE9C673329D5E496DBFDB3C5C28E" />
    <field dbtable="PSI_Inventory_ReportLoss" name="applyDate" name-db="applyDate" type="date" primary-key="false" keep-valid="false" author="" title="申请日期" description="" ID="6D1A7902F52944DC9697F4E3C4E392E1" />
    <field dbtable="PSI_Inventory_ReportLoss" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="5BFAB834035657EBE1B46AFEEC80EAAF" />
    <field dbtable="PSI_Inventory_ReportLoss" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人标识" description="" ID="9D1EADD9A8266B47FE1F8B5B16699BF6" />
    <field dbtable="PSI_Inventory_ReportLoss" name="creator" name-db="creator" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="创建人名称" description="" ID="F39C2C72F509B4BE501A239554315F66" />
    <field dbtable="PSI_Inventory_ReportLoss" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="A06493808FA9321BBE65C0B8742E001F" />
    <field dbtable="PSI_Inventory_ReportLoss" name="approvalPersonId" name-db="approvalPersonId" type="guid" primary-key="false" keep-valid="false" author="" title="审批人标识" description="" ID="5865E16928E3E0425EA1DDD563801D39" />
    <field dbtable="PSI_Inventory_ReportLoss" name="approvalPersonName" name-db="approvalPersonName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="审批人名称" description="" ID="9325622ADED8D1028B66325ACAAA8A0A" />
    <field dbtable="PSI_Inventory_ReportLoss" name="approvalDate" name-db="approvalDate" type="date" primary-key="false" keep-valid="false" author="" title="审批日期" description="" ID="901EED5C66B5F117B0C10ACBAF3420BD" />
    <field dbtable="PSI_Inventory_ReportLoss" name="rejectReason" name-db="rejectReason" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="驳回原因" description="" ID="76C923ECF52BCC9926C403CE6EC70BE1" />
    <field dbtable="PSI_Inventory_ReportLoss" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="D6199E25E1034E698F0EC4F408899907" />
    <field dbtable="PSI_Inventory_ReportLoss" name="reportType" name-db="reportType" type="char(2)" primary-key="false" keep-valid="false" author="" title="报损类型" description="" ID="76E4FC0E35CC2959E7FF44384C36217B" />
    <field dbtable="PSI_Inventory_ReportLoss" name="accountApprover" name-db="accountApprover" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="6AB40FDC3C1885C6FA8751083DD2E5A7" />
    <field dbtable="PSI_Inventory_ReportLoss" name="accountApproverId" name-db="accountApproverId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="BBD22748A9CF5711C8990C27BADE9F78" />
    <field dbtable="PSI_Inventory_ReportLoss" name="accountApproveDate" name-db="accountApproveDate" type="date" primary-key="false" keep-valid="false" author="" title="" description="" ID="1EBAC409A3CF94D42C676E6D464595F4" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Inventory_ReportLoss" name-db="PSI_Inventory_ReportLoss" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

