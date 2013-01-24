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
 * 通用的请求
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
//		MsgRequest request = new MsgRequest(pci, "选择客户", windowStyle);
		return createSelectCustomerRequest(customerId);
	}
	
	public final static MsgRequest createSelectCustomerRequest(GUID customerId) {
		PageController pc = new PageController(CustomerSelectProcessor.class, CustomerSelectRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, customerId);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(720, 480);
		MsgRequest request = new MsgRequest(pci, "选择客户", windowStyle);
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
		MsgRequest request = new MsgRequest(pci, "选择供应商", windowStyle);
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
	 *            指定仓库ID
	 * @param onsaleOnly
	 *            是否只查看在售商品
	 * @param enableAdd
	 *            是否可以快速新增商品
	 * @return
	 */
	private final static MsgRequest createSelectGoodsRequest(GUID storeId, boolean onsaleOnly, boolean enableAdd) {
		return createSelectGoodsRequest(storeId, onsaleOnly, enableAdd, false);
	}

	/**
	 * 
	 * @param storeId
	 *            指定仓库ID，全部则为null
	 * @param onsaleOnly
	 *            是否只查看在售商品
	 * @param enableAdd
	 *            是否可以快速新增商品
	 * @param isSingleLimit
	 *            是否只能选择一个商品条目
	 * @return
	 */
	public final static MsgRequest createSelectGoodsRequest(GUID storeId, boolean onsaleOnly, boolean enableAdd, boolean isSingleLimit) {
		PageController pc = new PageController(GoodsSelectProcessor.class, GoodsSelectRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, storeId, onsaleOnly, enableAdd, isSingleLimit);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(960, 540);
		MsgRequest request = new MsgRequest(pci, "选择商品", windowStyle);
		return request;
	}

	/**
	 * 
	 * @param storeId
	 *            指定仓库ID，全部则为null
	 * @param onsaleOnly
	 *            是否只查看在售商品
	 * @param enableAdd
	 *            是否可以快速新增商品
	 * @param isSingleLimit
	 *            是否只能选择一个商品条目
	 * @return
	 */
	public final static MsgRequest createSelectMaterialsRequest(MaterialsSelectParameters parameters) {
		PageController pc = new PageController(MaterialsSelectProcessor.class, MaterialsSelectRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, parameters);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(960, 540);
		MsgRequest request = new MsgRequest(pci, "选择商品", windowStyle);
		return request;
	}

	/**
	 * @param storeId
	 *            指定仓库ID，全部则为null
	 * @param onsaleOnly
	 *            是否只查看在售商品
	 * @param isSingleLimit
	 *            是否只能选择一个商品条目
	 * @return
	 */
	public final static MsgRequest createSelectMaterialRequest(MaterialsSelectParameters parameters) {
		PageController pc = new PageController(MaterialsSelectProcessor.class, MaterialsSelectRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, parameters);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(960, 540);
		MsgRequest request = new MsgRequest(pci, "选择材料", windowStyle);
		return request;
	}

	/**
	 * 创建一个确认操作界面请求，弹出一个确认对话框<br>
	 * 已废除，请直接使用PageProcess中的接口方法
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
		MsgRequest request = new MsgRequest(pci, "提示信息", windowStyle);
		request.setResponseHandler(new ResponseHandler() {
			public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
				runnable.run();
			}
		});
		return request;
	}
	
	/**
	 * 退回通用窗口
	 * @param isNullAble
	 * @return
	 */
	public final static MsgRequest createCommonDenyRequest(boolean isNullAble) {
		PageController pc = new PageController(CommonDenyResonProcessor.class, CommonDenyResonRender.class);
		PageControllerInstance pci = new PageControllerInstance(pc, isNullAble);
		WindowStyle windowStyle = new WindowStyle(JWT.CLOSE | JWT.MODAL);
		windowStyle.setSize(400, 300);
		MsgRequest request = new MsgRequest(pci, "退回原因", windowStyle);
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
