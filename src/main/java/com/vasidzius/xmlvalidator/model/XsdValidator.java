package com.vasidzius.xmlvalidator.model;

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

public class XsdValidator {

    public boolean xsdValidate(String pathToXml, String pathToXsd) throws IOException, SAXException {
        Schema schema = getXsdSchema(pathToXsd);
        Validator validator = schema.newValidator();
        CustomErrorHandler errorHandler = new CustomErrorHandler();
        validator.setErrorHandler(errorHandler);
        Source source = new StreamSource(pathToXml);
        validate(validator, source);
        return errorHandler.isValidationSuccess();
    }

    private void validate(Validator validator, Source source) throws IOException, SAXException {
            validator.validate(source);
    }

    private Schema getXsdSchema(String pathToXsd) throws MalformedURLException, SAXException {
            SchemaFactory xsdSchemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            return xsdSchemaFactory.newSchema(new File(pathToXsd).toURI().toURL());
    }
}
