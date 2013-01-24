<?xml version="1.0" encoding="UTF-8"?>
<tabledefine storagename="PSI_DeliverTicket_Det" ID="9677164329D8F39CE9AA45F53D8D76C0" name-db="PSI_DeliverTicket_Det" category="" title="发货单明细" author="" description="" packagename="com.spark.psi.storage.sales" name="PSI_DeliverTicket_Det" isneedrebuild="false">
  <fields>
    <field dbtable="PSI_DeliverTicket_Det" name="ticketId" name-db="ticketId" type="guid" primary-key="false" keep-valid="false" author="" title="订单GUID" description="" ID="2EB01DA35BA7C0E64860EE732B3C33B7" />
    <field dbtable="PSI_DeliverTicket_Det" name="goodsId" name-db="goodsId" type="guid" primary-key="false" keep-valid="false" author="" title="商品ID" description="" ID="9491CC2D084ADEA52B9D23203C1319BD" />
    <field dbtable="PSI_DeliverTicket_Det" name="goodsCode" name-db="goodsCode" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品编码" description="" ID="5EAF27F1F4D8B31D2599F7C51299623A" />
    <field dbtable="PSI_DeliverTicket_Det" name="goodsNo" name-db="goodsNo" type="nvarchar(30)" primary-key="false" keep-valid="false" author="" title="商品条码" description="" ID="7F601C0D6830C6897F82603C60F783CC" />
    <field dbtable="PSI_DeliverTicket_Det" name="goodsName" name-db="goodsName" type="nvarchar(50)" primary-key="false" keep-valid="false" author="" title="商品名称" description="" ID="D51D3FCAD0D48525AEA33030BF0B955C" />
    <field dbtable="PSI_DeliverTicket_Det" name="goodsSpec" name-db="goodsSpec" type="nvarchar(1000)" primary-key="false" keep-valid="false" author="" title="商品规格" description="" ID="1BED2BD69ACE56CDA302B39BEDC753F1" />
    <field dbtable="PSI_DeliverTicket_Det" name="unit" name-db="unit" type="nvarchar(10)" primary-key="false" keep-valid="false" author="" title="单位" description="" ID="9E21D08AB8E0727AAAA48733F9714E21" />
    <field dbtable="PSI_DeliverTicket_Det" name="goodsScale" name-db="goodsScale" type="int" primary-key="false" keep-valid="false" author="" title="商品小数位数" description="" ID="6E2FDF1B65ADCCA8F6D32DE8397C7D3A" />
    <field dbtable="PSI_DeliverTicket_Det" name="price" name-db="price" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="单价" description="" ID="3AF37DF9C9034DD5F3DB3F974754F374" />
    <field dbtable="PSI_DeliverTicket_Det" name="count" name-db="count" type="numeric(17,5)" primary-key="false" keep-valid="false" author="" title="数量" description="" ID="C351A8FE1A41F0A0FADC8FB398F37664" />
    <field dbtable="PSI_DeliverTicket_Det" name="disRate" name-db="disRate" type="numeric(6,5)" primary-key="false" keep-valid="false" author="" title="折扣率" description="" ID="FB49393C38A1B8C3A095C13D8E78B34A" />
    <field dbtable="PSI_DeliverTicket_Det" name="disAmount" name-db="disAmount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="折扣额" description="" ID="45B85AA7800DE864E300BC727AC4A418" />
    <field dbtable="PSI_DeliverTicket_Det" name="amount" name-db="amount" type="numeric(17,2)" primary-key="false" keep-valid="false" author="" title="金额" description="" ID="6DEF61695BE2E276CDE87F40C22F0A73" />
  </fields>
  <indexs />
  <hierarchies />
  <dbtables>
    <dbtable author="" name="PSI_DeliverTicket_Det" name-db="PSI_DeliverTicket_Det" title="" description="" />
  </dbtables>
  <relations />
</tabledefine>

