<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_ACCOUNT_DEALING" ID="1C9846F7214EB671DA35F02FE15A1546" name-db="SA_ACCOUNT_DEALING" category="业务主体" title="往来" author="" description="" packagename="com.spark.psi.storage.account.cusdeal" name="SA_ACCOUNT_DEALING" isneedrebuild="false">
  <fields>
    <field dbtable="SA_ACCOUNT_DEALING" name="tenantsId" name-db="tenantsId" type="guid" primary-key="false" keep-valid="false" author="" title="租户ID" description="" ID="D322502BB84928438989AB3AAC67EB68" />
    <field dbtable="SA_ACCOUNT_DEALING" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="03A712628DC2A83FE3F369124D453F57" />
    <field dbtable="SA_ACCOUNT_DEALING" name="partnerName" name-db="partnerName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="客户供应商全称" description="" ID="3FADC803DD5A28C59B2E83D8630012DA" />
    <field dbtable="SA_ACCOUNT_DEALING" name="partnerShortName" name-db="partnerShortName" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="客户供应商简称" description="" ID="7406FEBE4A32E7CDC4A66AC6B014D291" />
    <field dbtable="SA_ACCOUNT_DEALING" name="type" name-db="type" type="char(2)" primary-key="false" keep-valid="false" author="" title="类型（客户/供应商）" description="" ID="6B4064530F61545BD517CCAF98B1DE28" />
    <field dbtable="SA_ACCOUNT_DEALING" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="应收/应付" description="" ID="F48458D1D1EB9F420006F1E3A56236A4" />
    <field dbtable="SA_ACCOUNT_DEALING" name="initAmount" name-db="initAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="初始化应收/应付" description="" ID="1945BF9A7E42CBCF934A73B89939D489" />
  </fields>
  <indexs>
    <index author="" name="INDEX_ACCOUNT_DEALING" name-db="INDEX_ACCOUNT_DEALING" title="INDEX_ACCOUNT_DEALING" unique="false" description="">
      <index-item fieldID="D322502BB84928438989AB3AAC67EB68" field="tenantsId" field-author="" desc="false" />
      <index-item fieldID="1C9846F7214EB671DA35F02FE15A1546" field="RECID" field-author="" desc="false" />
      <index-item fieldID="F48458D1D1EB9F420006F1E3A56236A4" field="amount" field-author="" desc="false" />
      <index-item fieldID="6B4064530F61545BD517CCAF98B1DE28" field="type" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_ACCOUNT_DEALING" name-db="SA_ACCOUNT_DEALING" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

