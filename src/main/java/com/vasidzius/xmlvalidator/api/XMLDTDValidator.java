package com.vasidzius.xmlvalidator.api;

import com.vasidzius.xmlvalidator.model.DtdValidator;
import com.vasidzius.xmlvalidator.model.ElementCounter;
import com.vasidzius.xmlvalidator.model.SAXParserProvider;
import com.vasidzius.xmlvalidator.model.XsdValidator;
import org.xml.sax.SAXException;

import java.io.IOException;

public class XMLDTDValidator {

    private final XsdValidator xsdValidator;
    private final DtdValidator dtdValidator;
    private final ElementCounter elementCounter;

    public XMLDTDValidator() {
        xsdValidator = new XsdValidator();
        SAXParserProvider parserProvider = new SAXParserProvider();
        dtdValidator = new DtdValidator(parserProvider);
        elementCounter = new ElementCounter(parserProvider);
    }

    public boolean xsdValidate(String pathToXsd, String pathToXml) throws IOException, SAXException {
        return xsdValidator.xsdValidate(pathToXml, pathToXsd);
    }

    public boolean dtdValidate(String pathToXml) throws IOException, SAXException {
        return dtdValidator.dtdValidate(pathToXml);
    }

    public int countElements(String elementName, String pathToXml) throws IOException, SAXException {
        return elementCounter.count(pathToXml, elementName);
    }
}
