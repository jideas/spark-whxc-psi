<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Base_Customer" ID="4D616FF17C4CA3111792C899E35D884B" name-db="PSI_Base_Customer" category="基础数据主体" title="客户信息" author="" description="" packagename="com.spark.psi.storage.base.customer" name="PSI_Base_Customer" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Base_Customer" name="customerName" name-db="customerName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="全称" description="" ID="9CEBB359F17E372748F466622C308B76" />
    <field dbtable="PSI_Base_Customer" name="namePY" name-db="namePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="全称拼音" description="" ID="93F49A6D222A2F7A96DE2B4DCB036A32" />
    <field dbtable="PSI_Base_Customer" name="shortName" name-db="shortName" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="简称" description="" ID="7C86CB64755DE04E9D083BA736E9E610" />
    <field dbtable="PSI_Base_Customer" name="customerNo" name-db="customerNo" type="nvarchar(25)" primary-key="false" keep-valid="false" author="" title="编号" description="" ID="7BDA65FF6D33DA020C1D6A40A878D0F9" />
    <field dbtable="PSI_Base_Customer" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="2617035EF445387C7A54871DDA9257F9" />
    <field dbtable="PSI_Base_Customer" name="province" name-db="province" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="省份" description="" ID="105E02FCEBA96530EBAF69385642FAEF" />
    <field dbtable="PSI_Base_Customer" name="city" name-db="city" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="城市" description="" ID="A56B18A8E815E24F3F6630DFA5A373B9" />
    <field dbtable="PSI_Base_Customer" name="town" name-db="town" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="区县" description="" ID="EEDF2BA936048C7A07756EA31B394D98" />
    <field dbtable="PSI_Base_Customer" name="address" name-db="address" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="地址" description="" ID="601763BA8F51DE607631CBA346D630D7" />
    <field dbtable="PSI_Base_Customer" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="8B367AE9E44F819A6196F9B817AD3907" />
    <field dbtable="PSI_Base_Customer" name="postcode" name-db="postcode" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="邮编" description="" ID="287B62C7BA913B475242BA7968D7A73B" />
    <field dbtable="PSI_Base_Customer" name="taxRate" name-db="taxRate" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="税率" description="" ID="C184A25229EC5B2768A7558AEAE07FDE" />
    <field dbtable="PSI_Base_Customer" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="所属部门" description="" ID="37B5AF0763D1887B1263176C76326F20" />
    <field dbtable="PSI_Base_Customer" name="businessPerson" name-db="businessPerson" type="guid" primary-key="false" keep-valid="false" author="" title="业务负责人" description="" ID="E51C0CC2747CBE5F3BB0F644E267236F" />
    <field dbtable="PSI_Base_Customer" name="totalSalesAmount" name-db="totalSalesAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="销售总额" description="" ID="44A17426E3ED80959DEF59E4B5773604" />
    <field dbtable="PSI_Base_Customer" name="lastSalesDate" name-db="lastSalesDate" type="date" primary-key="false" keep-valid="false" author="" title="最近销售日期" description="" ID="95C762CEFCDF74FCAA0A7937F09FAA13" />
    <field dbtable="PSI_Base_Customer" name="totalSalesTimes" name-db="totalSalesTimes" type="int" primary-key="false" keep-valid="false" author="" title="销售次数" description="" ID="FD0DF16F0A8A91E14B11F1F5D429CCA6" />
    <field dbtable="PSI_Base_Customer" name="workTel" name-db="workTel" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="工作电话" description="" ID="1EBF7AA925C0FCD4FB18E5ECF5E7E807" />
    <field dbtable="PSI_Base_Customer" name="fax" name-db="fax" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="传真" description="" ID="D725E259EA04F063019A67381D09D9E6" />
    <field dbtable="PSI_Base_Customer" name="accountPeriod" name-db="accountPeriod" type="int" primary-key="false" keep-valid="false" author="" title="帐期" description="" ID="9F5CE7B86DF0897FCDEC45379F82AC6C" />
    <field dbtable="PSI_Base_Customer" name="accountRemind" name-db="accountRemind" type="int" primary-key="false" keep-valid="false" author="" title="帐期提醒" description="" ID="8DEBD8CBDF0C2D6D522B2A84AC9858C7" />
    <field dbtable="PSI_Base_Customer" name="pricePolicy" name-db="pricePolicy" type="char(2)" primary-key="false" keep-valid="false" author="" title="价格政策" description="" ID="85D82935C2579B165776C043563376CE" />
    <field dbtable="PSI_Base_Customer" name="creditAmount" name-db="creditAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="信用额度" description="" ID="EF5BC3BF830764A8042A0CA8311DF930" />
    <field dbtable="PSI_Base_Customer" name="linkmanName" name-db="linkmanName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人姓名" description="" ID="410D8CF4418BC4D62338159EF2A7FBCB" />
    <field dbtable="PSI_Base_Customer" name="linkmanSuffix" name-db="linkmanSuffix" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="联系人后缀" description="" ID="8C9EEE1EC84951F2AF08350276D0DFE0" />
    <field dbtable="PSI_Base_Customer" name="linkmanNamePY" name-db="linkmanNamePY" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="联系人拼音" description="" ID="FD429F014FA69A4444A572E0B9326670" />
    <field dbtable="PSI_Base_Customer" name="linkmanTel" name-db="linkmanTel" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人电话" description="" ID="EC1452C498133C6F9D0DBD9ED7A702DE" />
    <field dbtable="PSI_Base_Customer" name="linkmanMobile" name-db="linkmanMobile" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人手机" description="" ID="3ADE4A2E0C4BC467720014CD2F540D2A" />
    <field dbtable="PSI_Base_Customer" name="linkmanEmail" name-db="linkmanEmail" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="联系人邮箱" description="" ID="F46CDBAB12B769E6078316F8C187B84F" />
    <field dbtable="PSI_Base_Customer" name="canDelete" name-db="canDelete" type="boolean" primary-key="false" keep-valid="false" author="" title="是否可以删除" description="" ID="CAD134CBA59BC66FE7E8900DB9DE23F6" />
    <field dbtable="PSI_Base_Customer" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="64D9AD7768409A02E43AE74D3B87A2B1" />
    <field dbtable="PSI_Base_Customer" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Guid" description="" ID="FDE8BB7211B8EDB3AB3FF909EDA4908F" />
    <field dbtable="PSI_Base_Customer" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="3ED9400A4E3DDF41ECAB127DE7AAE302" />
    <field dbtable="PSI_Base_Customer" name="customerType" name-db="customerType" type="char(2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="5753E08367578E400988901EE9E25AAA" />
    <field dbtable="PSI_Base_Customer" name="taxNumber" name-db="taxNumber" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="37709B82642A3A5D5F5D0C52891A44D6" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Base_Customer" name-db="PSI_Base_Customer" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

