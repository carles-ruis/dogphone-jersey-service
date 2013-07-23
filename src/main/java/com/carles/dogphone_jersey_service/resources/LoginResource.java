package com.carles.dogphone_jersey_service.resources;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
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
@Context private HttpServletRequest req;

/*- ***************************************************************************** */
/*- ***** CRUD ***** */
/*- ***************************************************************************** */
/*- faig logout delete perque representa una modificacio. el retorn void genera un status code 204 */
@DELETE
public void logout() {
//public void logout(@Context HttpServletRequest req) {
req.getSession().removeAttribute("usuari");
}

@POST
@Consumes(MediaType.APPLICATION_JSON)
public Response login(Usuari form) {
//public Response login(Usuari form, @Context HttpServletRequest req) {
	Response response = null;
	String nom = form.getNom();
	String password = form.getPassword();
	Usuari usuari = repo.findOne("nom", nom);;
	
	if ( usuari==null || usuari.getPassword().equals(password)==false ) {
		response = Response.status(401).build();
	} else {
		req.getSession().setAttribute("usuari",usuari);
		response = Response.ok().build(); /*-200 ok*/
	}
	return response;
}
/*- ***************************************************************************** */
/*- ***** PRIVATE ***** */
/*- ***************************************************************************** */		

}