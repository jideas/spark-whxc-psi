package com.spark.common.utils.excel;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelReadHelper {

	protected String[] head;
	protected List<String[]> data;

	public void read(InputStream input, boolean hasHead) throws Exception {
		HSSFWorkbook book = null;
		try {
			book = new HSSFWorkbook(input);
		} catch (IOException e) {
			throw new Exception("��ȡ�ļ�������ȷ�ϴ��������ȷ��excel��ʽ!");
		}
		HSSFSheet sheet = book.getSheetAt(0); // ��ȡ��һ��������
		int index = 0;
		if (hasHead) {
			readHead(sheet); // ��ȡ��ͷ
			index++;
		}
		readData(sheet, index); // ��ȡ����
	}

	private void readData(HSSFSheet sheet, int rowIndex) {
		data = new ArrayList<String[]>();
		for (; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
			HSSFRow row = sheet.getRow(rowIndex);
			String[] str = new String[head.length];
			for (int cellIndex = 0; cellIndex < head.length; cellIndex++) {
				HSSFCell cell = row.getCell(cellIndex);
				if (cell == null) {
					str[cellIndex] = "";
				} else {
					str[cellIndex] = cell.toString();
				}
			}
			data.add(str);
		}
	}

	private void readHead(HSSFSheet sheet) throws Exception {
		HSSFRow headRow = sheet.getRow(0); // ��ȡ��һ�� Ĭ����Ϊ��һ��Ϊ��ͷ
		head = new String[headRow.getLastCellNum()];
		for (int i = 0; i < head.length; i++) {
			HSSFCell cell = headRow.getCell(i);
			if (cell == null)
				throw new Exception("excel��ʽ���󣬱�ͷ�ֶβ���Ϊ�գ�");
			head[i] = cell.toString();
		}
	}

	/**
	 * ���ض�������������
	 * 
	 * @return int
	 */
	public int getRowCount() {
		return data.size();
	}

	/**
	 * ���ָ����
	 * 
	 * @param index
	 * @return String[]
	 */
	public String[] getRow(int index) {
		if (index < 0 || index > data.size() - 1)
			throw new IllegalArgumentException("index:" + index + " Խ��");
		return data.get(index);
	}

	/**
	 * ��ñ�ͷ����
	 * 
	 * @return String[]
	 */
	public String[] getHead() {
		return head;
	}

	public List<String[]> getData() {
		return data;
	}
}
