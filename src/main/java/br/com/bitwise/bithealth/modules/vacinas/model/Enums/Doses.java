package br.com.bitwise.bithealth.modules.vacinas.model.Enums;

public enum Doses {
    UNICA("Dose única"),
    PRIMEIRA("1ª Dose"),
    SEGUNDA("2ª Dose"),
    TERCEIRA("3ª Dose"),
    REFORCO("Reforço");


    private final String tipo;

    Doses(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static Doses fromString(String texto) {
        for (Doses dose : Doses.values()) {
            if (dose.tipo.equalsIgnoreCase(texto)) {
                return dose;
            }
        }
        throw new IllegalArgumentException("Nenhuma constante enum " + Doses.class.getCanonicalName() + " com o tipo " + texto);
    }
}
