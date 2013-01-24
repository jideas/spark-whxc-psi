package com.spark.psi.base.internal.service;

import java.util.List;

import com.jiuqi.dna.core.Context;
import com.jiuqi.dna.core.da.ORMAccessor;
import com.jiuqi.dna.core.service.Publish;
import com.jiuqi.dna.core.service.Service;
import com.jiuqi.dna.core.type.GUID;
import com.spark.psi.base.internal.service.orm.Orm_Note;
import com.spark.psi.base.publicimpl.NoteImpl;
import com.spark.psi.publish.base.index.entity.Note;
import com.spark.psi.publish.base.index.task.NoteTask;

/**
 * ÀÊ ÷º«
 *
 */
public class NoteService extends Service {

	protected final Orm_Note q_Orm_Note;

	protected NoteService(Orm_Note qOrmNote) {
		super("NoteService");
		q_Orm_Note = qOrmNote;
	}

	@Publish
	protected class BaseNoteProvider extends OneKeyResultProvider<Note, GUID> {
		@Override
		protected Note provide(Context context, GUID id) throws Throwable {
			ORMAccessor<NoteImpl> acc = context.newORMAccessor(q_Orm_Note);
			return acc.findByRECID(id);
		}
	}

	@Publish
	protected class AllNoteProvider extends ResultListProvider<Note> {
		@Override
		protected void provide(Context context, List<Note> resultList)
				throws Throwable {
			ORMAccessor<NoteImpl> acc = context.newORMAccessor(q_Orm_Note);
			resultList.addAll(acc.fetch());
		}
	}

	@Publish
	protected class AddNoteHandler extends
			TaskMethodHandler<NoteTask, NoteTask.Method> {
		public AddNoteHandler() {
			super(NoteTask.Method.ADD);
		}

		@Override
		protected void handle(Context context, NoteTask task) throws Throwable {
			ORMAccessor<NoteImpl> acc = context.newORMAccessor(q_Orm_Note);
			NoteImpl noteImpl = new NoteImpl();
			noteImpl.setNoteText(task.getText());
			noteImpl.setRecid(task.getRecid());
			acc.insert(noteImpl);
		}
	}

	@Publish
	protected class ModifyNoteHandler extends
			TaskMethodHandler<NoteTask, NoteTask.Method> {
		public ModifyNoteHandler() {
			super(NoteTask.Method.MODIFY);
		}

		@Override
		protected void handle(Context context, NoteTask task) throws Throwable {
			ORMAccessor<NoteImpl> acc = context.newORMAccessor(q_Orm_Note);
			NoteImpl noteImpl = new NoteImpl();
			noteImpl.setNoteText(task.getText());
			noteImpl.setRecid(task.getRecid());
			boolean success=acc.update(noteImpl);
			if (!success) {
				acc.insert(noteImpl);
			}
		}
	}
}
