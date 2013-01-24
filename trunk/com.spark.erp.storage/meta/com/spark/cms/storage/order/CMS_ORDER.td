<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_ORDER" ID="B37C19417F123282B201D793FB655087" name-db="CMS_ORDER" category="业务主体" title="网站订单" author="" description="" packagename="com.spark.cms.storage.order" name="CMS_ORDER" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_ORDER" name="billsNo" name-db="billsNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="订单编号" description="" ID="1A702700B1CB3C016AAFFF02D37A7351" />
    <field dbtable="CMS_ORDER" name="memberId" name-db="memberId" type="guid" primary-key="false" keep-valid="false" author="" title="会员id" description="" ID="40D66D4EACA3F32BEF232F4093F744A7" />
    <field dbtable="CMS_ORDER" name="realName" name-db="realName" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="真实姓名" description="" ID="4B6E67FB7D916835D9C11315D87655AF" />
    <field dbtable="CMS_ORDER" name="consignee" name-db="consignee" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="收货人" description="" ID="05C8F2D0EF07C31CDF577AB348A9A680" />
    <field dbtable="CMS_ORDER" name="consigneeTel" name-db="consigneeTel" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="收货人电话" description="" ID="DF6ACD4E47D10630E52374D530C39BA3" />
    <field dbtable="CMS_ORDER" name="address" name-db="address" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="收货地址" description="" ID="D5E9F596F855DA633B158ACA09A1813A" />
    <field dbtable="CMS_ORDER" name="deliveryeDate" name-db="deliveryeDate" type="date" primary-key="false" keep-valid="false" author="" title="交货日期" description="" ID="E04362D401367AA3290506B837966D5A" />
    <field dbtable="CMS_ORDER" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="CC43C13B7305935354983E0C6E8A0276" />
    <field dbtable="CMS_ORDER" name="disAmount" name-db="disAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="整单折扣" description="" ID="12777129BCE6C5C6F5BB13AF49712226" />
    <field dbtable="CMS_ORDER" name="totalAmount" name-db="totalAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="总金额" description="" ID="E332C82A32ABA15843B88C37EE40BCA3" />
    <field dbtable="CMS_ORDER" name="type" name-db="type" type="char(2)" primary-key="false" keep-valid="false" author="" title="类型" description="" ID="96A6B7A49BC58596C61F7DDEC862EB20" />
    <field dbtable="CMS_ORDER" name="stationId" name-db="stationId" type="guid" primary-key="false" keep-valid="false" author="" title="站点ID" description="" ID="80C5229812475A171C880420BB5E16C6" />
    <field dbtable="CMS_ORDER" name="stationName" name-db="stationName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="站点名称" description="" ID="426DB3C3EA12088EBA82F67F9AFF897E" />
    <field dbtable="CMS_ORDER" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="308878D354196C114C79807240E57529" />
    <field dbtable="CMS_ORDER" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="C243C9D03AC88FC277AC37C9B79108EC" />
    <field dbtable="CMS_ORDER" name="consignorId" name-db="consignorId" type="guid" primary-key="false" keep-valid="false" author="" title="发货人ID" description="" ID="DB5E05F6A88D2AD60986AF87FCC4978A" />
    <field dbtable="CMS_ORDER" name="consignor" name-db="consignor" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="发货人" description="" ID="54B4450FCE4749ECA6F0457FE4AFC8F7" />
    <field dbtable="CMS_ORDER" name="consignedDate" name-db="consignedDate" type="date" primary-key="false" keep-valid="false" author="" title="发货日期" description="" ID="01429FE39552FD5169BBB373F634BDCD" />
    <field dbtable="CMS_ORDER" name="deliverPersonId" name-db="deliverPersonId" type="guid" primary-key="false" keep-valid="false" author="" title="配送人Id" description="" ID="77D91B02E32DEA60FD2DEB7B113AE0BD" />
    <field dbtable="CMS_ORDER" name="deliverPerson" name-db="deliverPerson" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="配送人" description="" ID="97D12B4DA76CDDA587D5CF73BEBD0DDC" />
    <field dbtable="CMS_ORDER" name="deliverDate" name-db="deliverDate" type="date" primary-key="false" keep-valid="false" author="" title="配送日期" description="" ID="8198B9AAD727F92F2FAFA2F227224A64" />
    <field dbtable="CMS_ORDER" name="arrivedConfirmId" name-db="arrivedConfirmId" type="guid" primary-key="false" keep-valid="false" author="" title="确认到货人Id" description="" ID="9023F0C2B8DE1C870A2A94D34B0B893C" />
    <field dbtable="CMS_ORDER" name="arrivedConfirm" name-db="arrivedConfirm" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="确认到货人" description="" ID="D74FF15AFF61824722B1C3BFD6628272" />
    <field dbtable="CMS_ORDER" name="arrivedConfirmDate" name-db="arrivedConfirmDate" type="date" primary-key="false" keep-valid="false" author="" title="确认到货日期" description="" ID="520C842FCDFC5563C667AE4A62F07694" />
    <field dbtable="CMS_ORDER" name="verificationCode" name-db="verificationCode" type="nvarchar(6)" primary-key="false" keep-valid="false" author="" title="验证码" description="" ID="7F0EBFBE5B69656545BD9CEECA285B20" />
    <field dbtable="CMS_ORDER" name="noVerificationReason" name-db="noVerificationReason" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="跳过验证原因" description="" ID="7D5D44940CA51090E38E28A154965A08" />
    <field dbtable="CMS_ORDER" name="returnFlag" name-db="returnFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="是否有退货" description="" ID="884C9945DA97D5EC9A0B4758448AE423">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="CMS_ORDER" name="deliveryCost" name-db="deliveryCost" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="运费" description="" ID="46ACF420A738A14BB10828848C124C41">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="CMS_ORDER" name="bagsCost" name-db="bagsCost" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="购物袋费用" description="" ID="6DCCD34AB68A064DF69EF222675E21BB">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="CMS_ORDER" name="payType" name-db="payType" type="char(2)" primary-key="false" keep-valid="false" author="" title="支付类型" description="" ID="1DAF484B301E71186CB3BA743E8BED81">
      <default>
        <const type="char(2)" value="0" />
      </default>
    </field>
    <field dbtable="CMS_ORDER" name="toDoor" name-db="toDoor" type="boolean" primary-key="false" keep-valid="false" author="" title="是否送货上门" description="" ID="80AE0B7DA8BB560DF41A51AB273FF8A0">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="CMS_ORDER" name="vantages" name-db="vantages" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="送积分" description="" ID="A2E73B5E8ADA7A226DB9C307AC9C2538" />
    <field dbtable="CMS_ORDER" name="vantagesCost" name-db="vantagesCost" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="消耗积分" description="" ID="D672660624ADAF1BEF995E7FCED185E1" />
    <field dbtable="CMS_ORDER" name="lockedDeliveryBalance" name-db="lockedDeliveryBalance" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="锁定上门送货包月次数" description="" ID="D670CFA132815004CDBCD24F7D8E1572">
      <default>
        <const type="numeric(17,0)" value="0" />
      </default>
    </field>
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_ORDER" name-db="CMS_ORDER" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

