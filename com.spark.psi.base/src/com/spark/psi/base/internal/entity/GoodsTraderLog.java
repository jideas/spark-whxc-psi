package com.spark.psi.base.internal.entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentFactory;
import org.dom4j.Element;
import org.xml.sax.SAXException;

import com.jiuqi.dna.core.misc.SXElement;
import com.jiuqi.dna.core.misc.SXElementBuilder;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.internal.entity.ormentity.GoodsTraderLogOrmEntity;

public class GoodsTraderLog{

	public static final String Encoding = "utf-8";
	public static final String Root = "trader";
	public static final String Count = "count";
	public static final String TotalTraderCount = "totalTraderCount";
	public static final String TotalTraderAmount = "totalTraderAmount";
	public static final String RecentCount = "recentCount";
	public static final String RecentPrice = "recentPrice";
	public static final String RecentDate = "recentDate";
	public static final String PriceList = "priceList";

	public GoodsTraderLog(GoodsTraderLogOrmEntity entity){
		this.setData(entity.getData());
		this.setGoodsItemId(entity.getGoodsItemId());
		this.setPartnerId(entity.getPartnerId());
	}

	public GoodsTraderLog(){

	}

	private GUID id;

	private GUID goodsItemId;

	private GUID partnerId;

	private int count;

	private double totalTraderCount;

	private double totalTraderAmount;

	private double recentCount;

	private long recentDate;

	private double recentPrice;

	private List<Double> priceList = new ArrayList<Double>();

	public int getCount(){
		return count;
	}

	public double getTotalTraderCount(){
		return totalTraderCount;
	}

	public double getTotalTraderAmount(){
		return totalTraderAmount;
	}

	public double getRecentCount(){
		return recentCount;
	}

	public void setRecentCount(double recentCount){
		this.recentCount = recentCount;
		this.count++;
		this.totalTraderCount += recentCount;
		this.recentDate = System.currentTimeMillis();
	}

	public long getRecentDate(){
		return recentDate;
	}

	//
	//	public void setRecentDate(long recentDate){
	//    	this.recentDate = recentDate;
	//    }

	public double getRecentPrice(){
		return recentPrice;
	}

	public void setRecentPrice(double recentPrice){
		this.recentPrice = recentPrice;
		this.totalTraderAmount += recentPrice;
		this.priceList.add(0, recentPrice);
	}

	public double[] getPriceList(){
		double[] result =
		        new double[priceList.size() > 10 ? 10 : priceList.size()];
		for(int i = 0; i < priceList.size(); i++){
			result[i] = priceList.get(i);
			if(i == 9) break;
		}
		return result;
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
		DocumentFactory factory = DocumentFactory.getInstance();
		Document doc = factory.createDocument(Encoding);
		Element root = doc.addElement(Root);
		root.addAttribute(Count, String.valueOf(this.getCount()));
		root.addAttribute(TotalTraderAmount, String.valueOf(this
		        .getTotalTraderAmount()));
		root.addAttribute(TotalTraderCount, String.valueOf(this
		        .getTotalTraderCount()));
		root.addAttribute(RecentPrice, String.valueOf(this.getRecentPrice())); 
		root.addAttribute(RecentCount, String.valueOf(this.getRecentCount()));
		root.addAttribute(RecentDate, String.valueOf(this.getRecentDate()));
		for(double price : this.getPriceList()){
			root.addElement(PriceList).setText(String.valueOf(price));
		}
		return doc.asXML();
	}

	public void setData(String data){
		try{
			SXElementBuilder seb = new SXElementBuilder();
			SXElement element = seb.build(data).firstChild();
			this.count = Integer.parseInt(element.getAttribute(Count));
			this.totalTraderAmount =
			        Double.parseDouble(element.getAttribute(TotalTraderAmount));
			this.totalTraderCount =
			        Double.parseDouble(element.getAttribute(TotalTraderCount));
			this.recentCount =
			        Double.parseDouble(element.getAttribute(RecentCount));
			this.recentDate = Long.parseLong(element.getAttribute(RecentDate));
			this.recentPrice =
			        Double.parseDouble(element.getAttribute(RecentPrice));
			Iterator<SXElement> it = element.getChildren(PriceList).iterator();
			List<Double> priceList = new ArrayList<Double>();
			while(it.hasNext()){
				SXElement child = it.next();
				priceList.add(Double.parseDouble(child.getText()));
			}
		}
		catch(IOException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch(SAXException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
