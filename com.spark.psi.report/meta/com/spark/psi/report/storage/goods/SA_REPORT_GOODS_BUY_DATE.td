<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_REPORT_GOODS_BUY_DATE" ID="6124929F05B7B01193BE18DA1B2CC976" name-db="SA_REPORT_GOODS_BUY_DATE" category="" title="" author="" description="" packagename="com.spark.psi.report.storage.goods" name="SA_REPORT_GOODS_BUY_DATE" isneedrebuild="false">
  <fields>
    <field dbtable="SA_REPORT_GOODS_BUY_DATE" name="tenantId" name-db="tenantId" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="191580BABF4D0D95384D8DA64D6D8B89" />
    <field dbtable="SA_REPORT_GOODS_BUY_DATE" name="goodsItemId" name-db="goodsItemId" type="guid" primary-key="false" keep-valid="false" author="" title="商品属性GUID" description="" ID="609BD1C043D9C3E18D763050570BD81C" />
    <field dbtable="SA_REPORT_GOODS_BUY_DATE" name="dateNo" name-db="dateNo" type="int" primary-key="false" keep-valid="false" author="" title="日号" description="" ID="ED257A5FE4670808B5067684586AE1EE" />
    <field dbtable="SA_REPORT_GOODS_BUY_DATE" name="monthNo" name-db="monthNo" type="int" primary-key="false" keep-valid="false" author="" title="期号" description="" ID="E603D2149EE1A3B782FF6B893991285F" />
    <field dbtable="SA_REPORT_GOODS_BUY_DATE" name="quarter" name-db="quarter" type="int" primary-key="false" keep-valid="false" author="" title="季度" description="" ID="5798F3B22075387C7435C5CCAE6589A8" />
    <field dbtable="SA_REPORT_GOODS_BUY_DATE" name="yearNo" name-db="yearNo" type="int" primary-key="false" keep-valid="false" author="" title="年份" description="" ID="880C8B120BAF755F831190463BE82CAC" />
    <field dbtable="SA_REPORT_GOODS_BUY_DATE" name="ordAmount" name-db="ordAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="本月订单金额" description="" ID="2C76015286CB67E1B0223B7E5BCD3BE1">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_GOODS_BUY_DATE" name="ordCount" name-db="ordCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="200D185AE71CE6D766C56D314808A69C">
      <default>
        <const type="numeric(17,5)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_GOODS_BUY_DATE" name="outstoAmount" name-db="outstoAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="本月出/入库金额" description="" ID="CB90679B3B1E3270CF3A2F998C95C71A">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_GOODS_BUY_DATE" name="receiptAmount" name-db="receiptAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="采购收/付款金额" description="" ID="AD1271A8D264C3E6A45B812A68E0B912">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_GOODS_BUY_DATE" name="rtnAmount" name-db="rtnAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="本月退货金额" description="" ID="919FD491DD3C9EAFBD581234D4180481">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_GOODS_BUY_DATE" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="制单人所属部门" description="" ID="CFA5D2DF4AD3420A80763E87C0D09FDD" />
    <field dbtable="SA_REPORT_GOODS_BUY_DATE" name="orderPerson" name-db="orderPerson" type="guid" primary-key="false" keep-valid="false" author="" title="制单人" description="" ID="884056838EEA8F741C924B7A11EC285A" />
    <field dbtable="SA_REPORT_GOODS_BUY_DATE" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="true" author="" title="创建时间" description="" ID="D5E9D7B8B6E608295851DEE70ED16983" />
  </fields>
  <indexs>
    <index author="" name="SA_REPORT_GOODS_BUY_MONO" name-db="SA_REPORT_GOODS_BUY_MONO" title="SA_REPORT_GOODS_BUY_MONO" unique="false" description="">
      <index-item fieldID="191580BABF4D0D95384D8DA64D6D8B89" field="tenantId" field-author="" desc="false" />
      <index-item fieldID="609BD1C043D9C3E18D763050570BD81C" field="goodsItemId" field-author="" desc="false" />
      <index-item fieldID="ED257A5FE4670808B5067684586AE1EE" field="dateNo" field-author="" desc="false" />
      <index-item fieldID="CFA5D2DF4AD3420A80763E87C0D09FDD" field="deptGuid" field-author="" desc="false" />
      <index-item fieldID="884056838EEA8F741C924B7A11EC285A" field="orderPerson" field-author="" desc="false" />
    </index>
    <index author="" name="SA_REPORT_GOODS_BUY_MONT" name-db="SA_REPORT_GOODS_BUY_MONT" title="SA_REPORT_GOODS_BUY_MONT" unique="false" description="">
      <index-item fieldID="191580BABF4D0D95384D8DA64D6D8B89" field="tenantId" field-author="" desc="false" />
      <index-item fieldID="609BD1C043D9C3E18D763050570BD81C" field="goodsItemId" field-author="" desc="false" />
      <index-item fieldID="880C8B120BAF755F831190463BE82CAC" field="yearNo" field-author="" desc="false" />
      <index-item fieldID="CFA5D2DF4AD3420A80763E87C0D09FDD" field="deptGuid" field-author="" desc="false" />
      <index-item fieldID="884056838EEA8F741C924B7A11EC285A" field="orderPerson" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_REPORT_GOODS_BUY_DATE" name-db="SA_REPORT_GOODS_BUY_DATE" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

