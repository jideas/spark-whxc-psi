package com.spark.psi.base.browser.start;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.MouseClickListener;
import com.jiuqi.dna.ui.wt.events.MouseEvent;
import com.jiuqi.dna.ui.wt.graphics.Color;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.FileChooser;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.jiuqi.dna.ui.wt.widgets.List;
import com.jiuqi.dna.ui.wt.widgets.ListItem;
import com.jiuqi.dna.ui.wt.widgets.Display.ExporterWithContext;
import com.spark.psi.publish.base.start.task.BatchSaveForExcelTask;
import com.spark.psi.publish.base.start.task.BatchSaveForExcelTask.ErrType;
import com.spark.psi.publish.base.start.task.BatchSaveForExcelTask.Model;

/**
 * 
 * <p>����������洦��������</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-7-13
 */
public abstract class BatchSaveProcessor extends WizardBaseStepProcessor{

	/**
	 * ���ID
	 */
	public static final String ID_Lable_Down = "ID_Lab_Down";
	public final static String ID_File_Upload = "ID_File_Upload";
	public final static String ID_Button_Save = "ID_Button_Save";
	public final static String ID_List_Err = "ID_List_Err";

	/**
	 * ���
	 */
	protected Label downLable;
	protected FileChooser upload;
	protected Button saveButton;
	protected List errList;

	/**
	 * ҳ���ʼ��
	 */
	@Override
	public void process(final Situation context){
		super.process(context);
		//����ģ������
		createDownLoad();
		//�������水ť
		createSaveButton();
		//
		errList = this.createControl(ID_List_Err, List.class);
		upload = this.createControl(ID_File_Upload, FileChooser.class);
		this.createControl(ID_File_Upload, FileChooser.class).setRelativeTarget(saveButton, ActionListener.class);

	}

	/**
	 * ����ģ�����ذ�ť
	 */
	private void createDownLoad(){
		downLable = this.createControl(ID_Lable_Down, Label.class);
		downLable.addMouseClickListener(new MouseClickListener(){
			
			public void click(MouseEvent e){
				downLable.getDisplay().exportFile(getPageTitle()+".xls", "application/vnd.ms-excel", 1000000, new ExporterWithContext(){
					
					public void run(Context context, OutputStream outputStream)
					throws IOException
					{
						final InputStream in = getClass().getResourceAsStream(getTemplateFilePath());
						if(in==null){
							throw new NullPointerException("û���ҵ�ģ���ļ���"+getTemplateFilePath());
						}
						byte[] buffer = new byte[1024];
						int bytesRead = 0;
						do {
							bytesRead = in.read(buffer);
							if (bytesRead > 0) {
								outputStream.write(buffer, 0, bytesRead);
							}
						} while (bytesRead > 0);
						try{
							in.close();
						}
						catch(IOException e1){}				
					}
				});
			}
		});
//		downLable.setCustomObject("tempPath", json);
//		downLable.addClientEventHandler(JWT.CLIENT_EVENT_CLICK, "PSIWizard.downTemplate");
	}

	/**
	 * ����
	 */
	private void createSaveButton(){
		saveButton = this.createControl(ID_Button_Save, Button.class);
		saveButton.addActionListener(new ActionListener(){

			public void actionPerformed(ActionEvent e){
				try{
					BatchSaveForExcelTask task = new BatchSaveForExcelTask(upload.getInputStream(upload.getFileName()));
					getContext().handle(task, getModel());
					showMsg(task);
				}
				catch(IOException e1){
					e1.printStackTrace();
				}
			}
		});
	}

	/**
	 * ��ʾ������Ϣ
	 */
	protected void showMsg(BatchSaveForExcelTask task){
		errList.clear();
		//
		for(Integer index : task.getExcutesNumber()){
			if(task.getErr(index) == null){
				ListItem item = new ListItem(errList);
				item.setText("��" + index + "�� ����ɹ���");
				item.setForeground(Color.COLOR_BLUE);
			}
			else{
				ListItem item = new ListItem(errList);
				item.setText("��" + index + "�� ����ʧ��!");
				item.setForeground(Color.COLOR_RED);
				Map<String, ErrType> err = task.getErr(index);
				for(Map.Entry<String, ErrType> entry : err.entrySet()){
					item = new ListItem(errList);
					item.setText("    " + entry.getKey() + "�ֶΣ�" + entry.getValue().getTitle());
					item.setForeground(Color.COLOR_RED);
				}
			}
		}
	}

	/**
	 * ���ģ��ö��
	 * 
	 * @return Model
	 */
	protected abstract Model getModel();

	/**
	 * ҳ������
	 * 
	 * @return String
	 */
	protected abstract String getPageTitle();

	/**
	 * ģ�����·��
	 * 
	 * @return String
	 */
	protected abstract String getTemplateFilePath();
}
