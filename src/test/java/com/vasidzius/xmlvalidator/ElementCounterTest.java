package com.vasidzius.xmlvalidator;

import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertEquals;

public class ElementCounterTest {

    @Test
    public void test() throws URISyntaxException {
        String xml = getClass().getResource("shiporder_count_test.xml").toURI().toString();
        String elementName = "quantity";
        ElementCounter counter = new ElementCounter(xml, elementName);
        assertEquals(2, counter.count());
    }

}