package business;

import java.util.*;

public class App {

    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) throws Exception {

        try {
            PlataformaStreaming plat = new PlataformaStreaming("Netflix");
            menu(plat);
        } catch (ClienteInvalidoException | AvaliacaoInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void menu(PlataformaStreaming plat) throws Exception {

        // mudar pro construtor da plataforma e deixar metodos privados - olhar ordem

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
            Serie serie = new Serie(nome, idioma, genero, qtdEpisodios);
            if(eLancamento())
                plat.setLancamento(serie);

            //plat.adicionarSerie(nome, idioma, genero, qtdEpisodios);
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
            Filme filme = new Filme(nome, idioma, genero, duracao);
            if(eLancamento())
                plat.setLancamento(filme);
            //plat.adicionarFilme(nome, idioma, genero, duracao);
        } catch (MidiaInvalidaException e) {
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
            System.out.println("2 - Adicionar Midia ja assistida");
            System.out.println("3 - Ver midias assistidas");
            System.out.println("4 - Ver midia para assistir");
            System.out.println("5 - Buscar uma mídia");
            System.out.println("6 - Avaliar uma mídia");
            System.out.println("7 - Ver nota média de uma mídia");
            System.out.println("8 - Ver relatórios");
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
                case 5:
                    buscarMidia(plat);
                    break;
                case 6:
                    entrada.nextLine();
                    System.out.println("Digite o nome da midia que deseja avaliar:");
                    String nomeMidia = entrada.nextLine();
                    avaliarMidia(plat, nomeMidia);
                    break;
                case 7:
                    notaMediaMidia(plat);
                    break;
                case 8:
                    relatorios(plat);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (op != 0);
    }

    private static void relatorios(PlataformaStreaming plat) {

        int op;

        do {
            System.out.println("Selecione o relatório que você deseja ver:");

            System.out.println("1 - Qual cliente assistiu mais mídias, e quantas mídias;.");

            System.out.println("2 - Qual cliente avaliou mais mídias, e quantas mídias;.");
            
            System.out.println("0 - Sair");
            op = entrada.nextInt();

            switch (op) {
                case 1:
                clienteComMaisMidias(plat);
                    break;
                case 2:
                System.out.println(plat.clienteComMaisAvaliacoes());
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (op != 0);

    }

    private static void clienteComMaisMidias(PlataformaStreaming plat) {
        System.out.println(plat.clienteComMaisMidias());
    }

    private static void notaMediaMidia(PlataformaStreaming plat) {
        try {
            entrada.nextLine();
            System.out.println("Digite o nome da midia que você deseja ver a nota:");
            String nomeMidia = entrada.nextLine();
            System.out.printf("%.2f", plat.getNotaMedia(nomeMidia));
            ;
            System.out.println();
        } catch (MidiaInvalidaException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void buscarMidia(PlataformaStreaming plat) {

        int op;

        do {
            System.out.println("Selecione como deseja filtrar:");

            System.out.println("1 - Filtrar por gênoro.");
            System.out.println("2 - Filtrar por idioma.");
            System.out.println("3 - Filtrar por quantidade de episódios.");
            System.out.println("0 - Sair");
            op = entrada.nextInt();

            switch (op) {
                case 1:
                    filtrarPorGenero(plat);
                    break;
                case 2:
                    filtrarPorIdioma(plat);
                    break;
                case 3:
                    filtrarPorQtdEpisodios(plat);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opção inválida!");
                    break;
            }

        } while (op != 0);
    }

    private static void filtrarPorQtdEpisodios(PlataformaStreaming plat) {
        entrada.nextLine();
        System.out.println("Digite a quantidade de episódios:");
        int qtdEpisodios = entrada.nextInt();

        System.out.println(plat.filtrarPorQtdEpisodios(qtdEpisodios));
    }

    private static void filtrarPorIdioma(PlataformaStreaming plat) {

        entrada.nextLine();
        System.out.println("Digite o idioma:");
        String idioma = entrada.nextLine();

        System.out.println(plat.filtrarPorIdioma(idioma));
    }

    // ver se precisa de exception
    private static void filtrarPorGenero(PlataformaStreaming plat) {

        entrada.nextLine();
        System.out.println("Digite o genero:");
        String genero = entrada.nextLine();

        System.out.println(plat.filtrarPorGenero(genero));
    }

    public static void adicionarMidiaParaAssistir(PlataformaStreaming plat) {

        try {
            entrada.nextLine();
            System.out.println("Digite o nome da midia que você deseja adicionar:");
            String nomeMidiaAVer = entrada.nextLine();
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

        try {
            int op;

            System.out.println("Você gostaria de avaliar essa serie:");
            System.out.println("1 - Sim");
            System.out.println("2 - Não");

            op = entrada.nextInt();

            switch (op) {
                case 1:
                    plat.adicionarMidiaVista(nomeMidia);
                    avaliarMidia(plat, nomeMidia);
                    break;
                case 2:
                    plat.adicionarMidiaVista(nomeMidia);
                    break;
            }
        } catch (MidiaInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void avaliarMidia(PlataformaStreaming plat, String nomeMidia) {

        try {
            float nota;

            System.out.println("Digite a nota para essa midia:");
            nota = entrada.nextFloat();
            entrada.nextLine();

            plat.adicionarAvaliacao(nomeMidia, nota);

            if (plat.eEspecialista()) {
                System.out.println("Gostaria de deixar um comentário?");
                System.out.println("1- Sim");
                System.out.println("2- Não");
                int resposta = entrada.nextInt();

                if (resposta == 1) {
                    System.out.println("Qual é seu comentário para essa midia?");
                    entrada.nextLine();
                    String comentario = entrada.nextLine();

                    plat.comentar(comentario, nomeMidia);
                }
            }
        } catch (MidiaInvalidaException | ClienteInvalidoException | AvaliacaoInvalidaException e) {
            System.out.println(e.getMessage());
        }
    }

    private static boolean eLancamento(){
        System.out.println("Essa midia é um lançamento?");
        System.out.println("1- Sim");
        System.out.println("2- Não");
        int op = entrada.nextInt();
        return (op==1);
    }
}