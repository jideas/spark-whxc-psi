/**
 * 
 */
package com.spark.psi.order.browser.purchase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.situation.Situation;
import com.jiuqi.dna.core.type.GUID;
import com.jiuqi.dna.ui.common.constants.JWT;
import com.jiuqi.dna.ui.wt.events.ActionEvent;
import com.jiuqi.dna.ui.wt.events.ActionListener;
import com.jiuqi.dna.ui.wt.events.SelectionEvent;
import com.jiuqi.dna.ui.wt.events.SelectionListener;
import com.jiuqi.dna.ui.wt.widgets.Button;
import com.jiuqi.dna.ui.wt.widgets.Label;
import com.spark.common.components.pages.IDataProcessPrompt;
import com.spark.common.components.pages.PageController;
import com.spark.common.components.pages.PageControllerInstance;
import com.spark.common.components.table.STableStatus;
import com.spark.common.components.table.edit.SNameValue;
import com.spark.common.utils.character.DoubleUtil;
import com.spark.portal.browser.MsgRequest;
import com.spark.portal.browser.ResponseHandler;
import com.spark.psi.base.browser.CommonSelectRequest;
import com.spark.psi.base.browser.PSIListPageProcessor;
import com.spark.psi.order.browser.util.IPurchaseGoodsGroup;
import com.spark.psi.order.browser.util.OrderUtilFactory;
import com.spark.psi.order.browser.util.IPurchaseGoodsGroup.PurchaseGroupGoods;
import com.spark.psi.order.browser.util.IPurchaseGoodsGroup.PurchaseOrderGroup;
import com.spark.psi.publish.Action;
import com.spark.psi.publish.ListEntity;
import com.spark.psi.publish.base.materials.task.MarkMaterialsItemUsedTask;
import com.spark.psi.publish.base.partner.entity.SupplierInfo;
import com.spark.psi.publish.inventory.entity.InventoryInfo;
import com.spark.psi.publish.inventory.key.GetWarningGoodsItemListKey;
import com.spark.psi.publish.order.entity.PurchaseGoodsInfo;
import com.spark.psi.publish.order.entity.PurchaseGoodsItem;
import com.spark.psi.publish.order.key.GetPurchasingGoodsItemListKey;
import com.spark.psi.publish.order.task.CreatePurchaseGoodsTask;
import com.spark.psi.publish.order.task.DeletePurchaseGoodsTask;
import com.spark.psi.publish.order.task.UpdatePurchaseGoodsTask;

/**
 * �ɹ��嵥�б�����
 * 
 */
public class PurchasingGoodsListProcessor extends PSIListPageProcessor<PurchaseGoodsItem> implements IDataProcessPrompt {

	// �ɹ�����
	public final static String ID_LABEL_ORDER_COUNT = "Label_Order_Count";
	// ���Ԥ��
	public final static String ID_BUTTON_INVENTORY_PREWARNING = "Button_Inventory_Prewarning";
	// ȫ������
	public final static String ID_BUTTON_All_GOODS = "Button_All_Goods";
	// ָ����Ӧ��
	public final static String ID_BUTTON_SET_PROVIDER = "Button_Set_Provider";
	// ���ɲɹ�����
	public final static String ID_BUTTON_CREATE_ORADER = "Button_Create_Order";

	public static enum Columns {
		GoodsCode, GoodsNo, GoodsName, GoodsProperties(), GoodsUnit(), PurchasingCount(), StoreName(), Supplier(), PurchasePrice(), LastPurchasePrice();

	}

	private Label lblCount;
	private Button setSupplier, selectWarningGoods;

	@Override
	public void init(Situation situation) {
		super.init(situation);
	}

	@Override
	public void process(Situation situation) {

		super.process(situation);

		lblCount = this.createControl(ID_LABEL_ORDER_COUNT, Label.class, JWT.NONE);
 		//
		selectWarningGoods = this.createControl(ID_BUTTON_INVENTORY_PREWARNING, Button.class);
		selectWarningGoods.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageController pc = new PageController(SelectPurchaseWarningGoodsProcessor.class,
						SelectPurchaseWarningGoodsRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				MsgRequest request = new MsgRequest(pci, "ѡ��ɹ����ϣ���Ԥ������У�");
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						SelectPurchaseWarningGoodsProcessor.SelectItem[] items = (SelectPurchaseWarningGoodsProcessor.SelectItem[]) returnValue;
						if (items != null) {
							CreatePurchaseGoodsTask task = null;
							for (SelectPurchaseWarningGoodsProcessor.SelectItem si : items) {
								task = new CreatePurchaseGoodsTask();
								task.setGoodsItemId(si.getGoodsInventoryDetail().getGoodsItemId());
								task.setStoreId(si.getGoodsInventoryDetail().getStoreId());
								task.setPurchaseCount(si.getThisPurchaseCount());
								getContext().handle(task);
								// ����ʹ����Ϣ
								getContext().handle(
										new MarkMaterialsItemUsedTask(si.getGoodsInventoryDetail().getGoodsItemId()));
							}
						}
						table.render();
					}
				});
				getContext().bubbleMessage(request);
			}
		});
		this.createControl(ID_BUTTON_All_GOODS, Button.class).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PageController pc = new PageController(PurchasingGoodsSelectProcessor.class,
						PurchasingGoodsSelectRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc);
				MsgRequest request = new MsgRequest(pci, "ѡ��ɹ����ϣ���ȫ�������У�");
				request.setResponseHandler(new ResponseHandler() {

					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						PurchasingGoodsSelectProcessor.SelectedPurchaseItem[] items = (PurchasingGoodsSelectProcessor.SelectedPurchaseItem[]) returnValue;
						if (items != null) {
							CreatePurchaseGoodsTask task = null;
							for (PurchasingGoodsSelectProcessor.SelectedPurchaseItem si : items) {
								task = new CreatePurchaseGoodsTask();
								task.setGoodsItemId(si.getGoodsItemId());
								task.setStoreId(si.getStoreId());
								task.setPurchaseCount(si.getCount());
								getContext().handle(task);
								// ����ʹ����Ϣ
								getContext().handle(new MarkMaterialsItemUsedTask(si.getGoodsItemId()));
							}
						}
						table.render();
					}
				});
				getContext().bubbleMessage(request);
			}
		});

		setSupplier = this.createControl(ID_BUTTON_SET_PROVIDER, Button.class);
		setSupplier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// TODO�����ù�Ӧ��
				MsgRequest request = CommonSelectRequest.createSelectSupplierRequest();
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						SupplierInfo supp = getContext().find(SupplierInfo.class, returnValue);
						String[] ids = table.getSelections();
						if (null != ids) {
							UpdatePurchaseGoodsTask task = new UpdatePurchaseGoodsTask();
							List<UpdatePurchaseGoodsTask.PurchaseGoods> list = new ArrayList<UpdatePurchaseGoodsTask.PurchaseGoods>();
							UpdatePurchaseGoodsTask.PurchaseGoods pg = null;
							for (String id : ids) {
								pg = new UpdatePurchaseGoodsTask.PurchaseGoods();
								GUID purchaseGoodsId = GUID.valueOf(id);
								pg.setContactId((GUID) returnValue2);
								pg.setDirect(isDirect(id));
								pg.setId(purchaseGoodsId);
								pg.setSupplierId(GUID.valueOf(String.valueOf(returnValue)));
								list.add(pg); 
								table.updateCellAndToolTip(id, 7, supp.getShortName(), supp.getName());
							}
							task.setPurchaseGoods(list.toArray(new UpdatePurchaseGoodsTask.PurchaseGoods[list.size()]));
							getContext().handle(task);
							for (String id : ids) {
								PurchaseGoodsInfo info = getContext().find(PurchaseGoodsInfo.class, GUID.valueOf(id));
								table.updateCell(id, Columns.LastPurchasePrice.name(), "", DoubleUtil.getRoundStr(info
										.getRecentPrice()));
							}
							// table.render();
							table.renderUpate();
						}
					}
				});
				getContext().bubbleMessage(request);
			}
		});

		this.createControl(ID_BUTTON_CREATE_ORADER, Button.class).addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				IPurchaseGoodsGroup pgg = OrderUtilFactory.getPurchaseGoodsGroup(getContext(), table);
				if (0 == pgg.getSupplierGroupList().length) {
					alert("��������Ҫ�ɹ��Ĳ��ϵĲɹ����ۺ͹�Ӧ�̣�");
					return;
				}
				PageController pc = new PageController(GeneratePurchaseOrderProcessor.class,
						GeneratePurchaseOrderRender.class);
				PageControllerInstance pci = new PageControllerInstance(pc, pgg);
				MsgRequest request = new MsgRequest(pci, "���ɲɹ�����");
				request.setResponseHandler(new ResponseHandler() {
					public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
						PurchaseOrderGroup[] purchaseOrderGroups = (PurchaseOrderGroup[]) returnValue;
						PurchasingGoodsListProcessor.this.table.getAllRowId();
						for (PurchaseOrderGroup order : purchaseOrderGroups) {
							for (PurchaseGroupGoods goods : order.getGoodsItems()) {
								// getContext().handle(new
								// DeletePurchaseGoodsTask(goods.getPurchaseGoodsId()));
								PurchasingGoodsListProcessor.this.table
										.removeRow(goods.getPurchaseGoodsId().toString());
							}
						}
						PurchasingGoodsListProcessor.this.table.renderUpate();
						lblCount.setText(table.getAllRowId().length + " ��                                                                                                                                            ");
						processData();
						resetDataChangedstatus();
					}
				});
				getContext().bubbleMessage(request);
			}
		});
		table.addSelectionListener(new SelectionListener() {

			public void widgetSelected(SelectionEvent e) {
				String[] rowIds = table.getSelections();
				if (null == rowIds || rowIds.length == 0) {
					setSupplier.setEnabled(false);
				} else {
					setSupplier.setEnabled(true);

				}
			}
		});

	}

	private Map<GUID, PurchaseGoodsItem> elementsMap;

	/*
	 * ��ȡ�����б�
	 */
	@Override
	public Object[] getElements(Context context, STableStatus tablestatus) {
		@SuppressWarnings("unchecked")
		ListEntity<PurchaseGoodsItem> listEntity = context.find(ListEntity.class, new GetPurchasingGoodsItemListKey());
		lblCount.setText(String.valueOf(listEntity.getTotalCount()) + " ��                                                                                                                                             ");
		elementsMap = new HashMap<GUID, PurchaseGoodsItem>();
		for (PurchaseGoodsItem pgi : listEntity.getItemList()) {
			elementsMap.put(pgi.getId(), pgi);
		}
		setSupplier.setEnabled(false);
		// Ԥ������ѡ��ť��ʼ��
		List<InventoryInfo> list = getContext().getList(InventoryInfo.class, new GetWarningGoodsItemListKey(false));
		if (null == list || 0 == list.size()) {
			selectWarningGoods.setText(" ѡ��Ԥ������(0) ");
			selectWarningGoods.setEnabled(false);
		} else {
			selectWarningGoods.setText(" ѡ��Ԥ������(" + list.size() + ") ");
			selectWarningGoods.setEnabled(true);
		}
		return listEntity.getItemList().toArray();
	}

	/*
	 * ��ȡָ������ID
	 */
	@Override
	public String getElementId(Object element) {
		PurchaseGoodsItem item = (PurchaseGoodsItem) element;
		return item.getId().toString();
	}

	@Override
	public String[] getTableActionIds() {
		return new String[] { Action.SetSupplier.name(), Action.Delete.name() };
	}

	@Override
	protected String[] getElementActionIds(Object element) {
		PurchaseGoodsItem item = (PurchaseGoodsItem) element;
		if (item.isDirectDelivery()) {
			return new String[] { Action.SetSupplier.name() };
		}
		return getTableActionIds();
	}

	/**
	 * ָ����������ʱ���������¼�
	 */
	@Override
	public void actionPerformed(final String rowId, String actionName, String actionValue) {
		if (actionName.equals(Action.Delete.name())) {
			confirm("�Ƿ�ɾ����", new Runnable() {
				public void run() {
					DeletePurchaseGoodsTask task = new DeletePurchaseGoodsTask(GUID.valueOf(rowId));
					getContext().handle(task);
					table.render();
				}
			});
		} else if (Action.SetSupplier.name().equals(actionName)) {
			// ���ù�Ӧ��
			MsgRequest request = CommonSelectRequest.createSelectSupplierRequest();
			request.setResponseHandler(new ResponseHandler() {
				public void handle(Object returnValue, Object returnValue2, Object returnValue3, Object returnValue4) {
					UpdatePurchaseGoodsTask task = new UpdatePurchaseGoodsTask();
					UpdatePurchaseGoodsTask.PurchaseGoods pg = new UpdatePurchaseGoodsTask.PurchaseGoods();
					GUID purchaseGoodsId = GUID.valueOf(rowId);
					pg.setContactId((GUID) returnValue2);
					pg.setDirect(isDirect(rowId));
					pg.setId(purchaseGoodsId);
					pg.setSupplierId(GUID.valueOf(String.valueOf(returnValue)));
					task.setPurchaseGoods(pg);
					getContext().handle(task); 

					PurchaseGoodsInfo info = getContext().find(PurchaseGoodsInfo.class, GUID.valueOf(rowId));
					table.updateCell(rowId, Columns.LastPurchasePrice.name(), "", DoubleUtil.getRoundStr(info
							.getRecentPrice())); 
					table.updateCellAndToolTip(rowId, 7, info.getSupplierShorName(), info.getSupplierName());
					// ���±���ж���
					table.renderUpate();
				}
			});
			getContext().bubbleMessage(request);
		}
	}

	/**
	 * ��ȡָ���еı༭ֵ
	 */
	@Override
	public String getValue(Object element, String columnName) {
		PurchaseGoodsItem item = (PurchaseGoodsItem) element;
		if (Columns.PurchasePrice.name().equals(columnName)) {
			return item.getPrice() < 0 ? "" : String.valueOf(item.getPrice());
		}
		return null;
	}

	/**
	 * ��ȡָ���еı༭ֵ
	 */
	@Override
	public SNameValue[] getExtraData(Object element) {
		return null;
	}

	private boolean isDirect(String rowId) {
		GUID id = GUID.valueOf(rowId);
		PurchaseGoodsItem item = elementsMap.get(id);
		if (null == item) {
			item = getContext().find(PurchaseGoodsInfo.class, id);
			elementsMap.put(id, item);
		}
		if (null == item) {
			alert("��ǰ�����ѱ��ɹ���");
			return false;
		}
		return item.isDirectDelivery();
	}

	public String getPromptMessage() {
		return "����������δ����";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.spark.common.components.pages.IDataProcessPrompt#processData()
	 */
	public boolean processData() {
		String[] ids = table.getAllRowId();
		if (null != ids) {
			UpdatePurchaseGoodsTask task = new UpdatePurchaseGoodsTask();
			List<UpdatePurchaseGoodsTask.PurchaseGoods> list = new ArrayList<UpdatePurchaseGoodsTask.PurchaseGoods>();
			UpdatePurchaseGoodsTask.PurchaseGoods pg = null;
			for (String id : ids) {
				String[] prices = table.getEditValue(id, Columns.PurchasePrice.name());
				if (null == prices || 0 == prices.length) {
					continue;
				}
				try {
					double purchasePrice = Double.valueOf(prices[0]);
					if (0 > purchasePrice) {
						continue;
					}
					pg = new UpdatePurchaseGoodsTask.PurchaseGoods();
					GUID purchaseGoodsId = GUID.valueOf(id);
					pg.setDirect(isDirect(id));
					pg.setId(purchaseGoodsId);
					pg.setPrice(purchasePrice);
				} catch (NumberFormatException ex) {
					continue;
				}
				list.add(pg);
			}
			task.setPurchaseGoods(list.toArray(new UpdatePurchaseGoodsTask.PurchaseGoods[list.size()]));
			getContext().handle(task);
		}
		return true;
	}

	@Override
	protected String getExportFileTitle() {
		// TODO Auto-generated method stub
		return "�ɹ��嵥";
	}
}