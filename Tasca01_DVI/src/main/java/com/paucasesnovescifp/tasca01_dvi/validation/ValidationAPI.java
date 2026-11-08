/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paucasesnovescifp.tasca01_dvi.validation;

/**
 *
 * @author 2007r
 */
public final class ValidationAPI {
    private ValidationAPI() { }

    public static boolean esBuit(String text) {
        return text == null || text.trim().isEmpty();
    }

    public static boolean comencaPerMajuscula(String text) {
        if (esBuit(text)) return false;
        return Character.isUpperCase(text.trim().charAt(0));
    }

    public static boolean esDniValid(String dni) {
        return dni != null && dni.trim().matches("\\d{8}[A-Za-z]");
    }

    public static boolean esTelefonValid(String telefon) {
        return telefon != null && telefon.trim().matches("\\d{9}");
    }

    public static String validarNom(String nom) {
        if (esBuit(nom)) return "validacio.camp.obligatori";
        if (!comencaPerMajuscula(nom)) return "validacio.majuscula";
        return null;
    }

    public static String validarDni(String dni) {
        if (esBuit(dni)) return "validacio.camp.obligatori";
        if (!esDniValid(dni)) return "validacio.dni.invalid";
        return null;
    }

    public static String validarTelefon(String telefon) {
        if (esBuit(telefon)) return "validacio.camp.obligatori";
        if (!esTelefonValid(telefon)) return "validacio.telefon.invalid";
        return null;
    }
}
