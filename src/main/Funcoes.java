package main;

import java.util.Scanner;

public class Funcoes {

    public static int lerEntradaInt() {
        Scanner scanner = new java.util.Scanner(System.in);
        int entrada = 0;
        boolean entradaValida = false;

        while (!entradaValida) {
            try {
                entrada = Integer.parseInt(scanner.nextLine());
                entradaValida = true;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, digite um número inteiro.");
            }
        }

        return entrada;
    }

    public static String lerEntradaString() {
        Scanner scanner = new java.util.Scanner(System.in);
        String entrada = "";
        entrada = scanner.nextLine().trim();
        return entrada;
    }

    public static void darEspacoNoConsole() {
        for (int i = 0; i < 10; i++) {
            System.out.print("\n");
        }
    }

    public static int lancarDado() {
        int resultado = (int) (Math.random() * 6) + 1;
        return resultado;
    }

    public static void aguardar(int segundos) {
        try {
            Thread.sleep(segundos * 1000);
        } catch (InterruptedException e) {
        }
    }
}
