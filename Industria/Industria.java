package Industria;
import Medicamento.Medicamento;
import Utilizador.Utilizador;
import java.util.HashMap;

/**
 * Classe Industria que herda de Utilizador e representa uma industria farmaceutica.
 */
public class Industria extends Utilizador {
    private String contacto;
    private HashMap<String, Medicamento> medicamentos;

    /**
     * Construtor da classe Industria
     * @param primeiro_nome primeiro nome da industria
     * @param email email da industria
     * @param password password da industria
     * @param papel papel da industria
     * @param tipo tipo da industria
     */
    public Industria(String primeiro_nome, String email, String password, String papel, String tipo) {
        setPrimeiro_nome(primeiro_nome);
        setEmail(email);
        setPassword(password);
        setPapel(papel);
        setTipo(tipo);
        this.contacto = "";
        this.medicamentos = new HashMap<>();
    }

    /**
     * Getter do contacto da industria
     * @return contacto
     */
    public String getContacto() {
        return contacto;
    }

    /**
     * Setter do contacto da industria
     * @param contacto contacto da industria
     */
    public void setContacto(String contacto) {
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
     * @param medicamentos medicamentos da industria
     */
    public void setMedicamentos(HashMap<String, Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }
}
