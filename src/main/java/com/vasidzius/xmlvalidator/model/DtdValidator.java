package com.vasidzius.xmlvalidator.model;

import org.xml.sax.SAXException;

import javax.xml.parsers.SAXParser;
import java.io.IOException;

public class DtdValidator {

    private SAXParserProvider parserProvider;

    public DtdValidator(SAXParserProvider parserProvider) {
        this.parserProvider = parserProvider;
    }

    public boolean dtdValidate(String pathToXml) throws IOException, SAXException{
        CustomErrorHandler handler = new CustomErrorHandler();
        SAXParser parser = parserProvider.getParser(true, true);
        parser.parse(pathToXml, handler);
        return handler.isValidationSuccess();
    }
}
