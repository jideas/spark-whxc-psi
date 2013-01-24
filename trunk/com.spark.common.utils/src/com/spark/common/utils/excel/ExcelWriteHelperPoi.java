package com.spark.common.utils.excel;

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
import org.apache.poi.hssf.util.HSSFColor;

public abstract class ExcelWriteHelperPoi {

	@SuppressWarnings("unchecked")
	public ExcelWriteHelperPoi(List datalist) {
		this.head = getHead();
		this.data = new ArrayList<String[]>();
		int columnCount = getColumnCount();
		for (Object element : datalist) {
			String[] str = new String[columnCount];
			for (int i = 0; i < columnCount; i++) {
				str[i] = this.getText(element, i);
			}
			data.add(str);
		}
	}

	/**
	 * ��ͷ
	 */
	private String[] head;

	protected abstract String[] getHead();

	protected abstract String getText(Object element, int columnIndex);
	
	protected int getColumnCount() {
		if (null != head) return head.length;
		return 0;
	}

	/**
	 * ����
	 */
	private List<String[]> data;

	public boolean writeNormal(OutputStream outputStream, String title) {
		exportExcel(title, outputStream);
		return true;
	}

	@SuppressWarnings("deprecation")
	private void exportExcel(String title, OutputStream out) {
		// ����һ��������
		HSSFWorkbook workbook = new HSSFWorkbook();
		// ����һ�����
		HSSFSheet sheet = workbook.createSheet(title);
		// ���ñ��Ĭ���п��Ϊ15���ֽ�
		sheet.setDefaultColumnWidth((short) 15);
		// ����һ����ʽ
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
		// ���ɲ�������һ����ʽ
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

		// ����һ����ͼ�Ķ���������
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// ����ע�͵Ĵ�С��λ��,����ĵ�
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// ����ע������
		comment.setString(new HSSFRichTextString("������POI�����ע�ͣ�"));
		// ����ע�����ߣ�������ƶ�����Ԫ�����ǿ�����״̬���п���������.
		comment.setAuthor("leno");

		int index = 0;
		HSSFRow row = null;
		// ������������
		if (null != head) {
			row = sheet.createRow(index);
			index++;
			for (short i = 0; i < head.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style);
				HSSFRichTextString text = new HSSFRichTextString(head[i]);
				cell.setCellValue(text);
			}
		}

		// �����������ݣ�����������
		Iterator<String[]> it = data.iterator();
		
		while (it.hasNext()) {
			//
			row = sheet.createRow(index);
			index++;
			String[] t = it.next();
			for (short i = 0; i < t.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				try {
					// �������ͼƬ���ݣ�������������ʽ�ж�textValue�Ƿ�ȫ�����������
					if (t[i] != null) {
						HSSFRichTextString richString = new HSSFRichTextString(t[i]);
//						HSSFFont font3 = workbook.createFont();
//						font3.setColor(HSSFColor.BLUE.index);
//						richString.applyFont(font3);
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
		try {
			workbook.write(out); 
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
