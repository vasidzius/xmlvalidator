package com.vasidzius.xmlvalidator;

import org.junit.Test;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DtdValidatorTest {

    @Test
    public void successDtdValidation() throws IOException, ParserConfigurationException {
        String pathToXml = getClass().getResource("note_success.xml").getPath();
        DtdValidator validator = new DtdValidator(pathToXml);
        assertTrue(validator.dtdValidate());
    }

    @Test
    public void failedDtdValidation() throws IOException, ParserConfigurationException {
        String pathToXml = getClass().getResource("note_failed.xml").getPath();
        DtdValidator validator = new DtdValidator(pathToXml);
        assertFalse(validator.dtdValidate());
    }

}