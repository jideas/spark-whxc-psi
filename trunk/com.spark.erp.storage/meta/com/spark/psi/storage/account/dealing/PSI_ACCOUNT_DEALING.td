<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_ACCOUNT_DEALING" ID="1942E70810B22BCF7FD447DD51F36256" name-db="PSI_ACCOUNT_DEALING" category="基础数据主体" title="往来款" author="" description="" packagename="com.spark.psi.storage.account.dealing" name="PSI_ACCOUNT_DEALING" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_ACCOUNT_DEALING" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="B1A0A4A6B940C85804EA98D88555FFAD" />
    <field dbtable="PSI_ACCOUNT_DEALING" name="partnerName" name-db="partnerName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="客户供应商全称" description="" ID="9FF02B61A7FB6B88D8F656570942A3A5" />
    <field dbtable="PSI_ACCOUNT_DEALING" name="namePY" name-db="namePY" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="名称拼音" description="" ID="C5A551174C5C859D07B5156B338C518D" />
    <field dbtable="PSI_ACCOUNT_DEALING" name="partnerShortName" name-db="partnerShortName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="客户供应商简称" description="" ID="F7005DB316367B53AB3B9706BF775D67" />
    <field dbtable="PSI_ACCOUNT_DEALING" name="type" name-db="type" type="char(2)" primary-key="false" keep-valid="false" author="" title="类型（客户/供应商）" description="" ID="DD0F9FB23267F7490B8535E51D95060A" />
    <field dbtable="PSI_ACCOUNT_DEALING" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="应收/应付" description="" ID="DD85C7994DD5928BA2B37156E87F2F54" />
    <field dbtable="PSI_ACCOUNT_DEALING" name="initAmount" name-db="initAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="初始化应收/应付" description="" ID="FB4A7FF5CFEA704640AC6B7CF33EC6D4" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_ACCOUNT_DEALING" name-db="PSI_ACCOUNT_DEALING" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

