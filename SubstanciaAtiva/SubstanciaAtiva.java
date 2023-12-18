package SubstanciaAtiva;


/**
 * Classe que representa uma substância ativa
 */
public class SubstanciaAtiva {
    private String nome;

    /**
     * Construtor default da classe SubstanciaAtiva
     */
    public SubstanciaAtiva() {
        this.nome = "";
    }

    /**
     * Construtor da classe SubstanciaAtiva
     * @param nome, nome da substância ativa
     */
    public SubstanciaAtiva(String nome) {
        this.nome = nome;
    }

    /**
     * Getter do nome da substância ativa
     * @return nome da substância ativa
     */
    public String getNome() {
        return nome;
    }

    /**
     * Setter do nome da substância ativa
     * @param nome, nome da substância ativa
     */
    public void setNome(String nome){
        this.nome = nome;
    }

}
