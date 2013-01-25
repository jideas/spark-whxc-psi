<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_FINANCE_RECEIPT_RECORD" ID="2825F03ED103F0854C2AAB7E97C03F23" name-db="SA_FINANCE_RECEIPT_RECORD" category="业务主体" title="收款纪录信息表" author="" description="收款纪录信息表" packagename="com.spark.psi.storage.account.receipt" name="SA_FINANCE_RECEIPT_RECORD" isneedrebuild="false">
  <fields>
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiptBillNo" name-db="receiptBillNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="收款单编号" description="" ID="E711447CD6AA0F70280D66089B8B9BA3" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="3049227D8353FA93B5612D44E28F40FF" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiptDate" name-db="receiptDate" type="date" primary-key="false" keep-valid="false" author="" title="收款日期" description="" ID="F702A270528B2A045902F1153953792C" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiptObjectGuid" name-db="receiptObjectGuid" type="guid" primary-key="false" keep-valid="false" author="" title="收款对象编号" description="" ID="A17E4C3D41FE21DC3AA584F773536F95" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiptObject" name-db="receiptObject" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="收款对象名称" description="" ID="F8D3E3BDCF255332DBDA851EF4166FDB" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiptObjectShort" name-db="receiptObjectShort" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="收款对象简称" description="" ID="99D1E155DD4C70F9902F07AE6785C05B" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiptType" name-db="receiptType" type="char(2)" primary-key="false" keep-valid="false" author="" title="收款类型" description="" ID="DC984BBF8B7AED4FD8A652145F278FFD" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiptTypeName" name-db="receiptTypeName" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="收款类型名称" description="" ID="6FC4D4C8F866868C0C8F2EEF76A4C7CA" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiptWay" name-db="receiptWay" type="char(2)" primary-key="false" keep-valid="false" author="" title="收款方式" description="" ID="56FD836383E01BA63DDCA3891CE623C3" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiptWayName" name-db="receiptWayName" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="收款方式名称" description="" ID="713D0A2362FDF012C3467AC49771E098" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiptMoney" name-db="receiptMoney" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="收款金额" description="" ID="52CC2DEA5E63F7009EFC1393F3677C7F" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiverGuid" name-db="receiverGuid" type="guid" primary-key="false" keep-valid="false" author="" title="收款人编号" description="" ID="790613A4715F4086F6650EA486C46377" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiver" name-db="receiver" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="收款人" description="" ID="5D5C482621B21BB7232FF2DEF593105E" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="affirmPerson" name-db="affirmPerson" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="确认人" description="" ID="A79C4C02E18DD46DED2D7941C9D3ACD4" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="affirmDate" name-db="affirmDate" type="date" primary-key="false" keep-valid="false" author="" title="确认日期" description="" ID="CB0F2C84E24F280C82A5CC4964AC4746" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="FF5BD6F410A0D1958F6CB312072F5E27" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="部门编号" description="" ID="45FC90F9831DCC9638ED0908376A3A88" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="saleEmpGuid" name-db="saleEmpGuid" type="guid" primary-key="false" keep-valid="false" author="" title="销售员工编号" description="" ID="BF8DFF9516DE6EAFCB8DFF493D782EB9" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="saleEmpName" name-db="saleEmpName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="销售员工名称" description="" ID="17E31C3E4ECAA696203B1696FF6D1A06" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="shouldCardCount" name-db="shouldCardCount" type="int" primary-key="false" keep-valid="false" author="" title="应交刷卡底单（数量）" description="" ID="2A6251E9A32A56E92925AA32B55AEC8B" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="shouldCardMoney" name-db="shouldCardMoney" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="应交刷卡底单（金额）" description="" ID="04ADD46DB134BDA19B7B06A82EE930FF" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="retailGuid" name-db="retailGuid" type="guid" primary-key="false" keep-valid="false" author="" title="零售单据编号" description="" ID="00E077C7B3761504F048A95E0433FF83" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiptObjectPY" name-db="receiptObjectPY" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="收款对象名称拼音" description="" ID="3892F82278A3CEE2F1220995ABE7DD6A" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiptObjectShortPY" name-db="receiptObjectShortPY" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="收款对象简称拼音" description="" ID="F935EB3652CEDAAA0D1C0152C47E2B3F" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiptTypeNamePY" name-db="receiptTypeNamePY" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="收款类型名称拼音" description="" ID="72EFE107B478E570BCF6E651D4CD1EC8" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiptWayNamePY" name-db="receiptWayNamePY" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="收款方式名称拼音" description="" ID="66D2BC6474625A7761319EEAFED48409" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="receiverPY" name-db="receiverPY" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="收款人拼音" description="" ID="43E153C6004077B5011CDB84F21978C8" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="affirmPersonPY" name-db="affirmPersonPY" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="确认人拼音" description="" ID="C876BF59462ABCFBD8514AF6705D07AD" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="isRetail" name-db="isRetail" type="boolean" primary-key="false" keep-valid="false" author="" title="是否零售" description="" ID="F15A418A2DAF19E8F97CAA762F7FB6FA" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="isSureReceipt" name-db="isSureReceipt" type="boolean" primary-key="false" keep-valid="false" author="" title="是否确认收款" description="" ID="C6A7ADF3E0125E769DD13A90CFF9869B" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="saleEmpNamePY" name-db="saleEmpNamePY" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="销售员工名称拼音" description="" ID="EE69813487F5000387284A1281289888" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="isRetailDataByPay" name-db="isRetailDataByPay" type="boolean" primary-key="false" keep-valid="false" author="" title="是否零售退款数据(付款)" description="" ID="6848D176FC44BD75E7B75FC7B85A14EF">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="quarter" name-db="quarter" type="int" primary-key="false" keep-valid="false" author="" title="季度" description="" ID="19E0D2D6FB59985EA7050BCAC18AF5F1" />
    <field dbtable="SA_FINANCE_RECEIPT_RECORD" name="remonth" name-db="remonth" type="int" primary-key="false" keep-valid="false" author="" title="月份" description="" ID="82D8F91F5B4FEBA3F3AA015ADB56B130" />
  </fields>
  <indexs>
    <index author="" name="IDX_RECEIPT_RECORD" name-db="IDX_RECEIPT_RECORD" title="IDX_RECEIPT_RECORD" unique="false" description="">
      <index-item fieldID="3049227D8353FA93B5612D44E28F40FF" field="tenantsGuid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_FINANCE_RECEIPT_RECORD" name-db="SA_FINANCE_RECEIPT_RECORD" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

