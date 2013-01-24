<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Base_Linkman" ID="CD1ADFB64AB8C9F7979540A225AF8427" name-db="PSI_Base_Linkman" category="基础数据主体" title="联系人" author="" description="" packagename="com.spark.psi.storage.base.linkman" name="PSI_Base_Linkman" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Base_Linkman" name="name" name-db="name" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="姓名" description="" ID="EFA7559EB57E6E8421677B3DFF5CBF10" />
    <field dbtable="PSI_Base_Linkman" name="namePY" name-db="namePY" type="varchar(40)" primary-key="false" keep-valid="false" author="" title="" description="" ID="3D3568F6AB3B1085911E395033371C2B" />
    <field dbtable="PSI_Base_Linkman" name="main" name-db="main" type="boolean" primary-key="false" keep-valid="false" author="" title="主联系人" description="" ID="0ADEF111D31BBBCBB354417FACB42690" />
    <field dbtable="PSI_Base_Linkman" name="sex" name-db="sex" type="char(2)" primary-key="false" keep-valid="false" author="" title="性别" description="" ID="314706DB97DC8C5EFA945CF05A0F1043" />
    <field dbtable="PSI_Base_Linkman" name="nickname" name-db="nickname" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="尊称" description="" ID="F8B6F09F5BF77D0723B5ED68C9E3174C" />
    <field dbtable="PSI_Base_Linkman" name="mobile" name-db="mobile" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="手机" description="" ID="146938A818A6C501A078DC9F611BBE9F" />
    <field dbtable="PSI_Base_Linkman" name="phone" name-db="phone" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="固话" description="" ID="51F4A23DCE0F97F0983B89922F6F999F" />
    <field dbtable="PSI_Base_Linkman" name="email" name-db="email" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="邮箱" description="" ID="C07299CA2C6F5C37EF24007EF7882223" />
    <field dbtable="PSI_Base_Linkman" name="company" name-db="company" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="公司" description="" ID="D57E2A9D893F6B709A64CBAF367DBD4C" />
    <field dbtable="PSI_Base_Linkman" name="companyPY" name-db="companyPY" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="" description="" ID="8F2326CD29707314FD655922E43C2990" />
    <field dbtable="PSI_Base_Linkman" name="department" name-db="department" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="部门" description="" ID="D8239E5D3BE9146F8C1B8CF066AF1A3F" />
    <field dbtable="PSI_Base_Linkman" name="job" name-db="job" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="职位" description="" ID="16706190EEB48F205A083354B1CAFFD1" />
    <field dbtable="PSI_Base_Linkman" name="jobPY" name-db="jobPY" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="FEE27ACADA0A27E745227280C7B0DEC8" />
    <field dbtable="PSI_Base_Linkman" name="qq" name-db="qq" type="nvarchar(15)" primary-key="false" keep-valid="false" author="" title="qq" description="" ID="4C8C3A39FDEC6BF73943C8FE2C010ADF" />
    <field dbtable="PSI_Base_Linkman" name="msn" name-db="msn" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="msn" description="" ID="9A5A44F3FBFB6D5FE6C19D3B62FD2E73" />
    <field dbtable="PSI_Base_Linkman" name="aliim" name-db="aliim" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="旺旺" description="" ID="7F47CC4A011F41A9F5AD247A4F0E742A" />
    <field dbtable="PSI_Base_Linkman" name="birth" name-db="birth" type="date" primary-key="false" keep-valid="false" author="" title="生日" description="" ID="5673D066E542129CAB5E4CCF3559D2BB" />
    <field dbtable="PSI_Base_Linkman" name="fav" name-db="fav" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="爱好" description="" ID="38057ADBA1FF97234876909163297286" />
    <field dbtable="PSI_Base_Linkman" name="comments" name-db="comments" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="AD834090349353225E466237B322C2FD" />
    <field dbtable="PSI_Base_Linkman" name="type" name-db="type" type="char(2)" primary-key="false" keep-valid="false" author="" title="类型" description="" ID="F01263290419402AC3B59BF61B5F3B17" />
    <field dbtable="PSI_Base_Linkman" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人GUID" description="" ID="88579D128FFFD58578AD9A093BF7BCA5" />
    <field dbtable="PSI_Base_Linkman" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人明文" description="" ID="770B0DEBDCE647996D34BBEB41A5F80B" />
    <field dbtable="PSI_Base_Linkman" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="CAAC6790CB5C8B8EF0CDD64D5A4BDC0A" />
    <field dbtable="PSI_Base_Linkman" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="客户供应商编号" description="" ID="E7498461EB66CBD5EFB2326D8517BE5E" />
    <field dbtable="PSI_Base_Linkman" name="province" name-db="province" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="省份" description="" ID="6728C754281BDBD22F62068CCBC8BB84" />
    <field dbtable="PSI_Base_Linkman" name="city" name-db="city" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="城市" description="" ID="A673868573A925CF1E7DD17931A74D52" />
    <field dbtable="PSI_Base_Linkman" name="district" name-db="district" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="地址" description="" ID="2AC6F8BA346F556FFFA325DFE9E7FD75" />
    <field dbtable="PSI_Base_Linkman" name="address" name-db="address" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="详细地址" description="" ID="EAFECE12A2099BBA56A21FDF9C45DA74" />
    <field dbtable="PSI_Base_Linkman" name="postcode" name-db="postcode" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="邮编" description="" ID="A695D03D9DD6FA2E76C233EFEA130CFA" />
    <field dbtable="PSI_Base_Linkman" name="lastDate" name-db="lastDate" type="date" primary-key="false" keep-valid="false" author="" title="最近使用日期" description="" ID="D41FC9F5BA377E1F08F0CABCE20B4570" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Base_Linkman" name-db="PSI_Base_Linkman" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

