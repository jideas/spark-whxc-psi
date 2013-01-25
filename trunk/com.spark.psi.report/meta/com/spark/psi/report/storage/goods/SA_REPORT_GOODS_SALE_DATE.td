<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_REPORT_GOODS_SALE_DATE" ID="62E2E3F517CD5E2D486AAAE8DD6BB859" name-db="SA_REPORT_GOODS_SALE_DATE" category="报表主体" title="销售商品分析数据表（按月份）" author="" description="" packagename="com.spark.psi.report.storage.goods" name="SA_REPORT_GOODS_SALE_DATE" isneedrebuild="false">
  <fields>
    <field dbtable="SA_REPORT_GOODS_SALE_DATE" name="tenantId" name-db="tenantId" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="E14392AC591E438DFAA7643619BA0841" />
    <field dbtable="SA_REPORT_GOODS_SALE_DATE" name="goodsItemId" name-db="goodsItemId" type="guid" primary-key="false" keep-valid="false" author="" title="商品属性GUID" description="" ID="245CA555B119BD52E63464DDBD1B6336" />
    <field dbtable="SA_REPORT_GOODS_SALE_DATE" name="dateNo" name-db="dateNo" type="int" primary-key="false" keep-valid="false" author="" title="日号" description="" ID="72533D9947BA663A07951D87A4F9689D" />
    <field dbtable="SA_REPORT_GOODS_SALE_DATE" name="monthNo" name-db="monthNo" type="int" primary-key="false" keep-valid="false" author="" title="期号" description="" ID="5E4114891E677947342C1ED318A2AEDA" />
    <field dbtable="SA_REPORT_GOODS_SALE_DATE" name="quarter" name-db="quarter" type="int" primary-key="false" keep-valid="false" author="" title="季度" description="" ID="BB1021612C4E7593AD1B53C829B590DF" />
    <field dbtable="SA_REPORT_GOODS_SALE_DATE" name="yearNo" name-db="yearNo" type="int" primary-key="false" keep-valid="false" author="" title="年份" description="" ID="DEC484D6AA1D1C0804284CF2060AC5FF" />
    <field dbtable="SA_REPORT_GOODS_SALE_DATE" name="ordAmount" name-db="ordAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="订单金额" description="" ID="6FD94ED80D5CDC06972179D106D05D28">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_GOODS_SALE_DATE" name="ordCount" name-db="ordCount" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="3184D8AF74935BD089BC9C02A9377374">
      <default>
        <const type="numeric(17,5)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_GOODS_SALE_DATE" name="outstoAmount" name-db="outstoAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="出/入库金额" description="" ID="A3123C71EE1C97886F7EAC8FCFF2B14A">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_GOODS_SALE_DATE" name="receiptAmount" name-db="receiptAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="收/付款金额" description="" ID="AFAF12A6CF54A983FE4732C8D4F25CD5">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_GOODS_SALE_DATE" name="rtnAmount" name-db="rtnAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="退货金额" description="" ID="9186BB1C413EB464A4BCDB99F7A9338E">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_REPORT_GOODS_SALE_DATE" name="deptGuid" name-db="deptGuid" type="guid" primary-key="false" keep-valid="false" author="" title="制单人所属部门" description="" ID="C0408363C8C1AC0067AFF4211D18643F" />
    <field dbtable="SA_REPORT_GOODS_SALE_DATE" name="orderPerson" name-db="orderPerson" type="guid" primary-key="false" keep-valid="false" author="" title="制单人" description="" ID="5CD118724C2297BD89FDBEED843ADEA7" />
    <field dbtable="SA_REPORT_GOODS_SALE_DATE" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="true" author="" title="创建时间" description="" ID="72DD2E8D30BAF972806DD303DBF1C6DA" />
  </fields>
  <indexs>
    <index author="" name="SA_REPORT_GOODS_SALE_MONO" name-db="SA_REPORT_GOODS_SALE_MONO" title="SA_REPORT_GOODS_SALE_MONO" unique="false" description="">
      <index-item fieldID="E14392AC591E438DFAA7643619BA0841" field="tenantId" field-author="" desc="false" />
      <index-item fieldID="245CA555B119BD52E63464DDBD1B6336" field="goodsItemId" field-author="" desc="false" />
      <index-item fieldID="72533D9947BA663A07951D87A4F9689D" field="dateNo" field-author="" desc="false" />
      <index-item fieldID="C0408363C8C1AC0067AFF4211D18643F" field="deptGuid" field-author="" desc="false" />
      <index-item fieldID="5CD118724C2297BD89FDBEED843ADEA7" field="orderPerson" field-author="" desc="false" />
    </index>
    <index author="" name="SA_REPORT_GOODS_SALE_MONT" name-db="SA_REPORT_GOODS_SALE_MONT" title="SA_REPORT_GOODS_SALE_MONT" unique="false" description="">
      <index-item fieldID="E14392AC591E438DFAA7643619BA0841" field="tenantId" field-author="" desc="false" />
      <index-item fieldID="245CA555B119BD52E63464DDBD1B6336" field="goodsItemId" field-author="" desc="false" />
      <index-item fieldID="5E4114891E677947342C1ED318A2AEDA" field="monthNo" field-author="" desc="false" />
      <index-item fieldID="C0408363C8C1AC0067AFF4211D18643F" field="deptGuid" field-author="" desc="false" />
      <index-item fieldID="5CD118724C2297BD89FDBEED843ADEA7" field="orderPerson" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_REPORT_GOODS_SALE_DATE" name-db="SA_REPORT_GOODS_SALE_DATE" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

