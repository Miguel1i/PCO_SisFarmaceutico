package Industria;
import Medicamento.Medicamento;
import Utilizador.Utilizador;
import java.util.HashMap;

/**
 * Classe Industria que herda de Utilizador e representa uma industria farmaceutica.
 */
public class Industria extends Utilizador {
    private int contacto;
    private HashMap<String, Medicamento> medicamentos;

    /**
     * Construtor da classe Industria
     * @param primeiro_nome primeiro nome da industria
     * @param password password da industria
     * @param papel papel da industria
     */
    public Industria(String primeiro_nome, String password, String papel) {
        setPrimeiro_nome(primeiro_nome);
        setEmail(primeiro_nome+"@"+primeiro_nome+".com");
        setPassword(password);
        setPapel(papel);
        this.contacto = 0;
        this.medicamentos = new HashMap<>();
    }

    /**
     * Getter do contacto da industria
     * @return contacto
     */
    public int getContacto() {
        return contacto;
    }

    /**
     * Setter do contacto da industria
     * @param contacto contacto da industria
     */
    public void setContacto(int contacto) {
        this.contacto = contacto;
    }

    /**
     * Getter dos medicamentos da industria
     * @return medicamentos
     */
    public HashMap<String, Medicamento> getMedicamentos() {
        return medicamentos;
    }

    /**
     * Setter dos medicamentos da industria
     * @param medicamento medicamento da industria
     */
    public void setMedicamentos(Medicamento medicamento) {
        this.medicamentos.put(medicamento.getNome(), medicamento);
   }

    /**
     * Método que retorna uma String com a informação da industria
     * @return String com a informação da industria
     */
    @Override
    public String toString() {
        return  "Nome:" + getPrimeiro_nome()+ "\n"+
                "email:" + getEmail() + "\n" +
                "contacto:" + contacto + "\n";
    }
}
