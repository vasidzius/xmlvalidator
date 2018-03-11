package com.vasidzius.xmlvalidator;

import org.junit.Test;

import java.net.URISyntaxException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class XsdValidatorTest {

    @Test
    public void successXsdValidation() throws URISyntaxException {
        String pathToXsd = getClass().getResource("shiporder.xsd").getPath();
        String pathToXml = getClass().getResource("shiporder_success.xml").getPath();
        XsdValidator validator = new XsdValidator(pathToXml, pathToXsd);
        assertTrue(validator.xsdValidate());
    }

    @Test
    public void failedXsdValidation() {
        String pathToXsd = getClass().getResource("shiporder.xsd").getPath();
        String pathToXml = getClass().getResource("shiporder_failed.xml").getPath();
        XsdValidator validator = new XsdValidator(pathToXml, pathToXsd);
        assertFalse(validator.xsdValidate());
    }
}