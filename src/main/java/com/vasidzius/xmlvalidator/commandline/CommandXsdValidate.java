package com.vasidzius.xmlvalidator.commandline;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Validate XML with XSD")
public class CommandXsdValidate {

    @Parameter(names = "-xml", description = "Path to target xml-file", validateWith = PathValidator.class, required = true)
    private String xmlUri;

    @Parameter(names = "-xsd", description = "Path to xsd-file", validateWith = PathValidator.class, required = true)
    private String xsdUri;

    public String getXmlUri() {
        return xmlUri;
    }

    public String getXsdUri() {
        return xsdUri;
    }

}
