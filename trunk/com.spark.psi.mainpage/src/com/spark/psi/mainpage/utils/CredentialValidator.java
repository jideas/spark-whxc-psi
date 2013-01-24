package com.spark.psi.mainpage.utils;

import com.jiuqi.dna.core.type.GUID;

public class CredentialValidator {

	public final static boolean validate(String credential, GUID userId) {
		return true;
		// URL url = null;
		// HttpURLConnection httpConn = null;
		// try {
		// url = new URL(PsiRemoteServiceInvoker.getUacURL() + "/validate");
		// URLConnection conn = url.openConnection();
		// if (conn instanceof HttpURLConnection) {
		// httpConn = (HttpURLConnection) conn;
		// httpConn.setDoOutput(true);
		// httpConn.setDoInput(true);
		// StringBuffer buffer = new StringBuffer();
		// buffer.append("userId=");
		// buffer.append(userId);
		// buffer.append("&credential=");
		// buffer.append(credential);
		// httpConn.setRequestMethod("POST");
		// OutputStream outputStream = httpConn.getOutputStream();
		// outputStream.write(buffer.toString().getBytes());
		// outputStream.flush();
		// outputStream.close();
		// BufferedReader reader = new BufferedReader(new
		// InputStreamReader(httpConn.getInputStream()));
		// String result = reader.readLine();
		// if ("1".equals(result)) {
		// return true;
		// }
		// throw new IllegalStateException();
		// }
		// } catch (Throwable t) {
		// // t.printStackTrace();
		// } finally {
		// if (httpConn != null) {
		// httpConn.disconnect();
		// }
		// }
		// return false;
	}

}
