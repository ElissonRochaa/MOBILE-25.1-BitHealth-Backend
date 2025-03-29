package br.com.bitwise.bithealth.modules.unidade_saude.model.ENUMS;

public enum TipoUnidade {
    UBS("UBS"),
    UPA("UPA"),
    HOSPITAL("Hospital"),
    FARMA("Farmacia");

    private final String tipo;

    TipoUnidade(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    public static TipoUnidade fromString(String roleName) {
        for (TipoUnidade role : TipoUnidade.values()) {
            if (role.tipo.equalsIgnoreCase(roleName)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No enum constant " + TipoUnidade.class.getCanonicalName() + "." + roleName);
    }
}
