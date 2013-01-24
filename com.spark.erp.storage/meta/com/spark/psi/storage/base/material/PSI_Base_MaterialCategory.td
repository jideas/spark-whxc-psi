<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Base_MaterialCategory" ID="1EA71AB7F5753C0604AA29237B80CF9F" name-db="PSI_Base_MaterialCategory" category="基础数据主体" title="材料分类" author="" description="" packagename="com.spark.psi.storage.base.material" name="PSI_Base_MaterialCategory" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Base_MaterialCategory" name="categoryNo" name-db="categoryNo" type="nvarchar(25)" primary-key="false" keep-valid="false" author="" title="分类编号" description="" ID="74FDFBAAE28FA637182A43794C4CE9FD" />
    <field dbtable="PSI_Base_MaterialCategory" name="categoryName" name-db="categoryName" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="分类名称" description="" ID="1BDB5C933FAEC5878E201E8F95683500" />
    <field dbtable="PSI_Base_MaterialCategory" name="parentId" name-db="parentId" type="guid" primary-key="false" keep-valid="false" author="" title="父分类GUID" description="" ID="C374338724B839D885DE0027382898BF" />
    <field dbtable="PSI_Base_MaterialCategory" name="path" name-db="path" type="varbinary(544)" primary-key="false" keep-valid="false" author="" title="节点的路径" description="" ID="9B716DCF6EA8B7D55FAB5EDEF26D7476" />
    <field dbtable="PSI_Base_MaterialCategory" name="leafFlag" name-db="leafFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="叶子节点标识" description="" ID="4C0EF5097B8E7B80A4424FD16907F6DB">
      <default>
        <const type="boolean" value="true" />
      </default>
    </field>
    <field dbtable="PSI_Base_MaterialCategory" name="scale" name-db="scale" type="int" primary-key="false" keep-valid="false" author="" title="显示位数" description="" ID="0B6A44FA1C71BEA76A8C5B55938F024E" />
    <field dbtable="PSI_Base_MaterialCategory" name="canDelete" name-db="canDelete" type="boolean" primary-key="false" keep-valid="false" author="" title="是否可以删除" description="" ID="187728F2B72B4017BE5C85A0D6FAF1E4">
      <default>
        <const type="boolean" value="true" />
      </default>
    </field>
    <field dbtable="PSI_Base_MaterialCategory" name="reflag" name-db="reflag" type="boolean" primary-key="false" keep-valid="false" author="" title="关联标识" description="" ID="61A52516CC472664D9F3153F768E9557">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="PSI_Base_MaterialCategory" name="setPropertyFlag" name-db="setPropertyFlag" type="boolean" primary-key="false" keep-valid="false" author="" title="是否已设置属性标识" description="" ID="A3E4AAE4EF806F0D153E5D3907B40C4B">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
    <field dbtable="PSI_Base_MaterialCategory" name="properties" name-db="properties" type="text" primary-key="false" keep-valid="false" author="" title="材料属性" description="" ID="C33FC89392B57770A9FCF7E40B2579CB" />
    <field dbtable="PSI_Base_MaterialCategory" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="数据插入时间" description="" ID="A51269C151C3C090A32C02AF16C91C1C" />
    <field dbtable="PSI_Base_MaterialCategory" name="lastModifyDate" name-db="lastModifyDate" type="date" primary-key="false" keep-valid="false" author="" title="数据最后修改时间" description="" ID="F66D60FC7D0E026DF9060BC08C63B32D" />
    <field dbtable="PSI_Base_MaterialCategory" name="lastModifyPerson" name-db="lastModifyPerson" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="数据最后修改人" description="" ID="461C62387026EE5794C42304298AE682" />
    <field dbtable="PSI_Base_MaterialCategory" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Guid" description="" ID="1AC27A5BDCB66C38F227B4F4987452CD" />
    <field dbtable="PSI_Base_MaterialCategory" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="1F99DE474ADF453B56A1412A30FA129F" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Base_MaterialCategory" name-db="PSI_Base_MaterialCategory" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

