<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_ACCOUNT_Payment_Log" ID="F34BC14C1B64AFB0855E464F247E0050" name-db="PSI_ACCOUNT_Payment_Log" category="基础数据主体" title="付款记录" author="" description="" packagename="com.spark.psi.storage.account.payment" name="PSI_ACCOUNT_Payment_Log" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_ACCOUNT_Payment_Log" name="paymentId" name-db="paymentId" type="guid" primary-key="false" keep-valid="false" author="" title="申请单id" description="" ID="367BF277569B30A3439C0C603977701B" />
    <field dbtable="PSI_ACCOUNT_Payment_Log" name="paymentNo" name-db="paymentNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="申请单编号" description="" ID="10EA5543DCA638D9E00967C46A7D009B" />
    <field dbtable="PSI_ACCOUNT_Payment_Log" name="checkinSheetId" name-db="checkinSheetId" type="guid" primary-key="false" keep-valid="false" author="" title="入库单id" description="" ID="33751318E2E618E6349B88F869E86AFC" />
    <field dbtable="PSI_ACCOUNT_Payment_Log" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="付款对象" description="" ID="717388330FA13D3AF9084354F6CBD20D" />
    <field dbtable="PSI_ACCOUNT_Payment_Log" name="partnerType" name-db="partnerType" type="char(2)" primary-key="false" keep-valid="false" author="" title="对象类型" description="" ID="C6EF40FCB6DB4B2915B3DD9B9D87D0D2" />
    <field dbtable="PSI_ACCOUNT_Payment_Log" name="sheetNo" name-db="sheetNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="入库单号" description="" ID="3E754E5DD3B8B7C24B3C83FB2551AD37" />
    <field dbtable="PSI_ACCOUNT_Payment_Log" name="relevantBillId" name-db="relevantBillId" type="guid" primary-key="false" keep-valid="false" author="" title="相关单据Id" description="" ID="A2CB9CC721C68502447333D816E5C105" />
    <field dbtable="PSI_ACCOUNT_Payment_Log" name="relevantBillNo" name-db="relevantBillNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="相关单据" description="" ID="C7F25B48BDECFE4AD790578E3C012C5E" />
    <field dbtable="PSI_ACCOUNT_Payment_Log" name="checkinDate" name-db="checkinDate" type="date" primary-key="false" keep-valid="false" author="" title="入库日期" description="" ID="5FB1CA8A089C1AB68D9B59E7A8F8C687" />
    <field dbtable="PSI_ACCOUNT_Payment_Log" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="付款金额" description="" ID="E11BBF4C01C1A20AB55778C69EC1EBB8" />
    <field dbtable="PSI_ACCOUNT_Payment_Log" name="molingAmount" name-db="molingAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="抹零金额" description="" ID="CB1C24E407EAE018B7123E1A5BE3537B" />
    <field dbtable="PSI_ACCOUNT_Payment_Log" name="payPersonId" name-db="payPersonId" type="guid" primary-key="false" keep-valid="false" author="" title="付款人" description="" ID="6E2A3FAE7635CB02270111BD48B7B991" />
    <field dbtable="PSI_ACCOUNT_Payment_Log" name="payPersonName" name-db="payPersonName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="付款人姓名" description="" ID="EE251B9C476A8F140C01CD9ECD5C1297" />
    <field dbtable="PSI_ACCOUNT_Payment_Log" name="payDate" name-db="payDate" type="date" primary-key="false" keep-valid="false" author="" title="付款日期" description="" ID="26D4B908C988F797F8F1D83210055463" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_ACCOUNT_Payment_Log" name-db="PSI_ACCOUNT_Payment_Log" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

