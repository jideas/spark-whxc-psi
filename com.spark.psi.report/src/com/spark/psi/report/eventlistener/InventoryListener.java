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
 * 监听库存模块事件服务
 * 
 */
public class InventoryListener extends Service{

	protected InventoryListener(){
		super("InventoryListener");
	}

	/**
	 * 调拨单审批驳回事件
	 */
	@Publish 
	class P extends EventListener<InventoryAllocateDenyEvent>{
		@Override 
		protected void occur(Context context, InventoryAllocateDenyEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}
	}/**
	 * 调拨单审批通过事件
	 */
	@Publish
	class P0 extends EventListener<InventoryAllocateApprovalEvent>{

		@Override
		protected void occur(Context context, InventoryAllocateApprovalEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * 确认出库事件（出库单ID、出库明细单ID）
	 */
	@Publish
	class PP extends EventListener<CheckOutEvent>{

		@Override
		protected void occur(Context context, CheckOutEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * 确认入库事件（入库单ID、入库明细单ID）
	 */
	@Publish
	class PP1 extends EventListener<CheckInEvent>{

		@Override
		protected void occur(Context context, CheckInEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * 出库单事件：创建
	 */
	@Publish
	class PP2 extends EventListener<CheckingOutEvent>{

		@Override
		protected void occur(Context context, CheckingOutEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * 入库单事件：创建
	 */
	@Publish
	class PP3 extends EventListener<CheckingInEvent>{

		@Override
		protected void occur(Context context, CheckingInEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * 库存流水事件（流水ID）
	 */
	@Publish
	class PP4 extends EventListener<InventoryLogEvent>{

		@Override
		protected void occur(Context context, InventoryLogEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * 出库单事件： 中止、重新执行（出库单ID）
	 */
	@Publish
	class PP21 extends EventListener<CheckOutSheetStatusChanageEvent>{

		@Override
		protected void occur(Context context, CheckOutSheetStatusChanageEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * 入库单事件： 中止、重新执行（入库单ID）
	 */
	@Publish
	class PP31 extends EventListener<CheckInSheetStatusChanageEvent>{

		@Override
		protected void occur(Context context, CheckInSheetStatusChanageEvent event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}

	}

	/**
	 * 调拨单待审批事件
	 */
	@Publish
	class PP6 extends EventListener<InventoryAllocateSubmitted>{

		@Override
		protected void occur(Context context, InventoryAllocateSubmitted event) throws Throwable{
			context.handle(new QueueAddTask(event));
		}
	}
}
