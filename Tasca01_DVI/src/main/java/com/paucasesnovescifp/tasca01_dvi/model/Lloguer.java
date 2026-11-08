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
public class Lloguer {
    public static final int DIES_LLOGUER = 7;

    private final int numRegistre;
    private final Usuari usuari;
    private final Joc joc;
    private final Plataforma plataforma;
    private final LocalDate dataInici;
    private final LocalDate dataPrevista;
    private boolean retornat;
    private LocalDate dataRetornada;

    public Lloguer(int numRegistre, Usuari usuari, Joc joc,
                   Plataforma plataforma, LocalDate dataInici) {
        this.numRegistre = numRegistre;
        this.usuari = usuari;
        this.joc = joc;
        this.plataforma = plataforma;
        this.dataInici = dataInici;
        this.dataPrevista = dataInici.plusDays(DIES_LLOGUER);
    }

    public int getNumRegistre() { return numRegistre; }
    public Usuari getUsuari() { return usuari; }
    public Joc getJoc() { return joc; }
    public Plataforma getPlataforma() { return plataforma; }
    public LocalDate getDataInici() { return dataInici; }
    public LocalDate getDataPrevista() { return dataPrevista; }
    public boolean isRetornat() { return retornat; }
    public LocalDate getDataRetornada() { return dataRetornada; }

    public boolean isActiu() { return !retornat; }

    public boolean isForaDeTermini() {
        return !retornat && LocalDate.now().isAfter(dataPrevista);
    }

    public void marcarRetornat() {
        this.retornat = true;
        this.dataRetornada = LocalDate.now();
    }
}
