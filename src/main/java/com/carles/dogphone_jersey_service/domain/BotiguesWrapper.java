package com.carles.dogphone_jersey_service.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class BotiguesWrapper {

private List<Botiga> botigues = new ArrayList<Botiga>();

/*- ***************************************************************************** */
/*- ***** CONSTRUCTORS ***** */
/*- ***************************************************************************** */
public BotiguesWrapper() {}

public BotiguesWrapper(List<Botiga> botigues) {
	super();
	this.botigues= botigues;
}

/*- ***************************************************************************** */
/*- ***** GETTERS I SETTERS***** */
/*- ***************************************************************************** */
public List<Botiga> getBotigues() {
	return botigues;
}

//@XmlElementWrapper(name="botigues") crearia un { botigues { botiga [...] } }
//@XmlElement(name="botiga")
public void setBotigues(List<Botiga> botigues) {
	this.botigues = botigues;
}

}
