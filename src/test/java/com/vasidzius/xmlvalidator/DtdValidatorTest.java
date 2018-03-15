package com.vasidzius.xmlvalidator;

import com.vasidzius.xmlvalidator.model.DtdValidator;
import com.vasidzius.xmlvalidator.model.SAXParserProvider;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DtdValidatorTest {

    @Test
    public void successDtdValidation() throws Exception {
        String pathToXml = getClass().getResource("note_success.xml").getPath();
        SAXParserProvider parserProvider = new SAXParserProvider();
        DtdValidator validator = new DtdValidator(parserProvider);
        assertTrue(validator.dtdValidate(pathToXml));
    }

    @Test
    public void failedDtdValidation() throws Exception{
        String pathToXml = getClass().getResource("note_failed.xml").getPath();
        SAXParserProvider parserProvider = new SAXParserProvider();
        DtdValidator validator = new DtdValidator(parserProvider);
        assertFalse(validator.dtdValidate(pathToXml));
    }

}