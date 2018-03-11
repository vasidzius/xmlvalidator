package com.vasidzius.xmlvalidator.commandline;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

@Parameters(commandDescription = "Validate XML with DTD")
public class CommandDtdValidate {

    @Parameter(names = "-xml", description = "Path to target xml-file", validateWith = PathValidator.class, required = true)
    private String xmlUri;

    public String getXmlUri() {
        return xmlUri;
    }
}
