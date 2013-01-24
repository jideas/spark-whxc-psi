package com.spark.psi.base.utils;

import java.net.URL;

import com.jiuqi.dna.core.exception.DeadLockException;
import com.jiuqi.dna.core.invoke.SimpleTask;
import com.jiuqi.dna.core.invoke.Task;
import com.jiuqi.dna.core.service.RemoteServiceInvoker;

/**
 * 获得远程调用服务
 
 *
 */
public abstract class PsiRemoteServiceInvoker_bak  {
	
	protected static URL uacURL;

	public static URL getUacURL(){		
		return uacURL;
	}
	
	
	public static void setUacURL(URL uacURL){
		PsiRemoteServiceInvoker_bak.uacURL = uacURL;
    }



	public abstract URL getURL();

	public abstract RemoteServiceInvoker getRemoteServiceInvoker();
	
	public abstract void handle(SimpleTask task) throws DeadLockException;
	
	public abstract <TMethod extends Enum<TMethod>> void handle(Task<TMethod> task,
			TMethod method) throws DeadLockException;
}
