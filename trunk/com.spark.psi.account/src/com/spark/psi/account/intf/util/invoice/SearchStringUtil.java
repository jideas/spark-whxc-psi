/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.bus.finance.invoice.intf.util
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2011-11-28       Wangtiancai        
 * ============================================================*/

package com.spark.psi.account.intf.util.invoice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import com.spark.psi.account.intf.constant.invoice.InvoiceConstant;
import com.spark.psi.publish.InvoiceType;

/**
 * <p>�ַ����������봦����</p>
 *	�����ڷ���value�д��ڵ�keyֵ���ϡ�keyֵ���ظ�
 * <p>Copyright: ��Ȩ���� (c) 2002 - 2008<br>

 *
 * @author Wangtiancai
 * @version 2011-11-28
 */
public class SearchStringUtil {
	private final Map<String, String> map = new HashMap<String, String>();
	/**
	 * ����key-value
	 * @param key
	 * @param value
	 * @return StringSearchUtil
	 */
	public SearchStringUtil put(String key, String value){
		map.put(key, value);
		return this;
	}
	
	public SearchStringUtil()
	{
		initMap();
	}
	private void initMap() {
		map.put(InvoiceConstant.ZZSFP, "��ֵ˰��Ʊ");
		map.put(InvoiceConstant.PTFP, "��ͨ��Ʊ");
	}

	/**
	 * ��ȡvalue�д���ָ��ֵ��keyֵ����
	 * @param search
	 * @return List<String>
	 */
	public List<String> getKeys(String searchText){
		List<String> list = new ArrayList<String>();
		if(null==searchText||"".equals(searchText))
		{
			return list;
		}
		if(InvoiceType.Common.getName().indexOf(searchText)!=-1)
		{
			list.add(InvoiceType.Common.getCode());
		}
		if(InvoiceType.ValueAddesTax.getName().indexOf(searchText)!=-1)
		{
			list.add(InvoiceType.ValueAddesTax.getCode());
		}
		return list;
	}
	
	/**
	 * ��ռ���
	 *  void
	 */
	public void clear(){
		map.clear();
	}
}
