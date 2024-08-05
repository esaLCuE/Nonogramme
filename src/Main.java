import java.util.*;
//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        String[][] nono = new String[4][4];
        int tot =0;
        boolean toutvalid=false;

        // ON DEMANDE A L'UTILISATEUR DE DEFINIR LES CASES A COLORIER
        while(!toutvalid) {

            Scanner sc = new Scanner(System.in);

            int totx=0;
            int toty=0;
            boolean valid = false;

            // DEFINITION DES LIGNES
            for (int i = 1; i < 4; i++) {
                while (!valid) {
                    System.out.println("Combien de cases à colorier sur la ligne " + i + " ? 0, 1, 2, 3 ou 101.");
                    int xtemp = sc.nextInt();

                    if (xtemp == 0 || xtemp == 1 || xtemp == 2 || xtemp == 3) {
                        if (xtemp == 0) {
                            nono[i][0] = " 0 ";
                        } else if (xtemp == 1) {
                            nono[i][0] = " 1 ";
                        } else if (xtemp == 2) {
                            nono[i][0] = " 2 ";
                        } else {
                            nono[i][0] = " 3 ";
                        }
                        totx += xtemp;
                        valid = true;
                    } else if (xtemp == 101) {
                        nono[i][0] = "1 1";
                        totx += 2;
                        valid = true;
                    } else {
                        System.out.println("Entrée invalide");
                    }
                }
                valid = false;
            }
            // DEFINITION DES COLONNES
            for (int i = 1; i < 4; i++) {
                while (!valid) {
                    System.out.println("Combien de cases à colorier sur la colonne " + i + " ? 0, 1, 2, 3 ou 101.");
                    int ytemp = sc.nextInt();

                    if (ytemp == 0 || ytemp == 1 || ytemp == 2 || ytemp == 3) {
                        if (ytemp == 0) {
                            nono[0][i] = " 0 ";
                        } else if (ytemp == 1) {
                            nono[0][i] = " 1 ";
                        } else if (ytemp == 2) {
                            nono[0][i] = " 2 ";
                        } else {
                            nono[0][i] = " 3 ";
                        }
                        toty += ytemp;
                        valid = true;
                    } else if (ytemp == 101) {
                        nono[0][i] = "1 1";
                        toty += 2;
                        valid = true;
                    } else {
                        System.out.println("Entrée invalide");
                    }
                }
                valid = false;
            } // SI LE NOMBRE DE CASES A COLORIER SUR LES LIGNES ET LES COLONNES EST DIFFERENT IL FAUT RECOMMENCER
            if (totx==toty){
                toutvalid=true;
                tot=totx;
            } else {
                System.out.println("Incohérence sur le nombre de cases à colorier. Retour au début.");
            }
        }


        // ON DONNE LA VALEUR "   " A TOUTES LES AUTRES CASES DU TABLEAU
        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < 4; j++) {
                nono[i][j]="   ";
            }
        }
        nono[0][0]="   ";

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(nono[i][j]+" ");
            }
            System.out.println();
        }

        String[][] copie = nono;
        // ON LANCE L'ALGORITHME POUR QU'IL RESOLVE LE NONOGRAMME
        // ON COMMENCE PAR LES LIGNES
        int color = 0;
        while(color<tot) {
            for (int i = 1; i < 4; i++) {
                if (Objects.equals(nono[i][0], " 3 ")) {
                    for (int j = 1; j < 4; j++) {
                        if (Objects.equals(nono[i][j], " O ")) {
                            System.out.println("Ce nonogramme n'est pas résolvable. Fin du programme.");
                            System.exit(0);
                        } else if (Objects.equals(nono[i][j], "   ")) {
                            nono[i][j] = " X ";
                            color++;
                        }
                    }
                }
                else if (Objects.equals(nono[i][0], "1 1")) {
                    if (Objects.equals(nono[i][1], " O ") || Objects.equals(nono[i][3], " O ") || Objects.equals(nono[i][2], " X ")){
                        System.out.println("Ce nonogramme n'est pas résolvable. Fin du programme.");
                        System.exit(0);
                    } else {
                        if (Objects.equals(nono[i][1], "   ")) {
                            nono[i][1] = " X ";
                            color++;
                        }
                        nono[i][2] = " O ";
                        if (Objects.equals(nono[i][3], "   ")) {
                            nono[i][3] = " X ";
                            color++;
                        }
                    }
                }
                else if (Objects.equals(nono[i][0], " 2 ")) {
                    if ((Objects.equals(nono[i][2], " O "))
                            || ((Objects.equals(nono[i][1], " X "))&&(Objects.equals(nono[i][3], " X "))
                            || (Objects.equals(nono[i][1], " O ") && Objects.equals(nono[i][3], " O ")))){
                        System.out.println("Ce nonogramme n'est pas résolvable. Fin du programme.");
                        System.exit(0);
                    }
                    if (Objects.equals(nono[i][2], "   ")) {
                        nono[i][2] = " X ";
                        color++;
                    }
                    if (Objects.equals(nono[i][1], " O ")) {
                        if (Objects.equals(nono[i][3], "   ")) {
                            nono[i][3] = " X ";
                            color++;
                        }
                    } else if (Objects.equals(nono[i][3], " O ")) {
                        if (Objects.equals(nono[i][1], "   ")) {
                            nono[i][1] = " X ";
                            color++;
                        }
                    }
                    if (Objects.equals(nono[i][1], " X ")) {
                        nono[i][3] = " O ";
                    } else if (Objects.equals(nono[i][3], " X ")) {
                        nono[i][1] = " O ";
                    }
                }
                else if (Objects.equals(nono[i][0], " 1 ")) {
                    if ( (Objects.equals(nono[i][1], " O ") && Objects.equals(nono[i][2], " O ") && Objects.equals(nono[i][3], " O "))
                        || (Objects.equals(nono[i][1], " X ") && (Objects.equals(nono[i][2], " X ") || Objects.equals(nono[i][3], " X ")))
                            || (Objects.equals(nono[i][2], " X ") && (Objects.equals(nono[i][3], " X ")))){
                        System.out.println("Le nonogramme n'est pas résolvable. Fin du programme.");
                        System.exit(0);
                    }
                    if (Objects.equals(nono[i][1], " O ") && Objects.equals(nono[i][2], " O ") && Objects.equals(nono[i][3], "   ")){
                        nono[i][3] = " X ";
                        color++;
                    } else if (Objects.equals(nono[i][1], " O ") && Objects.equals(nono[i][2], "   ") && Objects.equals(nono[i][3], " O ")){
                        nono[i][2] = " X ";
                        color++;
                    } else if (Objects.equals(nono[i][1], "   ") && Objects.equals(nono[i][2], " O ") && Objects.equals(nono[i][3], " O ")){
                        nono[i][1] = " X ";
                        color++;
                    }
                    if (Objects.equals(nono[i][1], " X ")){
                        nono[i][2] = " O ";
                        nono[i][3] = " O ";
                    } else if (Objects.equals(nono[i][2], " X ")) {
                        nono[i][1] = " O ";
                        nono[i][3] = " O ";
                    } else if (Objects.equals(nono[i][3], " X ")) {
                        nono[i][1] = " O ";
                        nono[i][2] = " O ";
                    }
                }
                else if (Objects.equals(nono[i][0], " 0 ")){
                    if ((Objects.equals(nono[i][1], " X ")) || (Objects.equals(nono[i][2], " X ")) || (Objects.equals(nono[i][3], " X "))) {
                        System.out.println("Le nonogramme n'est pas résolvable. Fin du programme.");
                        System.exit(0);
                    } else {
                        nono[i][1]=" O ";
                        nono[i][2]=" O ";
                        nono[i][3]=" O ";
                    }
                }
            }
            for (int i = 1; i < 4; i++) {
                if (Objects.equals(nono[0][i], " 3 ")) {
                    for (int j = 1; j < 4; j++) {
                        if (Objects.equals(nono[j][i], " O ")) {
                            System.out.println("Ce nonogramme n'est pas résolvable. Fin du programme.");
                            System.exit(0);
                        } else if (Objects.equals(nono[j][i], "   ")) {
                            nono[j][i] = " X ";
                            color++;
                        }
                    }
                }
                else if (Objects.equals(nono[0][i], "1 1")) {
                    if (Objects.equals(nono[1][i], " O ") || Objects.equals(nono[3][i], " O ") || Objects.equals(nono[2][i], " X ")){
                        System.out.println("Ce nonogramme n'est pas résolvable. Fin du programme.");
                        System.exit(0);
                    } else {
                        if (Objects.equals(nono[1][i], "   ")) {
                            nono[1][i] = " X ";
                            color++;
                        }
                        nono[2][i] = " O ";
                        if (Objects.equals(nono[3][i], "   ")) {
                            nono[3][i] = " X ";
                            color++;
                        }
                    }
                }
                else if (Objects.equals(nono[0][i], " 2 ")) {
                    if ((Objects.equals(nono[2][i], " O "))
                            || ((Objects.equals(nono[1][i], " X "))&&(Objects.equals(nono[3][i], " X "))
                            || (Objects.equals(nono[1][i], " O ") && Objects.equals(nono[3][i], " O ")))){
                        System.out.println("Ce nonogramme n'est pas résolvable. Fin du programme.");
                        System.exit(0);
                    }
                    if (Objects.equals(nono[2][i], "   ")) {
                        nono[2][i] = " X ";
                        color++;
                    }
                    if (Objects.equals(nono[1][i], " O ")) {
                        if (Objects.equals(nono[3][i], "   ")) {
                            nono[3][i] = " X ";
                            color++;
                        }
                    } else if (Objects.equals(nono[3][i], " O ")) {
                        if (Objects.equals(nono[1][i], "   ")) {
                            nono[1][i] = " X ";
                            color++;
                        }
                    }
                    if (Objects.equals(nono[1][i], " X ")) {
                        nono[3][i] = " O ";
                    } else if (Objects.equals(nono[3][i], " X ")) {
                        nono[1][i] = " O ";
                    }
                }
                else if (Objects.equals(nono[0][i], " 1 ")) {
                    if ( (Objects.equals(nono[1][i], " O ") && Objects.equals(nono[2][i], " O ") && Objects.equals(nono[3][i], " O "))
                            || (Objects.equals(nono[1][i], " X ") && (Objects.equals(nono[2][i], " X ") || Objects.equals(nono[3][i], " X ")))
                            || (Objects.equals(nono[2][i], " X ") && (Objects.equals(nono[3][i], " X ")))){
                        System.out.println("Le nonogramme n'est pas résolvable. Fin du programme.");
                        System.exit(0);
                    }
                    if (Objects.equals(nono[1][i], " O ") && Objects.equals(nono[2][i], " O ") && Objects.equals(nono[3][i], "   ")){
                        nono[3][i] = " X ";
                        color++;
                    } else if (Objects.equals(nono[1][i], " O ") && Objects.equals(nono[2][i], "   ") && Objects.equals(nono[3][i], " O ")){
                        nono[2][i] = " X ";
                        color++;
                    } else if (Objects.equals(nono[1][i], "   ") && Objects.equals(nono[2][i], " O ") && Objects.equals(nono[3][i], " O ")){
                        nono[1][i] = " X ";
                        color++;
                    }
                    if (Objects.equals(nono[1][i], " X ")){
                        nono[2][i] = " O ";
                        nono[3][i] = " O ";
                    } else if (Objects.equals(nono[2][i], " X ")) {
                        nono[1][i] = " O ";
                        nono[3][i] = " O ";
                    } else if (Objects.equals(nono[3][i], " X ")) {
                        nono[1][i] = " O ";
                        nono[2][i] = " O ";
                    }
                }
                else if(Objects.equals(nono[0][i], " 0 ")){
                    if ((Objects.equals(nono[1][i], " X ")) || (Objects.equals(nono[2][i], " X ")) || (Objects.equals(nono[3][i], " X "))) {
                        System.out.println("Le nonogramme n'est pas résolvable. Fin du programme.");
                        System.exit(0);
                    } else {
                        nono[1][i]=" O ";
                        nono[2][i]=" O ";
                        nono[3][i]=" O ";
                    }
                }
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    System.out.print(nono[i][j]+" ");
                }
                System.out.println();
            }
            if (copie==nono && color<tot){
                System.out.println("Il existe plusieurs solutions. Fin du programme.");
                System.exit(0);
            } else {
                copie=nono;
            }

        }
        System.out.println("Le nonogramme est résolu.");
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(nono[i][j]+" ");
            }
            System.out.println();
        }
    }
}