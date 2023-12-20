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
import com.google.gson.reflect.TypeToken;

import javax.annotation.processing.SupportedSourceVersion;
import javax.swing.text.html.parser.Parser;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Sistema {
    private TreeMap<String, SubstanciaAtiva> substancias_ativas;
    private TreeMap<String, Medicamento> medicamentos;
    private ArrayList<InteracaoAlimentar> interacoes_alimentares;
    private HashMap<String, Industria> industrias;
    private HashMap<String, Utilizador> utilizadores;
    private final String[] tipos_utilizador = {"Industria", "Farmaceutico", "Administrador"};
    private Utilizador utilizador_atual;
    private ArrayList<String> alimentos;
    private MedicamentoHandler medicamento_handler;
    private SubstanciaHandler substancia_ativa_handler;
    private InteracaoHandler interacao_alimentar_handler;
    private RegistHandler regist_handler;
    private final HashMap<Integer, String> niveis = new HashMap<>();

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

    public Utilizador getUtilizador_atual() {
        return utilizador_atual;
    }
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
                if (!verificaSubstancia(substancia_nome)) {
                    s = substancia_ativa_handler.criarSubstancia(substancia_nome);
                    substancias_ativas.put(substancia_nome, s);
                } else {
                    s = substancia_ativa_handler.criarSubstancia(substancia_nome);
                }
                adicionarInteracaoAlimentar(s, explicacao, alimento, efeito, level, referencia);
            }
            // Indústrias
            for (int i = 0; i < laboratories.size(); i++) {
                int contato;
                String name = laboratories.get(i).getAsJsonObject().get("Name").getAsString();
                if (laboratories.get(i).getAsJsonObject().get("Surveillance").getAsString().equals("")){
                    contato = 0;
                }else {
                    contato = laboratories.get(i).getAsJsonObject().get("Surveillance").getAsInt();
                }
                Industria in = regist_handler.registarIndustria(name, "", "", "Industria", contato);
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
                if (!verificaSubstancia(substancia)) {
                    s = substancia_ativa_handler.criarSubstancia(substancia);
                    substancias_ativas.put(substancia, s);
                } else {
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
            System.out.println("ok");
        }
    }

    public void shutdown() {

    }

    public void listarInteracoesAlimentares() {

    }

    public void listarSubstanciasAtivas(int max) {
        for (int i = max - 10; i < max; i++) {
            System.out.println(substancias_ativas.values());
        }
    }

    public void listarMedicamentos() {
    }

    public void pesquisarInteracoes(String nome_medicamento) {
        for (Medicamento m: medicamentos.values()) {
            if (m.getNome().equals(nome_medicamento)){
                ArrayList<SubstanciaAtiva> s = m.getSubstanciaAtivas();
                for (InteracaoAlimentar ia: interacoes_alimentares){
                    if (s.contains(ia.getSubstanciaAtiva())){
                        System.out.println(s.contains(ia.getSubstanciaAtiva()));
                        System.out.println(ia);
                    }
                }
            }
        }
    }

    public Integer pesquisarContacto(String nome_medicamento) {
        for (Map.Entry<String, Industria> industria: industrias.entrySet()) {
            if (industria.getValue().getMedicamentos().containsKey(nome_medicamento)){
                return industria.getValue().getContacto();
            }
        }
        return 0;
    }

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

    public String adicionarInteracaoAlimentar(SubstanciaAtiva substancia_ativa, String explicacao, String alimento, String efeito, int nivelEfeito, String referencia) {
        InteracaoAlimentar ia = interacao_alimentar_handler.criarInteracaoAlimentar(substancia_ativa, explicacao, alimento, efeito, nivelEfeito, referencia);
        if (!verificaInteracao(ia)){
            interacoes_alimentares.add(ia);
            return "Interação alimentar adicionada com sucesso";
        }else{
            return "A interação alimentar já existe";
        }
    }

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

    public boolean verificaMedicamento(String nome_medicamento) {
        return medicamentos.containsKey(nome_medicamento);
    }

    public boolean verificaSubstancia(String nome_substancia) {
        return substancias_ativas.containsKey(nome_substancia);
    }

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
