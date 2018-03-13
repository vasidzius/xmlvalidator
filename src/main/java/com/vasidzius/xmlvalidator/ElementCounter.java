package com.vasidzius.xmlvalidator;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

class ElementCounter {

    private String xml;
    private String elementName;
    private SAXParserHolder parserProvider;

    ElementCounter(String xmlUri, String elementName) {
        this.elementName = elementName;
        this.xml = xmlUri;
        parserProvider = new SAXParserHolder(false, false);
    }

    int count() {
        final int[] count = {0};
        DefaultHandler handler = getHandler(count);
        parserProvider.parse(handler, xml);
        return count[0];
    }

    private DefaultHandler getHandler(int[] count) {
        return new DefaultHandler() {
            @Override
            public void startElement(String uri, String localName, String qName, Attributes attributes) {
                if (qName.equals(elementName)) {
                    count[0]++;
                }
            }
        };
    }
}
