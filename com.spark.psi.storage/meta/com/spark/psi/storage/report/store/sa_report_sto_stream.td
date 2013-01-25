<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="sa_report_sto_stream" ID="F5890E0DD56AAF4EDA287A008C367AFA" name-db="sa_report_sto_stream" category="" title="库存流水" author="" description="" packagename="com.spark.psi.storage.report.store" name="sa_report_sto_stream" isneedrebuild="false">
  <fields>
    <field dbtable="sa_report_sto_stream" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="0E1A7F006A81470B8EAFDC635A0C4CA8" />
    <field dbtable="sa_report_sto_stream" name="storeGuid" name-db="storeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="仓库GUID" description="" ID="BCB5A4BEFFAEDC658830FA5CD93EA0A0" />
    <field dbtable="sa_report_sto_stream" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="" ID="8E8610C2DADB44B721DB6F44CF1E5946" />
    <field dbtable="sa_report_sto_stream" name="goodsName" name-db="goodsName" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="3C48C1FC5BAECF2C625E2DB75606E1A2" />
    <field dbtable="sa_report_sto_stream" name="goodsAttr" name-db="goodsAttr" type="varchar(100)" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="513E4E101F9932A6EF37852EFEA459DE" />
    <field dbtable="sa_report_sto_stream" name="goodsUnit" name-db="goodsUnit" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="6CA8729CDA4D9CA475A29368B660F9E1" />
    <field dbtable="sa_report_sto_stream" name="goodsTypeGuid" name-db="goodsTypeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品分类GUID" description="" ID="B7FA12AA79BAF36B9E32551A79A5534D" />
    <field dbtable="sa_report_sto_stream" name="goodsNo" name-db="goodsNo" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="商品编号" description="" ID="8A4F42005EF4AA5A8B24CD9381724822" />
    <field dbtable="sa_report_sto_stream" name="currDate" name-db="currDate" type="date" primary-key="false" keep-valid="false" author="" title="单据创建日期" description="" ID="B7D8591410F706A1CA42C110C2ED5D91" />
    <field dbtable="sa_report_sto_stream" name="orderGuid" name-db="orderGuid" type="guid" primary-key="false" keep-valid="false" author="" title="单据编号" description="" ID="E4B1BF02133F0B26699772A7A05C7030" />
    <field dbtable="sa_report_sto_stream" name="orderNo" name-db="orderNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="单据编号" description="" ID="A344327483F44782030BCBA1F48DB078" />
    <field dbtable="sa_report_sto_stream" name="streamType" name-db="streamType" type="char(2)" primary-key="false" keep-valid="false" author="" title="流水类型" description="" ID="D7B13F194E9BF0E7A65F93C71384241D" />
    <field dbtable="sa_report_sto_stream" name="instoCount" name-db="instoCount" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="入库数量" description="" ID="6124C62AA5609B5784797DE33F556AF1">
      <default>
        <const type="numeric(17,4)" value="0" />
      </default>
    </field>
    <field dbtable="sa_report_sto_stream" name="instoAmount" name-db="instoAmount" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="入库金额" description="" ID="AD3EAE3ED2136E73FD1109EE714F4AD2">
      <default>
        <const type="numeric(17,4)" value="0" />
      </default>
    </field>
    <field dbtable="sa_report_sto_stream" name="goodsScale" name-db="goodsScale" type="int" primary-key="false" keep-valid="false" author="" title="商品精度" description="" ID="79A9130C0F6A1D7E7BC49E77A8DE7CA3" />
    <field dbtable="sa_report_sto_stream" name="outstoCount" name-db="outstoCount" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="出库数量" description="" ID="6595DBE87177D2C18CC26206D1C0DFAB">
      <default>
        <const type="numeric(17,4)" value="0" />
      </default>
    </field>
    <field dbtable="sa_report_sto_stream" name="outstoAmount" name-db="outstoAmount" type="numeric(17,4)" primary-key="false" keep-valid="false" author="" title="出库金额" description="" ID="7E595895F476C8078A5E8249A5F7BEC3">
      <default>
        <const type="numeric(17,4)" value="0" />
      </default>
    </field>
    <field dbtable="sa_report_sto_stream" name="createPerson" name-db="createPerson" type="varchar(20)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="73675D3269FEA9D260605A94FD993E07" />
    <field dbtable="sa_report_sto_stream" name="createdDate" name-db="createdDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="1009EA574805A32DE6432140049A9B75" />
    <field dbtable="sa_report_sto_stream" name="dayNo" name-db="dayNo" type="int" primary-key="false" keep-valid="false" author="" title="天号" description="" ID="8DD05F2498545BDDE96DB15FB79848A9" />
    <field dbtable="sa_report_sto_stream" name="monthNo" name-db="monthNo" type="int" primary-key="false" keep-valid="false" author="" title="月号" description="" ID="ED98EC9109F98BE6395C70CAA950B40E" />
    <field dbtable="sa_report_sto_stream" name="quarter" name-db="quarter" type="int" primary-key="false" keep-valid="false" author="" title="季号" description="" ID="509C0865CA09BBB7B3B5B22029376CBA" />
    <field dbtable="sa_report_sto_stream" name="yearNo" name-db="yearNo" type="int" primary-key="false" keep-valid="false" author="" title="年号" description="" ID="00EE493CF23E18AAA44A0A4ADAE28051" />
  </fields>
  <indexs>
    <index author="" name="index_orderNo" name-db="index_orderNo" title="index_orderNo" unique="false" description="">
      <index-item fieldID="A344327483F44782030BCBA1F48DB078" field="orderNo" field-author="" desc="false" />
      <index-item fieldID="3C48C1FC5BAECF2C625E2DB75606E1A2" field="goodsName" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="sa_report_sto_stream" name-db="sa_report_sto_stream" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

