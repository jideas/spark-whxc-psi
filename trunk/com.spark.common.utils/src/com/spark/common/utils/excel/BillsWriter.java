package com.spark.common.utils.excel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.spark.common.utils.character.CheckIsNull;

@SuppressWarnings("deprecation")
public class BillsWriter {

	private OutputStream out;
	private String title;
	private List<DoubleString> labels;
	private List<DoubleString> totalLabels;
	private String[] head;
	private List<String[]> data;

	public BillsWriter(OutputStream out) {
		this.out = out;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addLabel(String label, String value) {
		if (null == labels) {
			labels = new ArrayList<DoubleString>();
		}
		labels.add(new DoubleString(label, value));
	}

	public void setTotalLabel(String label, String value) {
		if (null == totalLabels) {
			totalLabels = new ArrayList<DoubleString>();
		}
		this.totalLabels.add(new DoubleString(label, value));
	}

	public void setTotalLabel(DoubleString ds) {
		if (null == totalLabels) {
			totalLabels = new ArrayList<DoubleString>();
		}
		this.totalLabels.add(ds);
	}

	public void setTable(String[] head, List<String[]> data) {
		this.head = head;
		this.data = data;
	}

	public void write(String fileName) throws Exception {
		if (!vilidate()) {
			return;
		}
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(fileName);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 15);
		// 生成一个样式
		HSSFCellStyle style = this.getBlueStyle(workbook);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = this.getYellowStyle(workbook);
		HSSFCellStyle style3 = this.getTitleStyle(workbook);
		HSSFCellStyle style4 = this.getLabelStyle(workbook);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
		comment.setAuthor("leno");
		int rowIndex = 0;
		HSSFRow row0 = sheet.createRow((short) rowIndex);
		Region region0 = new Region(rowIndex, (short) 0, rowIndex, (short) (head.length - 1));
		sheet.addMergedRegion(region0);
		setRegionStyle(sheet, region0, style3);
		HSSFCell ce0 = row0.createCell((short) 0);
		ce0.setCellValue(title); // 表格的第一行第一列显示的数据
		ce0.setCellStyle(style3);
		rowIndex++;

		for (DoubleString label : labels) {
			HSSFRow row = sheet.createRow((short) rowIndex);
			Region region = new Region(rowIndex, (short) 1, rowIndex, (short) (head.length - 1));
			sheet.addMergedRegion(region);
			setRegionStyle(sheet, region, style4);
			HSSFCell ce = row.createCell((short) 0);
			ce.setCellValue(label.getLabel()); // 表格的第一行第一列显示的数据
			ce.setCellStyle(style4);
			HSSFCell ce1 = row.createCell((short) 1);
			ce1.setCellValue(label.getValue()); // 表格的第一行第一列显示的数据
			ce1.setCellStyle(style4);
			rowIndex++;
		}

		// 产生表格标题行
		HSSFRow row = sheet.createRow(rowIndex);
		for (short i = 0; i < head.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(head[i]);
			cell.setCellValue(text);
		}

		// 遍历集合数据，产生数据行
		Iterator<String[]> it = data.iterator();
		while (it.hasNext()) {
			rowIndex++;
			row = sheet.createRow(rowIndex);
			String[] t = it.next();
			for (short i = 0; i < t.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				try {
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (t[i] != null) {
						HSSFRichTextString richString = new HSSFRichTextString(t[i]);
						HSSFFont font3 = workbook.createFont();
						font3.setColor(HSSFColor.BLUE.index);
						richString.applyFont(font3);
						cell.setCellValue(richString);
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} finally {
				}
			}
		}

		if (totalLabels != null) {
			for (DoubleString totalLabel : totalLabels) {
				rowIndex++;
				HSSFRow rowl = sheet.createRow((short) rowIndex);
				HSSFCell cel = rowl.createCell((short) 0);
				cel.setCellValue(totalLabel.getLabel()); // 表格的第一行第一列显示的数据
				cel.setCellStyle(style4);
				for (int i = 1; i < head.length - 1; i++) {
					HSSFCell cel2 = rowl.createCell((short) i);
					cel2.setCellValue(" "); // 表格的第一行第一列显示的数据
					cel2.setCellStyle(style4);
				}

				HSSFCell cel2 = rowl.createCell((short) head.length - 1);
				cel2.setCellValue(totalLabel.getValue()); // 表格的第一行第一列显示的数据
				cel2.setCellStyle(style4);
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
	}

	private boolean vilidate() {
		if (null == out) {
			return false;
		}
		if (CheckIsNull.isEmpty(title)) {
			return false;
		}
		if (null == labels) {
			return false;
		}
		if (null == head) {
			return false;
		}
		if (null == data) {
			return false;
		}
		return true;
	}

	private void setRegionStyle(HSSFSheet sheet, Region region, HSSFCellStyle cs) {
		for (int i = region.getRowFrom(); i <= region.getRowTo(); i++) {
			HSSFRow row = HSSFCellUtil.getRow(i, sheet);
			for (int j = region.getColumnFrom(); j <= region.getColumnTo(); j++) {
				HSSFCell cell = HSSFCellUtil.getCell(row, (short) j);
				cell.setCellStyle(cs);
			}
		}
	}

	private HSSFCellStyle getTitleStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.LIGHT_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font = workbook.createFont();
		// font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 14);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		style.setFont(font);
		return style;
	}

	private HSSFCellStyle getLabelStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.LIGHT_GREEN.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style.setFont(font2);
		return style;
	}

	private HSSFCellStyle getBlueStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		return style;
	}

	private HSSFCellStyle getYellowStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.LIGHT_YELLOW.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);
		return style2;
	}

	public static void main(String[] a) {

		File file = new File("D://a.xls");
		OutputStream out = null;
		try {
			out = new FileOutputStream(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BillsWriter bw = new BillsWriter(out);
		bw.setTitle("材料入库单");
		bw.addLabel("供应商", "新晨视频");
		bw.addLabel("仓库", "仓库A");
		bw.addLabel("出库日期", "2012-11-11");
		bw.addLabel("创建人", "老张");
		String[] head = new String[] { "商品编号", "商品条码", "商品名称", "商品规格", "商品单位", "数量", "单价", "金额" };
		List<String[]> list = new ArrayList<String[]>();
		list.add(new String[] { "0101", "11010101", "戒指", "100g", "个", "123.11", "22.22", "3,333.33" });
		list.add(new String[] { "0101", "11010101", "戒指", "100g", "个", "123.11", "22.22", "3,333.33" });
		list.add(new String[] { "0101", "11010101", "戒指", "100g", "个", "123.11", "22.22", "3,333.33" });
		bw.setTotalLabel(new DoubleString("订单金额", "999,999.99"));
		bw.setTable(head, list);
		try {
			bw.write("测试文件");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
