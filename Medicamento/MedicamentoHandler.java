package Medicamento;
import SubstanciaAtiva.SubstanciaAtiva;
import java.util.ArrayList;


/**
 * Classe MedicamentoHandler que cria um medicamento
 */
public class MedicamentoHandler {

    /**
     * Cria um medicamento
     * @param nome nome do medicamento
     * @param forma forma do medicamento
     * @param dosagem dosagem do medicamento
     * @param substanciaAtivas lista de substâncias ativas do medicamento
     * @return medicamento criado
     */
    public Medicamento criarMedicamento(String nome, String forma, float dosagem, ArrayList<SubstanciaAtiva> substanciaAtivas){
        Medicamento m = new Medicamento();
        m.setNome(nome);
        m.setForma(forma);
        m.setDosagem(dosagem);
        m.setSubstanciaAtivas(substanciaAtivas);
        return m;
    }

}
