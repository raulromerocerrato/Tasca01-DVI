/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paucasesnovescifp.tasca01_dvi.i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

/**
 *
 * @author 2007r
 */
public class Idioma {
    public static final Locale LOCALE = new Locale("ca", "ES");
    private static final ResourceBundle BUNDLE =
            ResourceBundle.getBundle("resources.Bundle", LOCALE);

    private Idioma() { }

    public static String get(String clau) {
        try {
            return BUNDLE.getString(clau);
        } catch (MissingResourceException e) {
            return "!" + clau + "!";
        }
    }
}
