package com.carles.dogphone_jersey_service.resources;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.util.Collections;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.glassfish.embed.GlassFish;
import org.glassfish.embed.ScatteredWar;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import com.carles.dogphone_jersey_service.domain.Botiga;
import com.carles.dogphone_jersey_service.domain.BotiguesWrapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class BotigaResourceTest extends BaseTest {

private static GlassFish server = null;
private static WebResource wr;
private static final URI BASE_URI = UriBuilder.fromUri("http://localhost/").port(8888).path("dogphone-jersey-service").build();

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
public static void tearDownClass() /*throws Exception*/ {
	server.stop();
}

/*- ***************************************************************************** */
/*- ***** TEST ***** */
/*- ***************************************************************************** */
@Test
public void testGetOne() {
	Botiga botiga = wr.path("botiga/Canonge").accept(MediaType.APPLICATION_JSON).get(Botiga.class);
	String json = wr.path("botiga/Canonge").accept(MediaType.APPLICATION_JSON).get(String.class);
	Botiga botigaXml = wr.path("botiga/Canonge").accept(MediaType.APPLICATION_XML).get(Botiga.class);
	String xml = wr.path("botiga/Canonge").accept(MediaType.APPLICATION_XML).get(String.class);
	System.out.println("BOTIGA JSON:"+botiga);
	System.out.println("BOTIGA JSON:"+json);
	System.out.println("BOTIGA XML:"+botigaXml);
	System.out.println("BOTIGA XML:"+xml);
	assertEquals("Canonge",botiga.getNom());
	assertTrue(json.contains("Canonge"));
	assertEquals("Canonge",botigaXml.getNom());
	assertTrue(xml.contains("<nom>Canonge</nom>"));
}

@Test
public void testGet() {
	List<Botiga> botigues = wr.path("botiga").accept(MediaType.APPLICATION_JSON).get(BotiguesWrapper.class).getBotigues();
	String json = wr.path("botiga").accept(MediaType.APPLICATION_JSON).get(String.class);
	List<Botiga> botiguesXml = wr.path("botiga").accept(MediaType.APPLICATION_XML).get(BotiguesWrapper.class).getBotigues();
	String xml= wr.path("botiga").accept(MediaType.APPLICATION_XML).get(String.class);
	System.out.println("BOTIGUES JSON:"+botigues);
	System.out.println("BOTIGUES JSON:"+json);
	System.out.println("BOTIGUES XML:"+botiguesXml);
	System.out.println("BOTIGUES XML:"+xml);
	assertTrue(botigues.get(0).getTelefon().startsWith("(555)"));
	assertTrue(json.contains("Canonge"));
	assertTrue(botiguesXml.get(0).getTelefon().startsWith("(555)"));
	assertTrue(xml.contains("<nom>Canonge</nom>"));
}

/*- ***************************************************************************** */
/*- ***** PRIVATE ***** */
/*- ***************************************************************************** */
}
