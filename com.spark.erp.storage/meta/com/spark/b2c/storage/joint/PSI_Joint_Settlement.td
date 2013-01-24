<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Joint_Settlement" ID="197625635BBAC9E61415A43EB8C34329" name-db="PSI_Joint_Settlement" category="业务主体" title="联营结算" author="" description="" packagename="com.spark.b2c.storage.joint" name="PSI_Joint_Settlement" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Joint_Settlement" name="supplierName" name-db="supplierName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="全称" description="" ID="62D840024B3B37DF9E98AC61E9636DA3" />
    <field dbtable="PSI_Joint_Settlement" name="namePY" name-db="namePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="全称拼音" description="" ID="33CF692ACA47BA1F0A5F99B336C031C5" />
    <field dbtable="PSI_Joint_Settlement" name="shortName" name-db="shortName" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="简称" description="" ID="3F3196CE9EC1F2F325A166A84EBEAB1F" />
    <field dbtable="PSI_Joint_Settlement" name="supplierNo" name-db="supplierNo" type="nvarchar(25)" primary-key="false" keep-valid="false" author="" title="编号" description="" ID="6840C87449745C76FDE1A869E680E3DF" />
    <field dbtable="PSI_Joint_Settlement" name="supplierId" name-db="supplierId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="DFEDEEFB1A6AD2D2E99EB29E9F6FF638" />
    <field dbtable="PSI_Joint_Settlement" name="beginDate" name-db="beginDate" type="date" primary-key="false" keep-valid="false" author="" title="" description="" ID="9C3967D30E2BC5DF7D52A206CAAD90E1" />
    <field dbtable="PSI_Joint_Settlement" name="endDate" name-db="endDate" type="date" primary-key="false" keep-valid="false" author="" title="" description="" ID="EDCF0528AEC131D9BFBA07E15192551E" />
    <field dbtable="PSI_Joint_Settlement" name="salesAmount" name-db="salesAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="销售金额" description="" ID="D99221DF39F485F729FD1A8BD5AC7248" />
    <field dbtable="PSI_Joint_Settlement" name="percentageAmount" name-db="percentageAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="提成金额" description="" ID="AC88EA3EA341136AE3CC7B0136869EDF" />
    <field dbtable="PSI_Joint_Settlement" name="adjustAmount" name-db="adjustAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="调整金额" description="" ID="0FFE34AA50B8F8ED8AD1BC585A063506">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="PSI_Joint_Settlement" name="molingAmount" name-db="molingAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="抹零金额" description="" ID="9CE82BD1EDFEB82EBB260449B22D6496">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="PSI_Joint_Settlement" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="应付金额" description="" ID="A0D3D0CC4D802878B8219E7666FFB4AA" />
    <field dbtable="PSI_Joint_Settlement" name="paidAmount" name-db="paidAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="已付金额" description="" ID="840CCA786C8E28C7810276F58593983C">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="PSI_Joint_Settlement" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="773586E2D80E49317F9748D7115768AE" />
    <field dbtable="PSI_Joint_Settlement" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="D0CB323B31466940390DC9068D723165" />
    <field dbtable="PSI_Joint_Settlement" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="" description="" ID="A777185837DAF82162C73A0BC6E72738" />
    <field dbtable="PSI_Joint_Settlement" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="6B7F86189DA61FBEBEC2A966A83F0449" />
    <field dbtable="PSI_Joint_Settlement" name="sheetNo" name-db="sheetNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="" description="" ID="77D57997AD04B6FB2607C090CA57C2F7" />
    <field dbtable="PSI_Joint_Settlement" name="recordIds" name-db="recordIds" type="ntext" primary-key="false" keep-valid="false" author="" title="" description="" ID="61DB5C83A02A00CDB302AEFEF4A76693" />
    <field dbtable="PSI_Joint_Settlement" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="" description="" ID="03F92EE4DD8A57227F4D66D94BFC1A00" />
    <field dbtable="PSI_Joint_Settlement" name="denyReason" name-db="denyReason" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="退回原因" description="" ID="B356491712E7A425D6103909DF0DB6E3" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Joint_Settlement" name-db="PSI_Joint_Settlement" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

