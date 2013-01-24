/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-27    jiuqi
 * ============================================================*/

/**============================================================
 * ��Ȩ��  ��Ȩ���� (c) 2002 - 2012
 * ���� com.spark.psi.base.internal.service
 * �޸ļ�¼��
 * ����                ����           ����
 * =============================================================
 * 2012-3-27    jiuqi
 * ============================================================*/

package com.spark.psi.base.internal.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.misc.SXElement;
import com.jiuqi.dna.core.misc.SXElementBuilder;
import com.jiuqi.dna.core.resource.ResourceContext;
import com.jiuqi.dna.core.resource.ResourceInserter;
import com.jiuqi.dna.core.resource.ResourceService;
import com.jiuqi.dna.core.resource.ResourceToken;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.util.PinYinComparator;
import com.spark.psi.base.internal.entity.EnumEntityImpl;
import com.spark.psi.base.task.GetEnumEntityListByNameKey;
import com.spark.psi.publish.EnumEntity;
import com.spark.psi.publish.EnumType;

/**
 * �����ֵ����
 */

public class EnumResourceService extends
		ResourceService<EnumEntity, EnumEntityImpl, EnumEntityImpl> {

	protected EnumResourceService() {
		super("�����ֵ���Դ����");
	}

	@Override
	protected void init(Context context) {
		context.find(EnumEntity.class, EnumType.Sex, "01");
	}

	@Override
	protected void initResources(
			Context context,
			ResourceInserter<EnumEntity, EnumEntityImpl, EnumEntityImpl> initializer) {
		InputStream is = getClass().getResourceAsStream("EnumEntity.xml");
		SXElementBuilder seb;
		try {
			seb = new SXElementBuilder();
			SXElement tenantsNode = seb.build(is).firstChild();
			Iterator<SXElement> it = tenantsNode.getChildren("enum-define")
					.iterator();
			while (it.hasNext()) {
				SXElement element = it.next();
				String type = element.getAttribute("type");
				EnumType enumType = EnumType.getEnumTypeByCode(type);
				if (enumType != null) {
					// �����ڳ�ʼ�������в���ResourceToken�����ѭ��������������һ����ʱ��Map��������е�ResourceToken���Ա���Բ��ҵ�����ResourceToken
					Map<String, ResourceToken<EnumEntity>> resourceTokens = new HashMap<String, ResourceToken<EnumEntity>>();
					EnumEntityImpl rootEntity = new EnumEntityImpl(enumType,
							"", "");
					resourceTokens.put(rootEntity.getCode(), initializer
							.putResource((ResourceToken<EnumEntity>) null,
									rootEntity)); // �����
					Iterator<SXElement> entityIt = element
							.getChildren("entity").iterator();
					while (entityIt.hasNext()) {
						SXElement entityEl = entityIt.next();
						EnumEntityImpl entity = new EnumEntityImpl(enumType,
								entityEl.getAttribute("code"),
								entityEl.getAttribute("name"));
						String parentCode = entityEl.getAttribute("parent");
						ResourceToken<EnumEntity> parentToken = resourceTokens
								.get(parentCode);
						ResourceToken<EnumEntity> token = initializer
								.putResource(entity);
						resourceTokens.put(entity.getCode(), token);
						initializer.putResourceReference(parentToken, token);
					}
				}
			}
		} catch (Throwable t) {
			t.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
			}
		}
	}

	protected class GroupResourceReference extends
			ResourceReference<EnumEntity> {

		@Override
		public int compare(EnumEntity a, EnumEntity b) {
			return PinYinComparator.compareStr(a.getCode(), b.getCode());
		}

	}

	/**
	 * ����ö�����ͺ�ö�ٴ������ö�ٶ���
	 * 
	 */
	@Publish
	protected final class GetEnumEntityProvider extends
			TwoKeyResourceProvider<EnumType, String> {

		@Override
		protected EnumType getKey1(EnumEntityImpl entity) {
			return entity.getType();
		}

		@Override
		protected String getKey2(EnumEntityImpl entity) {
			return entity.getCode();
		}
	}

	/**
	 * ����ָ�����͵�ö���б�
	 */
	@Publish
	protected final class GetEnumEntityListProvider extends
			OneKeyResultListProvider<EnumEntity, EnumType> {

		@Override
		protected void provide(
				ResourceContext<EnumEntity, EnumEntityImpl, EnumEntityImpl> context,
				EnumType type, List<EnumEntity> resultList) throws Throwable {
			resultList.addAll(context.getList(EnumEntity.class, type, ""));
		}
	}

	/**
	 * ����ָ�����ͺ͸�ö�ٴ��룬����������ö��
	 */
	@Publish
	protected final class GetEnumEntityListProvider2 extends
			TwoKeyResultListProvider<EnumEntity, EnumType, String> {

		@Override
		protected void provide(
				ResourceContext<EnumEntity, EnumEntityImpl, EnumEntityImpl> context,
				EnumType type, String parentCode, List<EnumEntity> resultList)
				throws Throwable {
			ResourceToken<EnumEntity> token = context.findResourceToken(
					EnumEntity.class, type, parentCode);
			resultList.addAll(context.getResourceReferences(EnumEntity.class,
					token));
		}
	}
	
	@Publish
	protected final class GetEnumEntityListProvider3 extends OneKeyResultListProvider<EnumEntity,GetEnumEntityListByNameKey>{

		@Override
        protected void provide(
                ResourceContext<EnumEntity, EnumEntityImpl, EnumEntityImpl> context,
                GetEnumEntityListByNameKey key, List<EnumEntity> resultList)
                throws Throwable
        {
			String name = key.getName() == null ? "" : key.getName();
	        List<EnumEntity> list = context.getList(EnumEntity.class,key.getType());
	        for(EnumEntity enumEntity : list){
	            if(enumEntity.getName().indexOf(name)>-1){
	            	resultList.add(enumEntity);
	            }
            }
        }
		
	}
}
