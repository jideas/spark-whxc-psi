<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Base_Notice" ID="FF93011EFEF4D0C736FC7EA5573DCCB7" name-db="PSI_Base_Notice" category="基础数据主体" title="公告" author="" description="" packagename="com.spark.psi.storage.base.notice" name="PSI_Base_Notice" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Base_Notice" name="deptId" name-db="deptId" type="guid" primary-key="false" keep-valid="false" author="" title="部门编号" description="" ID="12DD7AB2E6DA2EDEEFFB35EF0A464BBD" />
    <field dbtable="PSI_Base_Notice" name="publishScope" name-db="publishScope" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="发布范围" description="" ID="6A30BB06ECD528D4E6D72AEB352A917E" />
    <field dbtable="PSI_Base_Notice" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="创建人Guid" description="" ID="C775990C75497BD5BBB172BA8EE00C16" />
    <field dbtable="PSI_Base_Notice" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="创建人" description="" ID="B28980946F75CB55F498A0569A6D6A2B" />
    <field dbtable="PSI_Base_Notice" name="noticeTitle" name-db="noticeTitle" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="公告标题" description="" ID="9D913453275C74CBE3D1F39324D7FB54" />
    <field dbtable="PSI_Base_Notice" name="noticeTitlePY" name-db="noticeTitlePY" type="nvarchar(100)" primary-key="false" keep-valid="false" author="" title="公告标题拼音" description="" ID="3E3E384693235A9EB000E5518237CE97" />
    <field dbtable="PSI_Base_Notice" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="创建时间" description="" ID="6B4F78F3955F2AB38AB9B97BB82A2E25" />
    <field dbtable="PSI_Base_Notice" name="publishDate" name-db="publishDate" type="date" primary-key="false" keep-valid="false" author="" title="发布时间" description="" ID="B0763C8193026D948E16F46E4C48D3E9" />
    <field dbtable="PSI_Base_Notice" name="cancelDate" name-db="cancelDate" type="date" primary-key="false" keep-valid="false" author="" title="撤销时间" description="" ID="1EA3414D55322EDB3B399F537FE6EDB8" />
    <field dbtable="PSI_Base_Notice" name="isTop" name-db="isTop" type="boolean" primary-key="false" keep-valid="false" author="" title="是否置顶" description="" ID="4FFDD7318091EB431F653728AACE984E" />
    <field dbtable="PSI_Base_Notice" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="公告状态" description="" ID="05A8D6FA6A041A36424300FC33653A07" />
    <field dbtable="PSI_Base_Notice" name="noticeContent" name-db="noticeContent" type="text" primary-key="false" keep-valid="false" author="" title="公告内容" description="" ID="FE02A0B7BB40140640155FBEBA8F1A7B" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Base_Notice" name-db="PSI_Base_Notice" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

