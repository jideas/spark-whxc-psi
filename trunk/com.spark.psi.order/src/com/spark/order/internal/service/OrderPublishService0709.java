package com.spark.order.internal.service;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.order.purchase.intf.entity.PurchaseOrderInfo;
import com.spark.order.purchase.intf.entity.PurchaseOrderItem;
import com.spark.psi.inventory.intf.entity.instorage.mod.Instorage;
import com.spark.psi.publish.CheckingInStatus;
import com.spark.psi.publish.CheckingInType;
import com.spark.psi.publish.inventory.entity.IrregualrCheckingInInfo;
import com.spark.psi.publish.inventory.entity.IrregualrCheckingInInfo.IrregualrGoodsItem;
import com.spark.psi.publish.inventory.key.GetIrregularCheckingInDetailKey;

public class OrderPublishService0709 extends Service {

	protected OrderPublishService0709() {
		super("com.spark.order.internal.service.OrderPublishService0709");
	}

	@Publish
	class GetIrregularCheckingInDetailProvider extends
			OneKeyResultProvider<IrregualrCheckingInInfo, GetIrregularCheckingInDetailKey> {

		@Override
		protected IrregualrCheckingInInfo provide(Context context, GetIrregularCheckingInDetailKey key) throws Throwable {
			// Èë¿âµ¥
			Instorage insto = context.find(Instorage.class, key.getId());
			if (null == insto) {
				return null;
			}
			PurchaseOrderInfo poi = context.find(PurchaseOrderInfo.class, insto.getRelaBillsId());
			if (null == poi) {
				return null;
			}
			return getIrregualrCheckingInInfo(context, insto, poi);
		}

		private IrregualrCheckingInInfo getIrregualrCheckingInInfo(final Context context, final Instorage insto,
				final PurchaseOrderInfo poi) {
			return new IrregualrCheckingInInfo() {

				public CheckingInType getType() {
					return CheckingInType.getCheckingInType(insto.getSheetType());
				}

				public String getSupplierName() {
					return poi.getPartnerName();
				}

				public String getStoreName() {
					return insto.getStoreName();
				}

				public CheckingInStatus getStatus() {
					return CheckingInStatus.getCheckingInType(insto.getStatus());
				}

				public String getBuyPerson() {
					return poi.getCreator();
				}

				public long getBuyDate() {
					return poi.getCreateDate();
				}

				public String getRemark() {
					return poi.getRemark();
				}

				public GUID getId() {
					return insto.getRECID();
				}

				public IrregualrGoodsItem[] getGoodsItems() {
					return getIrregualrGoodsItems(context.getList(PurchaseOrderItem.class, poi.getRECID()));
				}

				public String getCheckInPersonName() {
					return insto.getCreator();
				}

				public long getCheckingInDate() {
					return insto.getCreateDate();
				}

				public double getCheckingInAmount() {
					return poi.getTotalAmount();
				}

				public String getSheetNo() {
					return "";
				}
			};
		}

		private IrregualrGoodsItem[] getIrregualrGoodsItems(List<PurchaseOrderItem> list) {
			List<IrregualrGoodsItem> resultList = new ArrayList<IrregualrGoodsItem>();
			for (final PurchaseOrderItem item : list) {
				resultList.add(new IrregualrGoodsItem() {

					public String getUnit() {
						return item.getUnit();
					}

					public String getSpec() {
						return item.getGoodsSpec();
					}

					public double getPrice() {
						return item.getPrice();
					}

					public String getName() {
						return item.getGoodsName();
					} 

					public GUID getId() {
						return item.getRECID();
					}

					public GUID getGoodsItemId() {
						return item.getGoodsId();
					}

					public int getScale() {
						return item.getScale();
					}

					public double getCount() {
						return item.getCount();
					}

					public String getGoodsCode() {
						return item.getGoodsNo();
					}

					public double getAmount() {
						return item.getAmount();
					}

					public String getGoodsNo() {
						return item.getGoodsNo();
					}
				});
			}
			return resultList.toArray(new IrregualrGoodsItem[resultList.size()]);
		}

	}
}
