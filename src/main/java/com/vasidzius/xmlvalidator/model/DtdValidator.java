package com.vasidzius.xmlvalidator.model;

import javax.xml.parsers.SAXParser;

public class DtdValidator extends AbstractXmlHandler {

    private SAXParserProvider parserProvider;

    public DtdValidator(SAXParserProvider parserProvider) {
        this.parserProvider = parserProvider;
    }

    public boolean dtdValidate(String pathToXml) {
        CustomErrorHandler handler = new CustomErrorHandler();
        SAXParser parser = parserProvider.getParser(true, true);
        parse(parser, handler, pathToXml);
        return handler.isValidationSuccess();
    }
}
