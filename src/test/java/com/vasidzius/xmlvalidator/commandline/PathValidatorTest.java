package com.vasidzius.xmlvalidator.commandline;

import org.junit.Test;

public class PathValidatorTest {

    @Test(expected = IllegalArgumentException.class)
    public void testNotExist() {
        PathValidator validator = new PathValidator();
        validator.validate("-xml", "notexistfile");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFolder() {
        PathValidator validator = new PathValidator();
        validator.validate("-xml", "java");
    }

}