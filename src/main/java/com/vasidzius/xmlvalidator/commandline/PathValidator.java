package com.vasidzius.xmlvalidator.commandline;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.ParameterException;

import java.io.File;

public class PathValidator implements IParameterValidator {
    @Override
    public void validate(String name, String value) throws ParameterException {
        File file = new File(value);
        if (!file.exists()) {
            throw new IllegalArgumentException(String.format("file %s doesn't exist", value));
        }
        if (!file.isFile()) {
            throw new IllegalArgumentException(String.format("file %s is Directory", value));
        }
    }
}
