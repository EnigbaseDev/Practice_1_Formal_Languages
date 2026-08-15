import java.util.Scanner;

public class Practice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder salidaTotal = new StringBuilder();

        // Si no llega un entero inicial, no hay entrada válida.
        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }

        int casos = sc.nextInt();
        sc.nextLine(); // consumir el resto de la línea de "casos"

        for (int caso = 0; caso < casos; caso++) {
            int n = sc.nextInt(); // cantidad de estados
            sc.nextLine(); // consumir el resto de la línea de "n"

            // alfabeto (tamaño variable)
            String[] alfabeto = sc.nextLine().trim().split("\\s+");
            int m = alfabeto.length;

            boolean[] finales = new boolean[n];

            // estados finales
            String lineaFinales = sc.nextLine().trim();
            if (!lineaFinales.isEmpty()) {
                String[] estadosFinales = lineaFinales.split("\\s+");
                for (String estado : estadosFinales) {
                    int estadoFinal = Integer.parseInt(estado);
                    finales[estadoFinal] = true;
                }
            }

            // transiciones: para cada estado y cada símbolo
            int[][] transiciones = new int[n][m];

            for (int i = 0; i < n; i++) {
                sc.nextInt(); // el estado actual
                for (int j = 0; j < m; j++) {
                    transiciones[i][j] = sc.nextInt();
                }
            }

            // consumimos el salto de línea si existe para evitar mezclar el siguiente caso
            if (sc.hasNextLine()) {
                sc.nextLine();
            }

            // matriz de pares marcados como no equivalentes
            boolean[][] marcados = new boolean[n][n];

            // primera pasada: si uno es final y el otro no, ya son distinguibles
            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (finales[i] != finales[j]) {
                        marcados[i][j] = true;
                    }
                }
            }

            // algoritmo de llenado de tabla
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

            // imprimir pares equivalentes
            StringBuilder lineaSalida = new StringBuilder();
            boolean primero = true;

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (!marcados[i][j]) {
                        if (!primero) {
                            lineaSalida.append(" ");
                        }
                        lineaSalida.append("(").append(i).append(", ").append(j).append(")");
                        primero = false;
                    }
                }
            }

            salidaTotal.append(lineaSalida).append(System.lineSeparator());
        }

        System.out.print(salidaTotal.toString());
        sc.close();
    }
}
