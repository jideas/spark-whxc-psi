package com.spark.psi.mainpage;

import com.jiuqi.dna.core.spi.application.ContextSPI;
import com.jiuqi.dna.core.spi.application.Session;

public abstract class DNAContextUtil {

	/**
	 * 执行一个在上下文环境中的运行体
	 * 
	 * @param sessionId
	 * @param runnable
	 * @return
	 * @throws Throwable
	 */
	@SuppressWarnings("deprecation")
	public final static Object execContextRunnable(long sessionId,
			long verificationCode, ContextRunnable runnable) throws Throwable {
		ContextSPI context = null;
		try {
			Session session = com.jiuqi.dna.core.spi.application.AppUtil
					.getDefaultApp().getSession(sessionId);
			if (session.getVerificationCode() != verificationCode) {
				throw new IllegalStateException();
			}
			context = session.newContext(false);
			return runnable.runWidthContext(context);
		} catch (Throwable t) {
//			t.printStackTrace();
			if (context != null) {
				context.exception(t);
			}
			throw t;
		} finally {
			if (context != null) {
				context.resolveTrans();
				context.dispose();
			}
		}
	}

	/**
	 * @author leezizi
	 */
	public interface ContextRunnable {
		public Object runWidthContext(ContextSPI contex) throws Throwable;
	}

}
