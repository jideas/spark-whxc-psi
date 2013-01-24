<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="cms_channel" ID="07B5C9547DEBDFCC237DABBE89B260B1" name-db="cms_channel" category="业务主体" title="栏目管理" author="" description="" packagename="com.spark.cms.storage.manager" name="cms_channel" isneedrebuild="false">
  <fields>
    <field dbtable="cms_channel" name="code" name-db="code" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="栏目编号" description="" ID="AD715F5451FE752B1F538FAC704B2C1F" />
    <field dbtable="cms_channel" name="name" name-db="name" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="栏目名称" description="" ID="B29F4F31E6586C0128B645B57952CB9A" />
    <field dbtable="cms_channel" name="floorId" name-db="floorId" type="guid" primary-key="false" keep-valid="false" author="" title="楼层id" description="" ID="FEAAE9E186C9AACAA2C21A2FE581606B" />
    <field dbtable="cms_channel" name="channelType" name-db="channelType" type="char(2)" primary-key="false" keep-valid="false" author="" title="类型(内容||广告||商品)" description="" ID="3CE031B15D500F2A5376FAF828F8AA3E" />
    <field dbtable="cms_channel" name="pageType" name-db="pageType" type="char(2)" primary-key="false" keep-valid="false" author="" title="页面级别" description="" ID="56E0ABCCB3D9CAB6923C7E62F30290DB" />
    <field dbtable="cms_channel" name="isMainMenu" name-db="isMainMenu" type="boolean" primary-key="false" keep-valid="false" author="" title="主菜单栏标记" description="" ID="FA8EA01E56B1207EF9C6F2F8EF52C8EF">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="cms_channel" name-db="cms_channel" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

