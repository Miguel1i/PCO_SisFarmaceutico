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
     * @return utilizador criado
     */
    public Utilizador registarUtilizador(String primeiro_nome, String ult_nome, String password, String email, String papel){
        Utilizador u = new Utilizador();
        u.setPrimeiro_nome(primeiro_nome);
        u.setUltimo_nome(ult_nome);
        u.setPassword(password);
        u.setEmail(email);
        u.setPapel(papel);
        return u;
    }

    /**
     * Cria uma indústria
     * @param primeiro_nome primeiro nome da indústria
     * @param password password da indústria
     * @param papel papel da indústria
     * @param contacto contacto da indústria
     * @return indústria criada
     */
    public Industria registarIndustria(String primeiro_nome, String password, String papel,int contacto){
        Industria i = new Industria(primeiro_nome,password, papel);
        i.setContacto(contacto);
        return i;
    }


}
