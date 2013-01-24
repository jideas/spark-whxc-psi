<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="CMS_CARD_CONFIG" ID="366B6499096000B6DA415FA7D761B03B" name-db="CMS_CARD_CONFIG" category="业务主体" title="充值配置" author="" description="" packagename="com.spark.cms.storage.card" name="CMS_CARD_CONFIG" isneedrebuild="false">
  <fields>
    <field dbtable="CMS_CARD_CONFIG" name="amount" name-db="amount" type="numeric(10,2)" primary-key="false" keep-valid="false" author="" title="面值" description="" ID="E1781CB5571A46AA59AFEF292B653C5B" />
    <field dbtable="CMS_CARD_CONFIG" name="promotionType" name-db="promotionType" type="char(2)" primary-key="false" keep-valid="false" author="" title="促销方式" description="" ID="9FB534C0438D577D79EEF9B79E3F7DD5" />
    <field dbtable="CMS_CARD_CONFIG" name="giftId" name-db="giftId" type="guid" primary-key="false" keep-valid="false" author="" title="赠品id" description="" ID="69FDE8270E7DAC223ED0A6032C98C651" />
    <field dbtable="CMS_CARD_CONFIG" name="realValue" name-db="realValue" type="numeric(10,0)" primary-key="false" keep-valid="false" author="" title="值（积分数，赠品数，送金额）" description="" ID="5FBD29DF804491907F7FA8E7B66A71F8" />
    <field dbtable="CMS_CARD_CONFIG" name="giftLastDate" name-db="giftLastDate" type="date" primary-key="false" keep-valid="false" author="" title="赠品截止日期" description="" ID="A9FCBFAA518F04BB9A4B24D7693A311A" />
    <field dbtable="CMS_CARD_CONFIG" name="beginDate" name-db="beginDate" type="date" primary-key="false" keep-valid="false" author="" title="开始日期" description="" ID="42E7B34CDCF03BA050A75A48FC6AED12" />
    <field dbtable="CMS_CARD_CONFIG" name="endDate" name-db="endDate" type="date" primary-key="false" keep-valid="false" author="" title="结束日期" description="" ID="8484C4C6CE978B861F5BAC02DDA6E006" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="CMS_CARD_CONFIG" name-db="CMS_CARD_CONFIG" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

