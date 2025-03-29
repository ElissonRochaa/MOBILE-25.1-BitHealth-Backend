package br.com.bitwise.bithealth.modules.medicamentos.model.ENUMS;


public enum TipoMedicamentoEnum {
    ORIGINAL("Original"),
    GENERICO("Generico");

    private final String tipo;

    TipoMedicamentoEnum(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static TipoMedicamentoEnum fromString(String roleName) {
        for (TipoMedicamentoEnum tipo : TipoMedicamentoEnum.values()) {
            if (tipo.tipo.equalsIgnoreCase(roleName)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("No enum constant " + TipoMedicamentoEnum.class.getCanonicalName() + "." + roleName);
    }
}
