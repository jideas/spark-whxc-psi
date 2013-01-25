<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_MESSAGE_INFO" ID="01DF1BC36446AF25852DBE78DFFC9D96" name-db="SA_MESSAGE_INFO" category="报表主体" title="" author="" description="" packagename="com.spark.psi.message.storage" name="SA_MESSAGE_INFO" isneedrebuild="false">
  <fields>
    <field dbtable="SA_MESSAGE_INFO" name="messageType" name-db="messageType" type="char(4)" primary-key="false" keep-valid="false" author="" title="消息类型" description="" ID="D4802C6AF442338A855659186C67421B" />
    <field dbtable="SA_MESSAGE_INFO" name="relaObjId" name-db="relaObjId" type="guid" primary-key="false" keep-valid="false" author="" title="关联对象id" description="" ID="A0040A94CAADF99189E95A19E847FCE3" />
    <field dbtable="SA_MESSAGE_INFO" name="templateCode" name-db="templateCode" type="char(4)" primary-key="false" keep-valid="false" author="" title="显示模板code" description="" ID="C8DDE438ED835F44E48BFCE84F946942" />
    <field dbtable="SA_MESSAGE_INFO" name="stringValue1" name-db="stringValue1" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="内容" description="" ID="84EF2A5D6954A9D2EC01DAAC03534382" />
    <field dbtable="SA_MESSAGE_INFO" name="stringValue2" name-db="stringValue2" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="内容" description="" ID="8C237F6A2E415487EA81D236BCC380D2" />
    <field dbtable="SA_MESSAGE_INFO" name="stringValue3" name-db="stringValue3" type="varchar(50)" primary-key="false" keep-valid="false" author="" title="内容" description="" ID="04DF42E82647DEC5A0C34514D2D85D6A" />
  </fields>
  <indexs>
    <index author="" name="SA_MESSAGE_INDEX" name-db="SA_MESSAGE_INDEX" title="SA_MESSAGE_INDEX" unique="false" description="">
      <index-item fieldID="A0040A94CAADF99189E95A19E847FCE3" field="relaObjId" field-author="" desc="false" />
      <index-item fieldID="D4802C6AF442338A855659186C67421B" field="messageType" field-author="" desc="false" />
    </index>
  </indexs>
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_MESSAGE_INFO" name-db="SA_MESSAGE_INFO" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

