<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_ACCOUNT_Receipts_Log" ID="EA81A110096E9256A18BE6EFD6855B9F" name-db="PSI_ACCOUNT_Receipts_Log" category="基础数据主体" title="收款记录" author="" description="" packagename="com.spark.psi.storage.account.receipts" name="PSI_ACCOUNT_Receipts_Log" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="id" name-db="id" type="guid" primary-key="false" keep-valid="false" author="" title="标识" description="" ID="E24E3A59B2BD5A878FDC597ABA10724D" />
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="receiptsId" name-db="receiptsId" type="guid" primary-key="false" keep-valid="false" author="" title="通知单id" description="" ID="01D5D00FE3BE83858A68B9EBA5FA8A52" />
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="receiptNo" name-db="receiptNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="通知单编号" description="" ID="F37A92DB101D3F16E72D4A9B7F2B8F1E" />
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="付款对象" description="" ID="0CA5D3A470178B5284FACBAC4526890A" />
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="partnerType" name-db="partnerType" type="char(2)" primary-key="false" keep-valid="false" author="" title="对象类型" description="" ID="267215FAAB2E547ADC02E208B67F9C48" />
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="checkoutSheetId" name-db="checkoutSheetId" type="guid" primary-key="false" keep-valid="false" author="" title="入库单id" description="" ID="374E2ED0794FA5A6F95E0391F73D0589" />
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="sheetNo" name-db="sheetNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="入库单号" description="" ID="9D383C1E9C3F97E17FEC46B82A5072BE" />
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="relevantBillId" name-db="relevantBillId" type="guid" primary-key="false" keep-valid="false" author="" title="相关单据Id" description="" ID="60A6EB248B9951DAB9815722EC0B0CB2" />
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="relevantBillNo" name-db="relevantBillNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="相关单据" description="" ID="737C4AF3147544859C96E7656180020C" />
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="checkinDate" name-db="checkinDate" type="date" primary-key="false" keep-valid="false" author="" title="入库日期" description="" ID="A34B12829710FA35036D6D2A5BE11B04" />
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="收款金额" description="" ID="B396D1684E4A9BBDB828354D1D26A680" />
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="molingAmount" name-db="molingAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="抹零金额" description="" ID="80DE6310D9BB82188BB8EF2C565ECE5E" />
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="receiptPersonId" name-db="receiptPersonId" type="guid" primary-key="false" keep-valid="false" author="" title="收款人" description="" ID="7CF07E42C52772BC221D97B0A61259C6" />
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="receiptPersonName" name-db="receiptPersonName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="姓名" description="" ID="171F2B2C1B1F9F2F5DDD9D80DF594C4F" />
    <field dbtable="PSI_ACCOUNT_Receipts_Log" name="receiptDate" name-db="receiptDate" type="date" primary-key="false" keep-valid="false" author="" title="收款时间" description="" ID="4F6958EF5E5E2E5A9874F9809C8E310E" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_ACCOUNT_Receipts_Log" name-db="PSI_ACCOUNT_Receipts_Log" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

