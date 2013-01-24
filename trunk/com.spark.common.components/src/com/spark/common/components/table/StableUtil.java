package com.spark.common.components.table;


import com.jiuqi.dna.ui.wt.graphics.Color;
import com.spark.common.components.table.edit.SNameValue;

/**
 * table工具类
 * 
 */
public class StableUtil {

	/**
	 * 根据传入的值自动生成一个超链接
	 * 
	 * @param actionName
	 *            操作名称
	 * @param actionValue
	 *            操作值
	 * @param textName
	 *            超链接的文本值
	 * @return
	 */
	public static String toLink(String actionName, String actionValue,
			String textName) {
		return toLink(new SNameValue(actionName, actionValue), null, null,
				textName,null);
	}
	
	/**
	 * 根据传入的值自动生成一个超链接,修改字体颜色
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
	 * 根据传入的值自动生成一个超链接，包括点击/光标移入/光标移出,修改字体颜色
	 * 
	 * @param actionName
	 *            操作名称
	 * @param actionValue
	 *            操作值
	 * @param textName
	 *            超链接的文本值
	 * @param color
	 *            文本颜色           
	 * @return
	 */
	public static String toLink(SNameValue clickInfo, SNameValue mouseInInfo,
			SNameValue mouseOutInfo, String textName) {
		return toLink( clickInfo,  mouseInInfo,
				 mouseOutInfo,  textName, null);
	}

	/**
	 * 根据传入的值自动生成一个超链接，包括点击/光标移入/光标移出
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
	 * 根据传入的值生成一个图片
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
