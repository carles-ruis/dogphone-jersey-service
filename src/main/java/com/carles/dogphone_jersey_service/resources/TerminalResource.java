package com.carles.dogphone_jersey_service.resources;

import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.carles.dogphone_jersey_service.domain.Terminal;
import com.carles.dogphone_jersey_service.domain.TerminalsWrapper;
import com.carles.dogphone_jersey_service.repo.TerminalRepo;
import com.sun.jersey.spi.resource.Singleton;

@Component
@Path("/terminal")
@Singleton
public class TerminalResource {

@Autowired private TerminalRepo repo;

/*- ***************************************************************************** */
/*- ***** CRUD ***** */
/*- ***************************************************************************** */
@GET
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public TerminalsWrapper get() {
	List<Terminal> terminals = repo.find().asList();
	return new TerminalsWrapper(terminals);
}

/*- ***************************************************************************** */
/*- ***** PRIVATE ***** */
/*- ***************************************************************************** */
}
