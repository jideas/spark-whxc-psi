<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_SALES_RETURN" ID="F409034697115B146CA534701C82AFFD" name-db="SA_SALES_RETURN" category="" title="销售退货信息表" author="" description="" packagename="com.spark.psi.order.storage.sales" name="SA_SALES_RETURN" isneedrebuild="false">
  <fields>
    <field dbtable="SA_SALES_RETURN" name="tenantsId" name-db="tenantsId" type="guid" primary-key="false" keep-valid="false" author="" title="租户GUID" description="" ID="A78E71DA3407C972BE2BB2C0FA49BB91" />
    <field dbtable="SA_SALES_RETURN" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人GUID" description="" ID="67FBDC58F20101FE142DC2D94157B8AA" />
    <field dbtable="SA_SALES_RETURN" name="creator" name-db="creator" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="19C6819FBFB09BA2BA375B21ABB0D54E" />
    <field dbtable="SA_SALES_RETURN" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="37B613BD0E3155E879D275B871838CC5" />
    <field dbtable="SA_SALES_RETURN" name="orderNumber" name-db="orderNumber" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="销售订单编号" description="" ID="2EE92CDC7D87AC19F2ABCC002D2BC81C" />
    <field dbtable="SA_SALES_RETURN" name="partnerId" name-db="partnerId" type="guid" primary-key="false" keep-valid="false" author="" title="客户GUID" description="" ID="368D9116817D03050EC6636C394483E4" />
    <field dbtable="SA_SALES_RETURN" name="partnerShortName" name-db="partnerShortName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="客户名称" description="" ID="E8C22D2B37B3D86C0AB7A1ADAAB45D08" />
    <field dbtable="SA_SALES_RETURN" name="partnerName" name-db="partnerName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="客户全称" description="" ID="EB069E51A6875A1F452F7DDDE1AB18BB" />
    <field dbtable="SA_SALES_RETURN" name="partnerNamePY" name-db="partnerNamePY" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="客户拼音" description="" ID="8B16409356B394B10BEC7D29A50BCE07" />
    <field dbtable="SA_SALES_RETURN" name="partnerFax" name-db="partnerFax" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="客户传真" description="" ID="7AD071572B5024FF73DD8584C5E68EA9" />
    <field dbtable="SA_SALES_RETURN" name="contactId" name-db="contactId" type="guid" primary-key="false" keep-valid="false" author="" title="联系人GUID" description="" ID="1E26C4B346CAEEDBE6668C842A7A193C" />
    <field dbtable="SA_SALES_RETURN" name="contactName" name-db="contactName" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="联系人" description="" ID="B00A6BF749B16733820C63FE8FFBF5C4" />
    <field dbtable="SA_SALES_RETURN" name="contactTel" name-db="contactTel" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人电话" description="" ID="ED68B302A47516F3D9EB9FE1F3937238" />
    <field dbtable="SA_SALES_RETURN" name="contactPhone" name-db="contactPhone" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="联系人手机" description="" ID="DDF53FDC05FA08F0ED44E55E5F6EF8DD" />
    <field dbtable="SA_SALES_RETURN" name="returnCause" name-db="returnCause" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="退回/中止原因" description="" ID="A117496B4C21058616003B5BE03FE901" />
    <field dbtable="SA_SALES_RETURN" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="9C7974753C24885450DB40A020538559" />
    <field dbtable="SA_SALES_RETURN" name="totalAmount" name-db="totalAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="总金额" description="" ID="6E5214EEFA87E362EE89AE71ADED44D3">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_SALES_RETURN" name="type" name-db="type" type="char(2)" primary-key="false" keep-valid="false" author="" title="类型" description="" ID="70C32AC479EB965B7332644CB8584605" />
    <field dbtable="SA_SALES_RETURN" name="state" name-db="state" type="char(2)" primary-key="false" keep-valid="false" author="" title="处理情况" description="" ID="444400089A081D539A502119208051CF" />
    <field dbtable="SA_SALES_RETURN" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="所属部门部门GUID" description="" ID="EA71F4138314F0DE4F7BE7957191187F" />
    <field dbtable="SA_SALES_RETURN" name="isStoped" name-db="isStoped" type="boolean" primary-key="false" keep-valid="false" author="" title="是否中止状态" description="" ID="BD4A9327729E5B9CE6878CF34935945F" />
    <field dbtable="SA_SALES_RETURN" name="examin" name-db="examin" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="审核人及日期" description="" ID="37151FD639B93D937B1716F8B032F749" />
    <field dbtable="SA_SALES_RETURN" name="effectiveDate" name-db="effectiveDate" type="date" primary-key="false" keep-valid="false" author="" title="订单生效时间" description="" ID="88E067F1A1255341C2E453ACB435000B" />
    <field dbtable="SA_SALES_RETURN" name="deliveryDate" name-db="deliveryDate" type="date" primary-key="false" keep-valid="false" author="" title="交货日期" description="" ID="4682FE24F1180081E591CEBD2C7A11CF" />
    <field dbtable="SA_SALES_RETURN" name="defOne" name-db="defOne" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="用户自定义字段1" description="" ID="291812FC847A1BEB5AB077BE6E9BEEF1" />
    <field dbtable="SA_SALES_RETURN" name="defTwo" name-db="defTwo" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="用户自定义字段2" description="" ID="92D3A9B48247E44D34F96CBB77C06ADB" />
    <field dbtable="SA_SALES_RETURN" name="defThree" name-db="defThree" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="用户自定义字段3" description="" ID="05F8D82A5E4F83D2573D33A9ADD3F41D" />
    <field dbtable="SA_SALES_RETURN" name="defFour" name-db="defFour" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="用户自定义字段4" description="" ID="DFC86967A54306481FAAE42DBD1B6770" />
    <field dbtable="SA_SALES_RETURN" name="defFive" name-db="defFive" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="用户自定义字段5" description="" ID="707F944F0E2449406F05D491F6C3C7D8" />
    <field dbtable="SA_SALES_RETURN" name="defSix" name-db="defSix" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="用户自定义字段6" description="" ID="ED339FFCEFA0AA7D7A307AF713036D01" />
    <field dbtable="SA_SALES_RETURN" name="defSeven" name-db="defSeven" type="date" primary-key="false" keep-valid="false" author="" title="用户自定义字段7" description="" ID="8AF7B83F58B4CFA76521EE101140EB2C" />
    <field dbtable="SA_SALES_RETURN" name="defEight" name-db="defEight" type="date" primary-key="false" keep-valid="false" author="" title="用户自定义字段8" description="" ID="623A1E9E2373A7901DFF79E0B04E5A54" />
    <field dbtable="SA_SALES_RETURN" name="defNine" name-db="defNine" type="char(2)" primary-key="false" keep-valid="false" author="" title="用户自定义字段9" description="" ID="F40E088CEC3441BB94ED2A5E50714184" />
    <field dbtable="SA_SALES_RETURN" name="defTen" name-db="defTen" type="char(2)" primary-key="false" keep-valid="false" author="" title="用户自定义字段10" description="" ID="E48C8D97EF1B6E7ED3B689FF9E291291" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_SALES_RETURN" name-db="SA_SALES_RETURN" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

