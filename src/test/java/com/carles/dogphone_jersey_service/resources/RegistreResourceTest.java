package com.carles.dogphone_jersey_service.resources;

import static org.junit.Assert.assertEquals;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.embed.GlassFish;
import org.glassfish.embed.ScatteredWar;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import com.carles.dogphone_jersey_service.repo.UsuariRepo;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class RegistreResourceTest extends BaseTest {

private static GlassFish server = null;
private static WebResource wr;
private static final URI BASE_URI = UriBuilder.fromUri("http://localhost/").port(8888).path("dogphone-jersey-service").build();
@Autowired private UsuariRepo repo;

/*- ***************************************************************************** */
/*- ***** BEFORE I AFTER ***** */
/*- ***************************************************************************** */
@BeforeClass
public static void setUpClass() throws IOException {
	server= new GlassFish(BASE_URI.getPort());
	ScatteredWar war = new ScatteredWar(BASE_URI.getRawPath(),new File("src/main/webapp"),new File("src/main/webapp/WEB-INF/web.xml"),Collections.singleton(new File("target/classes").toURI().toURL()));
	server.deploy(war);
	wr = Client.create().resource(BASE_URI);	
}

@AfterClass
/*- espera una mica entre test i test perque li dongui temps a parar del tot */
/*- sino, generara un error StreamCorruptedException: invalid header */
public static void tearDownClass() /*throws Exception*/ {
	server.stop();
}

@Before
public void setUp() {}

@After
public void tearDown() {
	repo.deleteByQuery(repo.createQuery().filter("nom", "carles"));		
}

/*- ***************************************************************************** */
/*- ***** TEST ***** */
/*- ***************************************************************************** */
@Test
public void testPost() {
	String usuari = " {\"nom\":\"carles\", \"password\":\"password\", \"confirmacio\":\"passwor\"} ";
	ClientResponse responseError = wr.path("registre").type(MediaType.APPLICATION_JSON)
		.post(ClientResponse.class, usuari);
	usuari = " {\"nom\":\"carles\", \"password\":\"password\", \"confirmacio\":\"password\"} ";
	ClientResponse responseOk = wr.path("registre").type(MediaType.APPLICATION_JSON)
		.post(ClientResponse.class, usuari);
	usuari = " {\"nom\":\"carles\", \"password\":\"holaaaaa\", \"confirmacio\":\"holaaaaa\"} ";
	ClientResponse responseConflict = wr.path("registre").type(MediaType.APPLICATION_JSON)
		.post(ClientResponse.class, usuari);
	System.out.println(responseError.getStatus() + "," + responseOk.getStatus() + ","
		+ responseConflict.getStatus());
	assertEquals(401, responseError.getStatus());
	assertEquals(201, responseOk.getStatus());
	assertEquals(409, responseConflict.getStatus());
}

/*- ***************************************************************************** */
/*- ***** PRIVATE ***** */
/*- ***************************************************************************** */
}
