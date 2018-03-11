package com.vasidzius.xmlvalidator.commandline;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Count number of requested elements in incoming XML")
public class CommandCount {

    @Parameter(names = "-xml", description = "Path to target xml-file", validateWith = PathValidator.class, required = true)
    private String xmlUri;

    @Parameter(names = "-element", description = "Name of requested element that should be counted", required = true)
    private String elementName;

    public String getXmlUri() {
        return xmlUri;
    }

    public String getElementName() {
        return elementName;
    }
}
