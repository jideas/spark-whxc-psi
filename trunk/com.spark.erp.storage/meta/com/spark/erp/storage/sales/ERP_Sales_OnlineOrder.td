<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="ERP_Sales_OnlineOrder" ID="09641A9866B09E4DD2F2F577D29BFA1B" name-db="ERP_Sales_OnlineOrder" category="" title="网上订单" author="" description="" packagename="com.spark.erp.storage.sales" name="ERP_Sales_OnlineOrder" isneedrebuild="false">
  <fields>
    <field dbtable="ERP_Sales_OnlineOrder" name="billsNo" name-db="billsNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="订单编号" description="" ID="D0DC5041824481FDC23214CEB8C7B563" />
    <field dbtable="ERP_Sales_OnlineOrder" name="memberId" name-db="memberId" type="guid" primary-key="false" keep-valid="false" author="" title="会员id" description="" ID="9D9D8808D1CFD2316AD054C3F72F6B9A" />
    <field dbtable="ERP_Sales_OnlineOrder" name="realName" name-db="realName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="真实姓名" description="" ID="5F2FEB490AD2FD06E3EC1A10527E9ED0" />
    <field dbtable="ERP_Sales_OnlineOrder" name="consignee" name-db="consignee" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="收货人" description="" ID="826A6B77FD15859CF5D315BFB3D23EA7" />
    <field dbtable="ERP_Sales_OnlineOrder" name="consigneeTel" name-db="consigneeTel" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="收货人电话" description="" ID="21AF8B5D1FB9D06FE958169BE35F86C5" />
    <field dbtable="ERP_Sales_OnlineOrder" name="address" name-db="address" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="收货地址" description="" ID="97C59B68DA4D154F17E40654F9394E96" />
    <field dbtable="ERP_Sales_OnlineOrder" name="deliveryeDate" name-db="deliveryeDate" type="date" primary-key="false" keep-valid="false" author="" title="交货日期" description="" ID="01374D01B30D3886B39B109AE019778D" />
    <field dbtable="ERP_Sales_OnlineOrder" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="4D4EA8999CEEEE6ED00A2C410FE411D4" />
    <field dbtable="ERP_Sales_OnlineOrder" name="disAmount" name-db="disAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="整单折扣" description="" ID="3417AF074F2DA6956999EA19C2CD5D2F" />
    <field dbtable="ERP_Sales_OnlineOrder" name="totalAmount" name-db="totalAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="总金额" description="" ID="09EA8D57E584C86393DCA2C9BAF9E46E" />
    <field dbtable="ERP_Sales_OnlineOrder" name="type" name-db="type" type="char(2)" primary-key="false" keep-valid="false" author="" title="类型" description="" ID="ABD6D8B270D6FBAF973C9B01A419E436" />
    <field dbtable="ERP_Sales_OnlineOrder" name="stationId" name-db="stationId" type="guid" primary-key="false" keep-valid="false" author="" title="站点ID" description="" ID="D4FD113B24E2FB58AB79FC7C2624FF8C" />
    <field dbtable="ERP_Sales_OnlineOrder" name="stationName" name-db="stationName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="站点名称" description="" ID="A451F727574C0B1BF8581A5CDFB1527C" />
    <field dbtable="ERP_Sales_OnlineOrder" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="3F74D67626926761BB7D0891303D05E9" />
    <field dbtable="ERP_Sales_OnlineOrder" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="E667CC5A1C64C64E174C0E7B4F6CDE08" />
    <field dbtable="ERP_Sales_OnlineOrder" name="consignorId" name-db="consignorId" type="guid" primary-key="false" keep-valid="false" author="" title="发货人ID" description="" ID="8C1CF1527F674C617442FCF11E21C56E" />
    <field dbtable="ERP_Sales_OnlineOrder" name="consignor" name-db="consignor" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="发货人" description="" ID="73F68C09D35C992F50F978A003205005" />
    <field dbtable="ERP_Sales_OnlineOrder" name="consignedDate" name-db="consignedDate" type="date" primary-key="false" keep-valid="false" author="" title="发货日期" description="" ID="6DB9532ED176F892F461751F0FFD5D20" />
    <field dbtable="ERP_Sales_OnlineOrder" name="deliverPersonId" name-db="deliverPersonId" type="guid" primary-key="false" keep-valid="false" author="" title="配送人Id" description="" ID="51528F6C91B179B46B46F82F13713B8F" />
    <field dbtable="ERP_Sales_OnlineOrder" name="deliverPerson" name-db="deliverPerson" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="配送人" description="" ID="3AED0B2AEB991ED778F7EFF56ADDE21B" />
    <field dbtable="ERP_Sales_OnlineOrder" name="deliverDate" name-db="deliverDate" type="date" primary-key="false" keep-valid="false" author="" title="配送日期" description="" ID="C845C5F033F0C6032B3BB49B7E4A6DC4" />
    <field dbtable="ERP_Sales_OnlineOrder" name="arrivedConfirmId" name-db="arrivedConfirmId" type="guid" primary-key="false" keep-valid="false" author="" title="确认到货人Id" description="" ID="16CC5EC5B95AAC4BE9E460CE7567E331" />
    <field dbtable="ERP_Sales_OnlineOrder" name="arrivedConfirm" name-db="arrivedConfirm" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="确认到货人" description="" ID="5DE9882DD13E86386C04B6198B528E86" />
    <field dbtable="ERP_Sales_OnlineOrder" name="arrivedConfirmDate" name-db="arrivedConfirmDate" type="date" primary-key="false" keep-valid="false" author="" title="确认到货日期" description="" ID="056E27EC3D0C7124766712870A20FCD6" />
    <field dbtable="ERP_Sales_OnlineOrder" name="verificationCode" name-db="verificationCode" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="验证码" description="" ID="F6B138285B479D6A03DC6C6B68251EE2" />
    <field dbtable="ERP_Sales_OnlineOrder" name="noVerificationReason" name-db="noVerificationReason" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="跳过验证原因" description="" ID="5D4F3F803AEDC092B83EC01B12DFDD43" />
    <field dbtable="ERP_Sales_OnlineOrder" name="returnFlag" name-db="returnFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="是否有退货" description="" ID="42B5FF92334673CACF700AC550CC15BE">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="ERP_Sales_OnlineOrder" name="toDoor" name-db="toDoor" type="boolean" primary-key="false" keep-valid="false" author="" title="是否送货上门" description="" ID="73690C1764E6657C92A5A7A7F4D31C02">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="ERP_Sales_OnlineOrder" name="deliveryCost" name-db="deliveryCost" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="运费" description="" ID="0C6B6CDFB728CA438C87CCEBCE9957EB">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="ERP_Sales_OnlineOrder" name="bagsCost" name-db="bagsCost" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="购物袋费用" description="" ID="35BEAAC478445247556D1F605E0A695D">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="ERP_Sales_OnlineOrder" name="vantages" name-db="vantages" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="送积分" description="" ID="8AB88873DBCA9E65249613D69DA823F7">
      <default>
        <const type="numeric(17,0)" value="0" />
      </default>
    </field>
    <field dbtable="ERP_Sales_OnlineOrder" name="vantagesCost" name-db="vantagesCost" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="消耗积分" description="" ID="82E1F43315556CC21848BDA2F8BC5A11">
      <default>
        <const type="numeric(17,0)" value="0" />
      </default>
    </field>
    <field dbtable="ERP_Sales_OnlineOrder" name="payType" name-db="payType" type="char(2)" primary-key="false" keep-valid="false" author="" title="支付类型（余额支付，在线支付）" description="" ID="39AAFA228DE43B564D1449509A0F89DB" />
    <field dbtable="ERP_Sales_OnlineOrder" name="lockedDeliveryBalance" name-db="lockedDeliveryBalance" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="锁定上门送货包月次数" description="" ID="007F44352B0BF937451535ACA23E4C76">
      <default>
        <const type="numeric(17,0)" value="0" />
      </default>
    </field>
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="ERP_Sales_OnlineOrder" name-db="ERP_Sales_OnlineOrder" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

