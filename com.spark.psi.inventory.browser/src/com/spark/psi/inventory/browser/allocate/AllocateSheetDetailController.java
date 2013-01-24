package com.spark.psi.inventory.browser.allocate;

import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.edit.SEditTable;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler2;
import com.spark.psi.publish.Auth;
import com.spark.psi.publish.InventoryAllocateStatus;
import com.spark.psi.publish.LoginInfo;
import com.spark.psi.publish.inventory.entity.InventoryAllocateSheetInfo;

/**
 * <p>�����굥������(���ฺ�����еĵ����굥��ʾ)</p>
 *
 * <p>Copyright: ��Ȩ���� (c) 20012 - 20018<br>

 *
 * @author ����
 * @version 2012-6-5
 */

public class AllocateSheetDetailController{

	/**������*/
	private Situation context;

	/**���ε�ID*/
	private GUID id;

	/**���ε���ϸ��Ϣ*/
	private InventoryAllocateSheetInfo info;

	/**
	 * ���ؼ�����
	 */
	protected SEditTable table;

	/**���ε�״̬����*/
	private final int ADD = 1; //����

	private final int SUBMIT = 2; //���ύ

	private final int APPROVAL = 3; //������

	private final int PROCCESSING = 4; //������

	private final int PROCCESSED = 5; //�����

	/** 
	 *���췽��
	 *@param context
	 *@param info
	 */
	public AllocateSheetDetailController(Situation context, GUID id, SEditTable table){
		super();
		this.context = context;
		this.id = id;
		this.table = table;
	}

	/**
	 * ��ʾ���ε���Ϣ
	 */
	public void showAllocateSheetDetail(){
		if(id != null){
			info = context.find(InventoryAllocateSheetInfo.class, id);
		}
		//
		MsgRequest request = new MsgRequest(getPageControllerForDetail(), getPageDetailTitle());
		request.setResponseHandler(new ResponseHandler2(){
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
				if(table != null){
					table.render();
				}
			}

			public void afterHandle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4){
				if(returnValue != null){
					//��ʾ�б�ҳ��
					//					PageControllerInstance pci = getPageControllerForList();
					//					MsgRequest request = new MsgRequest(pci);
					//					context.bubbleMessage(request);
					//
					//					AllocateSheetDetailController.this.context = pci.getInstancePage().getContext();
					AllocateSheetDetailController.this.id = (GUID)returnValue;
					showAllocateSheetDetail();
				}

			}
		});
		context.bubbleMessage(request);

	}

	/**
	 * ����б�ҳǩ������
	 */
	private PageControllerInstance getPageControllerForList(){
		switch(getStatus()){
			case ADD:
			case SUBMIT:
				return new PageControllerInstance("SubmitingAllocateSheetListPage");
			case APPROVAL:
				return new PageControllerInstance("ApprovalingAllocateSheetListPage");
			case PROCCESSING:
				return new PageControllerInstance("ProcessingAllocateSheetListPage");
			default:
				return new PageControllerInstance("ProcessedAllocateSheetListPage");
		}
	}

	/**
	 * ��õ��ε���ϸ������
	 */
	private PageControllerInstance getPageControllerForDetail(){
		switch(getStatus()){
			case ADD:
				return new PageControllerInstance("EditAllocateSheetPage");
			case SUBMIT:
				return new PageControllerInstance("EditAllocateSheetPage", info.getSheetId().toString());
			case APPROVAL:
				return new PageControllerInstance("ApprovalingSheetDetailPage", info.getSheetId().toString());
			case PROCCESSING:
				return new PageControllerInstance("ProcessingSheetDetailPage", info.getSheetId().toString());
			default:
				return new PageControllerInstance("AllocateSheetDetailBasePage", info.getSheetId().toString());
		}
	}

	/**
	 * ��õ��ε���ϸ�ı���
	 */
	private String getPageDetailTitle(){
		switch(getStatus()){
			case ADD:
				return "�������ε�";
			case SUBMIT:
				return "�༭���ε�";
			case APPROVAL:
				return "����������";
			case PROCCESSING:
				return "����������";
			default:
				return "����������";
		}
	}

	/**
	 * ��õ��ε�״ֵ̬
	 */
	private int getStatus(){
		LoginInfo login = context.find(LoginInfo.class);
		if(info == null){ //����
			return ADD;
		}
		else if(InventoryAllocateStatus.Submitting.equals(info.getStatus())
		        || InventoryAllocateStatus.Rejected.equals(info.getStatus()))
		{ //���ύ�����˻�
			return SUBMIT;
		}
		else if(InventoryAllocateStatus.Submitted.equals(info.getStatus())
		        && login.hasAuth(Auth.Tag_InventoryAllocate_Approval))
		{ //������
			return APPROVAL;
		}
		else if(InventoryAllocateStatus.AllocateIn.equals(info.getStatus())){
			//�����
			return PROCCESSED;
		}
		else{
			//������
			return PROCCESSING;
		}
	}
}
