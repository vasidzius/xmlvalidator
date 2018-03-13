package com.vasidzius.xmlvalidator;

class DtdValidator {

    private String xml;
    private SAXParserHolder parserProvider;

    DtdValidator(String xml) {
        this.xml = xml;
        parserProvider = new SAXParserHolder(true, true);
    }

    boolean dtdValidate() {
        CustomErrorHandler handler = new CustomErrorHandler();
        parserProvider.parse(handler, xml);
        return handler.isValidationSuccess();
    }
}
