/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paucasesnovescifp.tasca01_dvi.model;

import java.time.LocalDate;

/**
 *
 * @author 2007r
 */
public class Usuari {
    private final String nom;
    private final String llinatge1;
    private final String llinatge2;
    private final String dni;
    private final String telefon;
    private final LocalDate dataRegistre;

    public Usuari(String nom, String llinatge1, String llinatge2, String dni, String telefon) {
        this.nom = nom;
        this.llinatge1 = llinatge1;
        this.llinatge2 = llinatge2;
        this.dni = dni;
        this.telefon = telefon;
        this.dataRegistre = LocalDate.now();
    }

    public String getNom() { return nom; }
    public String getLlinatge1() { return llinatge1; }
    public String getLlinatge2() { return llinatge2; }
    public String getDni() { return dni; }
    public String getTelefon() { return telefon; }
    public LocalDate getDataRegistre() { return dataRegistre; }

    public String getNomComplet() {
        return nom + " " + llinatge1 + " " + llinatge2;
    }

    @Override
    public String toString() { return getNomComplet(); }
}
