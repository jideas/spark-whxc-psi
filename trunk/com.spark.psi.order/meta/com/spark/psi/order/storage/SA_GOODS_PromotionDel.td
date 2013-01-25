<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_GOODS_PromotionDel" ID="97C79152B94E8E67DDEB2E971C77D573" name-db="SA_GOODS_PromotionDel" category="" title="促销2删除记录" author="" description="" packagename="com.spark.psi.order.storage" name="SA_GOODS_PromotionDel" isneedrebuild="false">
  <fields>
    <field dbtable="SA_GOODS_PromotionDel" name="tenantsId" name-db="tenantsId" type="guid" primary-key="false" keep-valid="false" author="" title="租户GUID" description="" ID="1FF0BCCE569F559A545C58FFEECA9219" />
    <field dbtable="SA_GOODS_PromotionDel" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人GUID" description="" ID="3ABDD93FF09D4E7DDE19ED14802B0FD8" />
    <field dbtable="SA_GOODS_PromotionDel" name="creator" name-db="creator" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="创建人姓名" description="" ID="3A66AB1C026034B60845ED3743CFE463" />
    <field dbtable="SA_GOODS_PromotionDel" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="532C30830EF2588DA94F2A1120816AF4" />
    <field dbtable="SA_GOODS_PromotionDel" name="goodsItemId" name-db="goodsItemId" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="E70F11F7854EB3EC5C34514F5732D641" />
    <field dbtable="SA_GOODS_PromotionDel" name="goodsName" name-db="goodsName" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="1A66CADC0C567C7AA2113E87C84FC3F4" />
    <field dbtable="SA_GOODS_PromotionDel" name="goodsNamePY" name-db="goodsNamePY" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="名称拼音" description="" ID="1F20480E3AA74090BB2139CF897ECD3F" />
    <field dbtable="SA_GOODS_PromotionDel" name="goodsProperties" name-db="goodsProperties" type="varchar(1000)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="9E6AE64C5C94259747C4302654601531" />
    <field dbtable="SA_GOODS_PromotionDel" name="countDecimal" name-db="countDecimal" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="A9E635B0A806C8D692A06E830B321FA3" />
    <field dbtable="SA_GOODS_PromotionDel" name="promotionCount" name-db="promotionCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="促销数量" description="" ID="04091033E71CA4A609481C9EEC163F89" />
    <field dbtable="SA_GOODS_PromotionDel" name="saledCount" name-db="saledCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="销售数量" description="" ID="C5B49D23B2C63F39373C6F228ECC4552">
      <default>
        <const type="numeric(17,5)" value="0" />
      </default>
    </field>
    <field dbtable="SA_GOODS_PromotionDel" name="price_goods" name-db="price_goods" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="原价" description="" ID="50C13EE8A5384D126C03B5FCF589A223" />
    <field dbtable="SA_GOODS_PromotionDel" name="price_promotion" name-db="price_promotion" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="促销单价" description="" ID="F90DE9E40AA53F3FBCB60C52E9EAA360" />
    <field dbtable="SA_GOODS_PromotionDel" name="beginDate" name-db="beginDate" type="date" primary-key="false" keep-valid="false" author="" title="开始日期" description="" ID="99A14AF47BE2017C913FB4C9BF83AF41" />
    <field dbtable="SA_GOODS_PromotionDel" name="endDate" name-db="endDate" type="date" primary-key="false" keep-valid="false" author="" title="结束日期" description="" ID="96DE94C1EC876DB6BBEA6643941A8BF6" />
    <field dbtable="SA_GOODS_PromotionDel" name="state" name-db="state" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="9EEB6802C79BFA6299C876B601291115" />
    <field dbtable="SA_GOODS_PromotionDel" name="promotionCause" name-db="promotionCause" type="varchar(500)" primary-key="false" keep-valid="false" author="" title="促销原因" description="" ID="597CC8EB62E41C71D87530AA5A6B54F3" />
    <field dbtable="SA_GOODS_PromotionDel" name="returnCause" name-db="returnCause" type="varchar(500)" primary-key="false" keep-valid="false" author="" title="退回原因" description="" ID="29A1097FDDEF1FB88E4B6F6A43860AE0" />
    <field dbtable="SA_GOODS_PromotionDel" name="approvalDate" name-db="approvalDate" type="date" primary-key="false" keep-valid="false" author="" title="审核时间" description="" ID="13565CECFBF81043D3D8B8C413D86AEA" />
    <field dbtable="SA_GOODS_PromotionDel" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="部门GUID" description="" ID="BBA96721C4D78F34FA07BD5667B247B8" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_GOODS_PromotionDel" name-db="SA_GOODS_PromotionDel" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

