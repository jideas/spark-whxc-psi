<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_ACCOUNT_Payment_Det" ID="A58C71D7B8E905472F8EFCE8E0673D79" name-db="PSI_ACCOUNT_Payment_Det" category="基础数据主体" title="付款明细" author="" description="" packagename="com.spark.psi.storage.account.payment" name="PSI_ACCOUNT_Payment_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_ACCOUNT_Payment_Det" name="paymentId" name-db="paymentId" type="guid" primary-key="false" keep-valid="false" author="" title="申请单id" description="" ID="929D1DDA80E696BED5D626D7FA8D56E4" />
    <field dbtable="PSI_ACCOUNT_Payment_Det" name="checkinSheetId" name-db="checkinSheetId" type="guid" primary-key="false" keep-valid="false" author="" title="入库单id" description="" ID="49B60DCA5DA48AB815D9E692B75269FB" />
    <field dbtable="PSI_ACCOUNT_Payment_Det" name="sheetNo" name-db="sheetNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="入库单号" description="" ID="CC64DF39E3B6B4CB0403FBB26C1060AA" />
    <field dbtable="PSI_ACCOUNT_Payment_Det" name="relevantBillId" name-db="relevantBillId" type="guid" primary-key="false" keep-valid="false" author="" title="相关单据Id" description="" ID="A980E1A8B7B5EAFC9CDAA45B54F7EC59" />
    <field dbtable="PSI_ACCOUNT_Payment_Det" name="relevantBillNo" name-db="relevantBillNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="相关单据" description="" ID="80D59C8C624AB38D48CC27C565523BF8" />
    <field dbtable="PSI_ACCOUNT_Payment_Det" name="checkinDate" name-db="checkinDate" type="date" primary-key="false" keep-valid="false" author="" title="入库日期" description="" ID="A8B9CD29E638FB3BE03002EA14F84312" />
    <field dbtable="PSI_ACCOUNT_Payment_Det" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="入库金额" description="" ID="7A8913F3ECE36F0C4BC8C14C79BE75F4" />
    <field dbtable="PSI_ACCOUNT_Payment_Det" name="askAmount" name-db="askAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="申请金额" description="" ID="5605EBD98BF25EF4FE764E7B79CF248B" />
    <field dbtable="PSI_ACCOUNT_Payment_Det" name="paidAmount" name-db="paidAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="已付金额" description="" ID="37F82ECFDB1DB3B180EFF0398D926D7F">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="PSI_ACCOUNT_Payment_Det" name="payingAmount" name-db="payingAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="未付金额" description="" ID="991062F5638DF27A63DF7B0724B042B7" />
    <field dbtable="PSI_ACCOUNT_Payment_Det" name="molingAmount" name-db="molingAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="抹零金额" description="" ID="6BE330745E128B35D2309A5B710BD634">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_ACCOUNT_Payment_Det" name-db="PSI_ACCOUNT_Payment_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

