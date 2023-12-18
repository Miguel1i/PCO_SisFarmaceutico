package Sistema;

import Industria.Industria;
import InteracaoAlimentar.InteracaoAlimentar;
import InteracaoAlimentar.InteracaoHandler;
import Medicamento.Medicamento;
import Medicamento.MedicamentoHandler;
import SubstanciaAtiva.SubstanciaAtiva;
import SubstanciaAtiva.SubstanciaHandler;
import Utilizador.Utilizador;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;

public class Sistema {

    private TreeMap<String, SubstanciaAtiva> substancias_ativas;

    private TreeMap<String, Medicamento> medicamentos;

    private TreeMap<String, InteracaoAlimentar> interacoes_alimentares;

    private HashMap<String, Industria> industrias;

    private HashMap<String, Utilizador> utilizadores;

    private final String[] tipos_utilizador = {"Industria", "Farmaceutico", "Administrador"};


    private ArrayList<String> referencias;
    private ArrayList<String> efeitos;
    private ArrayList<String> formas;

    private Utilizador utilizador_atual;

    private ArrayList<String> alimentos;

    private MedicamentoHandler medicamento_handler;
    private SubstanciaHandler substancia_ativa_handler;
    private InteracaoHandler interacao_alimentar_handler;


    public Sistema() {
        this.substancias_ativas = new TreeMap<>();
        this.medicamentos = new TreeMap<>();
        this.interacoes_alimentares = new TreeMap<>();
        this.industrias = new HashMap<>();
        this.utilizadores = new HashMap<>();
        this.referencias = new ArrayList<>();
        this.efeitos = new ArrayList<>();
        this.formas = new ArrayList<>();
        this.alimentos = new ArrayList<>();
        this.medicamento_handler = new MedicamentoHandler();
        this.substancia_ativa_handler = new SubstanciaHandler();
        this.interacao_alimentar_handler = new InteracaoHandler();
    }

    public Utilizador getUtilizador_atual() {
        return utilizador_atual;
    }

    public void startup() {

    }

    public void shutdown() {

    }

    public void listarInteracoesAlimentares() {

    }

    public void listarSubstanciasAtivas() {

    }

    public void listarMedicamentos() {

    }

    public void pesquisarInteracoes(String nome_medicamento) {

    }

    public void pesquisarContacto(String nome_medicamento) {

    }

    public void adicionarMedicamento(String nome, String forma, float dosagem, ArrayList<String> substancias_ativas) {

    }

    public void adicionarInteracaoAlimentar(SubstanciaAtiva substancia_ativa, String explicacao, String alimento, String efeito, String referencia) {

    }

    public void adicionarSubstanciaAtiva(String nome) {

    }

    public void login(String email, String password) {

    }

    public void registar(String primeiro_nome, String ultimo_nome, String email, String password, String papel, String tipo) {

    }

    public boolean verificaMedicamento(String nome_medicamento) {

        return false;
    }

    public boolean verificaSubstancia(String nome_substancia) {
        return false;
    }

    public boolean verificaIteracao(SubstanciaAtiva substanciaAtiva, String alimento, String efeito) {
        return false;
    }

    public boolean verificaUtilizador(String email) {
        return false;
    }

}
