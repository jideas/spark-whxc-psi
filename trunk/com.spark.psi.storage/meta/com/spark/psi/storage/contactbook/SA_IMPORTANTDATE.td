<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="SA_IMPORTANTDATE" ID="F4D95052DCD25040E232B2B7487E1CF1" name-db="SA_IMPORTANTDATE" category="" title="" author="" description="重要日期" packagename="com.spark.psi.storage.contactbook" name="SA_IMPORTANTDATE" isneedrebuild="false">
  <fields>
    <field dbtable="SA_IMPORTANTDATE" name="tenantsGUID" name-db="tenantsGUID" type="guid" primary-key="false" keep-valid="false" author="" title="租户编号" description="" ID="FA4C53D41890072119253033B81BEB08" />
    <field dbtable="SA_IMPORTANTDATE" name="personGUID" name-db="personGUID" type="guid" primary-key="false" keep-valid="false" author="" title="所属联系人" description="" ID="EBAF3E5A0BDE5A20E0D43DED21E1498D" />
    <field dbtable="SA_IMPORTANTDATE" name="month" name-db="month" type="int" primary-key="false" keep-valid="false" author="" title="提醒月份" description="" ID="BC1C2FD4EACDEF5EA9EC95499950CE39" />
    <field dbtable="SA_IMPORTANTDATE" name="day" name-db="day" type="int" primary-key="false" keep-valid="false" author="" title="提醒日期" description="" ID="BA12F4398B36242A435707AD68372DA9" />
    <field dbtable="SA_IMPORTANTDATE" name="cycle" name-db="cycle" type="char(2)" primary-key="false" keep-valid="false" author="" title="提醒周期" description="" ID="BE641F918DCA5F10B85E246191526542" />
    <field dbtable="SA_IMPORTANTDATE" name="note" name-db="note" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="日期说明" description="" ID="CD7D4913FF51AA6D7C9DB1DBBF2AC665" />
    <field dbtable="SA_IMPORTANTDATE" name="msgToMe" name-db="msgToMe" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="消息内容给用户" description="" ID="48A5831670DFBBCFBC294F1804591B72" />
    <field dbtable="SA_IMPORTANTDATE" name="msgToObj" name-db="msgToObj" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="消息内容给联系人" description="" ID="83EA87200847534BD7E0C8CA9867DA57" />
    <field dbtable="SA_IMPORTANTDATE" name="alertToMe" name-db="alertToMe" type="nvarchar(5)" primary-key="false" keep-valid="false" author="" title="提醒方式给用户" description="" ID="8792C4B74A1E31801E693F20325531FE" />
    <field dbtable="SA_IMPORTANTDATE" name="alertToObj" name-db="alertToObj" type="nvarchar(5)" primary-key="false" keep-valid="false" author="" title="提醒方式给联系人" description="" ID="23AEF63E3CB66C1C318F996459B30C34" />
    <field dbtable="SA_IMPORTANTDATE" name="dateToMe" name-db="dateToMe" type="date" primary-key="false" keep-valid="false" author="" title="部门" description="" ID="7646F234CF1A101A72AC6298A23CBCEB" />
    <field dbtable="SA_IMPORTANTDATE" name="dateToObj" name-db="dateToObj" type="date" primary-key="false" keep-valid="false" author="" title="职位" description="" ID="4A05343D278C9049F608B61A947B1A39" />
    <field dbtable="SA_IMPORTANTDATE" name="meBeforeDay" name-db="meBeforeDay" type="int" primary-key="false" keep-valid="false" author="" title="给用户提前提醒天数" description="" ID="98AB4B28BD7687BA4563FC175C67CD56" />
    <field dbtable="SA_IMPORTANTDATE" name="meBeforeHour" name-db="meBeforeHour" type="int" primary-key="false" keep-valid="false" author="" title="给用户提前提醒小时" description="" ID="A4354D79F10C9919DA0B49B482165CE3" />
    <field dbtable="SA_IMPORTANTDATE" name="meBeforeMinute" name-db="meBeforeMinute" type="int" primary-key="false" keep-valid="false" author="" title="给用户提前提醒分" description="" ID="9FB0376396153EF7DFC5B35DFC6447C1" />
    <field dbtable="SA_IMPORTANTDATE" name="objBeforeDay" name-db="objBeforeDay" type="int" primary-key="false" keep-valid="false" author="" title="给联系人提前提醒天数" description="" ID="876B8742ED369D9B4FE7DC6E4ACD2782" />
    <field dbtable="SA_IMPORTANTDATE" name="objBeforeHour" name-db="objBeforeHour" type="int" primary-key="false" keep-valid="false" author="" title="给联系人提前提醒小时" description="" ID="CAC2C4F546D615152FA90C1306E77E7C" />
    <field dbtable="SA_IMPORTANTDATE" name="objBeforeMinute" name-db="objBeforeMinute" type="int" primary-key="false" keep-valid="false" author="" title="给联系人提前提醒分" description="" ID="2D77B8445B1CA2F9568ED5E99365F364" />
    <field dbtable="SA_IMPORTANTDATE" name="createPerson" name-db="createPerson" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人明文" description="" ID="02036E45D9ABC54737E568F51ABE5216" />
    <field dbtable="SA_IMPORTANTDATE" name="createPersonGUID" name-db="createPersonGUID" type="guid" primary-key="false" keep-valid="false" author="" title="" description="" ID="EE13D0B2410B426A2A190EC3309D016F" />
    <field dbtable="SA_IMPORTANTDATE" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建日期" description="" ID="03CF69BC9ACAC021BD9775145DC8DE01" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="SA_IMPORTANTDATE" name-db="SA_IMPORTANTDATE" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

