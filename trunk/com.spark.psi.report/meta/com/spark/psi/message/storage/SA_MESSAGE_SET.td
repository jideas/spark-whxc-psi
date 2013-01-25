<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_MESSAGE_SET" ID="01DF1BC36446AF25852DBE78DFFC9D96" name-db="SA_MESSAGE_SET" category="业务主体" title="消息提醒设置" author="" description="" packagename="com.spark.psi.message.storage" name="SA_MESSAGE_SET" isneedrebuild="false">
  <fields>
    <field dbtable="SA_MESSAGE_SET" name="tenantId" name-db="tenantId" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="8440550E16459334A43FE94478611252" />
    <field dbtable="SA_MESSAGE_SET" name="setType" name-db="setType" type="char(4)" primary-key="false" keep-valid="false" author="" title="设置类型" description="" ID="883F98FA90F6E1A84BD67C0D074A93DF" />
    <field dbtable="SA_MESSAGE_SET" name="messageType" name-db="messageType" type="char(4)" primary-key="false" keep-valid="false" author="" title="消息类型" description="" ID="D4802C6AF442338A855659186C67421B" />
    <field dbtable="SA_MESSAGE_SET" name="userId" name-db="userId" type="guid" primary-key="false" keep-valid="false" author="" title="设置人id" description="" ID="A0040A94CAADF99189E95A19E847FCE3" />
    <field dbtable="SA_MESSAGE_SET" name="bvalue" name-db="bvalue" type="boolean" primary-key="false" keep-valid="false" author="" title="设置值" description="" ID="01D5D36E4A06A2E224C28677A4276750">
      <default>
        <const type="boolean" value="true" />
      </default>
    </field>
  </fields>
  <indexs>
    <index author="" name="SA_MESSAGE_INDEX" name-db="SA_MESSAGE_INDEX" title="SA_MESSAGE_INDEX" unique="false" description="">
      <index-item fieldID="A0040A94CAADF99189E95A19E847FCE3" field="userId" field-author="" desc="false" />
      <index-item fieldID="D4802C6AF442338A855659186C67421B" field="messageType" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_MESSAGE_SET" name-db="SA_MESSAGE_SET" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

