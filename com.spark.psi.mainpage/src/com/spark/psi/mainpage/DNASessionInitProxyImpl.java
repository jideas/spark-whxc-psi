package com.spark.psi.mainpage;

import javax.servlet.http.HttpServletRequest;

import com.jiuqi.dna.core.spi.application.RemoteInfoSPI;

public class DNASessionInitProxyImpl implements DNASessionInitProxy{
	
	private HttpServletRequest request;

	public DNASessionInitProxyImpl(HttpServletRequest request) {
		this.request = request;
	}

	public void initRemoteInfo(RemoteInfoSPI remoteInfo) {
		remoteInfo.setRemoteHost(request.getRemoteHost());
		String ip = request.getHeader("x-forwarded-for");
		if (isInvalidIP(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
			if (isInvalidIP(ip)) {
				ip = request
						.getHeader("WL-Proxy-Client-IP");
				if (isInvalidIP(ip)) {
					ip = request.getRemoteAddr();
				}
			}
		} else {
			int i = ip.indexOf(',', 0);
			if (i >= 0) {
				ip = ip.substring(0, i);
			}
		}
		remoteInfo.setRemoteAddr(ip);
	}

	private boolean isInvalidIP(String ip) {
		return ip == null || ip.length() == 0
				|| "unknown".equalsIgnoreCase(ip);
	}
}