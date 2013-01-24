<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Sales_Return" ID="F3BC70FCAB43F863AE085E2C12E6D7DC" name-db="PSI_Sales_Return" category="" title="销售退货单" author="" description="" packagename="com.spark.psi.storage.sales" name="PSI_Sales_Return" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Sales_Return" name="billsNo" name-db="billsNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="退货单编号" description="" ID="92D30B99EE1C7F9FD36494456B747481" />
    <field dbtable="PSI_Sales_Return" name="billType" name-db="billType" type="char(2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="A3C0E0B4C78CA2EA667A6453100297C5" />
    <field dbtable="PSI_Sales_Return" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="客户GUID" description="" ID="4CEF7B733E66D2439738C338ABE48616" />
    <field dbtable="PSI_Sales_Return" name="shortName" name-db="shortName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="客户简称" description="" ID="13EE64CC2C73DA3EA1A92DC5D958B428" />
    <field dbtable="PSI_Sales_Return" name="partnerName" name-db="partnerName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="客户全称" description="" ID="3311AB30E9BF7F5B696BAE3774CB65F1" />
    <field dbtable="PSI_Sales_Return" name="partnerNamePY" name-db="partnerNamePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="客户拼音" description="" ID="01E46D6B1D8D2A3376FD58BC31A6EF9C" />
    <field dbtable="PSI_Sales_Return" name="fax" name-db="fax" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="客户传真" description="" ID="E2AEA94D9C5940F416C77EC816272C02" />
    <field dbtable="PSI_Sales_Return" name="linkman" name-db="linkman" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="联系人" description="" ID="7E7F2080EF15ABE62337B3AAF29B8089" />
    <field dbtable="PSI_Sales_Return" name="rejectReason" name-db="rejectReason" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="退回原因" description="" ID="F7A3506A82445B51E5D7236EC38BFD5F" />
    <field dbtable="PSI_Sales_Return" name="stopReason" name-db="stopReason" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="中止原因" description="" ID="19B2D59609AB4F3F3EDFA85DC7558CF0" />
    <field dbtable="PSI_Sales_Return" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="E890C55E18203A31CCB994C9B65660FA" />
    <field dbtable="PSI_Sales_Return" name="totalAmount" name-db="totalAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="总金额" description="" ID="8D93226301BA39138AB3B4EDCE473E66" />
    <field dbtable="PSI_Sales_Return" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="制单人GUID" description="" ID="DEC4BF15BFFD2EC34D923B1AAE2767C2" />
    <field dbtable="PSI_Sales_Return" name="creator" name-db="creator" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="制单人" description="" ID="7C721FC7F6E8F61A940882852821F3AF" />
    <field dbtable="PSI_Sales_Return" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="制单日期" description="" ID="3B19B9EAC036F58A300B9358BEF5641E" />
    <field dbtable="PSI_Sales_Return" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="所属部门GUID" description="" ID="9FA6749AF9F174C8D862709978E2D55B" />
    <field dbtable="PSI_Sales_Return" name="storeId" name-db="storeId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="5ADB5FF0DF4EC675DB364D7B59D41ACF" />
    <field dbtable="PSI_Sales_Return" name="storeName" name-db="storeName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="" description="" ID="7DB6AD92B9BF1489FFB552978215D453" />
    <field dbtable="PSI_Sales_Return" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="706B12149BDD49CE401E8ADAA1646119" />
    <field dbtable="PSI_Sales_Return" name="isStoped" name-db="isStoped" type="boolean" primary-key="false" keep-valid="false" author="" title="中止状态" description="" ID="2A6AEE9709A0EF2EF438BA8A6823D58A" />
    <field dbtable="PSI_Sales_Return" name="approveStr" name-db="approveStr" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="审核人及日期" description="" ID="EB9BBA13D3F89CC9FBA41FAD06063AF5" />
    <field dbtable="PSI_Sales_Return" name="finishedDate" name-db="finishedDate" type="date" primary-key="false" keep-valid="false" author="" title="完成日期" description="" ID="86AF11796F49A16603F72DDAF10CB26E" />
    <field dbtable="PSI_Sales_Return" name="address" name-db="address" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="" description="" ID="E10149B2626978DAB1850A626FD24872" />
    <field dbtable="PSI_Sales_Return" name="deliveryDate" name-db="deliveryDate" type="date" primary-key="false" keep-valid="false" author="" title="" description="" ID="07E1DF66C966FC5762D859223F644927" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Sales_Return" name-db="PSI_Sales_Return" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

