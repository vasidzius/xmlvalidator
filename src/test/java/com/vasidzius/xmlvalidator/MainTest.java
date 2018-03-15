package com.vasidzius.xmlvalidator;

import org.junit.Rule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MainTest {

    @Rule
    public SystemOutResource sysOut = new SystemOutResource();

    @Test
    public void countTest() throws Exception{
        String element = "item";
        String[] args = {
                "count",
                "-element",
                element,
                "-xml",
                "test.xml"
        };
        Main.main(args);
        assertEquals(String.format("Number of %s elements is %s", element, 2),
                sysOut.asString());
    }

    @Test
    public void xsdValidation() throws Exception{
        String[] args = {
                "xsdvalidate",
                "-xsd",
                "test.xsd",
                "-xml",
                "test.xml"
        };
        Main.main(args);
        assertTrue(sysOut.asString().contains("Validation is true"));
    }

    @Test
    public void xsdFailedValidation() throws Exception{
        String[] args = {
                "xsdvalidate",
                "-xsd",
                "test.xsd",
                "-xml",
                "testFailed.xml"
        };
        Main.main(args);
        assertTrue(sysOut.asString().contains("Validation is false"));
        assertTrue(sysOut.asString().contains("Look for results in xml_validator.log"));
    }


    @Test
    public void dtdFailedValidation() throws Exception{
        String[] args = {
                "dtdvalidate",
                "-xml",
                "dtdfailedtest.xml"
        };
        Main.main(args);
        assertTrue(sysOut.asString().contains("Validation is false"));
        assertTrue(sysOut.asString().contains("Look for results in xml_validator.log"));
    }

    @Test
    public void dtdValidate() throws Exception{
        String[] args = {
                "dtdvalidate",
                "-xml",
                "dtdtest.xml"
        };
        Main.main(args);
        assertTrue(sysOut.asString().contains("Validation is true"));
    }


}