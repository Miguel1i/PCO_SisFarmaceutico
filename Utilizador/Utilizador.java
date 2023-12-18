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
     * 
     */
    public Utilizador() {
        this.primeiro_nome = "";
        this.ultimo_nome = "";
        this.password = "";
        this.email = "";
        this.papel = "";
        this.tipo = "";
    }

    public String getPrimeiro_nome() {
        return primeiro_nome;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getTipo() {
        return tipo;
    }

    public void setPrimeiro_nome(String primeiro_nome) {
        this.primeiro_nome = primeiro_nome;
    }

    public void setUltimo_nome(String ultimo_nome) {
        this.ultimo_nome = ultimo_nome;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPapel(String papel) {
        this.papel = papel;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
