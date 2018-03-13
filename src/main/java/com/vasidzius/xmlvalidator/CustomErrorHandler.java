package com.vasidzius.xmlvalidator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class CustomErrorHandler extends DefaultHandler {

    private final static Logger LOGGER = Logger.getLogger(CustomErrorHandler.class);

    private boolean isValidationSuccess = true;

    boolean isValidationSuccess() {
        return isValidationSuccess;
    }

    @Override
    public void warning(SAXParseException exception) {
        isValidationSuccess = false;
        LOGGER.info("WARNING: " + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) {
        isValidationSuccess = false;
        LOGGER.info("ERROR: " + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) {
        isValidationSuccess = false;
        LOGGER.info("FATAL ERROR: " + exception.getMessage());
    }
}
