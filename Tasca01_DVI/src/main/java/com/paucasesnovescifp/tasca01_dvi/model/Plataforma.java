/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.paucasesnovescifp.tasca01_dvi.model;

/**
 *
 * @author 2007r
 */
public enum Plataforma {
    PS5("PS5"), XBOX("Xbox"), PC("PC"), SWITCH_2("Switch 2");

    private final String nom;

    Plataforma(String nom) { this.nom = nom; }

    @Override
    public String toString() { return nom; }
}
