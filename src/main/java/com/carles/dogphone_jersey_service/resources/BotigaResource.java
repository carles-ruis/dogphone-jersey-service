package com.carles.dogphone_jersey_service.resources;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.carles.dogphone_jersey_service.domain.Botiga;
import com.carles.dogphone_jersey_service.domain.BotiguesWrapper;
import com.carles.dogphone_jersey_service.repo.BotigaRepo;
import com.sun.jersey.spi.resource.Singleton;

@Component
@Path("/botiga")
@Singleton
public class BotigaResource {

@Autowired private BotigaRepo repo;

/*- ***************************************************************************** */
/*- ***** CRUD ***** */
/*- ***************************************************************************** */
@GET
@Path("/{nom}")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public Botiga getOne(@PathParam("nom") String nom) {
	return repo.findByNom(nom);
}

@GET
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public BotiguesWrapper get() {
	List<Botiga> botigues = repo.find().asList();
	this.ordenarBotiguesPerCiutat(botigues);
	return new BotiguesWrapper(botigues);
}

/*- ***************************************************************************** */
/*- ***** PRIVATE ***** */
/*- ***************************************************************************** */
private void ordenarBotiguesPerCiutat(List<Botiga> botigues) {
	Collections.sort(botigues, new Comparator<Botiga>() {
		@Override
		public int compare(Botiga o1, Botiga o2) {
			int compCiutats = o1.getCiutat().compareTo(o2.getCiutat());
			if (compCiutats != 0) {
				return compCiutats;
			} else {
				return o1.getNom().compareTo(o2.getNom());
			}
		}
	});
}
}
