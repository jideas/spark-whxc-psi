<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_GOODS_INFO" ID="3E687C2FD4621B90F59DD0A73967D6B6" name-db="SA_GOODS_INFO" category="" title="商品信息表 " author="" description="存放商品管理信息" packagename="com.spark.psi.base.storage.goods" name="SA_GOODS_INFO" isneedrebuild="false">
  <fields>
    <field dbtable="SA_GOODS_INFO" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户GUID" description="关联租户" ID="5A3998E5056CF1F828234E4A6DE7E5E8" />
    <field dbtable="SA_GOODS_INFO" name="goodsNo" name-db="goodsNo" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="商品编号" description="" ID="95A480B6C9CDF79F7B3987BEE5697330" />
    <field dbtable="SA_GOODS_INFO" name="goodsName" name-db="goodsName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="AAA60475270E0FF068B4542820B11A13" />
    <field dbtable="SA_GOODS_INFO" name="goodsTypeGuid" name-db="goodsTypeGuid" type="guid" primary-key="false" keep-valid="false" author="" title="商品分类GUID" description="关联商品分类信息" ID="54503DD505D7BFDACAC9BF3F6CB4105F" />
    <field dbtable="SA_GOODS_INFO" name="salePrice" name-db="salePrice" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="统一销售价格" description="默认为0，0表示没有设置该字段" ID="9191AA1021EB96E063DE28452E0511E4">
      <default>
        <const type="numeric(17,2)" value="0" />
      </default>
    </field>
    <field dbtable="SA_GOODS_INFO" name="goodsStatus" name-db="goodsStatus" type="char(2)" primary-key="false" keep-valid="false" author="" title="商品状态" description="枚举(01在售、02停售、03既有在售又有停售)" ID="6AA87C9AFC550F22E61CE6AC80CC73AD" />
    <field dbtable="SA_GOODS_INFO" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="E890E6A188859DDE6D03AEE7980C030C" />
    <field dbtable="SA_GOODS_INFO" name="deleteFlag" name-db="deleteFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="删除标志" description="1可删除、0不可删除，默认为1" ID="1058BF913977FD6696881CCEDAC84412">
      <default>
        <const type="boolean" value="true" />
      </default>
    </field>
    <field dbtable="SA_GOODS_INFO" name="refFlag" name-db="refFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="关联标识" description="1已关联、0未关联，默认为0" ID="D90E47AED20DABCFEA59CBF8B8858F38">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="SA_GOODS_INFO" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="数据插入时间" description="" ID="FF3184F561395656183DA0BB83902D73" />
    <field dbtable="SA_GOODS_INFO" name="createPerson" name-db="createPerson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="数据创建人" description="" ID="CC9F1C55FE647772CE851E05D46F3A2B" />
    <field dbtable="SA_GOODS_INFO" name="lastModifyDate" name-db="lastModifyDate" type="date" primary-key="false" keep-valid="false" author="" title="数据最后修改时间" description="" ID="BF20B3BEA65A22AF4855B694F345A3A5" />
    <field dbtable="SA_GOODS_INFO" name="lastModifyPerson" name-db="lastModifyPerson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="数据最后修改人" description="" ID="F79802B01DA42685AA3A5EEBC9683332" />
    <field dbtable="SA_GOODS_INFO" name="tipsType" name-db="tipsType" type="char(2)" primary-key="false" keep-valid="false" author="" title="商品预警类型" description="" ID="B1307FF253B091F24F9D7D4385EE0EB2" />
    <field dbtable="SA_GOODS_INFO" name="setPriceFlag" name-db="setPriceFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="是否设置了销售价格" description="" ID="06A848CEE30D12EE21DB8A1D7BFD6D45">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
  </fields>
  <indexs>
    <index author="" name="SA_GOODS_INFO_ONE" name-db="SA_GOODS_INFO_ONE" title="SA_GOODS_INFO_ONE" unique="false" description="">
      <index-item fieldID="5A3998E5056CF1F828234E4A6DE7E5E8" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="6AA87C9AFC550F22E61CE6AC80CC73AD" field="goodsStatus" field-author="" desc="false" />
      <index-item fieldID="06A848CEE30D12EE21DB8A1D7BFD6D45" field="setPriceFlag" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_GOODS_INFO" name-db="SA_GOODS_INFO" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

