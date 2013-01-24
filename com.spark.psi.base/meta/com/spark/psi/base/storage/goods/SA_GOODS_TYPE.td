<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_GOODS_TYPE" ID="C27CF8F82A8535613E894CA96A88D3B5" name-db="SA_GOODS_TYPE" category="" title="商品分类表 " author="" description="存放商品分类内容,注意：该表新创建时需要加入一条guid为“10000000000000000000000000000001”，名称为“全部”，无上级分类的顶级节点" packagename="com.spark.psi.base.storage.goods" name="SA_GOODS_TYPE" isneedrebuild="false">
  <fields>
    <field dbtable="SA_GOODS_TYPE" name="tenantsGuid" name-db="tenantsGuid" type="guid" primary-key="false" keep-valid="false" author="" title="租户GUID" description="关联租户" ID="5D4AD63A7211A5A8C0A7CFBF20A97FA9" />
    <field dbtable="SA_GOODS_TYPE" name="goodsTypeName" name-db="goodsTypeName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品分类名称" description="" ID="079FE264D568AC15AD16464F41DA4E25" />
    <field dbtable="SA_GOODS_TYPE" name="parentGuid" name-db="parentGuid" type="guid" primary-key="false" keep-valid="false" author="" title="父分类GUID" description="" ID="EF386AB11804DC99E55CA659C8C45A4A" />
    <field dbtable="SA_GOODS_TYPE" name="path" name-db="path" type="varbinary(544)" primary-key="false" keep-valid="false" author="" title="节点的路径" description="节点从其最主宗节点到它本节点的路径，这个路径最长为544位，也就是最多为17级" ID="198388BF10E50FC7DD1C264B5603097E" />
    <field dbtable="SA_GOODS_TYPE" name="yzFlag" name-db="yzFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="叶子节点标识" description="1，叶子节点，0非叶子节点" ID="20CD520CEE6B5EF04636B0F3C5890DE8">
      <default>
        <const type="boolean" value="true" />
      </default>
    </field>
    <field dbtable="SA_GOODS_TYPE" name="goodsCountDigit" name-db="goodsCountDigit" type="int" primary-key="false" keep-valid="false" author="" title="商品显示位数" description="" ID="A92EF250A8E7488041FDD2AD7AA55B6A">
      <default>
        <const type="int" value="0" />
      </default>
    </field>
    <field dbtable="SA_GOODS_TYPE" name="deleteFlag" name-db="deleteFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="删除标志" description="1可删除、0不可删除，默认为1" ID="189E91762F54742590E2CFEA9B56C923">
      <default>
        <const type="boolean" value="true" />
      </default>
    </field>
    <field dbtable="SA_GOODS_TYPE" name="reflag" name-db="reflag" type="boolean" primary-key="false" keep-valid="false" author="" title="关联标识" description="1已关联、0未关联，默认为0" ID="453F07221B9A745F20F8F212821A5488">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="SA_GOODS_TYPE" name="setPropertyFlag" name-db="setPropertyFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="是否已设置属性标识" description="1已设置、0未设置），默认为0" ID="4ADD309E62D57A395972ACD75DA99AAE">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="SA_GOODS_TYPE" name="properties" name-db="properties" type="text" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="860CC45F2EF250A80F11804DC98F530A" />
    <field dbtable="SA_GOODS_TYPE" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="数据插入时间" description="" ID="FE1333D65E905C84CCF7DD5EDA44BB59" />
    <field dbtable="SA_GOODS_TYPE" name="createPerson" name-db="createPerson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="数据创建人" description="" ID="7D76A054E24654580C52EBE20F73058A" />
    <field dbtable="SA_GOODS_TYPE" name="lastModifyDate" name-db="lastModifyDate" type="date" primary-key="false" keep-valid="false" author="" title="数据最后修改时间" description="" ID="95A860CC530DC30BD0E250549741AA13" />
    <field dbtable="SA_GOODS_TYPE" name="lastModifyPerson" name-db="lastModifyPerson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="数据最后修改人" description="" ID="1109E856DD2CC0A81DB280B7CF5784C9" />
  </fields>
  <indexs>
    <index author="" name="SA_GOODS_TYPE_1" name-db="SA_GOODS_TYPE_1" title="SA_GOODS_TYPE_1" unique="false" description="">
      <index-item fieldID="EF386AB11804DC99E55CA659C8C45A4A" field="parentGuid" field-author="" desc="false" />
    </index>
    <index author="" name="INDEX_GOODS_TYPE_TENANT" name-db="INDEX_GOODS_TYPE_TENANT" title="INDEX_GOODS_TYPE_TENANT" unique="false" description="">
      <index-item fieldID="5D4AD63A7211A5A8C0A7CFBF20A97FA9" field="tenantsGuid" field-author="" desc="false" />
      <index-item fieldID="EF386AB11804DC99E55CA659C8C45A4A" field="parentGuid" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_GOODS_TYPE" name-db="SA_GOODS_TYPE" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

