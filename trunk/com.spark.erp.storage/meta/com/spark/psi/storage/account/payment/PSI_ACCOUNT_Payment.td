<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_ACCOUNT_Payment" ID="187B36A0035D925BA8F35D724760B331" name-db="PSI_ACCOUNT_Payment" category="基础数据主体" title="付款申请单" author="" description="" packagename="com.spark.psi.storage.account.payment" name="PSI_ACCOUNT_Payment" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_ACCOUNT_Payment" name="paymentNo" name-db="paymentNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="编号" description="" ID="23EB2CECB8B4DCA40A9A460522A1B4B6" />
    <field dbtable="PSI_ACCOUNT_Payment" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="付款对象" description="" ID="1E297F35363BBB0255B2FA608BE0D2AF" />
    <field dbtable="PSI_ACCOUNT_Payment" name="partnerType" name-db="partnerType" type="char(2)" primary-key="false" keep-valid="false" author="" title="对象类型" description="" ID="B847626F65287AF0C1939C7858D7FCBA" />
    <field dbtable="PSI_ACCOUNT_Payment" name="paymentType" name-db="paymentType" type="char(2)" primary-key="false" keep-valid="false" author="" title="付款类型" description="" ID="14469A4B48EE8EBA33546971E65FC70B" />
    <field dbtable="PSI_ACCOUNT_Payment" name="denyReason" name-db="denyReason" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="退回原因" description="" ID="82A472FA66841E1EE78B905630281DE0" />
    <field dbtable="PSI_ACCOUNT_Payment" name="payDate" name-db="payDate" type="date" primary-key="false" keep-valid="false" author="" title="付款日期" description="" ID="2BD91A88F367DB4A7569676848B80A4F" />
    <field dbtable="PSI_ACCOUNT_Payment" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="4E0F667E7AAEC52C52A18432A72E73AC" />
    <field dbtable="PSI_ACCOUNT_Payment" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="总金额" description="" ID="674F6B9DF3C9B66102A01033C4E9CD2E">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="PSI_ACCOUNT_Payment" name="paidAmount" name-db="paidAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="已付金额" description="" ID="438877381248069318D7B4605E4A5390">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="PSI_ACCOUNT_Payment" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="35C207351EA0439F617F6FE2CD5E0437" />
    <field dbtable="PSI_ACCOUNT_Payment" name="approvePerson" name-db="approvePerson" type="guid" primary-key="false" keep-valid="false" author="" title="审核人" description="" ID="33763F78718560C9C4CB14CFFAF33592" />
    <field dbtable="PSI_ACCOUNT_Payment" name="approvePersonName" name-db="approvePersonName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="审核人姓名" description="" ID="D14285072F935109747AD1DD3CD367A5" />
    <field dbtable="PSI_ACCOUNT_Payment" name="approveDate" name-db="approveDate" type="date" primary-key="false" keep-valid="false" author="" title="审核日期" description="" ID="37B0C652A94489D57E1264A7E0BBB1A4" />
    <field dbtable="PSI_ACCOUNT_Payment" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="D513962A96EDB4A407F7CB6DF7E86F95" />
    <field dbtable="PSI_ACCOUNT_Payment" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="774F4941B028FFCB4E16D41D3D8705AA" />
    <field dbtable="PSI_ACCOUNT_Payment" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="164DF8F11936BDC64CAB218E2DC6D426" />
    <field dbtable="PSI_ACCOUNT_Payment" name="partnerName" name-db="partnerName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="付款对象" description="" ID="4CFFEEBF484E855B8A36B17744D458FB" />
    <field dbtable="PSI_ACCOUNT_Payment" name="dealingsWay" name-db="dealingsWay" type="char(2)" primary-key="false" keep-valid="false" author="" title="付款方式" description="" ID="C87BDAD26F0C34AC82705BF4CAA7F97A" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_ACCOUNT_Payment" name-db="PSI_ACCOUNT_Payment" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

