package com.spark.common.utils;

import java.util.HashMap;
import java.util.Map;

import com.jiuqi.dna.core.type.GUID;

public class CredentialRegistry {

	private static Map<GUID, Credential> registry = new HashMap<GUID, Credential>();

	private static Object lock = new Object();

	public final static String generate(String name) {
		if (name == null) {
			throw new IllegalArgumentException("name²»ÄÜÎª¿Õ");
		}
		Credential credential = new Credential(GUID.randomID(), name,
				System.currentTimeMillis());
		synchronized (lock) {
			clean();
			registry.put(credential.id, credential);
		}
		return credential.id.toString();
	}

	public final static boolean validate(String credentialId, String name) {
		Credential credential = null;
		GUID id = GUID.tryValueOf(credentialId);
		if (id != null) {
			synchronized (lock) {
				credential = registry.remove(id);
			}
		}
		if (credential != null && credential.name.equals(name)) {
			return true;
		}
		return false;
	}

	private static void clean() {
		if (registry.size() > 1000) {
			HashMap<GUID, Credential> newRegistry = new HashMap<GUID, Credential>();
			long current = System.currentTimeMillis();
			for (Credential credential : registry.values()) {
				if (current - credential.time < 1000 * 60) {
					newRegistry.put(credential.id, credential);
				}
			}
			registry.clear();
			registry = newRegistry;
		}
	}
}

class Credential {

	GUID id;

	String name;

	long time;

	public Credential(GUID id, String name, long time) {
		super();
		this.id = id;
		this.name = name;
		this.time = time;
	}

}