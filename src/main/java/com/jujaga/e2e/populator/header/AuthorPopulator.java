package com.jujaga.e2e.populator.header;

import java.util.ArrayList;

import org.marc.everest.datatypes.TS;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.AssignedAuthor;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.Author;
import org.marc.everest.rmim.uv.cdar2.vocabulary.ContextControl;

import com.jujaga.e2e.StubRecord;
import com.jujaga.e2e.model.export.header.AuthorModel;
import com.jujaga.e2e.populator.Populator;

public class AuthorPopulator extends Populator {
	private final AuthorModel authorModel;

	public AuthorPopulator(Integer demographicNo) {
		authorModel = new AuthorModel(demographicNo);
	}

	@Override
	public void populate() {
		ArrayList<Author> authors = new ArrayList<Author>();
		Author provider = new Author();
		AssignedAuthor assignedAuthor = new AssignedAuthor();

		provider.setContextControlCode(ContextControl.OverridingPropagating);
		provider.setTime(StubRecord.Demographic.docCreated, TS.DAY);
		provider.setAssignedAuthor(assignedAuthor);

		assignedAuthor.setId(authorModel.getIds());
		assignedAuthor.setTelecom(authorModel.getTelecoms());
		assignedAuthor.setAssignedAuthorChoice(authorModel.getPerson());
		authors.add(provider);

		Author system = new Author();
		AssignedAuthor assignedSystem = new AssignedAuthor();

		system.setContextControlCode(ContextControl.OverridingPropagating);
		system.setTime(StubRecord.Demographic.docCreated, TS.DAY);
		system.setAssignedAuthor(assignedSystem);

		assignedSystem.setId(authorModel.getIds());
		assignedSystem.setAssignedAuthorChoice(authorModel.getDevice());
		authors.add(system);

		clinicalDocument.setAuthor(authors);
	}
}
