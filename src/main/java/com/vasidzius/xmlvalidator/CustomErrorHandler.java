package com.vasidzius.xmlvalidator;

import org.apache.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

public class CustomErrorHandler implements ErrorHandler {

    private final static Logger LOGGER = Logger.getLogger(CustomErrorHandler.class);

    private boolean isValidationSuccess = true;

    boolean isValidationSuccess() {
        return isValidationSuccess;
    }

    @Override
    public void warning(SAXParseException exception) throws SAXException {
        isValidationSuccess = false;
        LOGGER.info("WARNING: " + exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        isValidationSuccess = false;
        LOGGER.info("ERROR: " + exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        isValidationSuccess = false;
        LOGGER.info("FATAL ERROR: " + exception.getMessage());
    }
}
