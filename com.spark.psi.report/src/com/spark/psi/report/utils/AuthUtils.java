/**
 * 
 */
package com.spark.psi.report.utils;

import java.util.ArrayList;
import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.publish.Auth;

/**
 *
 */
public abstract class AuthUtils{

	/**
	 * 得到全部Auth的数组
	 * 
	 * @return
	 */
	public static Auth[] getAuthArray(){
		return new Auth[] {Auth.Boss, Auth.SalesManager, Auth.PurchaseManager, Auth.AccountManager,
		        Auth.StoreKeeperManager, Auth.Account, Auth.Sales, Auth.Purchase, Auth.StoreKeeper, Auth.Distribute,Auth.Assistant};
	}

	/**
	 * 得到员工的所有Auth
	 * 
	 * @param context
	 * @param id
	 * @return
	 */
	public static List<Auth> getAuthList(Context context, GUID id){
		List<Auth> auths = new ArrayList<Auth>();
		if(context.find(Boolean.class, Auth.Boss, id)){
			auths.add(Auth.Boss);
		}
		if(context.find(Boolean.class, Auth.SalesManager, id)){
			auths.add(Auth.SalesManager);
		}
		if(context.find(Boolean.class, Auth.PurchaseManager, id)){
			auths.add(Auth.PurchaseManager);
		}
		if(context.find(Boolean.class, Auth.AccountManager, id)){
			auths.add(Auth.AccountManager);
		}
		if(context.find(Boolean.class, Auth.StoreKeeperManager, id)){
			auths.add(Auth.StoreKeeperManager);
		}
		if(context.find(Boolean.class, Auth.Sales, id)){
			auths.add(Auth.Sales);
		}
		if(context.find(Boolean.class, Auth.Purchase, id)){
			auths.add(Auth.Purchase);
		}
		if(context.find(Boolean.class, Auth.Account, id)){
			auths.add(Auth.Account);
		}
		if(context.find(Boolean.class, Auth.StoreKeeper, id)){
			auths.add(Auth.StoreKeeper);
		}
		if(context.find(Boolean.class, Auth.Distribute, id)){
			auths.add(Auth.Distribute);
		}
		if(context.find(Boolean.class, Auth.Assistant, id)){
			auths.add(Auth.Assistant);
		}
		return auths;
	}

	/**
	 * 得到所有管理者职能
	 * 
	 * @param context
	 * @param id
	 * @return
	 */
	public static Auth[] getManagerAuthArray(Context context, GUID id){
		return new Auth[] {Auth.Boss, Auth.SalesManager, Auth.PurchaseManager, Auth.AccountManager,
		        Auth.StoreKeeperManager};
	}

	public static boolean isBoss(Context context, GUID id){
		return context.find(Boolean.class, Auth.Boss, id);
	}

	public static boolean isManager(Context context, GUID id){
		return context.find(Boolean.class, Auth.SalesManager, id)
		        || context.find(Boolean.class, Auth.PurchaseManager, id)
		        || context.find(Boolean.class, Auth.AccountManager, id)
		        || context.find(Boolean.class, Auth.StoreKeeperManager, id);
	}

	public static boolean isSalesManager(Context context, GUID id){
		return context.find(Boolean.class, Auth.SalesManager, id);
	}

	public static boolean isStoreKeeperManager(Context context, GUID id){
		return context.find(Boolean.class, Auth.StoreKeeperManager, id);
	}

	public static boolean isAccountManager(Context context, GUID id){
		return context.find(Boolean.class, Auth.AccountManager, id);
	}

	public static boolean isPurchaseManager(Context context, GUID id){
		return context.find(Boolean.class, Auth.PurchaseManager, id);
	}

	public static boolean isSales(Context context, GUID id){
		return context.find(Boolean.class, Auth.Sales, id);
	}

	public static boolean isAccount(Context context, GUID id){
		return context.find(Boolean.class, Auth.Account, id);
	}

	public static boolean isPurchase(Context context, GUID id){
		return context.find(Boolean.class, Auth.Purchase, id);
	}

	public static boolean isStoreKeeper(Context context, GUID id){
		return context.find(Boolean.class, Auth.StoreKeeper, id);
	}

	public static boolean isYG(Context context, GUID id){
		return !AuthUtils.isBoss(context, id) && !AuthUtils.isManager(context, id);
	}
}
