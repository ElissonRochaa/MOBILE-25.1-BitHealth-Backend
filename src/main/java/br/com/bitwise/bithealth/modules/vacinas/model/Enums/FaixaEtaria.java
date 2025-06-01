package br.com.bitwise.bithealth.modules.vacinas.model.Enums;

public enum FaixaEtaria {

    CRIANCA("Crianças"),
    ADOLESCENTE("Adolescentes"),
    ADULTO("Adulto e Idoso"),
    GESTANTE("Gestante");

    private final String tipos;

    FaixaEtaria(String tipos) {
        this.tipos = tipos;
    }

    public String getTipos() {
        return tipos;
    }

    public static FaixaEtaria getFaixaEtaria(String tipo) {
        for (FaixaEtaria faixaEtaria : values()) {
            if (faixaEtaria.getTipos().equals(tipo)) {
                return faixaEtaria;
            }
        }
        throw new IllegalArgumentException("Nenhuma constante enum" + FaixaEtaria.class.getCanonicalName());
    }

}
