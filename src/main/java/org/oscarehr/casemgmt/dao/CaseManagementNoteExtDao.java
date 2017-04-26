package org.oscarehr.casemgmt.dao;

import java.util.List;

import javax.persistence.Query;

import org.oscarehr.casemgmt.model.CaseManagementNoteExt;
import org.oscarehr.common.dao.AbstractDao;
import org.springframework.stereotype.Repository;

@Repository
public class CaseManagementNoteExtDao extends AbstractDao<CaseManagementNoteExt> {
	public CaseManagementNoteExtDao() {
		super(CaseManagementNoteExt.class);
	}

	@SuppressWarnings("unchecked")
	public List<CaseManagementNoteExt> getExtByNote(Integer noteId) {
		String sqlCommand = "SELECT x FROM " + modelClass.getName() + " x WHERE x.noteId=?1 ORDER BY x.id DESC";
		Query query = entityManager.createQuery(sqlCommand);
		query.setParameter(1, noteId);
		return query.getResultList();
	}
}
