<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_CONTACKBOOK" ID="EDE77E27BA0355B131926CD32807B843" name-db="SA_CONTACKBOOK" category="" title="" author="" description="通讯录" packagename="com.spark.psi.base.storage.partner" name="SA_CONTACKBOOK" isneedrebuild="false">
  <fields>
    <field dbtable="SA_CONTACKBOOK" name="tenantsGUID" name-db="tenantsGUID" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="3E62514C4A8468F74D8CA17D05E2CF3F" />
    <field dbtable="SA_CONTACKBOOK" name="name" name-db="name" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="姓名" description="" ID="42830414CA77A00800D3969360ADC481" />
    <field dbtable="SA_CONTACKBOOK" name="namePY" name-db="namePY" type="varchar(40)" primary-key="false" keep-valid="false" author="" title="" description="" ID="DA1C1C6198E73205052C7BA02EE79A50" />
    <field dbtable="SA_CONTACKBOOK" name="main" name-db="main" type="boolean" primary-key="false" keep-valid="false" author="" title="主联系人" description="" ID="225FB3CC8CA019BFC8ADDC29C8E53E53" />
    <field dbtable="SA_CONTACKBOOK" name="sex" name-db="sex" type="char(2)" primary-key="false" keep-valid="false" author="" title="性别" description="" ID="2C3ECF90B1C1622D931110503A109866" />
    <field dbtable="SA_CONTACKBOOK" name="nickname" name-db="nickname" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="尊称" description="" ID="528CDF8F3C4C02BE670B2D44F640B475" />
    <field dbtable="SA_CONTACKBOOK" name="mobile" name-db="mobile" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="手机" description="" ID="A5DD86F8D57B032AEFCE7F340E92ADAB" />
    <field dbtable="SA_CONTACKBOOK" name="phone" name-db="phone" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="固话" description="" ID="CBFC604FC69CB29054F2B31FB7280C70" />
    <field dbtable="SA_CONTACKBOOK" name="email" name-db="email" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="邮箱" description="" ID="7FFAB0D267E44028158C58984E0DFD8C" />
    <field dbtable="SA_CONTACKBOOK" name="company" name-db="company" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="公司" description="" ID="0032323D9F49B2254E05F2E70F25B779" />
    <field dbtable="SA_CONTACKBOOK" name="companyPY" name-db="companyPY" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="" description="" ID="785EA5246F1364EB5D0E60562967B7FB" />
    <field dbtable="SA_CONTACKBOOK" name="department" name-db="department" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="部门" description="" ID="D567226B819C0FBB4415CD2F8DB137CC" />
    <field dbtable="SA_CONTACKBOOK" name="job" name-db="job" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="职位" description="" ID="003226D8B3C995ABC9F5453EC01280A8" />
    <field dbtable="SA_CONTACKBOOK" name="jobPY" name-db="jobPY" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="98C5CA09FE301C92E87CFC2350B9BFA2" />
    <field dbtable="SA_CONTACKBOOK" name="qq" name-db="qq" type="nvarchar(15)" primary-key="false" keep-valid="false" author="" title="qq" description="" ID="ECBCA99F3DA7414B256A056F2A85716A" />
    <field dbtable="SA_CONTACKBOOK" name="msn" name-db="msn" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="msn" description="" ID="2FCFCE163874E83308F810272368DB3A" />
    <field dbtable="SA_CONTACKBOOK" name="aliim" name-db="aliim" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="旺旺" description="" ID="6E229F7238B4E67AAB1A7854647AAAE2" />
    <field dbtable="SA_CONTACKBOOK" name="birth" name-db="birth" type="date" primary-key="false" keep-valid="false" author="" title="生日" description="" ID="A22A70156E23992D0A87C3AA6E7104B9" />
    <field dbtable="SA_CONTACKBOOK" name="fav" name-db="fav" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="爱好" description="" ID="D80C119C442F6FC72BF596F8AC7EE65E" />
    <field dbtable="SA_CONTACKBOOK" name="comments" name-db="comments" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="1B9839C179603CB7197318C004942D92" />
    <field dbtable="SA_CONTACKBOOK" name="type" name-db="type" type="char(2)" primary-key="false" keep-valid="false" author="" title="类型" description="" ID="D183DE5C782B756AD645A4521A9E08B5" />
    <field dbtable="SA_CONTACKBOOK" name="createPersonGUID" name-db="createPersonGUID" type="guid" primary-key="false" keep-valid="false" author="" title="创建人GUID" description="" ID="9D363641578DE24B7D00384D2F1A2166" />
    <field dbtable="SA_CONTACKBOOK" name="createPerson" name-db="createPerson" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人明文" description="" ID="785B2F239C2E4DDCC04CC5F30B5D6FD4" />
    <field dbtable="SA_CONTACKBOOK" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="B1908AC15782EEA3961CCF8E32796BD2" />
    <field dbtable="SA_CONTACKBOOK" name="cusproGuid" name-db="cusproGuid" type="guid" primary-key="false" keep-valid="false" author="" title="客户供应商编号" description="" ID="53F3A80E045492C873F3993C3B1F4588" />
    <field dbtable="SA_CONTACKBOOK" name="province" name-db="province" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="省份" description="" ID="9E8F27D787C9BFF57595296FE2D160C7" />
    <field dbtable="SA_CONTACKBOOK" name="city" name-db="city" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="城市" description="" ID="63EEA4D860F724B1B56F55DFE64040B9" />
    <field dbtable="SA_CONTACKBOOK" name="district" name-db="district" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="地址" description="" ID="0CC8E4F4FCAC210CD8ED524020C47138" />
    <field dbtable="SA_CONTACKBOOK" name="address" name-db="address" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="详细地址" description="" ID="9BEDF6A6CCA8929FDF6D55FAAE2E9A9F" />
    <field dbtable="SA_CONTACKBOOK" name="postCode" name-db="postCode" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="邮编" description="" ID="4144FE5B29D24C4FF16D9A88D90EA655" />
    <field dbtable="SA_CONTACKBOOK" name="lastDate" name-db="lastDate" type="date" primary-key="false" keep-valid="false" author="" title="最近使用日期" description="" ID="BC036DF580CDA9B7437029B90DDF3BE3" />
  </fields>
  <indexs>
    <index author="" name="INDEX_CONTACKBOOK" name-db="INDEX_CONTACKBOOK" title="INDEX_CONTACKBOOK" unique="false" description="">
      <index-item fieldID="53F3A80E045492C873F3993C3B1F4588" field="cusproGuid" field-author="" desc="false" />
      <index-item fieldID="D183DE5C782B756AD645A4521A9E08B5" field="type" field-author="" desc="false" />
    </index>
    <index author="" name="INDEX_CONTACKBOOK2" name-db="INDEX_CONTACKBOOK2" title="INDEX_CONTACKBOOK2" unique="false" description="">
      <index-item fieldID="3E62514C4A8468F74D8CA17D05E2CF3F" field="tenantsGUID" field-author="" desc="false" />
      <index-item fieldID="D183DE5C782B756AD645A4521A9E08B5" field="type" field-author="" desc="false" />
      <index-item fieldID="42830414CA77A00800D3969360ADC481" field="name" field-author="" desc="false" />
      <index-item fieldID="DA1C1C6198E73205052C7BA02EE79A50" field="namePY" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_CONTACKBOOK" name-db="SA_CONTACKBOOK" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

