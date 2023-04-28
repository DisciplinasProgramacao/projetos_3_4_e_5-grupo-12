package business;

import java.util.*;

public final class App {

    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        PlataformaStreaming plat = new PlataformaStreaming("Netflix");

        menu(plat);

        // List<Serie> comediaVisto = new LinkedList<>();
        // comediaVisto = clienteLogado.filtrarPorGenero("comedia");
        // System.out.println();
        // System.out.println("Series de comedia vistas: " + comediaVisto);

        // List<Serie> portuguesVisto = new LinkedList<>();
        // portuguesVisto = clienteLogado.filtrarPorIdioma("portugues");
        // System.out.println();
        // System.out.println("Series em portugues vistas: " + portuguesVisto);

        

        /*
         * 
         * Cliente c = new Cliente("Pedro", "123dfdasfas");
         * Cliente c2 = new Cliente("bb", "123dsasagfas");
         * Cliente c3 = new Cliente("aa", "123aaa");
         * Serie s1 = new Serie("Terror", "Go", "P", 1);
         * Serie s2 = new Serie("Terror", "Goss", "b", 2);
         * Serie s3 = new Serie("Terror", "Gott", "a", 3);
         * PlataformaStreaming pS = new PlataformaStreaming("Netflix", c);
         * pS.adicionarSerie(s1);
         * pS.adicionarSerie(s2);
         * pS.adicionarSerie(s3);
         * pS.adicionarCliente(c);
         * pS.adicionarCliente(c2);
         * pS.adicionarCliente(c3);
         * 
         * escreveArqEspectadores(pS);
         * escreveArqSeries(pS);
         * escreveArqAudiencia(pS); 
         * 
         */

    }

  
    public static void menu(PlataformaStreaming plat) {

        try {     
            HashMap<String, Cliente> mapClientes = plat.carregarClientes();
            HashMap<Integer, Serie> mapSeries = plat.carregarSeries();
            HashMap<Integer, Filme> mapFilmes = plat.carregarFilmes();

            plat.setClientes(mapClientes);
            plat.setSeries(mapSeries);
            plat.setFilmes(mapFilmes);

            int op;
            int op2;
            Cliente clienteLogado = null;
            boolean logado = false;
            do {
                Cliente clienteCad; 
                Filme filmeCad;
                Serie serieCad;
                System.out.println("Selecione uma opção:");
                System.out.println("1 - Cadastrar um cliente");
                System.out.println("2 - Cadastrar uma série");
                System.out.println("3 - Cadastrar um filme");
                System.out.println("4 - Fazer login");
                System.out.println("0 - Sair");
                op = entrada.nextInt();
                switch (op) {
                    case 1:
                        System.out.println("Opção 1 selecionada.");
                        clienteCad = cadastrarCliente(plat);
                        if (clienteCad == null) {
                            throw new ClienteInvalido(clienteCad);
                        }
                        mapClientes.put(clienteCad.getNomeDeUsuario(), clienteCad);
                        System.out.println("Cliente cadastrado com sucesso");
                        System.out.println();
                        break;

                    case 2:
                        System.out.println("Opção 2 selecionada.");
                        entrada.nextLine();
                        serieCad = cadastrarSerie(plat);
                        if (serieCad == null) {
                            throw new SerieInvalida(serieCad);
                        }
                        mapSeries.put(serieCad.getId(), serieCad);
                        System.out.println("Serie cadastrado com sucesso");
                        System.out.println(serieCad);
                        System.out.println();
                        break;

                    case 3:
                        System.out.println("Opção 3 selecionada.");
                        entrada.nextLine();
                        filmeCad = cadastrarFilmSerie(plat);
                        if (filmeCad == null) {
                            throw new FilmeInvalido(filmeCad);
                        }
                        mapFilmes.put(filmeCad.getId(), filmeCad);
                        System.out.println("Filme cadastrado com sucesso");
                        System.out.println(mapFilmes);
                        System.out.println();
                        break;

                    case 4:
                        System.out.println("Opção 4 selecionada.");
                        entrada.nextLine();
                        System.out.println("Insira o nome de usuario:");
                        String nomeUsuario = entrada.nextLine();
                        System.out.println("Insira a senha:");
                        String senha = entrada.nextLine();

                        if (mapClientes.get(nomeUsuario) == null) {
                            System.out.println("Erro no login: usario nao encontrado");
                            System.out.println();
                            break;
                        }
                        clienteLogado = plat.login(nomeUsuario, senha);
                        // System.out.println("Logado como: " + clienteLogado);
                        if (clienteLogado != null) {
                            System.out.println("Logado com sucesso");
                            op = 0;
                            logado = true;
                        } else {
                            System.out.println("Erro no login: senha incorreta");
                        }
                        System.out.println();
                        break;

                    default:
                        System.out.println("Opção inválida. Tente novamente.");
                        break;

                    case 0:
                        break;

                }
            } while (op != 0);

            if (logado) {
                do {
                    System.out.println("Bem vindo de volta, " + clienteLogado.getNomeDeUsuario() + "!");
                    System.out.println("O que deseja fazer?");

                    System.out.println("1 - Adicionar serie para assistir");
                    System.out.println("2 - Adicionar serie ja assistida");
                    System.out.println("3 - Ver minhas series ja assistidas");
                    System.out.println("4 - Ver minhas series para assistir");

                    System.out.println("5 - Adicionar filme para assistir");
                    System.out.println("6 - Adicionar filme ja assistido");
                    System.out.println("7 - Ver meus filmes ja assistidos");
                    System.out.println("8 - Ver meus filmes para assistir");

                    System.out.println("0 - Sair");
                    op2 = entrada.nextInt();

                    switch (op) {
                        case 1:
                            break;


                        case 2:
                            break;


                        case 3:
                            break;


                        case 4:
                            break;   


                        case 5:
                            break;


                        case 6:
                            break;


                        case 7:
                            break;

                            
                        case 8:
                            break;
                    }

                } while (op2 != 0);
            }


        } catch (ClienteInvalido e1) {
            System.out.println(e1.getMessage());
        }  catch (SerieInvalida e2) {
            System.out.println(e2.getMessage());
        } catch (InputMismatchException e3) {
            System.out.println("Ocorreu um erro no input. Verifique se inseriu a informacao correta");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }


    private static Cliente cadastrarCliente(PlataformaStreaming plat) {
        String nome, senha;
        entrada.nextLine();
        System.out.println("Digite seu nome de usuario: ");
        nome = entrada.nextLine();
        if (nome.length() < 5) {
            System.out.println("O nome de usuario deve ter pelo menos 5 caracteres");
            return null;
        }
        System.out.println("Digite a sua senha: ");
        senha = entrada.nextLine();

        if (!checkString(senha) && senha.length() < 6) {
            System.out.println("A senha deve possuir pelo menos 6 caracteres, entre letras maiusculas, minusculas e numeros.");
            return null;
        }

        Cliente clienteCad = new Cliente(nome,senha); 
        plat.adicionarCliente(clienteCad);
        return clienteCad;
    }

    private static Serie cadastrarSerie(PlataformaStreaming plat) {

        String nome, idioma, genero;
        int qtdEpisodios;
        System.out.println("Digite o nome da série");
        nome = entrada.nextLine();

        System.out.println("Digite o idioma da série");
        idioma = entrada.nextLine();
        if (!idioma.equals("ingles") && !idioma.equals("portugues") && !idioma.equals("espanhol")) {
            System.out.println("O idioma deve ser 'portugues', 'ingles' ou 'espanhol'");
            return null;
        }

        System.out.println("Digite o genero da série");
        genero = entrada.nextLine();
        if (!genero.equals("comedia") && !genero.equals("terror") && !genero.equals("romance")) {
            System.out.println("O genero deve ser 'comedia', 'terror' ou 'romance'");
            return null;
        }

        System.out.println("Digite a quantidade de episódios que a série possui");
        qtdEpisodios = entrada.nextInt();
        if (qtdEpisodios <= 0) {
            System.out.println("A serie deve possuir pelo menos 1 episodio");
            return null;
        }

        Serie serieCad = new Serie(genero, nome, idioma, qtdEpisodios);
        plat.adicionarSerie(serieCad);
        return serieCad;
    }

    private static Filme cadastrarFilmSerie(PlataformaStreaming plat) {
        String nome, idioma, genero;
        int duracao;
        System.out.println("Digite o nome do filme");
        nome = entrada.nextLine();

        System.out.println("Digite o idioma do filme");;
        idioma = entrada.nextLine();
        if (!idioma.equals("ingles") && !idioma.equals("portugues") && !idioma.equals("espanhol")) {
            System.out.println("O idioma deve ser 'portugues', 'ingles' ou 'espanhol'");
            return null;
        }

        System.out.println("Digite o genero do filme");
        genero = entrada.nextLine();
        if (!genero.equals("comedia") && !genero.equals("terror") && !genero.equals("romance")) {
            System.out.println("O genero deve ser 'comedia', 'terror' ou 'romance'");
            return null;
        }

        System.out.println("Digite a duração do filme");
        duracao = entrada.nextInt();
        if (duracao < 1) {
            System.out.println("O filme deve possuir pelo menos 1 minuto");
            return null;
        }
                
        Filme filmeCad = new Filme(genero, nome, idioma, duracao);
        plat.adicionarFilme(filmeCad);

        return filmeCad;
    }

    private static boolean checkString(String str) {
        char ch;
        boolean letraMaiuscula = false;
        boolean letraMinuscula = false;
        boolean num = false;
        for(int i=0;i < str.length();i++) {
            ch = str.charAt(i);
            if( Character.isDigit(ch)) {
                num = true;
            }
            else if (Character.isUpperCase(ch)) {
                letraMaiuscula = true;
            } else if (Character.isLowerCase(ch)) {
                letraMinuscula = true;
            }
            if(num && letraMaiuscula && letraMinuscula)
                return true;
        }
        return false;
    }


    /*
     * public static void escreveArqEspectadores(PlataformaStreaming pS) {
     * String caminho = "Espectadores.csv";
     * for (int i = 0; i < pS.getClientes().size(); i++) {
     * try {
     * FileWriter fw = new FileWriter(caminho, true);
     * BufferedWriter bw = new BufferedWriter(fw);
     * String line = pS.getClientes().get(i).getNomeDeUsuario() + ";" + pS.login() +
     * ";" + pS.getClientes().get(i).getSenha();
     * bw.write(line);
     * bw.newLine();
     * bw.close();
     * fw.close();
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     * }
     * 
     * public static void escreveArqSeries(PlataformaStreaming pS) {
     * String caminho = "Séries.csv";
     * for (int i = 0; i < pS.getSeries().size(); i++) {
     * try {
     * FileWriter fw = new FileWriter(caminho, true);
     * BufferedWriter bw = new BufferedWriter(fw);
     * String line = pS.getSeries().get(i).getId() + ";" +
     * pS.getSeries().get(i).getNome() + ";" + pS.getSeries().get(i).getdata();
     * bw.write(line);
     * bw.newLine();
     * bw.close();
     * fw.close();
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     * }
     * 
     * public static void escreveArqAudiencia(PlataformaStreaming pS) {
     * String caminho = "Audiência.csv";
     * for (int i = 0; i < pS.getSeries().size(); i++) {
     * try {
     * FileWriter fw = new FileWriter(caminho, true);
     * BufferedWriter bw = new BufferedWriter(fw);
     * Cliente cliente = pS.getClienteAtual();
     * String tipo = tipoAssistir(cliente, pS.getSeries().get(i).getId(), pS);
     * String line = pS.login() + ";" + tipo + ";" + pS.getSeries().get(i).getId();
     * bw.write(line);
     * bw.newLine();
     * bw.close();
     * fw.close();
     * } catch (IOException e) {
     * e.printStackTrace();
     * }
     * }
     * }
     * 
     * public static String tipoAssistir(Cliente cliente, int id,
     * PlataformaStreaming pS){
     * Serie serie = pS.getSeries().get(id);
     * if(cliente.getListaParaVer().contains(serie)){
     * return "F";
     * }else if(cliente.getListaJaVista().contains(serie)){
     * return "A";
     * }else{
     * return "Série não encontrada";
     * }
     * }
     * 
     */

}


  