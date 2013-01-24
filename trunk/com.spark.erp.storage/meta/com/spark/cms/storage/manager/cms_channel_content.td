<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="cms_channel_content" ID="75B1569769DECADD2066128095B13D87" name-db="cms_channel_content" category="业务主体" title="内容管理" author="" description="" packagename="com.spark.cms.storage.manager" name="cms_channel_content" isneedrebuild="false">
  <fields>
    <field dbtable="cms_channel_content" name="channelId" name-db="channelId" type="guid" primary-key="false" keep-valid="false" author="" title="栏目id" description="" ID="846BBA013E1086A9A3CC8F46C7489E47" />
    <field dbtable="cms_channel_content" name="code" name-db="code" type="nvarchar(20)" primary-key="false" keep-valid="false" author="" title="编号" description="" ID="6D7301F8DC777489EF76ACE4CD59DD7A" />
    <field dbtable="cms_channel_content" name="title" name-db="title" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="名称" description="" ID="F970E5B58DDCEB23844F1399FE6052D7" />
    <field dbtable="cms_channel_content" name="content" name-db="content" type="text" primary-key="false" keep-valid="false" author="" title="内容" description="" ID="B8CDD312B655B8140534E16A416932A9" />
    <field dbtable="cms_channel_content" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="4619BF4F14552EDCABC555627F974C62" />
    <field dbtable="cms_channel_content" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="" description="" ID="603082D0921FB1FB8EA817325FFEBE1B" />
    <field dbtable="cms_channel_content" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="" description="" ID="CB8B01E0F569961BC66B04BB432EDF8F" />
    <field dbtable="cms_channel_content" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="F5C64A91A0059630E3BB6E8C58470F50" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="cms_channel_content" name-db="cms_channel_content" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

