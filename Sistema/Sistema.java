package Sistema;
import Utilizador.RegistHandler;
import Industria.Industria;
import InteracaoAlimentar.InteracaoAlimentar;
import InteracaoAlimentar.InteracaoHandler;
import Medicamento.Medicamento;
import Medicamento.MedicamentoHandler;
import SubstanciaAtiva.SubstanciaAtiva;
import SubstanciaAtiva.SubstanciaHandler;
import Utilizador.Utilizador;
import com.google.gson.*;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Classe que representa o sistema farmaceutico.
 */
public class Sistema {
    private TreeMap<String, SubstanciaAtiva> substancias_ativas;
    private TreeMap<String, Medicamento> medicamentos;
    private ArrayList<InteracaoAlimentar> interacoes_alimentares;
    private HashMap<String, Industria> industrias;
    private HashMap<String, Utilizador> utilizadores;
    private final String[] papel_utilizador = {"Industria", "Farmaceutico", "Administrador"};
    private Utilizador utilizador_atual;
    private ArrayList<String> alimentos;
    private MedicamentoHandler medicamento_handler;
    private SubstanciaHandler substancia_ativa_handler;
    private InteracaoHandler interacao_alimentar_handler;
    private RegistHandler regist_handler;
    private final HashMap<Integer, String> niveis = new HashMap<>();

    /**
     * Construtor default da classe sistema
     */
    public Sistema() {
        this.substancias_ativas = new TreeMap<>();
        this.medicamentos = new TreeMap<>();
        this.interacoes_alimentares = new ArrayList<InteracaoAlimentar>();
        this.industrias = new HashMap<>();
        this.utilizadores = new HashMap<>();
        this.alimentos = new ArrayList<>();
        this.medicamento_handler = new MedicamentoHandler();
        this.substancia_ativa_handler = new SubstanciaHandler();
        this.interacao_alimentar_handler = new InteracaoHandler();
        this.regist_handler = new RegistHandler();
        this.niveis.put(1, "Fraco"); this.niveis.put(2, "moderado"); this.niveis.put(3, "Forte");
    }

    /**
     * Getter para retornar o utilizador atual do sistema.
     * @return utilizador_atual
     */
    public Utilizador getUtilizador_atual() {
        return utilizador_atual;
    }

    /**
     * Startup do sistema
     * Recebe do ficheiro dataset.json os dados para o sistema como substâncias ativas, interações alimentares, medicamentos, alimentos e indústrias.
     * e adiciona-os ao sistema quando o sistema é iniciado.
     * @exception FileNotFoundException, caso o ficheiro não exista.
     * @exception IllegalStateException, caso o ficheiro não esteja no formato correto.
     * @exception JsonSyntaxException, caso o ficheiro não esteja no formato correto.
     * @exception IOException, caso haja um erro de IO.
     */
    public void startup() {
        Gson gson = new Gson();
        try {
            FileReader fileReader = new FileReader("Sistema/dataset.json");
            JsonObject jsonObject = gson.fromJson(fileReader, JsonObject.class);
            JsonArray drugsArray = jsonObject.getAsJsonArray("drugs");
            JsonArray substancesArray = jsonObject.getAsJsonArray("substances");
            JsonArray interactionsArray = jsonObject.getAsJsonArray("foodInteractions");
            JsonArray foodsArray = jsonObject.getAsJsonArray("foodTypes");
            JsonArray laboratories = jsonObject.getAsJsonArray("laboratories");
            fileReader.close();
            // Alimentos
            for (int i = 0; i < foodsArray.size(); i++) {
                String alimento_string = foodsArray.get(i).getAsJsonObject().get("Type").getAsString();
                if (!alimentos.contains(alimento_string)){
                    alimentos.add(alimento_string);
                }
            }
            // Substâncias Ativas
            for (int i = 0; i < substancesArray.size() ; i++) {
                String substance_string = substancesArray.get(i).getAsJsonObject().get("Substance").getAsString();
                adicionarSubstanciaAtiva(substance_string);
            }
            // Interações Alimentares
            for (int i = 0; i < interactionsArray.size(); i++) {
                String referencia = interactionsArray.get(i).getAsJsonObject().get("Bibliography").getAsString();
                String efeito = interactionsArray.get(i).getAsJsonObject().get("Effect").getAsString();
                int level = interactionsArray.get(i).getAsJsonObject().get("EffectLevel").getAsInt();
                String substancia_nome = interactionsArray.get(i).getAsJsonObject().get("Substances").getAsString();
                String alimento = interactionsArray.get(i).getAsJsonObject().get("Food").getAsString();
                String explicacao = interactionsArray.get(i).getAsJsonObject().get("Explanation").getAsString();
                if (!alimentos.contains(alimento)){
                    alimentos.add(alimento);
                }
                SubstanciaAtiva s;
                if (!verificaSubstancia(substancia_nome) && !substancia_nome.isEmpty()) {
                    s = substancia_ativa_handler.criarSubstancia(substancia_nome);
                    substancias_ativas.put(substancia_nome, s);
                } else {
                    s = substancia_ativa_handler.criarSubstancia(substancia_nome);
                }
                adicionarInteracaoAlimentar(s, explicacao, alimento, efeito, level, referencia);
            }
            // Indústrias
            for (int i = 0; i < laboratories.size(); i++) {
                int contacto;
                String name = laboratories.get(i).getAsJsonObject().get("Name").getAsString();
                if (laboratories.get(i).getAsJsonObject().get("Surveillance").getAsString().equals("")){
                    contacto = 0;
                }else {
                    contacto = laboratories.get(i).getAsJsonObject().get("Surveillance").getAsInt();
                }
                Industria in = regist_handler.registarIndustria(name, "", "Industria",contacto);
                if(!industrias.containsKey(name)){
                    industrias.put(name, in);
                }
            }
            // Medicamentos
            for (int i = 0; i < drugsArray.size(); i++) {
                String dosagem = drugsArray.get(i).getAsJsonObject().get("Dosage").getAsString();
                String forma = drugsArray.get(i).getAsJsonObject().get("Form").getAsString();
                String laboratorio = drugsArray.get(i).getAsJsonObject().get("Laboratory").getAsString();
                String nome = drugsArray.get(i).getAsJsonObject().get("Name").getAsString();
                String substancia = drugsArray.get(i).getAsJsonObject().get("Substances").getAsString();

                SubstanciaAtiva s;
                if (!verificaSubstancia(substancia) && !substancia.isEmpty()) {
                    s = substancia_ativa_handler.criarSubstancia(substancia);
                    substancias_ativas.put(substancia, s);
                } else{
                    s = substancia_ativa_handler.criarSubstancia(substancia);
                }
                ArrayList<SubstanciaAtiva> s_lista = new ArrayList<>();
                s_lista.add(s);

                adicionarMedicamento(nome, forma, dosagem, s_lista, laboratorio);
            }

        } catch (FileNotFoundException ignored) {
            System.out.printf("File %s does not exist\n","dataset.json");
            System.exit(1);
        } catch (IllegalStateException | JsonSyntaxException ignored) {
            System.out.println("Invalid file");
            System.exit(2);
        }catch (IOException ignored){
            System.out.println("IO Exception error");
            System.exit(3);
        }
    }

    public void shutdown() {

    }

    /**
     * Metodo para listar interações alimentares de 10 em 10
     * @param max, é incrementado ao max + 10
     */
    public void listarInteracoesAlimentares(int max) {
        for (int i = max - 10; i < max; i++) {
            System.out.println(i+1 + ": " + interacoes_alimentares.get(i));
        }
    }

    /**
     * Metodo para listar substâncias ativas de 10 em 10
     * @param max, é incrementado ao max + 10
     */
    public void listarSubstanciasAtivas(int max) {
       ArrayList<SubstanciaAtiva> substancias = new ArrayList<>(substancias_ativas.values());
       for (int i = max - 10; i < max; i++) {
           System.out.println(i+1 + ": " + substancias.get(i).getNome());
       }
    }

    /**
     * Metodo para listar medicamento de 10 em 10
     * @param max, é imcrementado ao max + 10
     */
    public void listarMedicamentos(int max) {
        ArrayList<Medicamento> m = new ArrayList<>(medicamentos.values());
        for (int i = max - 10; i < max; i++) {
            System.out.println(i + 1 + ": " + m.get(i));
        }
    }

    /**
     * Método para pesquisar interações alimentares dado um nome do medicamento.
     * @param nome_medicamento nome do medicamento
     */
    public void pesquisarInteracoes(String nome_medicamento) {
        for (Medicamento m: medicamentos.values()) {
            if (m.getNome().equals(nome_medicamento)){
                ArrayList<SubstanciaAtiva> s = m.getSubstanciaAtivas();
                for (InteracaoAlimentar ia: interacoes_alimentares){
                    if (s.contains(ia.getSubstanciaAtiva())){
                        System.out.println(ia);
                    }
                }
            }
        }
    }

    /**
     * Metodo para pesquisar um contacto através de um medicamento
     * @param nome_medicamento nome do medicamento.
     * @return industria
     */
    public Industria pesquisarContacto(String nome_medicamento) {
        for (Map.Entry<String, Industria> industria: industrias.entrySet()) {
            if (industria.getValue().getMedicamentos().containsKey(nome_medicamento)){
                return industria.getValue();
            }
        }
        return null;
    }

    /**
     * Método para adicionar um medicamento ao sistema
     * @param nome nome do medicamento
     * @param forma forma do medicamento
     * @param dosagem dosagem do medicamento
     * @param s_lista lista de substâncias ativas do medicamento
     * @param laboratorio laboratório do medicamento
     * @return String com a informação se o medicamento foi adicionado com sucesso ou não
     */
    public String adicionarMedicamento(String nome, String forma, String dosagem, ArrayList<SubstanciaAtiva> s_lista, String laboratorio) {
        if (!verificaMedicamento(nome+dosagem)) {
            Medicamento m = medicamento_handler.criarMedicamento(nome, forma, dosagem, s_lista);
            medicamentos.put(nome + dosagem, m);
            if (industrias.containsKey(laboratorio)) {
                Industria in = industrias.get(laboratorio);
                in.setMedicamentos(m);
                industrias.put(in.getPrimeiro_nome(), in);
            }
            return "Medicamento adicionado com sucesso";
        } else{
            return "Medicamento já existe";
        }
    }

    /**
     * Método para adicionar uma interação alimentar ao sistema
     * @param substancia_ativa substância ativa
     * @param explicacao explicação da interação alimentar
     * @param alimento alimento envolvido na interação alimentar
     * @param efeito efeito provocado pela interação alimentar
     * @param nivel_efeito nível do efeito provocado pela interação alimentar
     * @param referencia referência bibliográfica
     * @return String com a informação se a interação alimentar foi adicionada com sucesso ou não
     */
    public String adicionarInteracaoAlimentar(SubstanciaAtiva substancia_ativa, String explicacao, String alimento, String efeito, int nivel_efeito, String referencia) {
        InteracaoAlimentar ia = interacao_alimentar_handler.criarInteracaoAlimentar(substancia_ativa, explicacao, alimento, efeito, nivel_efeito, referencia);
        if (!verificaInteracao(ia)){
            interacoes_alimentares.add(ia);
            return "Interação alimentar adicionada com sucesso";
        }else {
            return "A interação alimentar já existe";
        }
    }

    /**
     * Metodo para adicionar substância ativa.
     * @param nome, nome da substância
     * @return String, confirmação caso seja adicionada, ou de já existir.
     */
    public String adicionarSubstanciaAtiva(String nome) {
        if (!verificaSubstancia(nome)){
            SubstanciaAtiva s = substancia_ativa_handler.criarSubstancia(nome);
            substancias_ativas.put(nome, s);
            return "Substância ativa adicionada com sucesso";
        } else {
            return "Substância ativa já existe";
        }
    }

    public void login(String email, String password) {

    }

    public void registar(String primeiro_nome, String ultimo_nome, String email, String password, String papel, String tipo) {

    }

    /**
     * Método para verificar se um medicamento existe no sistema ou não
     * @param nome_medicamento nome do medicamento
     * @return true se o medicamento existir, false caso contrário
     */
    public boolean verificaMedicamento(String nome_medicamento) {
        return medicamentos.containsKey(nome_medicamento);
    }

    /**
     * Método para verificar se uma substância ativa existe no sistema ou não
     * @param nome_substancia nome da substância ativa
     * @return true se a substância ativa existir, false caso contrário
     */
    public boolean verificaSubstancia(String nome_substancia) {
        return substancias_ativas.containsKey(nome_substancia);
    }

    /**
     * Método para verificar se uma interação alimentar existe no sistema ou não
     * @param ia interação alimentar
     * @return true se a interação alimentar existir, false caso contrário
     */
    public boolean verificaInteracao(InteracaoAlimentar ia) {
        for (InteracaoAlimentar interacaoAlimentar: interacoes_alimentares) {
            if (interacaoAlimentar.equals(ia)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaUtilizador(String email) {
        return false;
    }

}
