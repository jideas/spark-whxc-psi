<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_Deliver_Ticket" ID="B2679BC6BFA1D6A5EED72086E2F768C5" name-db="PSI_Deliver_Ticket" category="" title="发货单" author="" description="" packagename="com.spark.psi.storage.sales" name="PSI_Deliver_Ticket" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_Deliver_Ticket" name="sheetNo" name-db="sheetNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="单据编号" description="" ID="2C86D1D4E0489CA9D578E83BC164F910" />
    <field dbtable="PSI_Deliver_Ticket" name="onlineOrderId" name-db="onlineOrderId" type="guid" primary-key="false" keep-valid="false" author="" title="网上订单id" description="" ID="EF1CEA01036B26F04CE31CCD686B7AAD" />
    <field dbtable="PSI_Deliver_Ticket" name="onlineOrderNo" name-db="onlineOrderNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="网上订单编号" description="" ID="4A4F5ED788803C7E9ED9EBBE193DB543" />
    <field dbtable="PSI_Deliver_Ticket" name="memberId" name-db="memberId" type="guid" primary-key="false" keep-valid="false" author="" title="客户ID" description="" ID="25C8560CF17ED0FEFC6847C86D339D70" />
    <field dbtable="PSI_Deliver_Ticket" name="memberRealName" name-db="memberRealName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="客户名称" description="" ID="FFBEFFE17F36BD055F124A7F6D552030" />
    <field dbtable="PSI_Deliver_Ticket" name="mobilePhone" name-db="mobilePhone" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="手机号码" description="" ID="AB741B9F373DF5AA1834ECAACCE56348" />
    <field dbtable="PSI_Deliver_Ticket" name="stationId" name-db="stationId" type="guid" primary-key="false" keep-valid="false" author="" title="站点Id" description="" ID="E79F0AA294BAC7CFE9FF4C090F000441" />
    <field dbtable="PSI_Deliver_Ticket" name="stationName" name-db="stationName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="站点名称" description="" ID="94A0720864D20094225028DDE5885CB4" />
    <field dbtable="PSI_Deliver_Ticket" name="remark" name-db="remark" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="备注" description="" ID="4410B19006489544172C0E59CF6D8C70" />
    <field dbtable="PSI_Deliver_Ticket" name="disAmount" name-db="disAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="折扣金额" description="" ID="DE35601EDE18C892F64DA2BB9B3026CB" />
    <field dbtable="PSI_Deliver_Ticket" name="totalAmount" name-db="totalAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="订单金额" description="" ID="2F13CDACC5EBAA8659C24F169A89B7D8" />
    <field dbtable="PSI_Deliver_Ticket" name="creatorId" name-db="creatorId" type="guid" primary-key="false" keep-valid="false" author="" title="发货人Id" description="" ID="D17FC88569F1D989D2D95246194FB616" />
    <field dbtable="PSI_Deliver_Ticket" name="creator" name-db="creator" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="发货人" description="" ID="549B09C478641AFC03781897D1A2D27E" />
    <field dbtable="PSI_Deliver_Ticket" name="createDate" name-db="createDate" type="date" primary-key="false" keep-valid="false" author="" title="发货日期" description="" ID="8B0FA6124C9CC1FCDB4707E077CE2A83" />
    <field dbtable="PSI_Deliver_Ticket" name="address" name-db="address" type="nvarchar(200)" primary-key="false" keep-valid="false" author="" title="地址" description="" ID="7B4F48949E7FCA966788A77920E21636" />
    <field dbtable="PSI_Deliver_Ticket" name="status" name-db="status" type="char(2)" primary-key="false" keep-valid="false" author="" title="状态" description="" ID="58603AFE269525E3BB1381DF862D7943" />
    <field dbtable="PSI_Deliver_Ticket" name="deliverPersonId" name-db="deliverPersonId" type="guid" primary-key="false" keep-valid="false" author="" title="配送人Id" description="" ID="69618D03850E01AD3DDF045E7C745DAA" />
    <field dbtable="PSI_Deliver_Ticket" name="deliverPerson" name-db="deliverPerson" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="配送人" description="" ID="106B8AF3DA980616307DC36CB7868F85" />
    <field dbtable="PSI_Deliver_Ticket" name="deliverDate" name-db="deliverDate" type="date" primary-key="false" keep-valid="false" author="" title="配送日期" description="" ID="44143FA66CBF90C322EF278523A01D70" />
    <field dbtable="PSI_Deliver_Ticket" name="consigneeId" name-db="consigneeId" type="guid" primary-key="false" keep-valid="false" author="" title="收货人Id" description="" ID="9847784BD2D8A5CAFB2587269CC7676C" />
    <field dbtable="PSI_Deliver_Ticket" name="consignee" name-db="consignee" type="nvarchar(40)" primary-key="false" keep-valid="false" author="" title="收货人" description="" ID="10220F32B78F7A6B882F3A99A1C0593D" />
    <field dbtable="PSI_Deliver_Ticket" name="consigneeDate" name-db="consigneeDate" type="date" primary-key="false" keep-valid="false" author="" title="收货日期" description="" ID="4AE7763E3388F9223BCB45BC97828550" />
    <field dbtable="PSI_Deliver_Ticket" name="deliverTicketType" name-db="deliverTicketType" type="char(2)" primary-key="false" keep-valid="false" author="" title="发货类型" description="" ID="A2200C44C0BC089E4E8E60A9FEE615AC" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_Deliver_Ticket" name-db="PSI_Deliver_Ticket" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

