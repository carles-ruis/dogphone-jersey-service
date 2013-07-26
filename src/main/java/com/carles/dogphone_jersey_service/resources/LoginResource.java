package com.carles.dogphone_jersey_service.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.carles.dogphone_jersey_service.domain.Usuari;
import com.carles.dogphone_jersey_service.repo.UsuariRepo;
import com.sun.jersey.spi.resource.Singleton;

@Component
@Path("/login")
@Singleton
public class LoginResource {

@Autowired private UsuariRepo repo;

/*- ***************************************************************************** */
/*- ***** CRUD ***** */
/*- ***************************************************************************** */
@POST
@Consumes(MediaType.APPLICATION_JSON)
public Response login(Usuari form) {
	Response response = null;
	String nom = form.getNom();
	String password = form.getPassword();
	Usuari usuari = repo.findOne("nom", nom);;
	
	if ( usuari==null || usuari.getPassword().equals(password)==false ) {
		response = Response.status(401).build();
	} else {
		response = Response.ok().build(); /*-200 ok*/
	}
	return response;
}
/*- ***************************************************************************** */
/*- ***** PRIVATE ***** */
/*- ***************************************************************************** */		

}