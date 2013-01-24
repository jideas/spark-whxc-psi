package com.spark.common.utils.excel;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFCellUtil;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;

import com.spark.common.utils.character.CheckIsNull;

@SuppressWarnings("deprecation")
public class OnlineBillWriter {

	private OutputStream out;
	private List<String[]> data;

	public OnlineBillWriter(OutputStream out) {
		this.out = out;
	}

	public void setData(List<String[]> data) {
		this.data = data;
	}

	private void addCell(HSSFRow row0, int columnIndex, String text, HSSFCellStyle style3) {
		HSSFCell ce0 = row0.createCell((short) columnIndex);
		ce0.setCellValue(text); // ���ĵ�һ�е�һ����ʾ������
		ce0.setCellStyle(style3);
	}

	private void addRegion(HSSFSheet sheet, HSSFCellStyle style3, int a, int b, int c, int d) {
		Region region0 = new Region(a, (short) b, c, (short) d);
		sheet.addMergedRegion(region0);
		setRegionStyle(sheet, region0, style3);
	}

	private void initHead(HSSFSheet sheet, HSSFCellStyle style3) {
		HSSFRow row0 = sheet.createRow((short) 0);
		HSSFRow row1 = sheet.createRow((short) 1);
		addRegion(sheet, style3, 0, 0, 1, 0);
		addCell(row0, 0, "�������", style3);

		addRegion(sheet, style3, 0, 1, 0, 3);
		addCell(row0, 1, "������Ʒ", style3);

		addCell(row1, 1, "��Ʒ����", style3);
		addCell(row1, 2, "��Ʒ���", style3);
		addCell(row1, 3, "��Ʒ����", style3);

		addRegion(sheet, style3, 0, 4, 1, 4);
		addCell(row0, 4, "��Ա����", style3);

		addRegion(sheet, style3, 0, 5, 1, 5);
		addCell(row0, 5, "�������", style3);

		addRegion(sheet, style3, 0, 6, 1, 6);
		addCell(row0, 6, "�µ�����", style3);

		addRegion(sheet, style3, 0, 7, 1, 7);
		addCell(row0, 7, "����ʱ��", style3);

		addRegion(sheet, style3, 0, 8, 1, 8);
		addCell(row0, 8, "վ��", style3);
	}

	public void write(String fileName) throws Exception {
		// ����һ��������
		HSSFWorkbook workbook = new HSSFWorkbook();
		// ����һ�����
		HSSFSheet sheet = workbook.createSheet(fileName);
		// ���ñ��Ĭ���п��Ϊ15���ֽ�
		sheet.setDefaultColumnWidth((short) 15);
		// ����һ����ʽ
		HSSFCellStyle style = this.getBlueStyle(workbook);
		// ���ɲ�������һ����ʽ
		HSSFCellStyle style2 = this.getYellowStyle(workbook);

		this.initHead(sheet, style);
		int rowIndex = 2;

		// ������������
		HSSFRow row = null;

		// �����������ݣ�����������
		Iterator<String[]> it = data.iterator();
		while (it.hasNext()) {
			row = sheet.createRow(rowIndex);
			String[] t = it.next();
			String s = t[t.length - 1];
			if (CheckIsNull.isNotEmpty(s) && !"null".equals(s) && Integer.parseInt(s) > 1) {
				int x = Integer.parseInt(s);
				addRegion(sheet, style2, rowIndex, 0, rowIndex + x - 1, 0);
				addRegion(sheet, style2, rowIndex, 4, rowIndex + x - 1, 4);
				addRegion(sheet, style2, rowIndex, 5, rowIndex + x - 1, 5);
				addRegion(sheet, style2, rowIndex, 6, rowIndex + x - 1, 6);
				addRegion(sheet, style2, rowIndex, 7, rowIndex + x - 1, 7);
				addRegion(sheet, style2, rowIndex, 8, rowIndex + x - 1, 8);
			}
			for (short i = 0; i < t.length-1; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				try {
					// �������ͼƬ���ݣ�������������ʽ�ж�textValue�Ƿ�ȫ�����������
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
			rowIndex++;
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			out.close();
		}
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

	private HSSFCellStyle getBlueStyle(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
		// ������Щ��ʽ
		style.setFillForegroundColor(HSSFColor.SKY_BLUE.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// ����һ������
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// ������Ӧ�õ���ǰ����ʽ
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
		// ������һ������
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// ������Ӧ�õ���ǰ����ʽ
		style2.setFont(font2);
		return style2;
	}
}
