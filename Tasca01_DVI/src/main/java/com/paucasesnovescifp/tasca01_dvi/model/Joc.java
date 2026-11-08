/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paucasesnovescifp.tasca01_dvi.model;
import java.util.EnumMap;
import java.util.Map;

/**
 *
 * @author 2007r
 */
public class Joc {
    private final String titol;
    private final String genere;
    private final Map<Plataforma, Integer> unitats = new EnumMap<>(Plataforma.class);

    public Joc(String titol, String genere) {
        this.titol = titol;
        this.genere = genere;
    }

    public String getTitol() { return titol; }
    public String getGenere() { return genere; }

    public void setUnitats(Plataforma p, int quantitat) {
        unitats.put(p, quantitat);
    }

    public int getUnitatsDisponibles(Plataforma p) {
        return unitats.getOrDefault(p, 0);
    }

    public boolean teStock(Plataforma p) {
        return getUnitatsDisponibles(p) > 0;
    }

    public boolean treureUnitat(Plataforma p) {
        if (!teStock(p)) return false;
        unitats.put(p, getUnitatsDisponibles(p) - 1);
        return true;
    }

    public void tornarUnitat(Plataforma p) {
        unitats.put(p, getUnitatsDisponibles(p) + 1);
    }

    @Override
    public String toString() { return titol; }
}

