package com.spark.psi.inventory.browser.count;

import java.io.OutputStream;

import jxl.Workbook;
import jxl.format.Alignment;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.Number;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

import com.spark.psi.inventory.browser.count.KitSheetDetailInfo.Kit;

public class KitCountSheetDetailExport {
	
	private static WritableCellFormat titlewf = null;//title�� ��ʽ
	private static WritableCellFormat contextwf = null;//���ݵ���ʽ

	/**
	 * ����label
	 * @param c ��
	 * @param row ��
	 * @param name ��ʾ�ֶ�
	 * @param wf �Լ���ʽ
	 * @param sheet ��
	 * @throws Exception
	 */
	private static void setLabel(int c, int row,String name,WritableCellFormat wf,WritableSheet sheet) throws Exception{
		Label label = new Label(c,row, name,wf);
		sheet.addCell(label);
	}
	
	private static void setOtherTableTitle(WritableSheet sheet,WritableCellFormat cf2) throws Exception {
		setLabel(0,4,"��Ʒ����",cf2,sheet);
		setLabel(1,4,"��Ʒ����",cf2,sheet);
		setLabel(2,4,"��λ",cf2,sheet);
		setLabel(3,4,"��������",cf2,sheet);
		setLabel(4,4,"ʵ������",cf2,sheet);
		setLabel(5,4,"˵��",cf2,sheet);
	}
	
	/**
	 * ����������ʽ
	 * @throws Exception
	 */
	private static void initFont() throws Exception{
    	WritableFont wfont = new WritableFont(WritableFont.createFont("����"), 20); 
		wfont.setBoldStyle(WritableFont.BOLD);
		titlewf = new WritableCellFormat(wfont); 
		titlewf.setAlignment(Alignment.CENTRE);
		titlewf.setBorder(Border.BOTTOM, BorderLineStyle.THIN); 
		titlewf.setBorder(Border.RIGHT, BorderLineStyle.NONE); 
		
		WritableFont wfontcontext = new WritableFont(WritableFont.createFont("����"), 10); 
		contextwf = new WritableCellFormat(wfontcontext);
		contextwf.setAlignment(Alignment.LEFT);
		contextwf.setBorder(Border.ALL, BorderLineStyle.THIN); 
		contextwf.setWrap(true);
	}
	
	/**
	 * ����sheet����ʽ
	 * @param sheet
	 * @throws Exception
	 */
	private static void initSheet(WritableSheet sheet,int number,int cellnumber) throws Exception{
		sheet.mergeCells(0, 0, cellnumber-1, 0);
		sheet.mergeCells(1, 1, cellnumber-1, 0);
		sheet.mergeCells(1, 2, cellnumber-1, 0);
		sheet.mergeCells(1, 3, cellnumber-1, 0);
		for(int i = 0 ; i < cellnumber ; i ++) {
			if(cellnumber - 5 == i) {
				sheet.setColumnView(i, 20);
			}else if(cellnumber-1 == i) {
				sheet.setColumnView(i, 20);
			} else {
				sheet.setColumnView(i, 10);
			}
		}
		
	}
	
	public static void export(OutputStream outputStream,KitSheetDetailInfo kitInfo) {
		try{
			WritableFont wf1 = new WritableFont(WritableFont.createFont("����"), 10); 
			WritableCellFormat cf1 = new WritableCellFormat(wf1);
			cf1.setAlignment(Alignment.RIGHT);
			cf1.setBorder(Border.ALL, BorderLineStyle.THIN); 
			cf1.setWrap(true);
			
			WritableCellFormat cf2 = new WritableCellFormat(wf1);
			cf2.setAlignment(Alignment.CENTRE);
			cf2.setBorder(Border.ALL, BorderLineStyle.THIN); 
			cf2.setWrap(true);
			
			WritableCellFormat cf3 = new WritableCellFormat(wf1);
			cf3.setAlignment(Alignment.CENTRE);
			cf3.setBorder(Border.ALL, BorderLineStyle.THIN); 
			cf3.setBorder(Border.RIGHT, BorderLineStyle.NONE); 
			cf3.setWrap(true);
			
			WritableCellFormat cf4 = new WritableCellFormat(wf1);
			cf4.setAlignment(Alignment.CENTRE);
			cf4.setBorder(Border.LEFT, BorderLineStyle.THIN);  
			
			WritableWorkbook wwb = null;
			wwb = Workbook.createWorkbook(outputStream);
			WritableSheet sheet = wwb.createSheet("�̵㵥", 0);
			initFont();
			setLabel(0,0,"�̵㵥", titlewf, sheet);
			setLabel(0,1,"�̵�ֿ⣺",cf1,sheet);
			setLabel(0,2,"�̵��ˣ�",cf1,sheet);
			setLabel(0,3,"�̵����",cf1,sheet);
			setLabel(1,1,kitInfo.getStore(),cf2,sheet);
			setLabel(1,2,kitInfo.getCreator(),cf2,sheet);
			setLabel(1,3,"�������",cf2,sheet);
			initSheet(sheet,6,6);
			setOtherTableTitle(sheet, cf2);
			setLabel(1,3,"�������",cf2,sheet);
			addTableListData(kitInfo, cf1, cf2, sheet);
			
			wwb.write();
			wwb.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ӱ������
	 */
	private static void addTableListData(KitSheetDetailInfo kitInfo,WritableCellFormat cf1, WritableCellFormat cf2, WritableSheet sheet) throws Exception, WriteException, RowsExceededException {		
		Kit[] kits = kitInfo.getKits();
		if(kits==null || kits.length==0)
			return;
		for(int i = 5 ; i < kits.length+5;i++) {
			Kit kit = kits[(i-5)];
			setLabel(0,i,kit.getKitName(),contextwf,sheet);
			setLabel(1,i,kit.getKitDesc(),contextwf,sheet);
			setLabel(2,i,kit.getKitUnit(),cf2,sheet);
			Number number = new Number(3, i, kit.getCount(),cf1);
			sheet.addCell(number);
			if(kit.getActualCount() == -1) {
				setLabel(4,i,"",cf1,sheet);
			}else {
				Number number1 = new Number(4,i,kit.getActualCount(),cf1);
				sheet.addCell(number1);
			}
			setLabel(5,i,kit.getRemark(),contextwf,sheet);
		}
	}
}