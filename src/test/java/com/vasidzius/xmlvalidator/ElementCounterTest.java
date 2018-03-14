package com.vasidzius.xmlvalidator;

import com.vasidzius.xmlvalidator.model.ElementCounter;
import com.vasidzius.xmlvalidator.model.SAXParserProvider;
import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class ElementCounterTest {

    @Test
    public void test() throws URISyntaxException {
        String xml = getClass().getResource("shiporder_count_test.xml").toURI().toString();
        String elementName = "quantity";
        SAXParserProvider parserProvider = new SAXParserProvider();
        ElementCounter counter = new ElementCounter(parserProvider);
        assertEquals(2, counter.count(xml, elementName));
    }

}