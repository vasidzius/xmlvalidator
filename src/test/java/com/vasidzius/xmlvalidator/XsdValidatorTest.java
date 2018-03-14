package com.vasidzius.xmlvalidator;

import com.vasidzius.xmlvalidator.model.XsdValidator;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class XsdValidatorTest {

    @Test
    public void successXsdValidation() {
        String pathToXsd = getClass().getResource("shiporder.xsd").getPath();
        String pathToXml = getClass().getResource("shiporder_success.xml").getPath();
        XsdValidator validator = new XsdValidator();
        assertTrue(validator.xsdValidate(pathToXml, pathToXsd));
    }

    @Test
    public void failedXsdValidation() {
        String pathToXsd = getClass().getResource("shiporder.xsd").getPath();
        String pathToXml = getClass().getResource("shiporder_failed.xml").getPath();
        XsdValidator validator = new XsdValidator();
        assertFalse(validator.xsdValidate(pathToXml, pathToXsd));
    }
}