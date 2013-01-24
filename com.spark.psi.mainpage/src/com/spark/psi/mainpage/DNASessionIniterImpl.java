package com.spark.psi.mainpage;

import com.jiuqi.dna.core.spi.application.Session;
import com.jiuqi.dna.core.spi.application.SessionIniter;

public class DNASessionIniterImpl implements  SessionIniter<DNASessionInitProxy> {
	/**
	 * Î¨Ò»ÊµÀý
	 */
	public static DNASessionIniterImpl INSTANCE = new DNASessionIniterImpl();

	public void initSession(Session session, DNASessionInitProxy sessionInitProxy)
			throws Throwable {
		sessionInitProxy.initRemoteInfo(session.getRemoteInfo());
	}
}
