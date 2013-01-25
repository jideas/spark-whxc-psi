<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_FINANCE_PAY_RECORD" ID="B3F9F2318F7C4C810763ACA133195BA2" name-db="SA_FINANCE_PAY_RECORD" category="业务主体" title="付款纪录信息表" author="" description="付款纪录信息表" packagename="com.spark.psi.storage.account.payment" name="SA_FINANCE_PAY_RECORD" isneedrebuild="false">
  <fields>
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payBillNo" name-db="payBillNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="付款单编号" description="" ID="EF2A87C5221398749A3A2E7A3A4FF4FD" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="DD2CA6E04923C59B3966F9592075ACB3" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payDate" name-db="payDate" type="date" primary-key="false" keep-valid="false" author="" title="付款日期" description="" ID="53CCF3DB8A09710F6AAAD469AB7CD188" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payObjectGuid" name-db="payObjectGuid" type="guid" primary-key="false" keep-valid="false" author="" title="付款对象编号" description="" ID="03366AC8294D46B74D75797EC3A1F306" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payObject" name-db="payObject" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="付款对象名称" description="" ID="97C9AE2F0954C83672504D3C1512A2FA" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payObjectShort" name-db="payObjectShort" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="付款对象简称" description="" ID="0BEAE7A2A2351C5FE3386826228E7B66" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payType" name-db="payType" type="char(2)" primary-key="false" keep-valid="false" author="" title="付款类型" description="" ID="D4800BAD647E672FDF360A2AB8D2426C" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payTypeName" name-db="payTypeName" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="付款类型名称" description="" ID="0CBF2CDF6D1D6E91593F3E9E4CE8083D" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payWay" name-db="payWay" type="char(2)" primary-key="false" keep-valid="false" author="" title="付款方式" description="" ID="E20DDDA057DDAED1FD70CB8D30677D70" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payWayName" name-db="payWayName" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="付款方式名称" description="" ID="F71B4A0430AEE4E1CF733F657CD8EBE5" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payMoney" name-db="payMoney" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="付款金额" description="" ID="F8F9685115C3FC21F7A04447600D19FE" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payerGuid" name-db="payerGuid" type="guid" primary-key="false" keep-valid="false" author="" title="付款人编号" description="" ID="B6B5F36820C4E20B57571122EE26CD4E" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payer" name-db="payer" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="付款人" description="" ID="9D3B8C825F7A35ABA0461DE924A6D033" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="inStoragePerson" name-db="inStoragePerson" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="入库人" description="" ID="E9B9C61BE1819F2838D33780008D508F" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="affirmPersonName" name-db="affirmPersonName" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="确认人" description="" ID="91D780A9B177D881DC5D2C190C4182B8" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="affirmDate" name-db="affirmDate" type="date" primary-key="false" keep-valid="false" author="" title="确认日期" description="" ID="CB8659A5FB73A1261EC44EA6D18537BC" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="4367AAF0A7AE4C78A5FAB4B373C6A5F2" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="部门编号" description="" ID="A52C5A417A63A571AED616FE1D6A445A" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payObjectPY" name-db="payObjectPY" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="付款对象名称拼音" description="" ID="6AF3D6CFBF768677A12FF2C006150353" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payObjectShortPY" name-db="payObjectShortPY" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="付款对象简称拼音" description="" ID="3EB81630EE0EB5C63C91D8D8167C269F" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payTypeNamePY" name-db="payTypeNamePY" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="付款类型名称拼音" description="" ID="06183363EF7525648D9F8B5D636C4345" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payWayNamePY" name-db="payWayNamePY" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="付款方式名称拼音" description="" ID="080915F2B50206A42D43C13078B8BCF6" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="payerPY" name-db="payerPY" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="付款人拼音" description="" ID="E212D6F62CE5B3BC134E95E11816A045" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="inStoragePersonPY" name-db="inStoragePersonPY" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="入库人拼音" description="" ID="AFB1B38057C1E720C69290020DD875D8" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="isSureReturn" name-db="isSureReturn" type="boolean" primary-key="false" keep-valid="false" author="" title="是否确认退款" description="" ID="F7D7637F4BCCDD81421E065EA5123E05">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="SA_FINANCE_PAY_RECORD" name="quarter" name-db="quarter" type="int" primary-key="false" keep-valid="false" author="" title="季度" description="" ID="40B25CD55EC187BD5236D1E93646A674" />
    <field dbtable="SA_FINANCE_PAY_RECORD" name="remonth" name-db="remonth" type="int" primary-key="false" keep-valid="false" author="" title="月份" description="" ID="BAD3A9AA39A060910E5CD33CDD53ECC9" />
  </fields>
  <indexs>
    <index author="" name="IDX_PAY_RECORD" name-db="IDX_PAY_RECORD" title="IDX_PAY_RECORD" unique="false" description="">
      <index-item fieldID="DD2CA6E04923C59B3966F9592075ACB3" field="tenantsGuid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_FINANCE_PAY_RECORD" name-db="SA_FINANCE_PAY_RECORD" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

