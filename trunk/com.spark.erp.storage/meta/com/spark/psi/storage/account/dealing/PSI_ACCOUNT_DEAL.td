<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_ACCOUNT_DEAL" ID="10665210D028E51D1CD4286A156736A3" name-db="PSI_ACCOUNT_DEAL" category="基础数据主体" title="往来明细" author="" description="" packagename="com.spark.psi.storage.account.dealing" name="PSI_ACCOUNT_DEAL" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_ACCOUNT_DEAL" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="客户供应商GUID" description="" ID="F1440132552A0A642D982BD2F3DEDDD0" />
    <field dbtable="PSI_ACCOUNT_DEAL" name="billsId" name-db="billsId" type="guid" primary-key="false" keep-valid="false" author="" title="单据GUID" description="" ID="6E634EA0508EF3E9FD2F8447AF50F331" />
    <field dbtable="PSI_ACCOUNT_DEAL" name="partnerType" name-db="partnerType" type="char(2)" primary-key="false" keep-valid="false" author="" title="对象类型" description="" ID="A4C9836B8A5FA5ADC0F6C89C7B7D2DD5" />
    <field dbtable="PSI_ACCOUNT_DEAL" name="billsNo" name-db="billsNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="单据编号" description="" ID="E11031C75DF47FB07945011A9772B5C9" />
    <field dbtable="PSI_ACCOUNT_DEAL" name="billsType" name-db="billsType" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="单据类型" description="" ID="6BDF9E89DEB4D59ABAF5D570D5963B6F" />
    <field dbtable="PSI_ACCOUNT_DEAL" name="receiptType" name-db="receiptType" type="char(10)" primary-key="false" keep-valid="false" author="" title="收款方式" description="" ID="E76AE16988B3EB5C8DC72FF4804D34CB" />
    <field dbtable="PSI_ACCOUNT_DEAL" name="planAmount" name-db="planAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="应收/应付金额" description="" ID="8352970DA954849DCBCDB18647534CED" />
    <field dbtable="PSI_ACCOUNT_DEAL" name="realAmount" name-db="realAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="实收/实付金额" description="" ID="3DAA9D37168D5829958B4F484AA35AA4" />
    <field dbtable="PSI_ACCOUNT_DEAL" name="balance" name-db="balance" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="应收/应付余额" description="" ID="47900A19DE4B36C9F0FE5B3577D815AE" />
    <field dbtable="PSI_ACCOUNT_DEAL" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="日期" description="" ID="BF8EA863B9EB6437E6C91DE5547D3317" />
    <field dbtable="PSI_ACCOUNT_DEAL" name="remark" name-db="remark" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="332B33527E6041A94FE7858ABE7EE425" />
    <field dbtable="PSI_ACCOUNT_DEAL" name="accountBillsId" name-db="accountBillsId" type="guid" primary-key="false" keep-valid="false" author="" title="财务单据GUID" description="" ID="68971BCC8E7195C67EF16E4B50E795DF" />
    <field dbtable="PSI_ACCOUNT_DEAL" name="accountBillsNo" name-db="accountBillsNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="财务单据编号" description="" ID="549B4A07FA20F314E41BF8A79BC96D2E" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_ACCOUNT_DEAL" name-db="PSI_ACCOUNT_DEAL" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

