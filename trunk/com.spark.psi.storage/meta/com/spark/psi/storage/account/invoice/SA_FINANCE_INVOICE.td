<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_FINANCE_INVOICE" ID="FC738E22D64553155D940999D26026B8" name-db="SA_FINANCE_INVOICE" category="业务主体" title="开票登记信息表" author="" description="" packagename="com.spark.psi.storage.account.invoice" name="SA_FINANCE_INVOICE" isneedrebuild="false">
  <fields>
    <field dbtable="SA_FINANCE_INVOICE" name="cusproGuid" name-db="cusproGuid" type="guid" primary-key="false" keep-valid="false" author="" title="客户GUID" description="" ID="6FF0B84666099DEA5B168D4D19916BFB" />
    <field dbtable="SA_FINANCE_INVOICE" name="cusproName" name-db="cusproName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="客户简称" description="" ID="6DCE0C1680961D9ACD29C0B5999D6D71" />
    <field dbtable="SA_FINANCE_INVOICE" name="cusproNamePY" name-db="cusproNamePY" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="客户简称拼音" description="" ID="17542AA521CD9E037B0CEF7CB7C6B09D" />
    <field dbtable="SA_FINANCE_INVOICE" name="cusproFullName" name-db="cusproFullName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="客户全称" description="" ID="B403B35761CEA4D3ACB7AC24205540F9" />
    <field dbtable="SA_FINANCE_INVOICE" name="cusproFullNamePY" name-db="cusproFullNamePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="客户全称拼音" description="" ID="2B64612C5ECA58C22E86E2380C9DA2FF" />
    <field dbtable="SA_FINANCE_INVOICE" name="invoType" name-db="invoType" type="char(2)" primary-key="false" keep-valid="false" author="" title="发票类型" description="" ID="FDFEFE957A66E5C37BAA7F5A04332ED5" />
    <field dbtable="SA_FINANCE_INVOICE" name="invoCode" name-db="invoCode" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="发票号" description="" ID="3DB2B13F7977C01859B0FA6EE7E14883" />
    <field dbtable="SA_FINANCE_INVOICE" name="invoAmount" name-db="invoAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="开票金额" description="" ID="144BCCFCC43313B3E98C3D7F27226CEF" />
    <field dbtable="SA_FINANCE_INVOICE" name="invoPerson" name-db="invoPerson" type="guid" primary-key="false" keep-valid="false" author="" title="开票人GUID" description="" ID="E8EA8210EB8C4E93C92D3C51AB42B9F7" />
    <field dbtable="SA_FINANCE_INVOICE" name="invoPersonName" name-db="invoPersonName" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="开票人" description="" ID="8D3C4A930C8D9F9D73F69C59EBC3B6B3" />
    <field dbtable="SA_FINANCE_INVOICE" name="invoDate" name-db="invoDate" type="date" primary-key="false" keep-valid="false" author="" title="开票日期" description="" ID="BD4D39E4CB1A2C90CD97748E0859D043" />
    <field dbtable="SA_FINANCE_INVOICE" name="isInvalided" name-db="isInvalided" type="boolean" primary-key="false" keep-valid="false" author="" title="是否已作废" description="" ID="1061B1C9E5B51E64DBDBEAF735E9230E" />
    <field dbtable="SA_FINANCE_INVOICE" name="invalidReason" name-db="invalidReason" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="作废原因" description="" ID="D96B55567C9FFFB6C9854D061F80928A" />
    <field dbtable="SA_FINANCE_INVOICE" name="invalidPerson" name-db="invalidPerson" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="作废人" description="" ID="0293E8129A5331FE950E7BBF3B0A56F8" />
    <field dbtable="SA_FINANCE_INVOICE" name="invalidDate" name-db="invalidDate" type="date" primary-key="false" keep-valid="false" author="" title="作废日期" description="" ID="D4511785A306136925ADA557AE1C1BBB" />
    <field dbtable="SA_FINANCE_INVOICE" name="createPerson" name-db="createPerson" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="4AB773034CA6F468717ED0562773D73C" />
    <field dbtable="SA_FINANCE_INVOICE" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="F2CFD311607EA8D5F3CB18D5618E6F46" />
    <field dbtable="SA_FINANCE_INVOICE" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="所属部门" description="" ID="7E545CF37F155F59DEF801E1D3CFD9CF" />
    <field dbtable="SA_FINANCE_INVOICE" name="cusDeptGuid" name-db="cusDeptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="业务负责人部门" description="" ID="F5DF76E982855BCA9FD84C1BA322C4C0" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_FINANCE_INVOICE" name-db="SA_FINANCE_INVOICE" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

