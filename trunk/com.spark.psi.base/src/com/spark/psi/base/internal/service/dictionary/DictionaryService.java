package com.spark.psi.base.internal.service.dictionary;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.base.dictionary.BaseDictionaryConfig;
import com.spark.psi.publish.BaseDictionaryEnum;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.base.key.BaseDictionaryKey;

public class DictionaryService extends Service {

	protected DictionaryService() {
		super(
				"com.spark.psi.base.internal.service.dictionary.DictionaryService");
	}

	private static Map<BaseDictionaryEnum, BaseDictionaryConfig> map = new HashMap<BaseDictionaryEnum, BaseDictionaryConfig>();

	/* 
	 * 系统启动时加载配置文件
	 * @see com.jiuqi.dna.core.impl.ServiceBase#init(com.jiuqi.dna.core.Context)
	 */
	@Override
	protected void init(Context context) throws Throwable {
		for (BaseDictionaryEnum value : BaseDictionaryEnum.values()) {
			BaseDictionaryConfig config = new BaseDictionaryConfig(value);
			map.put(value, config);
		}
	}

	/**
	 * 得到所有基础模块配置项字典
	 */
	@Publish
	protected class BaseDictionaryProvider extends
			OneKeyResultListProvider<EnumEntity, BaseDictionaryKey> {

		@Override
		protected void provide(Context context, BaseDictionaryKey key,
				List<EnumEntity> resultList) throws Throwable {
			if (map.get(key.getEnumValue()) == null) {
				BaseDictionaryConfig config = new BaseDictionaryConfig(key
						.getEnumValue());
				map.put(key.getEnumValue(), config);
			}
			resultList.addAll(map.get(key.getEnumValue()).getTypeList());
		}
	}

	/**
	 * 得到具体的基础模块配置项字典
	 */
	@Publish
	protected class LoadBaseDictionaryProvider extends
			OneKeyResultProvider<EnumEntity, BaseDictionaryKey> {

		@Override
		protected EnumEntity provide(Context context, BaseDictionaryKey key)
				throws Throwable {
			BaseDictionaryConfig config = map.get(key.getEnumValue());
			if (config == null) {
				config = new BaseDictionaryConfig(key.getEnumValue());
				map.put(key.getEnumValue(), config);
			}
			return config.getType(key.getCode());
		}
	}
}
