package org.oscarehr.common.dao;

import java.util.List;
import javax.persistence.Query;
import org.oscarehr.common.model.Demographic;
import org.springframework.stereotype.Repository;

@Repository
public class DemographicDaoExport extends AbstractDao<Demographic> {
	public DemographicDaoExport() {
		super(Demographic.class);
	}

	@SuppressWarnings("unchecked")
	public List<Integer> getDemographicNumbers() {

		String sqlCommand = "SELECT demographicNo FROM " + modelClass.getName();
		Query query = entityManager.createQuery(sqlCommand);

		return query.getResultList();
	}
}
