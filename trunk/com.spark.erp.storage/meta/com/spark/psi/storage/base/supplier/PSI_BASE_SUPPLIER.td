<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_BASE_SUPPLIER" ID="89AE691E9B893E660E41FCA8BA41297D" name-db="PSI_BASE_SUPPLIER" category="基础数据主体" title="供应商信息" author="" description="" packagename="com.spark.psi.storage.base.supplier" name="PSI_BASE_SUPPLIER" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_BASE_SUPPLIER" name="supplierName" name-db="supplierName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="全称" description="" ID="0528FC094D8E8BEADD7BC1E2A5A95C41" />
    <field dbtable="PSI_BASE_SUPPLIER" name="namePY" name-db="namePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="全称拼音" description="" ID="9781F18A6F2FA22D19D7D0CEDF09E7B3" />
    <field dbtable="PSI_BASE_SUPPLIER" name="shortName" name-db="shortName" type="nvarchar(12)" primary-key="false" keep-valid="false" author="" title="简称" description="" ID="64439378BBD0237D38BCADC44235FBC2" />
    <field dbtable="PSI_BASE_SUPPLIER" name="supplierNo" name-db="supplierNo" type="nvarchar(25)" primary-key="false" keep-valid="false" author="" title="编号" description="" ID="2291BE866FC21058A85068653F0E4795" />
    <field dbtable="PSI_BASE_SUPPLIER" name="supplierType" name-db="supplierType" type="char(2)" primary-key="false" keep-valid="false" author="" title="类型" description="" ID="E598E782208E6B5E40DBA28531079B55" />
    <field dbtable="PSI_BASE_SUPPLIER" name="vatNo" name-db="vatNo" type="nvarchar(25)" primary-key="false" keep-valid="false" author="" title="增值税号" description="" ID="FF53EC25344C81B84DAA9CF07DCC5055" />
    <field dbtable="PSI_BASE_SUPPLIER" name="province" name-db="province" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="省份" description="" ID="0FE6E1167C8DF24BDECBB850F566A812" />
    <field dbtable="PSI_BASE_SUPPLIER" name="taxRate" name-db="taxRate" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="税率" description="" ID="67CB9E4F8A6C222900CCE7FB4908C1B1" />
    <field dbtable="PSI_BASE_SUPPLIER" name="city" name-db="city" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="城市" description="" ID="649CFFEE51D8DC3B328F9B7C563CC8C0" />
    <field dbtable="PSI_BASE_SUPPLIER" name="town" name-db="town" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="区县" description="" ID="6D416E30DF760C71D44B4A37158FFC7C" />
    <field dbtable="PSI_BASE_SUPPLIER" name="address" name-db="address" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="地址" description="" ID="1DF8635E0758BCE2C977EEE465747DE1" />
    <field dbtable="PSI_BASE_SUPPLIER" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="E814CDE8B5FE76DF90B3676C1F1EA9FD" />
    <field dbtable="PSI_BASE_SUPPLIER" name="postcode" name-db="postcode" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="邮编" description="" ID="953656F81EFB579C12DAF724AB005DF3" />
    <field dbtable="PSI_BASE_SUPPLIER" name="totalPurchaseAmount" name-db="totalPurchaseAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="采购总额" description="" ID="991EDD7C0D99A0E20C6D8971A30CA3BE" />
    <field dbtable="PSI_BASE_SUPPLIER" name="lastPurchaseDate" name-db="lastPurchaseDate" type="date" primary-key="false" keep-valid="false" author="" title="最近采购日期" description="" ID="03ED3051E09E5B89FA19A3BF3F836A23" />
    <field dbtable="PSI_BASE_SUPPLIER" name="totalPurchaseTimes" name-db="totalPurchaseTimes" type="int" primary-key="false" keep-valid="false" author="" title="采购次数" description="" ID="FF4BEAC0FAF0AB341737A21C0B5800E2" />
    <field dbtable="PSI_BASE_SUPPLIER" name="noticeAddress" name-db="noticeAddress" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="通知地址" description="" ID="5777F99CD82469D4D126E78E671D5762" />
    <field dbtable="PSI_BASE_SUPPLIER" name="noticePostcode" name-db="noticePostcode" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="通知邮编" description="" ID="1FD37C990EF8C2A728BBA2AC5799A20A" />
    <field dbtable="PSI_BASE_SUPPLIER" name="workTel" name-db="workTel" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="工作电话" description="" ID="ABB071D15D99C4384E7EF798111BE735" />
    <field dbtable="PSI_BASE_SUPPLIER" name="fax" name-db="fax" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="传真" description="" ID="DA5C84AF578C9083095EF7262516C445" />
    <field dbtable="PSI_BASE_SUPPLIER" name="accountPeriod" name-db="accountPeriod" type="int" primary-key="false" keep-valid="false" author="" title="帐期" description="" ID="928B9BDE8DE320918AEB72F4F3EFF468" />
    <field dbtable="PSI_BASE_SUPPLIER" name="accountRemind" name-db="accountRemind" type="int" primary-key="false" keep-valid="false" author="" title="帐期提醒天数" description="" ID="628CD6E5F946CF6B86646CDA8B4D9CAD" />
    <field dbtable="PSI_BASE_SUPPLIER" name="linkmanName" name-db="linkmanName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人姓名" description="" ID="A02B6A0F78E61F4C8B552D7C39F3B7AC" />
    <field dbtable="PSI_BASE_SUPPLIER" name="linkmanSuffix" name-db="linkmanSuffix" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="联系人后缀" description="" ID="A35EADBCBC06044E3173F478B18648E1" />
    <field dbtable="PSI_BASE_SUPPLIER" name="linkmanNamePY" name-db="linkmanNamePY" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="联系人拼音" description="" ID="A8028E961B9F274549DBF94A27EEC1C3" />
    <field dbtable="PSI_BASE_SUPPLIER" name="linkmanTel" name-db="linkmanTel" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人电话" description="" ID="B61FE43D55EFFE417256E360787208BA" />
    <field dbtable="PSI_BASE_SUPPLIER" name="linkmanMobile" name-db="linkmanMobile" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人手机" description="" ID="3EE72BB302F3A8C52563FECA45487C7F" />
    <field dbtable="PSI_BASE_SUPPLIER" name="linkmanEmail" name-db="linkmanEmail" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="联系人邮箱" description="" ID="1CB2DF452F9B3DACC6A3A4871E02C1FD" />
    <field dbtable="PSI_BASE_SUPPLIER" name="canDelete" name-db="canDelete" type="boolean" primary-key="false" keep-valid="false" author="" title="是否可以删除" description="" ID="DFD9947FF75E428F8486577CB1DC9BB4" />
    <field dbtable="PSI_BASE_SUPPLIER" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="CF491526B04A66EEFBE0286E0D56645D" />
    <field dbtable="PSI_BASE_SUPPLIER" name="creditAmount" name-db="creditAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="信用额度" description="" ID="4AB198FB6915C1827450626E532238E8" />
    <field dbtable="PSI_BASE_SUPPLIER" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="725CCF570CA90ED30AAA70886E7289FD" />
    <field dbtable="PSI_BASE_SUPPLIER" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Guid" description="" ID="08555A32A39D7AD555AF5EE51E951FD0" />
    <field dbtable="PSI_BASE_SUPPLIER" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="E9AFD80190D1AD1344CD5563FF5ABF83" />
    <field dbtable="PSI_BASE_SUPPLIER" name="cooperation" name-db="cooperation" type="char(2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="2F0D1E52111A9777C95E6F1BE57803DD" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_BASE_SUPPLIER" name-db="PSI_BASE_SUPPLIER" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

