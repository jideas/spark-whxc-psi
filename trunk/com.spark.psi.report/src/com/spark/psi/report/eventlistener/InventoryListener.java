package com.spark.psi.report.eventlistener;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.spark.psi.inventory.intf.event.CheckInEvent;
import com.spark.psi.inventory.intf.event.CheckInSheetStatusChanageEvent;
import com.spark.psi.inventory.intf.event.CheckOutEvent;
import com.spark.psi.inventory.intf.event.CheckOutSheetStatusChanageEvent;
import com.spark.psi.inventory.intf.event.CheckingInEvent;
import com.spark.psi.inventory.intf.event.CheckingOutEvent;
import com.spark.psi.inventory.intf.event.InventoryAllocateApprovalEvent;
import com.spark.psi.inventory.intf.event.InventoryAllocateDenyEvent;
import com.spark.psi.inventory.intf.event.InventoryAllocateSubmitted;
import com.spark.psi.inventory.intf.event.InventoryLogEvent;
import com.spark.psi.report.task.QueueAddTask;

/**
 * �������ģ���¼�����
 * 
 */
public class InventoryListener extends Service{

	protected InventoryListener(){
		super("InventoryListener");
	}

	/**
	 * ���������������¼�
	 */
	@Publish 
	class P extends EventListener<InventoryAllocateDenyEvent>{
		@Override 
		protected void occur(Context context, InventoryAllocateDenyEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}
	}/**
	 * ����������ͨ���¼�
	 */
	@Publish
	class P0 extends EventListener<InventoryAllocateApprovalEvent>{

		@Override
		protected void occur(Context context, InventoryAllocateApprovalEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * ȷ�ϳ����¼������ⵥID��������ϸ��ID��
	 */
	@Publish
	class PP extends EventListener<CheckOutEvent>{

		@Override
		protected void occur(Context context, CheckOutEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * ȷ������¼�����ⵥID�������ϸ��ID��
	 */
	@Publish
	class PP1 extends EventListener<CheckInEvent>{

		@Override
		protected void occur(Context context, CheckInEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * ���ⵥ�¼�������
	 */
	@Publish
	class PP2 extends EventListener<CheckingOutEvent>{

		@Override
		protected void occur(Context context, CheckingOutEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * ��ⵥ�¼�������
	 */
	@Publish
	class PP3 extends EventListener<CheckingInEvent>{

		@Override
		protected void occur(Context context, CheckingInEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * �����ˮ�¼�����ˮID��
	 */
	@Publish
	class PP4 extends EventListener<InventoryLogEvent>{

		@Override
		protected void occur(Context context, InventoryLogEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * ���ⵥ�¼��� ��ֹ������ִ�У����ⵥID��
	 */
	@Publish
	class PP21 extends EventListener<CheckOutSheetStatusChanageEvent>{

		@Override
		protected void occur(Context context, CheckOutSheetStatusChanageEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * ��ⵥ�¼��� ��ֹ������ִ�У���ⵥID��
	 */
	@Publish
	class PP31 extends EventListener<CheckInSheetStatusChanageEvent>{

		@Override
		protected void occur(Context context, CheckInSheetStatusChanageEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * �������������¼�
	 */
	@Publish
	class PP6 extends EventListener<InventoryAllocateSubmitted>{

		@Override
		protected void occur(Context context, InventoryAllocateSubmitted event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}
	}
}
