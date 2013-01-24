package com.spark.psi.base.browser;

import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.portal.browser.ConfirmPageProcessor;
import com.spark.portal.browser.ConfirmPageRender;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.portal.browser.WindowStyle;
import com.spark.psi.base.browser.customer.CustomerSelectProcessor;
import com.spark.psi.base.browser.customer.CustomerSelectRender;
import com.spark.psi.base.browser.goods.GoodsSelectProcessor;
import com.spark.psi.base.browser.goods.GoodsSelectRender;
import com.spark.psi.base.browser.material.MaterialsSelectParameters;
import com.spark.psi.base.browser.material.MaterialsSelectProcessor;
import com.spark.psi.base.browser.material.MaterialsSelectRender;
import com.spark.psi.base.browser.supplier.SupplierSelectProcessor;
import com.spark.psi.base.browser.supplier.SupplierSelectRender;

/**
 * ͨ�õ�����
 */
public class CommonSelectRequest {

	/**
	 * 
	 * @return
	 */
	public final static MsgRequest createSelectCustomerRequest(boolean contactSelect, boolean delieverySelect, boolean enableCreate) {
		return createSelectCustomerRequest(contactSelect, delieverySelect, enableCreate, null);
	}

	/**
	 * 
	 * @return
	 */
	public final static MsgRequest createSelectCustomerRequest(boolean contactSelect, boolean delieverySelect, boolean enableCreate,
			GUID customerId) {
//		PageController pc = new PageController(CustomerSelectProcessor.class, CustomerSelectRender.class);
//		PageControllerInstance pci = new PageControllerInstance(pc, contactSelect, delieverySelect, enableCreate, customerId);
//		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
//		windowStyle.setSize(720, 480);
//		MsgRequest request = new MsgRequest(pci, "ѡ��ͻ�", windowStyle);
		return createSelectCustomerRequest(customerId);
	}
	
	public final static MsgRequest createSelectCustomerRequest(GUID customerId) {
		PageController pc = new PageController(CustomerSelectProcessor.class, CustomerSelectRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, customerId);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(720, 480);
		MsgRequest request = new MsgRequest(pci, "ѡ��ͻ�", windowStyle);
		return request;
	}

	/**
	 * 
	 * @return
	 */
//	public final static MsgRequest createSelectSupplierRequest(boolean contactSelect, boolean delieverySelect, boolean enableCreate) {
//		return createSelectSupplierRequest(contactSelect, delieverySelect, enableCreate, null, false);
//	}

	/**
	 * 
	 * @return
	 */
	public final static MsgRequest createSelectSupplierRequest(GUID supplierId) {
		return createSelectSupplierRequest(supplierId, false);
	}
	
	public final static MsgRequest createSelectSupplierRequest() {
		return createSelectSupplierRequest(null, false);
	}
	
	public final static MsgRequest createSelectSupplierRequest(GUID supplierId, boolean isJoint) {
		PageController pc = new PageController(SupplierSelectProcessor.class, SupplierSelectRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, supplierId, isJoint);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(720, 480);
		MsgRequest request = new MsgRequest(pci, "ѡ��Ӧ��", windowStyle);
		return request;
	}

	/**
	 * 
	 * @return
	 */
	public final static MsgRequest createSelectGoodsRequest(boolean onsaleOnly) {
		return createSelectGoodsRequest(null, onsaleOnly, true);
	}

	/**
	 * 
	 * @return
	 */
	public final static MsgRequest createSelectGoodsRequest(boolean onsaleOnly, boolean onShowAddGoods) {
		return createSelectGoodsRequest(null, onsaleOnly, onShowAddGoods);
	}

	/**
	 * 
	 * @param storeId
	 * @param onsaleOnly
	 * @return
	 */
	public final static MsgRequest createSelectGoodsRequest(GUID storeId) {
		return createSelectGoodsRequest(storeId, false, false);
	}

	/**
	 * 
	 * @param storeId
	 * @param onsaleOnly
	 * @return
	 */
	public final static MsgRequest createSelectMaterialsRequest(GUID storeId, Boolean jointVenture) {
		return createSelectMaterialsRequest(new MaterialsSelectParameters(storeId, false, false, false, jointVenture));
	}

	/**
	 * 
	 * @return
	 */
	// public final static MsgRequest createSelectGoodsRequest(boolean
	// onsaleOnly, boolean enableAdd) {
	// return createSelectGoodsRequest(null, onsaleOnly, enableAdd);
	// }

	/**
	 * 
	 * @param storeId
	 *            ָ���ֿ�ID
	 * @param onsaleOnly
	 *            �Ƿ�ֻ�鿴������Ʒ
	 * @param enableAdd
	 *            �Ƿ���Կ���������Ʒ
	 * @return
	 */
	private final static MsgRequest createSelectGoodsRequest(GUID storeId, boolean onsaleOnly, boolean enableAdd) {
		return createSelectGoodsRequest(storeId, onsaleOnly, enableAdd, false);
	}

	/**
	 * 
	 * @param storeId
	 *            ָ���ֿ�ID��ȫ����Ϊnull
	 * @param onsaleOnly
	 *            �Ƿ�ֻ�鿴������Ʒ
	 * @param enableAdd
	 *            �Ƿ���Կ���������Ʒ
	 * @param isSingleLimit
	 *            �Ƿ�ֻ��ѡ��һ����Ʒ��Ŀ
	 * @return
	 */
	public final static MsgRequest createSelectGoodsRequest(GUID storeId, boolean onsaleOnly, boolean enableAdd, boolean isSingleLimit) {
		PageController pc = new PageController(GoodsSelectProcessor.class, GoodsSelectRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, storeId, onsaleOnly, enableAdd, isSingleLimit);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(960, 540);
		MsgRequest request = new MsgRequest(pci, "ѡ����Ʒ", windowStyle);
		return request;
	}

	/**
	 * 
	 * @param storeId
	 *            ָ���ֿ�ID��ȫ����Ϊnull
	 * @param onsaleOnly
	 *            �Ƿ�ֻ�鿴������Ʒ
	 * @param enableAdd
	 *            �Ƿ���Կ���������Ʒ
	 * @param isSingleLimit
	 *            �Ƿ�ֻ��ѡ��һ����Ʒ��Ŀ
	 * @return
	 */
	public final static MsgRequest createSelectMaterialsRequest(MaterialsSelectParameters parameters) {
		PageController pc = new PageController(MaterialsSelectProcessor.class, MaterialsSelectRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, parameters);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(960, 540);
		MsgRequest request = new MsgRequest(pci, "ѡ����Ʒ", windowStyle);
		return request;
	}

	/**
	 * @param storeId
	 *            ָ���ֿ�ID��ȫ����Ϊnull
	 * @param onsaleOnly
	 *            �Ƿ�ֻ�鿴������Ʒ
	 * @param isSingleLimit
	 *            �Ƿ�ֻ��ѡ��һ����Ʒ��Ŀ
	 * @return
	 */
	public final static MsgRequest createSelectMaterialRequest(MaterialsSelectParameters parameters) {
		PageController pc = new PageController(MaterialsSelectProcessor.class, MaterialsSelectRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, parameters);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(960, 540);
		MsgRequest request = new MsgRequest(pci, "ѡ�����", windowStyle);
		return request;
	}

	/**
	 * ����һ��ȷ�ϲ����������󣬵���һ��ȷ�϶Ի���<br>
	 * �ѷϳ�����ֱ��ʹ��PageProcess�еĽӿڷ���
	 * 
	 * @param confirmMessage
	 * @param responseHandler
	 * @return
	 */
	@Deprecated
	public final static MsgRequest createConfirmRequest(String confirmMessage, final Runnable runnable) {
		PageController pc = new PageController(ConfirmPageProcessor.class, ConfirmPageRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, confirmMessage);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(400, 220);
		MsgRequest request = new MsgRequest(pci, "��ʾ��Ϣ", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				runnable.run();
			}
		});
		return request;
	}
	
	/**
	 * �˻�ͨ�ô���
	 * @param isNullAble
	 * @return
	 */
	public final static MsgRequest createCommonDenyRequest(boolean isNullAble) {
		PageController pc = new PageController(CommonDenyResonProcessor.class, CommonDenyResonRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, isNullAble);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(400, 300);
		MsgRequest request = new MsgRequest(pci, "�˻�ԭ��", windowStyle);
		return request;
	}
	
	public final static MsgRequest createPopSingleTextRequest(boolean isNullAble, String windowTitle, String labelTitle) {
		PageController pc = new PageController(CommonDenyResonProcessor.class, CommonDenyResonRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, isNullAble, labelTitle);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(400, 300);
		MsgRequest request = new MsgRequest(pci, windowTitle, windowStyle);
		return request;
	}

}
