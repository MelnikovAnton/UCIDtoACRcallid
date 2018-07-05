package ru.melnikov.converter;

import org.junit.After;
import org.junit.Before;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConverterTest {

    private static Converter converter;

    @BeforeClass
    public static   void init(){
         converter=new Converter();
    }


    @Test
    public void parse() throws UcidFormatException {
        assertEquals("1101185296457599450",converter.parse("03912128491530726874"));
    }

    @Test(expected = UcidFormatException.class)
    public void parseException() throws UcidFormatException{
            converter.parse("03");
    }

    @Test(expected = UcidFormatException.class)
    public void parseACRexception() throws UcidFormatException {
        converter.parseACR("110110");
    }

    @Test
    public void parseACR() throws UcidFormatException {
        assertEquals("03912128551530727538",converter.parseACR("1101185322227403890"));
    }
}