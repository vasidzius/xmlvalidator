package com.vasidzius.xmlvalidator;

import com.vasidzius.xmlvalidator.api.XMLDTDValidator;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ThreadSafetyTest {

    @Test
    public void test() throws InterruptedException {
        int nThreads = 90;
        XMLDTDValidator validator = new XMLDTDValidator();
        Thread[] threads = new Thread[nThreads];
        for (int i = 0; i < nThreads / 3; i++) {
            threads[i] = new Thread(() -> {
                try {
                    assertTrue(validator.dtdValidate(getClass().getResource("note_success.xml").getPath()));
                } catch (IOException | SAXException e) {
                    e.printStackTrace();
                }
            });
        }

        for (int i = nThreads / 3; i < 2 * nThreads / 3; i++) {
            threads[i] = new Thread(() -> {
                try {
                    String pathToXsd = getClass().getResource("shiporder.xsd").getPath();
                    String pathToXml = getClass().getResource("shiporder_success.xml").getPath();
                    assertTrue(validator.xsdValidate(pathToXsd, pathToXml));
                } catch (IOException | SAXException e) {
                    e.printStackTrace();
                }
            });
        }

        for (int i = 2 * nThreads / 3; i < nThreads; i++) {
            threads[i] = new Thread(() -> {
                try {
                    String pathToXml = getClass().getResource("shiporder_success.xml").getPath();
                    String elementName = "item";
                    assertEquals(2, validator.countElements(elementName, pathToXml));
                } catch (IOException | SAXException e) {
                    e.printStackTrace();
                }
            });
        }
        for (Thread thread : threads) {
            thread.start();
        }
        for (Thread thread : threads) {
            thread.join();
        }

    }
}
