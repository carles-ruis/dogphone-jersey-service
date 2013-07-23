package com.carles.dogphone_jersey_service.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class TerminalsWrapper {

private List<Terminal> terminals = new ArrayList<Terminal>();

/*- ***************************************************************************** */
/*- ***** CONSTRUCTORS ***** */
/*- ***************************************************************************** */
public TerminalsWrapper() {}

public TerminalsWrapper(List<Terminal> terminals) {
	super();
	this.terminals= terminals;
}
/*- ***************************************************************************** */
/*- ***** GETTERS I SETTERS***** */
/*- ***************************************************************************** */
public List<Terminal> getTerminals() {
	return terminals;
}

//@XmlElementWrapper(name="terminals")
//@XmlElement(name="terminal")
public void setTerminals(List<Terminal> terminals) {
	this.terminals = terminals;
}

}
