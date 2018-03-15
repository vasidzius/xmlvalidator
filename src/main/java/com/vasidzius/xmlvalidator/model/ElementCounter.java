package com.vasidzius.xmlvalidator.model;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.SAXParser;
import java.io.IOException;

public class ElementCounter {

    private SAXParserProvider parserProvider;

    public ElementCounter(SAXParserProvider parserProvider) {
        this.parserProvider = parserProvider;
    }

    public int count(String pathToXml, String elementName) throws IOException, SAXException {
        final int[] count = {0};
        DefaultHandler handler = getHandler(count, elementName);
        SAXParser parser = parserProvider.getParser(false, false);
        parser.parse(pathToXml, handler);
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
