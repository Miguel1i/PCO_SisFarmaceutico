package Utilizador;
import Industria.Industria;

/**
 * Classe RegistHandler que cria um utilizador ou indústria
 */

public class RegistHandler {
    /**
     * Cria um utilizador
     * @param primeiro_nome primeiro nome do utilizador
     * @param ult_nome último nome do utilizador
     * @param password password do utilizador
     * @param email email do utilizador
     * @param papel papel do utilizador
     * @param tipo tipo de utilizador
     * @return utilizador criado
     */
    public Utilizador registarUtilizador(String primeiro_nome, String ult_nome, String password, String email, String papel, String tipo){
        Utilizador u = new Utilizador();
        u.setPrimeiro_nome(primeiro_nome);
        u.setUltimo_nome(ult_nome);
        u.setPassword(password);
        u.setEmail(email);
        u.setPapel(papel);
        u.setTipo(tipo);
        return u;
    }

    /**
     * Cria uma indústria
     * @param primeiro_nome primeiro nome da indústria
     * @param password password da indústria
     * @param papel papel da indústria
     * @param tipo tipo de indústria
     * @param contacto contacto da indústria
     * @return indústria criada
     */
    public Industria registarIndustria(String primeiro_nome, String password, String papel, String tipo, int contacto){
        Industria i = new Industria(primeiro_nome,password, papel, tipo);
        i.setContacto(contacto);
        return i;
    }


}
