package com.spark.psi.base.internal.entity.ormentity;

import com.jiuqi.dna.core.type.GUID;

public class GoodsTraderLogOrmEntity{
	
	private GUID id;
	
	private GUID goodsItemId;
	
	private GUID goodsId;
	
	private GUID partnerId;
	
	private String data;
	
	private String type;

	
	
	public GUID getGoodsId(){
    	return goodsId;
    }

	public void setGoodsId(GUID goodsId){
    	this.goodsId = goodsId;
    }

	public GUID getId(){
    	return id;
    }

	public void setId(GUID id){
    	this.id = id;
    }

	public GUID getGoodsItemId(){
    	return goodsItemId;
    }

	public void setGoodsItemId(GUID goodsItemId){
    	this.goodsItemId = goodsItemId;
    }

	public GUID getPartnerId(){
    	return partnerId;
    }

	public void setPartnerId(GUID partnerId){
    	this.partnerId = partnerId;
    }

	public String getData(){
    	return data;
    }

	public void setData(String data){
    	this.data = data;
    }

	public String getType(){
    	return type;
    }

	public void setType(String type){
    	this.type = type;
    }
	
	
	
}
