package com.carles.dogphone_jersey_service.resources;

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
import com.carles.dogphone_jersey_service.domain.Terminal;
import com.carles.dogphone_jersey_service.domain.TerminalsWrapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

public class TerminalResourceTest extends BaseTest {

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
public void testGet() {
	List<Terminal> terminals = wr.path("terminal").accept(MediaType.APPLICATION_JSON).get(TerminalsWrapper.class).getTerminals();
	String json = wr.path("terminal").accept(MediaType.APPLICATION_JSON).get(String.class);
	List<Terminal> terminalsXml = wr.path("terminal").accept(MediaType.APPLICATION_XML).get(TerminalsWrapper.class).getTerminals();
	String xml= wr.path("terminal").accept(MediaType.APPLICATION_XML).get(String.class);
	System.out.println("TERMINALS JSON:"+terminals);
	System.out.println("TERMINALS JSON:"+json);
	System.out.println("TERMINALS XML:"+terminalsXml);
	System.out.println("TERMINALS XML:"+xml);
	assertTrue(terminals.get(0).getDescripcio().contains("Cras justo odio"));
	assertTrue(json.contains("Cras justo odio"));
	assertTrue(terminalsXml.get(0).getDescripcio().contains("Cras justo odio"));
	assertTrue(xml.contains("<descripcio>Cras justo odio"));
}

/*- ***************************************************************************** */
/*- ***** PRIVATE ***** */
/*- ***************************************************************************** */
}
