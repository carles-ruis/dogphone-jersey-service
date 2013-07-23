package com.carles.dogphone_jersey_service.repo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.carles.dogphone_jersey_service.domain.Usuari;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;

@Repository 
public class UsuariRepo extends BasicDAO<Usuari, ObjectId> {

/*- ***************************************************************************** */
/*- ***** CONSTRUCTORS ***** */
/*- ***************************************************************************** */
@Autowired public UsuariRepo(Datastore ds) {
	super(Usuari.class, ds);
}

}
