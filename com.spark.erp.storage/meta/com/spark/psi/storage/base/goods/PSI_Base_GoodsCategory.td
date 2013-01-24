<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Base_GoodsCategory" ID="31E91D58E7BCD0BB301E61E461436252" name-db="PSI_Base_GoodsCategory" category="基础数据主体" title="商品分类" author="" description="" packagename="com.spark.psi.storage.base.goods" name="PSI_Base_GoodsCategory" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Base_GoodsCategory" name="categoryNo" name-db="categoryNo" type="nvarchar(25)" primary-key="false" keep-valid="false" author="" title="分类编号" description="" ID="8DB162F95996A9936C32B42BE25EB1A8" />
    <field dbtable="PSI_Base_GoodsCategory" name="categoryName" name-db="categoryName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="商品分类名称" description="" ID="9343C936A1BA1B0206E8A83C40A6ED9E" />
    <field dbtable="PSI_Base_GoodsCategory" name="parentId" name-db="parentId" type="guid" primary-key="false" keep-valid="false" author="" title="父分类GUID" description="" ID="EBD311A19A022FDCA7686627D29A70DB" />
    <field dbtable="PSI_Base_GoodsCategory" name="path" name-db="path" type="varbinary(544)" primary-key="false" keep-valid="false" author="" title="节点的路径" description="" ID="DE4D669CF4E95511DE09C510709EA3F7" />
    <field dbtable="PSI_Base_GoodsCategory" name="leafFlag" name-db="leafFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="叶子节点标识" description="" ID="5058ABC972AD52D77748A965F7DCC5FB">
      <default>
        <const type="boolean" value="true" />
      </default>
    </field>
    <field dbtable="PSI_Base_GoodsCategory" name="scale" name-db="scale" type="int" primary-key="false" keep-valid="false" author="" title="商品显示位数" description="" ID="37C0A6EC564249EBE3AA781F48208CE0">
      <default>
        <const type="int" value="0" />
      </default>
    </field>
    <field dbtable="PSI_Base_GoodsCategory" name="canDelete" name-db="canDelete" type="boolean" primary-key="false" keep-valid="false" author="" title="是否可以删除" description="" ID="33CBC88CE082CE97CF41E869A37DA34B">
      <default>
        <const type="boolean" value="true" />
      </default>
    </field>
    <field dbtable="PSI_Base_GoodsCategory" name="reflag" name-db="reflag" type="boolean" primary-key="false" keep-valid="false" author="" title="关联标识" description="" ID="D56D95AB8FC75F5FCF558A82D3656796">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="PSI_Base_GoodsCategory" name="setPropertyFlag" name-db="setPropertyFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="是否已设置属性标识" description="" ID="60EAA7D46862B13199606CC175DD0B8A">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="PSI_Base_GoodsCategory" name="properties" name-db="properties" type="text" primary-key="false" keep-valid="false" author="" title="商品属性" description="" ID="DA6E62BC60A5FDBE08502FB59CB8B5C9" />
    <field dbtable="PSI_Base_GoodsCategory" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="数据插入时间" description="" ID="F22A32EA9B8D7EB5F815D5B2856F1CF8" />
    <field dbtable="PSI_Base_GoodsCategory" name="lastModifyDate" name-db="lastModifyDate" type="date" primary-key="false" keep-valid="false" author="" title="数据最后修改时间" description="" ID="F17B07D39369ED3E01EE4778CDEBB0A6" />
    <field dbtable="PSI_Base_GoodsCategory" name="lastModifyPerson" name-db="lastModifyPerson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="数据最后修改人" description="" ID="9AF7A724D4F2835777145890E85AFE41" />
    <field dbtable="PSI_Base_GoodsCategory" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Guid" description="" ID="87F5D9DA156FE29027C810338FFD3C34" />
    <field dbtable="PSI_Base_GoodsCategory" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="81D77A49DF54AE3490FF5164CD01D793" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Base_GoodsCategory" name-db="PSI_Base_GoodsCategory" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

