package com.spark.common.components.table;


import com.jiuqi.dna.ui.wt.graphics.Color;
import com.spark.common.components.table.edit.SNameValue;

/**
 * table������
 * 
 */
public class StableUtil {

	/**
	 * ���ݴ����ֵ�Զ�����һ��������
	 * 
	 * @param actionName
	 *            ��������
	 * @param actionValue
	 *            ����ֵ
	 * @param textName
	 *            �����ӵ��ı�ֵ
	 * @return
	 */
	public static String toLink(String actionName, String actionValue,
			String textName) {
		return toLink(new SNameValue(actionName, actionValue), null, null,
				textName,null);
	}
	
	/**
	 * ���ݴ����ֵ�Զ�����һ��������,�޸�������ɫ
	 * @param actionName
	 * @param actionValue
	 * @param textName
	 * @param color
	 * @return
	 */
	public static String toLink(String actionName, String actionValue,
			String textName,Color color) {
		return toLink(new SNameValue(actionName, actionValue), null, null,
				textName,color);
	}
	
	/**
	 * ���ݴ����ֵ�Զ�����һ�������ӣ��������/�������/����Ƴ�,�޸�������ɫ
	 * 
	 * @param actionName
	 *            ��������
	 * @param actionValue
	 *            ����ֵ
	 * @param textName
	 *            �����ӵ��ı�ֵ
	 * @param color
	 *            �ı���ɫ           
	 * @return
	 */
	public static String toLink(SNameValue clickInfo, SNameValue mouseInInfo,
			SNameValue mouseOutInfo, String textName) {
		return toLink( clickInfo,  mouseInInfo,
				 mouseOutInfo,  textName, null);
	}

	/**
	 * ���ݴ����ֵ�Զ�����һ�������ӣ��������/�������/����Ƴ�
	 */
	private static String toLink(SNameValue clickInfo, SNameValue mouseInInfo,
			SNameValue mouseOutInfo, String textName,Color color) {
		StringBuffer buffer = new StringBuffer();
		buffer.append("<a href=\"javascript:void(0)\" ");
		if (clickInfo != null) {
			buffer.append("onclick=\"notifyGrid('" + clickInfo.getName()
					+ "','" + clickInfo.getValue() + "',event)\" ");
		}
		if (mouseInInfo != null) {
			buffer.append("onmouseover=\"notifyGrid('" + mouseInInfo.getName()
					+ "','" + mouseInInfo.getValue() + "',event)\" ");
		}
		if (mouseOutInfo != null) {
			buffer.append("onmouseout=\"notifyGrid('" + mouseOutInfo.getName()
					+ "','" + mouseOutInfo.getValue() + "',event)\" ");
		}
		if(color != null) {
			buffer.append("style='color:"+color.toString()+"'");
		}
		buffer.append(">" + textName + "</a>");
		return buffer.toString();
	}

	/**
	 * ���ݴ����ֵ����һ��ͼƬ
	 * 
	 * @param imgSrc
	 * @param imgTitle
	 * @param textName
	 * @return
	 */
	public static String toImg(String imgSrc, String imgTitle, int width) {
		return "<img src='" + imgSrc + "' title='" + imgTitle
				+ "' style='width:" + width
				+ "px;height:15px;vertical-align:middle;' />&nbsp;";
	}
}
