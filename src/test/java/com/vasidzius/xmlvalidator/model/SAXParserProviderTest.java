package com.vasidzius.xmlvalidator.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.LocatorImpl;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;

import static org.powermock.api.mockito.PowerMockito.*;

@RunWith(PowerMockRunner.class)
@PrepareForTest(SAXParserFactory.class)
public class SAXParserProviderTest {

    @Test(expected = RuntimeException.class)
    public void getParser() throws ParserConfigurationException, SAXException {
        PowerMockito.mockStatic(SAXParserFactory.class);

        SAXParserFactory mockedFactory = mock(SAXParserFactory.class);
        doThrow(new SAXParseException("", new LocatorImpl())).when(mockedFactory).newSAXParser();
        when(SAXParserFactory.newInstance()).thenReturn(mockedFactory);

        SAXParserProvider saxParserProvider = new SAXParserProvider();
        saxParserProvider.getParser(true, true);
    }
}