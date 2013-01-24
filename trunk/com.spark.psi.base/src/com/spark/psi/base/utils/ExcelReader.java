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
 * <p>excel解析类</p>
 *


 *
 
 * @version 2012-7-9
 */
public class ExcelReader{
	
	/**
	 * 表头
	 */
	private String[] head;
	
	/**
	 * 数据
	 */
	private String[][] data;
	
	
	public ExcelReader(InputStream in){
		read(in);
	}
	
	/**
	 * 读取一个excel
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
	        throw new FileFormatErrException("读取文件错误，请确认传入的是正确的excel格式!");
        }
        HSSFSheet sheet = book.getSheetAt(0);  //读取第一个工作薄
        readHead(sheet); //读取表头
		readData(sheet); //读取数据
	}
	
	private void readData(HSSFSheet sheet){
	    if(head==null) throw new RuntimeException("未初始化表头");
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
		HSSFRow headRow = sheet.getRow(0); //读取第一行 默认认为第一个为表头
		head = new String[headRow.getLastCellNum()];
		for(int i = 0; i < head.length; i++){
			HSSFCell cell = headRow.getCell(i);
			if(cell==null)throw new FileFormatErrException("excel格式错误，表头字段不能为空！");
	        head[i] = cell.toString();
        }
    }

	/**
	 * 返回读出来的总行数
	 * 
	 * @return int
	 */
	public int getRowCount(){
		return data.length;
	}
	
	/**
	 * 获得指定行
	 * 
	 * @param index
	 * @return String[]
	 */
	public String[] getRow(int index){
		if(index<0||index>data.length-1)throw new IllegalArgumentException("index:"+index+" 越界");
		return data[index];
	}
	
	/**
	 * 获得表头名称
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