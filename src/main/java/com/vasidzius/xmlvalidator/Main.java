package com.vasidzius.xmlvalidator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.vasidzius.xmlvalidator.api.XMLDTDValidator;
import com.vasidzius.xmlvalidator.commandline.CommandCount;
import com.vasidzius.xmlvalidator.commandline.CommandDtdValidate;
import com.vasidzius.xmlvalidator.commandline.CommandXsdValidate;
import org.apache.log4j.Logger;

public class Main {

    private static final Logger LOGGER = Logger.getLogger(Main.class);

    public static void main(String[] args) {

        CommandCount count = new CommandCount();
        CommandXsdValidate xsdValidate = new CommandXsdValidate();
        CommandDtdValidate dtdValidate = new CommandDtdValidate();

        try {
            JCommander jCommander = JCommander.newBuilder()
                    .addCommand("xsdvalidate", xsdValidate)
                    .addCommand("dtdvalidate", dtdValidate)
                    .addCommand("count", count)
                    .build();
            jCommander.parse(args);
            XMLDTDValidator validator = new XMLDTDValidator();
            if (count.getElementName() != null) {
                int countElements = validator.countElements(count.getElementName(), count.getXmlUri());
                System.out.printf("Number of %s elements is %s", count.getElementName(), countElements);
            } else if (xsdValidate.getXsdUri() != null) {
                boolean isValid = validator.xsdValidate(xsdValidate.getXsdUri(), xsdValidate.getXmlUri());
                validationOutput(isValid);
            } else if (dtdValidate.getXmlUri() != null) {
                boolean isValid = validator.dtdValidate(dtdValidate.getXmlUri());
                validationOutput(isValid);
            } else {
                jCommander.usage();
            }
        } catch (ParameterException e) {
            LOGGER.error(e);
            e.getJCommander().usage();
        }
    }

    private static void validationOutput(boolean validate) {
        System.out.println("Validation is " + validate);
        if (!validate) {
            System.out.println("Look for results in xml_validator.log");
        }
    }
}
