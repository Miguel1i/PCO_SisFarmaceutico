package Medicamento;
import SubstanciaAtiva.SubstanciaAtiva;
import java.util.ArrayList;


/**
 * Classe Medicamento que representa um medicamento.
 */
public class Medicamento {

    private String nome;
    private String forma;
    private float dosagem;
    private ArrayList<SubstanciaAtiva> substanciaAtivas;


    /**
     * Construtor default da classe Medicamento
     */
    public Medicamento(){
        nome = "";
        forma = "";
        dosagem = 0;
    }

    /**
     * Setter do nome do medicamento
     * @param nome nome do medicamento
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Setter da forma do medicamento
     * @param forma forma do medicamento
     */
    public void setForma(String forma) {
        this.forma = forma;
    }


    /**
     * Setter da dosagem do medicamento
     * @param dosagem dosagem do medicamento
     */
    public void setDosagem(float dosagem) {
        this.dosagem = dosagem;
    }

    /**
     * Setter da lista de substâncias ativas do medicamento
     * @param new_list lista de substâncias ativas do medicamento
     */
    public void setSubstanciaAtivas(ArrayList<SubstanciaAtiva> new_list) {
        this.substanciaAtivas = new_list;
    }

    /**
     * Adiciona uma substância ativa à lista de substâncias ativas do medicamento
     * @param substanciaAtiva substância ativa a adicionar
     */
    public void addSusbtancia(SubstanciaAtiva substanciaAtiva){
        this.substanciaAtivas.add(substanciaAtiva);
    }
}
