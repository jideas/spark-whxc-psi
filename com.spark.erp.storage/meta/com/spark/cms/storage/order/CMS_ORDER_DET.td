<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_ORDER_DET" ID="718886856305567221E3F3F2CB160367" name-db="CMS_ORDER_DET" category="业务主体" title="订单明细" author="" description="" packagename="com.spark.cms.storage.order" name="CMS_ORDER_DET" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_ORDER_DET" name="billsId" name-db="billsId" type="guid" primary-key="false" keep-valid="false" author="" title="销售订单GUID" description="" ID="68282E577268E6C38231CBEAAC265853" />
    <field dbtable="CMS_ORDER_DET" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品ID" description="" ID="835B625AADDF7713C4D72D6DA273A5FB" />
    <field dbtable="CMS_ORDER_DET" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="5D35D85C5E41D95F630BF3D0EE2B1181" />
    <field dbtable="CMS_ORDER_DET" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="35927CBD1DC32F33198C89FB8BDEA0FE" />
    <field dbtable="CMS_ORDER_DET" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="CC047621A1D40C65C664B872990F61B9" />
    <field dbtable="CMS_ORDER_DET" name="goodsSpec" name-db="goodsSpec" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="D3C113EB39DE14FB0E4A58C6C8825912" />
    <field dbtable="CMS_ORDER_DET" name="unit" name-db="unit" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="C9B8F174B42A6D25E9DAB58668F8C224" />
    <field dbtable="CMS_ORDER_DET" name="goodsScale" name-db="goodsScale" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="0052C5948A5CFCCC9C7771E93BD70486" />
    <field dbtable="CMS_ORDER_DET" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="单价" description="" ID="A00EDE33CAC6B5977FAFD72C6038D51D" />
    <field dbtable="CMS_ORDER_DET" name="planPrice" name-db="planPrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="" description="" ID="69ECAA82F522094F6A59F9AEF0DD8924" />
    <field dbtable="CMS_ORDER_DET" name="count" name-db="count" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="8D6D4E2C5DDEF880F7B3B1C820C574F5" />
    <field dbtable="CMS_ORDER_DET" name="disRate" name-db="disRate" type="numeric(6,0)" primary-key="false" keep-valid="false" author="" title="折扣率" description="" ID="6C04D84F90A341915F9E101912672314" />
    <field dbtable="CMS_ORDER_DET" name="disAmount" name-db="disAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="折扣额" description="" ID="5FCB6B32431A32120F1B47B33282C8DA" />
    <field dbtable="CMS_ORDER_DET" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="金额" description="" ID="45BB044DEE92B972ABE7EF06DCE683BC" />
    <field dbtable="CMS_ORDER_DET" name="promotionId" name-db="promotionId" type="guid" primary-key="false" keep-valid="false" author="" title="促销商品Guid" description="" ID="846CBC4A0AFB989DB418D13C04E1855D" />
    <field dbtable="CMS_ORDER_DET" name="vantages" name-db="vantages" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="送积分" description="" ID="CAABA85055045759FCF009038A72F6F3" />
    <field dbtable="CMS_ORDER_DET" name="vantagesType" name-db="vantagesType" type="char(1)" primary-key="false" keep-valid="false" author="" title="积分类型（0,1,2倍积分）" description="" ID="EF7602E872BE4F8CDD987008AE933E7D" />
    <field dbtable="CMS_ORDER_DET" name="vantagesCost" name-db="vantagesCost" type="numeric(17,0)" primary-key="false" keep-valid="false" author="" title="消耗积分" description="" ID="6759092DE354FDAE16D8243CB5ED702C" />
    <field dbtable="CMS_ORDER_DET" name="vantagesGoods" name-db="vantagesGoods" type="boolean" primary-key="false" keep-valid="false" author="" title="是否积分商城商品" description="" ID="69E308C985540299103A861861F9302F">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_ORDER_DET" name-db="CMS_ORDER_DET" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

