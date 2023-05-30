package business;

import java.util.*;

public class App {

    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        PlataformaStreaming plat = new PlataformaStreaming("Netflix");

        menu(plat);
    }

    public static void menu(PlataformaStreaming plat) throws Exception {


        //mudar pro construtor da plataforma e deixar metodos privados - olhar ordem

        int op;
        do {
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Cadastrar um cliente");
            System.out.println("2 - Cadastrar uma série");
            System.out.println("3 - Cadastrar um filme");
            System.out.println("4 - Fazer login");
            System.out.println("0 - Sair");
            op = entrada.nextInt();
            switch (op) {
                case 1:
                    cadastrarCliente(plat);
                    break;

                case 2:
                    cadastrarSerie(plat);
                    break;
                case 3:
                    cadastrarFilme(plat);
                    break;

                case 4:
                    fazerLogin(plat);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;

                case 0:
                    break;

            }
        } while (op != 0);

    }

    // tratar excecao com try catch dps
    private static void cadastrarCliente(PlataformaStreaming plat) throws Exception {

        System.out.println("Opção 1 selecionada.");
        System.out.println();

        String nomeCompleto, nomeDeUsuario, senha;

        entrada.nextLine();

        System.out.println("Digite seu nome completo: ");
        nomeCompleto = entrada.nextLine();

        System.out.println("Digite seu nome de usuario: ");
        nomeDeUsuario = entrada.nextLine();

        System.out.println("Digite a sua senha: ");
        senha = entrada.nextLine();

        try {
            plat.adicionarCliente(nomeCompleto, nomeDeUsuario, senha);
        } catch (ClienteInvalidoException e) {
            System.out.println(e.getMessage());
        }
        
    }

    // Da pra juntar parte do cadastrarSerie e cadastrar filme olhar depois
    private static void cadastrarSerie(PlataformaStreaming plat) {

        System.out.println("Opção 2 selecionada.");
        System.out.println();
        entrada.nextLine();

        String nome, idioma, genero;
        int qtdEpisodios;
        System.out.println("Digite o nome da série");
        nome = entrada.nextLine();

        System.out.println("Digite o idioma da série");
        idioma = entrada.nextLine();

        System.out.println("Digite o genero da série");
        genero = entrada.nextLine();

        System.out.println("Digite a quantidade de episódios que a série possui");
        qtdEpisodios = entrada.nextInt();

        try {
            plat.adicionarSerie(nome, idioma, genero, qtdEpisodios);
        } catch (MidiaInvalidaException e) {
            System.out.println(e.getMessage());
        } 
    }

    private static void cadastrarFilme(PlataformaStreaming plat) {

        System.out.println("Opção 3 selecionada.");
        System.out.println();
        entrada.nextLine();

        String nome, idioma, genero;
        int duracao;
        System.out.println("Digite o nome do filme");
        nome = entrada.nextLine();

        System.out.println("Digite o idioma do filme");
        idioma = entrada.nextLine();

        System.out.println("Digite o genero do filme");
        genero = entrada.nextLine();

        System.out.println("Digite a duração do filme");
        duracao = entrada.nextInt();

        try {
            plat.adicionarFilme(nome, idioma, genero, duracao);
        } catch (MidiaInvalidaException e){
            System.out.println(e.getMessage());
        }
        

        System.out.println("Filme cadastrado com sucesso!");
    }

    private static void fazerLogin(PlataformaStreaming plat) {

        System.out.println("Opção 4 selecionada.");
        entrada.nextLine();

        System.out.println("Insira o nome de usuario:");
        String nomeUsuario = entrada.nextLine();

        System.out.println("Insira a senha:");
        String senha = entrada.nextLine();

        try {
            plat.login(nomeUsuario, senha);
            System.out.println("Logado com sucesso");     
        } catch (ClienteInvalidoException e) {
            System.out.println(e.getMessage());
        }

        menu2(plat);
        
    }

    private static void menu2(PlataformaStreaming plat) {

        int op;

        do {

            System.out.println("Bem vindo de volta!");
            System.out.println("O que deseja fazer?");

            System.out.println("1 - Adicionar midia para assistir");
            System.out.println("2 - Adicionar Midia ja assistida"); // pendente
            System.out.println("3 - Ver midias assistidas");
            System.out.println("4 - Ver midia para assistir");
            System.out.println("9 - Deslogar");

            System.out.println("0 - Sair");
            op = entrada.nextInt();

            switch (op) {
                case 1:
                    adicionarMidiaParaAssistir(plat);
                    break;
                case 2:
                    adicionarMidiaAssistida(plat);
                    break;

                case 3:
                    verMidiasAssistidas(plat);
                    break;
                case 4:
                    verMidiasParaAssistir(plat);
                    break;
            }

        } while (op != 0);
    }

    public static void adicionarMidiaParaAssistir(PlataformaStreaming plat) {

        entrada.nextLine();
        System.out.println("Digite o nome da midia que você deseja adicionar:");
        String nomeMidiaAVer = entrada.nextLine();

        try {
            plat.adicionarMidiaParaAssistir(nomeMidiaAVer);
        } catch (MidiaInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void verMidiasAssistidas(PlataformaStreaming plat) {

        System.out.println("Lista de mídia assistida:");
        System.out.println(plat.getListaJaVista());
    }

    public static void verMidiasParaAssistir(PlataformaStreaming plat) {

        System.out.println("Lista de mídia assistida:");
        System.out.println(plat.getListaParaAssistir());
    }

    public static void adicionarMidiaAssistida(PlataformaStreaming plat) {

        entrada.nextLine();
        System.out.println("Digite o nome da Midia que você deseja adicionar:");
        String nomeMidia = entrada.nextLine();
    
        submenu(plat, nomeMidia); // fazer verificacao se a serie existe

    }

    public static void submenu(PlataformaStreaming plat, String nomeMidia) {

        int op;

        System.out.println("Você gostaria de avaliar essa serie:");
        System.out.println("1 - Sim");
        System.out.println("2 - Não");
        try {
            System.out.println("Nota media de " + nomeMidia + ": " + plat.getNotaMedia(nomeMidia));
        } catch (MidiaInvalidaException e ) {
            System.out.println(e.getMessage());
        }

        op = entrada.nextInt();
        
        switch (op) {
            case 1:
                avaliarMidia(plat, nomeMidia);
                break;
            case 2:
                try {
                    plat.adicionarMidiaVista(nomeMidia);
                } catch (MidiaInvalidaException e) {
                    System.out.println(e.getMessage());
                }
                break;
        }

    }

    private static void avaliarMidia(PlataformaStreaming plat, String nomeMidia) {

        float nota;
        
        System.out.println("Digite a nota para essa midia:");
        nota = entrada.nextFloat();
        entrada.nextLine();

        try {
            plat.adicionarAvaliacao(nomeMidia, nota);

        } catch (AvaliacaoInvalidaException | MidiaInvalidaException | ClienteInvalidoException e) {
            System.out.println(e.getMessage());
        }
        
        
        if(plat.eEspecialista()) {
            System.out.println("Gostaria de deixar um comentário?");
            System.out.println("1- Sim");
            System.out.println("2- Não");
            int resposta = entrada.nextInt();
    
            if (resposta == 1) {
                System.out.println("Qual é seu comentário para essa midia?");
                entrada.nextLine();
                String comentario = entrada.nextLine();

                try {
                    plat.comentar(comentario, nomeMidia);
                } catch (MidiaInvalidaException | ClienteInvalidoException | AvaliacaoInvalidaException e) {
                    System.out.println(e.getMessage());
                }
                 
            } 
        }
        
        try {
            plat.getAVALIACAO(nomeMidia);;
        } catch (MidiaInvalidaException e) {
            System.out.println(e.getMessage());
        }
        
    }
}