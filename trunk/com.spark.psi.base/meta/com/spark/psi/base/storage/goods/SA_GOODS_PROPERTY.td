<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_GOODS_PROPERTY" ID="C1723A9AAF8B1E284CF713F02F45C346" name-db="SA_GOODS_PROPERTY" category="" title="商品属性信息表 " author="" description="存放商品属性信息" packagename="com.spark.psi.base.storage.goods" name="SA_GOODS_PROPERTY" isneedrebuild="false">
  <fields>
    <field dbtable="SA_GOODS_PROPERTY" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户GUID" description="关联租户表" ID="02A27B64D3678304834CE061A3C475C2" />
    <field dbtable="SA_GOODS_PROPERTY" name="goodsGuid" name-db="goodsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品GUID" description="关联商品表" ID="B6FD460A218D7746A48C9D2DE496A583" />
    <field dbtable="SA_GOODS_PROPERTY" name="goodsNo" name-db="goodsNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="商品编号" description="" ID="C6DBEF6940D65B5E425BD1243B5956AE" />
    <field dbtable="SA_GOODS_PROPERTY" name="goodsName" name-db="goodsName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="2492058730EB9B1922AD69A4D241953C" />
    <field dbtable="SA_GOODS_PROPERTY" name="goodsTypeGuid" name-db="goodsTypeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品分类GUID" description="关联商品分类信息" ID="F204EE01351B014390B58F18EDB7A7DA" />
    <field dbtable="SA_GOODS_PROPERTY" name="goodsCountDigit" name-db="goodsCountDigit" type="int" primary-key="false" keep-valid="false" author="" title="商品显示位数" description="" ID="CE9AAD81575D06D346AD4C3E5620570A">
      <default>
        <const type="int" value="0" />
      </default>
    </field>
    <field dbtable="SA_GOODS_PROPERTY" name="salePrice" name-db="salePrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="销售价格" description="默认为0，表示没有设置" ID="3A0FB057A7D29D3AD27BF6F6991884C4">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_GOODS_PROPERTY" name="goodsPropertyState" name-db="goodsPropertyState" type="char(2)" primary-key="false" keep-valid="false" author="" title="商品属性状态" description="枚举(01在售、02停售)，默认为01" ID="88B4C46A7B20A26BC18545156665F5CF" />
    <field dbtable="SA_GOODS_PROPERTY" name="deleteFlag" name-db="deleteFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="删除标志" description="1可删除、0不可删除，默认为1" ID="CEF34094391E62FD439E94AD0D4A203D">
      <default>
        <const type="boolean" value="true" />
      </default>
    </field>
    <field dbtable="SA_GOODS_PROPERTY" name="reflag" name-db="reflag" type="boolean" primary-key="false" keep-valid="false" author="" title="关联标识" description="1已关联、0未关联，默认为0" ID="F5046E7CF878736DFEB56204767DA389">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="SA_GOODS_PROPERTY" name="propertyValue" name-db="propertyValue" type="text" primary-key="false" keep-valid="false" author="" title="属性值" description="用JSON的方式来记录这些属性的键值对，其中键为属性名称、值为录入的内容，例如：{“单位”:”个”,{“颜色”:{“01”:”红”}}}" ID="589EAAD4544413F127556331E14D9B70" />
    <field dbtable="SA_GOODS_PROPERTY" name="goodsUnit" name-db="goodsUnit" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="商品单位" description="" ID="EC26655D47ADABA76F3500155C0939D1" />
    <field dbtable="SA_GOODS_PROPERTY" name="avgBuyPrice" name-db="avgBuyPrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="平均采购价格" description="" ID="37EBEF10640E38A9E729F0D64A15CEE8">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_GOODS_PROPERTY" name="totalStoreUpper" name-db="totalStoreUpper" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="总库存上限数量" description="" ID="BC575350E89840EEA40C5DFA2B41D505" />
    <field dbtable="SA_GOODS_PROPERTY" name="totalStoreFlore" name-db="totalStoreFlore" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="总库存下限数量" description="" ID="29CC6236327A269CA8382580FB97E595" />
    <field dbtable="SA_GOODS_PROPERTY" name="totalStoreAmount" name-db="totalStoreAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="总库存上限金额" description="" ID="FDE49EB1A2F8D295DDA1FD62D653FD3C" />
    <field dbtable="SA_GOODS_PROPERTY" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="数据插入时间" description="" ID="2309385301ED334A00C58B2E2BA69548" />
    <field dbtable="SA_GOODS_PROPERTY" name="createPerson" name-db="createPerson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="数据创建人" description="" ID="A4FCDA3ADAE1169C91CD15BBBDDDA948" />
    <field dbtable="SA_GOODS_PROPERTY" name="lastModifyDate" name-db="lastModifyDate" type="date" primary-key="false" keep-valid="false" author="" title="数据最后修改时间" description="" ID="D7A89A834C034608EAA17481BE10621F" />
    <field dbtable="SA_GOODS_PROPERTY" name="lastModifyPerson" name-db="lastModifyPerson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="数据最后修改人" description="" ID="1FA992D7980B1DA49D230C5880B960F4" />
    <field dbtable="SA_GOODS_PROPERTY" name="tipsType" name-db="tipsType" type="char(2)" primary-key="false" keep-valid="false" author="" title="商品预警类型" description="" ID="85DEC3AC087BB92C53150FEBF1A404A4" />
    <field dbtable="SA_GOODS_PROPERTY" name="recentPurchasePrice" name-db="recentPurchasePrice" type="varchar(10)" primary-key="false" keep-valid="false" author="" title="最近采购价格" description="订单生效为准" ID="EA9B9483F4DD7507C1148B415369750F" />
  </fields>
  <indexs>
    <index author="" name="SA_GOODS_PROPERTY_ONE" name-db="SA_GOODS_PROPERTY_ONE" title="SA_GOODS_PROPERTY_ONE" unique="false" description="">
      <index-item fieldID="02A27B64D3678304834CE061A3C475C2" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="88B4C46A7B20A26BC18545156665F5CF" field="goodsPropertyState" field-author="" desc="false" />
      <index-item fieldID="B6FD460A218D7746A48C9D2DE496A583" field="goodsGuid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_GOODS_PROPERTY" name-db="SA_GOODS_PROPERTY" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

