package Main;
import InteracaoAlimentar.InteracaoAlimentar;
import Sistema.Sistema;
public class Main {
    public static void main(String[] args) {
        Sistema sistema = new Sistema();
        sistema.startup();
        sistema.pesquisarInteracoes("Amlodipina Mibral");
    }

}
