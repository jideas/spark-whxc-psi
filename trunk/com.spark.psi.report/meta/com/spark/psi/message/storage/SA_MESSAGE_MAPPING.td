<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_MESSAGE_MAPPING" ID="A31B586D96ACB5FAD6959209248F696D" name-db="SA_MESSAGE_MAPPING" category="报表主体" title="消息人员映射表" author="" description="" packagename="com.spark.psi.message.storage" name="SA_MESSAGE_MAPPING" isneedrebuild="false">
  <fields>
    <field dbtable="SA_MESSAGE_MAPPING" name="userId" name-db="userId" type="guid" primary-key="false" keep-valid="false" author="" title="消息接收人" description="" ID="4960DB202829009F3406726A71830E42" />
    <field dbtable="SA_MESSAGE_MAPPING" name="messageId" name-db="messageId" type="guid" primary-key="false" keep-valid="false" author="" title="消息主体" description="" ID="25AB3B981E1AC8D6612B0133D16C16FE" />
    <field dbtable="SA_MESSAGE_MAPPING" name="messageType" name-db="messageType" type="char(4)" primary-key="false" keep-valid="false" author="" title="消息类型" description="" ID="A198C631CCE911EBF806013B339EA452" />
    <field dbtable="SA_MESSAGE_MAPPING" name="startTime" name-db="startTime" type="date" primary-key="false" keep-valid="false" author="" title="开始时间" description="" ID="E47CB1BFC2E429737FC66738704F46C2" />
    <field dbtable="SA_MESSAGE_MAPPING" name="endTime" name-db="endTime" type="date" primary-key="false" keep-valid="false" author="" title="停止时间" description="" ID="A263454FED36C4F2875478C1E796EC51" />
    <field dbtable="SA_MESSAGE_MAPPING" name="showFlag" name-db="showFlag" type="boolean" primary-key="false" keep-valid="true" author="" title="是否显示过" description="" ID="BF8FE63D26250BCBDBC251B68272D09E">
      <default>
        <const type="boolean" value="false" />
      </default>
    </field>
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_MESSAGE_MAPPING" name-db="SA_MESSAGE_MAPPING" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

