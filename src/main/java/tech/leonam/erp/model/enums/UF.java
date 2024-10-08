package tech.leonam.erp.model.enums;

import java.security.SecureRandom;

public enum UF {
    AC("AC", "Acre"),
    AL("AL", "Alagoas"),
    AP("AP", "Amapá"),
    AM("AM", "Amazonas"),
    BA("BA", "Bahia"),
    CE("CE", "Ceará"),
    DF("DF", "Distrito Federal"),
    ES("ES", "Espírito Santo"),
    GO("GO", "Goiás"),
    MA("MA", "Maranhão"),
    MT("MT", "Mato Grosso"),
    MS("MS", "Mato Grosso do Sul"),
    MG("MG", "Minas Gerais"),
    PA("PA", "Pará"),
    PB("PB", "Paraíba"),
    PR("PR", "Paraná"),
    PE("PE", "Pernambuco"),
    PI("PI", "Piauí"),
    RJ("RJ", "Rio de Janeiro"),
    RN("RN", "Rio Grande do Norte"),
    RS("RS", "Rio Grande do Sul"),
    RO("RO", "Rondônia"),
    RR("RR", "Roraima"),
    SC("SC", "Santa Catarina"),
    SP("SP", "São Paulo"),
    SE("SE", "Sergipe"),
    TO("TO", "Tocantins");

    private final String sigla;
    private final String extenso;

    UF(String sigla, String extenso) {
        this.sigla = sigla;
        this.extenso = extenso;
    }

    public String getSigla() {
        return sigla;
    }

    public String getExtenso() {
        return extenso;
    }

    public static String estadoAleatorio(){

        SecureRandom random = new SecureRandom();

        UF[] ufs = UF.values();

        int indiceAleatorio = random.nextInt(ufs.length);

        UF ufAleatorio = ufs[indiceAleatorio];

        return ufAleatorio.getSigla();
    }
}
