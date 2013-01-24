package com.spark.psi.base.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.management.RuntimeErrorException;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.spark.psi.publish.base.start.FileFormatErrException;

/**
 * 
 * <p>excel������</p>
 *


 *
 
 * @version 2012-7-9
 */
public class ExcelReader{
	
	/**
	 * ��ͷ
	 */
	private String[] head;
	
	/**
	 * ����
	 */
	private String[][] data;
	
	
	public ExcelReader(InputStream in){
		read(in);
	}
	
	/**
	 * ��ȡһ��excel
	 * 
	 * @param in void
	 * @throws IOException 
	 */
	private void read(InputStream in){
		HSSFWorkbook book = null;
		try{
	       book = new HSSFWorkbook(in);
        }
        catch(IOException e){
	        throw new FileFormatErrException("��ȡ�ļ�������ȷ�ϴ��������ȷ��excel��ʽ!");
        }
        HSSFSheet sheet = book.getSheetAt(0);  //��ȡ��һ��������
        readHead(sheet); //��ȡ��ͷ
		readData(sheet); //��ȡ����
	}
	
	private void readData(HSSFSheet sheet){
	    if(head==null) throw new RuntimeException("δ��ʼ����ͷ");
	    if(sheet.getLastRowNum()==1){
	    	data = new String[0][0];
	    	return ;
	    }
	    data = new String[sheet.getLastRowNum()][head.length];
	    for(int rowIndex = 0; rowIndex < data.length; rowIndex++){
	        HSSFRow row = sheet.getRow(rowIndex+1);
	        for(int cellIndex = 0; cellIndex < head.length; cellIndex++){
	            HSSFCell cell = row.getCell(cellIndex);
	            try 
	            {
	            	   if(cell==null)
	            	   {
	            		   data[rowIndex][cellIndex] = "";
	            	   }
	            	   else
	            	   {
	            		   data[rowIndex][cellIndex] = cell.toString();
	            	   }
				}
	            catch (Exception e)
	            {
					e.printStackTrace();
				}
            }
        }
    }

	private void readHead(HSSFSheet sheet){
		HSSFRow headRow = sheet.getRow(0); //��ȡ��һ�� Ĭ����Ϊ��һ��Ϊ��ͷ
		head = new String[headRow.getLastCellNum()];
		for(int i = 0; i < head.length; i++){
			HSSFCell cell = headRow.getCell(i);
			if(cell==null)throw new FileFormatErrException("excel��ʽ���󣬱�ͷ�ֶβ���Ϊ�գ�");
	        head[i] = cell.toString();
        }
    }

	/**
	 * ���ض�������������
	 * 
	 * @return int
	 */
	public int getRowCount(){
		return data.length;
	}
	
	/**
	 * ���ָ����
	 * 
	 * @param index
	 * @return String[]
	 */
	public String[] getRow(int index){
		if(index<0||index>data.length-1)throw new IllegalArgumentException("index:"+index+" Խ��");
		return data[index];
	}
	
	/**
	 * ��ñ�ͷ����
	 * 
	 * @return String[]
	 */
	public String[] getHead(){
		return head;
	}
	
	public static void main(String[] args){
	    try{
	        FileInputStream in = new FileInputStream("f://pos_demo.xls");
	        ExcelReader reader = new ExcelReader(in);
	        for(int i = 0;i<reader.getRowCount();i++){
	            String[] row = reader.getRow(i);
	            System.out.println();
	            for(String string : row){
	                System.out.print(string+"\t");
                }
            }
        }
        catch(FileNotFoundException e){
	        // TODO Auto-generated catch block
	        e.printStackTrace();
        }
    }
	

}