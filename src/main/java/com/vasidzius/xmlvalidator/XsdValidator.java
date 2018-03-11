package com.vasidzius.xmlvalidator;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

class XsdValidator {
    private final static Logger LOGGER = Logger.getLogger(XsdValidator.class);

    private String xml;
    private String xsd;

    XsdValidator(String xml, String xsd) {
        this.xml = xml;
        this.xsd = xsd;
    }

    boolean xsdValidate() {
        Schema schema = getXsdSchema();
        Validator validator = schema.newValidator();
        CustomErrorHandler errorHandler = new CustomErrorHandler();
        validator.setErrorHandler(errorHandler);
        Source source = new StreamSource(xml);
        try {
            validator.validate(source);
        } catch (SAXException | IOException e) {
            LOGGER.error("Error during parsing xml occurred", e);
            System.exit(-1);
        }
        return errorHandler.isValidationSuccess();
    }

    private Schema getXsdSchema() {
        try {
            SchemaFactory xsdSchemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            return xsdSchemaFactory.newSchema(new File(xsd).toURI().toURL());
        } catch (MalformedURLException | SAXException e) {
            LOGGER.error("Error during parsing xsd occurred", e);
            System.exit(-1);
        }
        return null;
    }
}
