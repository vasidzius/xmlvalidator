package com.vasidzius.xmlvalidator;

import com.vasidzius.xmlvalidator.api.XMLDTDValidator;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class APITest {

    @Test
    @Parameters
    public void xsdValidate(String pathToXsd, String pathToXml, boolean expectedResult) {
        XMLDTDValidator validator = new XMLDTDValidator();
        boolean actualResult = validator.xsdValidate(pathToXsd, pathToXml);
        assertEquals(expectedResult, actualResult);
    }

    private Object[] parametersForXsdValidate() {
        return new Object[]{
                new Object[]{
                        getClass().getResource("shiporder.xsd").getPath(),
                        getClass().getResource("shiporder_success.xml").getPath(),
                        true},
                new Object[]{
                        getClass().getResource("shiporder.xsd").getPath(),
                        getClass().getResource("shiporder_failed.xml").getPath(),
                        false}
        };
    }

    @Test
    @Parameters
    public void dtdValidate(String pathToXml, boolean expectedResult) {
        XMLDTDValidator validator = new XMLDTDValidator();
        boolean actualResult = validator.dtdValidate(pathToXml);
        assertEquals(expectedResult, actualResult);
    }

    private Object[] parametersForDtdValidate() {
        return new Object[]{
                new Object[]{
                        getClass().getResource("note_success.xml").getPath(),
                        true},
                new Object[]{
                        getClass().getResource("note_failed.xml").getPath(),
                        false}
        };
    }

    @Test
    @Parameters
    public void countElements(String elementName, String pathToXml, int expectedResult) {
        XMLDTDValidator validator = new XMLDTDValidator();
        int count = validator.countElements(elementName, pathToXml);
        assertEquals(expectedResult, count);
    }

    private Object[] parametersForCountElements() {
        return new Object[]{
                new Object[]{"price", getClass().getResource("shiporder_success.xml").getPath(), 2},
                new Object[]{"note", getClass().getResource("shiporder_success.xml").getPath(), 1}
        };
    }
}
