package Utilizador;


/**
 * Classe que representa um Utilizador
 */

public class Utilizador {

    private String primeiro_nome;
    private String ultimo_nome;

    private String password;

    private String email;

    private String papel;

    private String tipo;

    /**
     * Construtor default da classe Utilizador
     */
    public Utilizador() {
        this.primeiro_nome = "";
        this.ultimo_nome = "";
        this.password = "";
        this.email = "";
        this.papel = "";
        this.tipo = "";
    }

    /**
     * Getter do primeiro nome do Utilizador
     * @return primeiro_nome
     */

    public String getPrimeiro_nome() {
        return primeiro_nome;
    }

    /**
     * Getter da password do Utilizador
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Getter do email do Utlizador
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Getter do tipo de Utilizador
     * @return tipo
     */
    public String getTipo() {
        return tipo;
    }

    /**
     * Setter do primeiro_nome do Utilizador
     * @param primeiro_nome, primeiro nome
     */

    public void setPrimeiro_nome(String primeiro_nome) {
        this.primeiro_nome = primeiro_nome;
    }

    /**
     * Setter do ultimo nome do Utilizador
     * @param ultimo_nome, ultimo nome do Utilizador
     */
    public void setUltimo_nome(String ultimo_nome) {
        this.ultimo_nome = ultimo_nome;
    }

    /**
     * Setter da password do Utilizador
     * @param password password do Utilizador
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Setter email do Utilizador
     * @param email, email do utilizador
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Setter do papel do Utilizador
     * @param papel, papel do farmaceutico ou indústria
     */
    public void setPapel(String papel) {
        this.papel = papel;
    }

    /**
     * Setter do tipo de Utilizador
     * @param tipo, Tipo de utilizador : Administrador, Farmaceutico, Industria
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
