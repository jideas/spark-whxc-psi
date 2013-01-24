<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_CUSPROVIDER_INFO" ID="5817D78FDA948155938EA404F2EE74C7" name-db="SA_CUSPROVIDER_INFO" category="基础数据主体" title="客户/供应商管理" author="" description="存放客户/供应商的相关信息" packagename="com.spark.psi.base.storage.partner" name="SA_CUSPROVIDER_INFO" isneedrebuild="false">
  <fields>
    <field dbtable="SA_CUSPROVIDER_INFO" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="true" author="" title="租户编号" description="" ID="F69CF462E93AAB6D1E2C92A3F7B53502" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="cusproNo" name-db="cusproNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="客户供应商编号" description="" ID="C7ED46A37C17416DFCA04E354751D247" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="cusproName" name-db="cusproName" type="nvarchar(100)" primary-key="false" keep-valid="true" author="" title="客户供应商全称" description="" ID="93BD65E328E7A9C11E23A8D7F02975AF" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="cusproShortName" name-db="cusproShortName" type="varchar(20)" primary-key="false" keep-valid="true" author="" title="客户供应商简称" description="" ID="28A6873B1B37B38C88AB8254801A07CF" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="cusproType" name-db="cusproType" type="char(2)" primary-key="false" keep-valid="true" author="" title="客户供应商类型" description="" ID="CF59DB40F29373062104F0384AFD55B1" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="cusproSour" name-db="cusproSour" type="char(2)" primary-key="false" keep-valid="false" author="" title="客户供应商来源" description="" ID="25443ACE8E838F150332E3B0237EDB49" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="cusproGrd" name-db="cusproGrd" type="char(2)" primary-key="false" keep-valid="false" author="" title="客户供应商分类" description="" ID="6E0F394444782F87F1279B9CD508AC5B" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="industy" name-db="industy" type="char(2)" primary-key="false" keep-valid="false" author="" title="所属行业" description="" ID="19A017F864A35410F78580434E1C59A9" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="scale" name-db="scale" type="char(10)" primary-key="false" keep-valid="false" author="" title="企业规模" description="" ID="DD715C03DBD5DDF0CE42C3971CCA1A46" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="account" name-db="account" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="银行帐号" description="" ID="D382141C5F720614206F522611E9E612" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="cusproBank" name-db="cusproBank" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="银行1" description="" ID="E90A4A271762BD87871D615A90294546" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="accountHolder" name-db="accountHolder" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="开户名称" description="" ID="CFA1F9733775A76FFB59406303315DF9" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="creditline" name-db="creditline" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="信用额度" description="" ID="16EA23B4F0EC8E6E081650B3A7C57ED5">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_CUSPROVIDER_INFO" name="creditLinePerson" name-db="creditLinePerson" type="guid" primary-key="false" keep-valid="false" author="" title="信用额度调整人" description="" ID="2ECF289E2F99645AD5177F1206DB723D" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="workTel" name-db="workTel" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="工作电话" description="" ID="44EE824A384C1CC5B41194B6A6F8D823" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="fax" name-db="fax" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="传真" description="" ID="EBEC294BA3DB1B654EF0731DF00BF44E" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="province" name-db="province" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="省份" description="" ID="D37773FB579EDA0DA054ED835892A7FB" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="city" name-db="city" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="城市" description="" ID="1D5F7B5D7D7D70E40340F97F9FB94D70" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="address" name-db="address" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="地址" description="" ID="B171AA27E9B93F5AEA765C4957717EE5" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="addressDetail" name-db="addressDetail" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="详细地址" description="" ID="8F87E1A39678FD81834467559B9063A6" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="taxationNo" name-db="taxationNo" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="增值税号" description="" ID="0DD25CFB738E6EAEA4061172936A7D2C" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="netAddress" name-db="netAddress" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="网址" description="" ID="2CDCE4F3645D26DAF3445D2C29229E07" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="E4A48B52C361FE535401C79413B3E16D" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="postCode" name-db="postCode" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="邮编" description="" ID="AE882B4AF4E1C478DEA74D5937BA3209" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="takePayBalance" name-db="takePayBalance" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="应收/应付余额" description="" ID="100791D7469E533B3DCAF3A79D5F05D5">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_CUSPROVIDER_INFO" name="initPayBalance" name-db="initPayBalance" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="初始化应收/应付余额" description="" ID="1E4B25CD2FEEF75F0059CAF1375D5450">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_CUSPROVIDER_INFO" name="busPerson" name-db="busPerson" type="guid" primary-key="false" keep-valid="false" author="" title="业务负责人" description="" ID="1E4B25CD2FEEF75F0059CAF1375D5458" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="accountPeriod" name-db="accountPeriod" type="int" primary-key="false" keep-valid="false" author="" title="帐期" description="" ID="87EC822BB4C95A86FC2F39BCCF833582" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="accountRemind" name-db="accountRemind" type="int" primary-key="false" keep-valid="false" author="" title="帐期提醒" description="" ID="53A16A8E259F83CAC55A32606CDE72B1" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="isReflag" name-db="isReflag" type="boolean" primary-key="false" keep-valid="false" author="" title="关联标志" description="" ID="FE7E86701E3E1B41FEC8FFA921D9E315" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="createPerson" name-db="createPerson" type="nvarchar(32)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="FC9760B15F87C3C1ECFD6B31DBEB7814" />
    <field dbtable="SA_CUSPROVIDER_INFO" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="90A0E7E2C78394AEA0DDC0DF41149084" />
  </fields>
  <indexs>
    <index author="" name="INDEX_PARTNER_NAME" name-db="INDEX_PARTNER_NAME" title="INDEX_PARTNER_NAME" unique="true" description="">
      <index-item fieldID="F69CF462E93AAB6D1E2C92A3F7B53502" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="CF59DB40F29373062104F0384AFD55B1" field="cusproType" field-author="" desc="false" />
      <index-item fieldID="93BD65E328E7A9C11E23A8D7F02975AF" field="cusproName" field-author="" desc="false" />
    </index>
    <index author="" name="INDEX_PARTNER_NAME2" name-db="INDEX_PARTNER_NAME2" title="INDEX_PARTNER_NAME2" unique="true" description="">
      <index-item fieldID="F69CF462E93AAB6D1E2C92A3F7B53502" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="CF59DB40F29373062104F0384AFD55B1" field="cusproType" field-author="" desc="false" />
      <index-item fieldID="28A6873B1B37B38C88AB8254801A07CF" field="cusproShortName" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_CUSPROVIDER_INFO" name-db="SA_CUSPROVIDER_INFO" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

