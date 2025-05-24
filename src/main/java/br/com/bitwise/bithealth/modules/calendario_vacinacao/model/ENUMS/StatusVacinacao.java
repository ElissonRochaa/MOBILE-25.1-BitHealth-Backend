package br.com.bitwise.bithealth.modules.calendario_vacinacao.model.ENUMS;

public enum StatusVacinacao {
    ANDAMENTO("Em Andamento"),
    EMBREVE("Em Breve"),
    REALIZADO("Realizado");

    private final String tipo;

    StatusVacinacao(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static StatusVacinacao fromString(String roleName) {
        for (StatusVacinacao tipo : StatusVacinacao.values()) {
            if (tipo.tipo.equalsIgnoreCase(roleName)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("No enum constant " + StatusVacinacao.class.getCanonicalName() + "." + roleName);
    }
}
