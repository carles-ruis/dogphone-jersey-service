package com.carles.dogphone_jersey_service.resources;

import java.net.URI;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.carles.dogphone_jersey_service.domain.Usuari;
import com.carles.dogphone_jersey_service.repo.UsuariRepo;
import com.sun.jersey.spi.resource.Singleton;

@Component
@Path("/registre")
@Singleton
public class RegistreResource {

@Autowired private UsuariRepo repo;
@Context private UriInfo uriInfo;

/*- ***************************************************************************** */
/*- ***** CRUD ***** */
/*- ***************************************************************************** */
@POST
@Consumes(MediaType.APPLICATION_JSON)
public Response post(Usuari usuari) {
	Response response = null;
	String nom = usuari.getNom();
	String password = usuari.getPassword();
	String confirmacio = usuari.getConfirmacio();

	if (StringUtils.isBlank(nom) || StringUtils.isBlank(password)) {
		response = Response.status(409).build(); /*- 409 conflict */
	} else if (repo.findOne("nom", nom) != null) {
		response = Response.status(409).build(); /*- 409 conflict */
	} else if (password.equals(confirmacio) == false) {
		response = Response.status(401).build(); /*-401 unauthorized */
	} else {
		String id = repo.save(usuari).getId().toString();
		URI uri =  uriInfo.getAbsolutePathBuilder().path(id).build();
		response = Response.created(uri).build(); /*- 201 created */
	}
	return response;
}

@POST
@Consumes(MediaType.APPLICATION_FORM_URLENCODED) /*-'application/x-www-form-urlencoded'*/
//public Response post(@FormParam("nom") String name,@FormParam("password") String password,@FormParam("confirmacio") String confirmacio) {
public Response postForm(MultivaluedMap<String, String> formParams) {
	Response response = null;
	String nom = formParams.getFirst("nom");
	String password = formParams.getFirst("password");
	String confirmacio = formParams.getFirst("confirmacio");

	if (StringUtils.isBlank(nom) || StringUtils.isBlank(password)) {
		response = Response.status(409).build(); /*- 409 conflict */
	} else if (repo.findOne("nom", nom) != null) {
		response = Response.status(409).build(); /*- 409 conflict */
	} else if (password.equals(confirmacio) == false) {
		response = Response.status(401).build(); /*-401 unauthorized */
	} else {
		Usuari usuari = new Usuari(nom,password,confirmacio);
		String id = repo.save(usuari).getId().toString();
		URI uri =  uriInfo.getAbsolutePathBuilder().path(id).build();
		response = Response.created(uri).build(); /*- 201 created */
	}
	return response;
}

/*- ***************************************************************************** */
/*- ***** PRIVATE ***** */
/*- ***************************************************************************** */		

}