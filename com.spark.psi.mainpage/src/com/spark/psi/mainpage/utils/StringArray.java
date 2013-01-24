package com.spark.psi.mainpage.utils;

public class StringArray{

	private String text;
	private String[] array;

	public StringArray(String text, String... array){
		this.text = text;
		this.array = array;
	}

	/**
     * @return the text
     */
    public String getText(){
    	return text;
    }

	/**
     * @return the array
     */
    public String[] getArray(){
    	return array;
    }
}
