import java.util.Scanner;

public class Practice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int casos = sc.nextInt();
        sc.nextLine(); // Consumir resto de la línea de "casos"

        for (int caso = 0; caso < casos; caso++) {

            int n = sc.nextInt(); // Estados
            sc.nextLine(); // Consumir resto de la línea de "n"

            // Alfabeto (tamaño variable)
            String[] alfabeto = sc.nextLine().trim().split("\\s+");
            int m = alfabeto.length;

            boolean[] finales = new boolean[n];

            // Finales
            String lineaFinales = sc.nextLine().trim();
            if (!lineaFinales.isEmpty()) {
                String[] estadosFinales = lineaFinales.split("\\s+");
                for (String estado : estadosFinales) {
                    int estadoFinal = Integer.parseInt(estado);
                    finales[estadoFinal] = true;
                }
            }

            // ransiciones
            int[][] transiciones = new int[n][m];

            for (int i = 0; i < n; i++) {
                sc.nextInt();
                for (int j = 0; j < m; j++) {
                    transiciones[i][j] = sc.nextInt();
                }
            }
            sc.nextLine(); // Consumir el salto de línea tras la última transición

            // Marcados
            boolean[][] marcados = new boolean[n][n];

            // Primera pasada
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (finales[i] != finales[j]) {
                        marcados[i][j] = true;
                    }
                }
            }

            // Fases^2
            boolean cambio = true;

            while (cambio) {
                cambio = false;

                for (int i = 0; i < n; i++) {
                    for (int j = i + 1; j < n; j++) {

                        if (marcados[i][j]) {
                            continue;
                        }

                        for (int k = 0; k < m; k++) {

                            int destinoI = transiciones[i][k];
                            int destinoJ = transiciones[j][k];

                            int menor = Math.min(destinoI, destinoJ);
                            int mayor = Math.max(destinoI, destinoJ);

                            if (marcados[menor][mayor]) {
                                marcados[i][j] = true;
                                cambio = true;
                                break;
                            }
                        }
                    }
                }
            }

            // Imprimir pares equivalentes
            boolean primero = true;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (!marcados[i][j]) {
                        if (!primero) {
                            System.out.print(" ");
                        }
                        System.out.print("(" + i + ", " + j + ")");
                        primero = false;
                    }
                }
            }

            System.out.println();
        }

        sc.close();
    }
}
