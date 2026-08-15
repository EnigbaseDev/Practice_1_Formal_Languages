import java.util.Scanner;

public class Practice {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder salidaTotal = new StringBuilder();

        if (!sc.hasNextInt()) {
            sc.close();
            return;
        }

        int casos = sc.nextInt();
        sc.nextLine();

        for (int caso = 0; caso < casos; caso++) {
            int n = sc.nextInt();
            sc.nextLine();

            String[] alfabeto = sc.nextLine().trim().split("\\s+");
            int m = alfabeto.length;

            boolean[] finales = new boolean[n];

            String lineaFinales = sc.nextLine().trim();
            if (!lineaFinales.isEmpty()) {
                String[] estadosFinales = lineaFinales.split("\\s+");
                for (String estado : estadosFinales) {
                    int estadoFinal = Integer.parseInt(estado);
                    finales[estadoFinal] = true;
                }
            }

            int[][] transiciones = new int[n][m];

            for (int i = 0; i < n; i++) {
                sc.nextInt();
                for (int j = 0; j < m; j++) {
                    transiciones[i][j] = sc.nextInt();
                }
            }

            if (sc.hasNextLine()) {
                sc.nextLine();
            }

            boolean[][] marcados = new boolean[n][n];

            for (int i = 0; i < n; i++) {
                for (int j = i + 1; j < n; j++) {
                    if (finales[i] != finales[j]) {
                        marcados[i][j] = true;
                    }
                }
            }

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
