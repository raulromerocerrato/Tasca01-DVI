/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.paucasesnovescifp.tasca01_dvi.dades;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.paucasesnovescifp.tasca01_dvi.model.*;
/**
 *
 * @author 2007r
 */
public class GestorDades {
    private final List<Usuari> usuaris = new ArrayList<>();
    private final List<Joc> jocs = new ArrayList<>();
    private final List<Lloguer> lloguers = new ArrayList<>();
    private int properNumRegistre = 1;

    public GestorDades() {
        carregarDadesInicials();
    }

    public List<Usuari> getUsuaris() { return new ArrayList<>(usuaris); }

    public List<Joc> getJocs() { return new ArrayList<>(jocs); }
    
    public List<Lloguer> getLloguers() {
        List<Lloguer> copia = new ArrayList<>(lloguers);
        copia.sort(Comparator.comparingInt(Lloguer::getNumRegistre).reversed());
        return copia;
    }

    public List<Lloguer> getLloguersActius() {
        List<Lloguer> actius = new ArrayList<>();
        for (Lloguer l : getLloguers()) {
            if (l.isActiu()) actius.add(l);
        }
        return actius;
    }

    public int getTotalLloguers() { return lloguers.size(); }

    public int getProperNumRegistre() { return properNumRegistre; }

    public boolean usuariTeLloguersActius(Usuari u) {
        for (Lloguer l : lloguers) {
            if (l.isActiu() && l.getUsuari() == u) return true;
        }
        return false;
    }

    public boolean jocTeLloguersActius(Joc j) {
        for (Lloguer l : lloguers) {
            if (l.isActiu() && l.getJoc() == j) return true;
        }
        return false;
    }

    public void afegirUsuari(Usuari u) { usuaris.add(u); }

    public void afegirJoc(Joc j) { jocs.add(j); }

    public void eliminarUsuari(Usuari u) {
        if (usuariTeLloguersActius(u)) {
            throw new IllegalStateException("error.usuari.lloguers.actius");
        }
        usuaris.remove(u);
    }

    public void eliminarJoc(Joc j) {
        if (jocTeLloguersActius(j)) {
            throw new IllegalStateException("error.joc.lloguers.actius");
        }
        jocs.remove(j);
    }

    public Lloguer registrarLloguer(Usuari u, Joc j, Plataforma p) {
        if (u == null) throw new IllegalArgumentException("error.sense.usuari");
        if (j == null) throw new IllegalArgumentException("error.sense.joc");
        if (p == null) throw new IllegalArgumentException("error.sense.plataforma");
        if (!j.treureUnitat(p)) {
            throw new IllegalStateException("error.sense.estoc");
        }
        Lloguer l = new Lloguer(properNumRegistre++, u, j, p, LocalDate.now());
        lloguers.add(l);
        return l;
    }

    public void tornarLloguer(Lloguer l) {
        if (l.isRetornat()) {
            throw new IllegalStateException("error.lloguer.ja.retornat");
        }
        l.marcarRetornat();
        l.getJoc().tornarUnitat(l.getPlataforma());
    }

    private void carregarDadesInicials() {
        Usuari marc = new Usuari("Marc", "Rosselló", "Pons", "12345678A", "600111222");
        Usuari laura = new Usuari("Laura", "García", "Soler", "87654321B", "600333444");
        usuaris.add(marc);
        usuaris.add(laura);

        Joc minecraft = new Joc("Minecraft", "Aventura");
        minecraft.setUnitats(Plataforma.PS5, 2);
        minecraft.setUnitats(Plataforma.SWITCH_2, 2);

        Joc cyberpunk = new Joc("Cyberpunk 2077", "Acció");
        cyberpunk.setUnitats(Plataforma.XBOX, 2);
        cyberpunk.setUnitats(Plataforma.PC, 2);

        jocs.add(minecraft);
        jocs.add(cyberpunk);

        minecraft.treureUnitat(Plataforma.SWITCH_2);
        lloguers.add(new Lloguer(properNumRegistre++, marc, minecraft,
                Plataforma.SWITCH_2, LocalDate.now().minusDays(20)));

        Lloguer retornat = new Lloguer(properNumRegistre++, laura, cyberpunk,
                Plataforma.PC, LocalDate.now().minusDays(10));
        retornat.marcarRetornat();
        lloguers.add(retornat);
    }
}
