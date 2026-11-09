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

    public static final Locale LOCALE = Locale.of("ca", "ES");
    private static final ResourceBundle BUNDLE =
            ResourceBundle.getBundle("Bundle", LOCALE);

    private Idioma() { }

    public static String get(String clau) {
        return llegir(clau);
    }

    public static String get(String clau, Object... params) {
        return MessageFormat.format(llegir(clau), params);
    }

    private static String llegir(String clau) {
        try {
            return BUNDLE.getString(clau);
        } catch (MissingResourceException e) {
            return "!" + clau + "!";
        }
    }
}
