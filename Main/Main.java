package Main;
import Sistema.Sistema;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        sistema.startup();
        menuInicial(sistema);

    }

    public static void menuInicial(Sistema sistema) {
        Scanner sc = new Scanner(System.in);
        Scanner sc2 = new Scanner(System.in);
        do {
            System.out.println("1: Iniciar Sessão");
            System.out.println("2: Pesquisar Interações");
            System.out.println("3: Pesquisar Contacto");
            System.out.println("0: Sair");
            System.out.print(">> ");
            int op = sc.nextInt();
            System.out.println();
            if (op == 1){
                continue;
            }if (op == 2) {
                System.out.print("Introduza o nome do medicamento > ");
                String medicamento = sc2.nextLine();
                System.out.println();
                sistema.pesquisarInteracoes(medicamento);
            }if (op == 3) {
                System.out.print("Introduza o nome do medicamento > ");
                String medicamento = sc2.nextLine();
                System.out.println();
                if (sistema.pesquisarContacto(medicamento) != null){
                    System.out.println(sistema.pesquisarContacto(medicamento));
                }else{
                    System.out.println("Não existe nenhuma industria com esse medicamento!");
                }
            }if (op == 0) {
                break;
            }else{
                System.out.println("Essa opção não existe!");
            }
        }while (true);
    }
}
