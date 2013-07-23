package com.carles.dogphone_jersey_service.repo;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.carles.dogphone_jersey_service.domain.Terminal;
import com.google.code.morphia.Datastore;
import com.google.code.morphia.dao.BasicDAO;

@Repository 
public class TerminalRepo extends BasicDAO<Terminal, ObjectId> {

/*- ***************************************************************************** */
/*- ***** CONSTRUCTORS ***** */
/*- ***************************************************************************** */
@Autowired public TerminalRepo(Datastore ds) {
	super(Terminal.class, ds);
}

}
