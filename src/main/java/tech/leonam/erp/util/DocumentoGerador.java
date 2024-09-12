package tech.leonam.erp.util;

import java.util.Random;

public class DocumentoGerador {

    private static final Random random = new Random();

    // Método para gerar um CPF válido
    public static String gerarCPF() {
        int[] cpf = new int[11];

        // Gera os 9 primeiros dígitos aleatórios
        for (int i = 0; i < 9; i++) {
            cpf[i] = random.nextInt(10);
        }

        // Calcula o 10º dígito (primeiro dígito verificador)
        int primeiroDigito = calcularDigito(cpf, 9, 10);
        cpf[9] = primeiroDigito;

        // Calcula o 11º dígito (segundo dígito verificador)
        int segundoDigito = calcularDigito(cpf, 10, 11);
        cpf[10] = segundoDigito;

        return formatarCPF(cpf);
    }

    // Método para calcular o dígito verificador do CPF
    private static int calcularDigito(int[] cpf, int tamanho, int peso) {
        int soma = 0;
        for (int i = 0; i < tamanho; i++) {
            soma += cpf[i] * peso--;
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    // Método para formatar o CPF
    private static String formatarCPF(int[] cpf) {
        return String.format("%d%d%d.%d%d%d.%d%d%d-%d%d",
                cpf[0], cpf[1], cpf[2],
                cpf[3], cpf[4], cpf[5],
                cpf[6], cpf[7], cpf[8],
                cpf[9], cpf[10]);
    }

    // Método para gerar um CNPJ válido
    public static String gerarCNPJ() {
        int[] cnpj = new int[14];

        // Gera os 12 primeiros dígitos aleatórios
        for (int i = 0; i < 12; i++) {
            cnpj[i] = random.nextInt(10);
        }

        // Calcula os 13º e 14º dígitos (dígitos verificadores)
        int primeiroDigito = calcularDigitoCNPJ(cnpj, 12, 5);
        cnpj[12] = primeiroDigito;

        int segundoDigito = calcularDigitoCNPJ(cnpj, 13, 6);
        cnpj[13] = segundoDigito;

        return formatarCNPJ(cnpj);
    }

    // Método para calcular o dígito verificador do CNPJ
    private static int calcularDigitoCNPJ(int[] cnpj, int tamanho, int pesoInicial) {
        int soma = 0;
        int peso = pesoInicial;
        for (int i = 0; i < tamanho; i++) {
            soma += cnpj[i] * peso;
            peso--;
            if (peso < 2) {
                peso = 9;
            }
        }
        int resto = soma % 11;
        return resto < 2 ? 0 : 11 - resto;
    }

    // Método para formatar o CNPJ
    private static String formatarCNPJ(int[] cnpj) {
        return String.format("%d%d%d.%d%d%d/%d%d%d%d-%d%d",
                cnpj[0], cnpj[1], cnpj[2],
                cnpj[3], cnpj[4], cnpj[5],
                cnpj[6], cnpj[7], cnpj[8], cnpj[9],
                cnpj[10], cnpj[11]);
    }

}
