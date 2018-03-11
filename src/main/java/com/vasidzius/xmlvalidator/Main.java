package com.vasidzius.xmlvalidator;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import com.vasidzius.xmlvalidator.commandline.CommandCount;
import com.vasidzius.xmlvalidator.commandline.CommandDtdValidate;
import com.vasidzius.xmlvalidator.commandline.CommandXsdValidate;

public class Main {

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
            if (count.getElementName() != null) {
                ElementCounter counter = new ElementCounter(count.getXmlUri(), count.getElementName());
                System.out.printf("Number of %s elements is %s", count.getElementName(), counter.count());
            } else if (xsdValidate.getXsdUri() != null) {
                XsdValidator validator = new XsdValidator(xsdValidate.getXmlUri(), xsdValidate.getXsdUri());
                boolean validate = validator.xsdValidate();
                validationOutput(validate);
            } else if (dtdValidate.getXmlUri() != null) {
                DtdValidator validator = new DtdValidator(dtdValidate.getXmlUri());
                boolean validate = validator.dtdValidate();
                validationOutput(validate);
            } else {
                jCommander.usage();
            }
        } catch (ParameterException e) {
            e.printStackTrace();
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
