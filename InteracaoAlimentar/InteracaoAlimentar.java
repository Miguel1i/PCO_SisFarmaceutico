package InteracaoAlimentar;
import SubstanciaAtiva.SubstanciaAtiva;

/**
 * Classe que representa uma interação alimentar
 */
public class InteracaoAlimentar {

    private SubstanciaAtiva substanciaAtiva;
    private String explicacao;
    private String alimento;
    private String efeito;
    private String referencia_bibliografica;


    /**
     * Construtor default da classe InteracaoAlimentar
     */
    public InteracaoAlimentar() {
        this.substanciaAtiva = new SubstanciaAtiva();
        this.explicacao = "";
        this.alimento = "";
        this.efeito = "";
        this.referencia_bibliografica = "";
    }

    /**
     * Setter da substância ativa
     * @param substanciaAtiva substância ativa
     */
    public void setSubstanciaAtiva(SubstanciaAtiva substanciaAtiva) {
        this.substanciaAtiva = substanciaAtiva;
    }

    /**
     * Setter da explicação
     * @param explicacao explicação
     */
    public void setExplicacao(String explicacao) {
        this.explicacao = explicacao;
    }

    /**
     * Setter do alimento
     * @param alimento alimento
     */
    public void setAlimento(String alimento) {
        this.alimento = alimento;
    }

    /**
     * Setter do efeito
     * @param efeito efeito
     */
    public void setEfeito(String efeito) {
        this.efeito = efeito;
    }

    /**
     * Setter da referência bibliográfica
     * @param referencia_bibliografica referência bibliográfica
     */
    public void setReferencia_bibliografica(String referencia_bibliografica) {
        this.referencia_bibliografica = referencia_bibliografica;
    }

    /**
     * Getter da substância ativa
     * @return substância ativa
     */
    public SubstanciaAtiva getSubstanciaAtiva() {
        return substanciaAtiva;
    }

    /**
     * Getter do alimento
     * @return alimento
     */
    public String getAlimento() {
        return alimento;
    }

    /**
     * Getter do efeito
     * @return efeito
     */
    public String getEfeito() {
        return efeito;
    }
}
