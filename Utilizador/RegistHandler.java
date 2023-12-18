package Utilizador;

import Industria.Industria;

public class RegistHandler {

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
    public Industria registarIndustria(String primeiro_nome, String password, String email, String papel, String tipo, String contacto){
        Industria i = new Industria(primeiro_nome, email,password, papel, tipo);
        i.setContacto(contacto);
        return i;
    }

}
