package com.carles.dogphone_jersey_service.repo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.carles.dogphone_jersey_service.domain.Botiga;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;

@Repository
public class BotigaRepo extends BasicDAO<Botiga, ObjectId> {

/*- ***************************************************************************** */
/*- ***** CONSTRUCTORS ***** */
/*- ***************************************************************************** */
@Autowired public BotigaRepo(Datastore ds) {
	super(Botiga.class, ds);
}

/*- ***************************************************************************** */
/*- ***** PUBLIC ***** */
/*- ***************************************************************************** */
public Botiga findByNom( String nom ) {
    return ds.find(Botiga.class,"nom",nom).get();
}

}
