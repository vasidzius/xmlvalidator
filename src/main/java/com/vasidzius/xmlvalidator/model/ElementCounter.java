package com.vasidzius.xmlvalidator.model;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;

public class ElementCounter extends AbstractXmlHandler {

    private SAXParserProvider parserProvider;

    public ElementCounter(SAXParserProvider parserProvider) {
        this.parserProvider = parserProvider;
    }

    public int count(String pathToXml, String elementName) {
        final int[] count = {0};
        DefaultHandler handler = getHandler(count, elementName);
        SAXParser parser = parserProvider.getParser(false, false);
        parse(parser, handler, pathToXml);
        return count[0];
    }

    private DefaultHandler getHandler(int[] count, String elementName) {
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
