package business;

import java.util.*;

public final class App {

    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        PlataformaStreaming plat = new PlataformaStreaming("Netflix");

        menu(plat);

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
            int op3;
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
                        mapClientes.put(clienteCad.getNomeDeUsuario(), clienteCad);
                        plat.escreveArqCliente(clienteCad);
                        System.out.println();
                        break;

                    case 2:
                        System.out.println("Opção 2 selecionada.");
                        entrada.nextLine();
                        serieCad = cadastrarSerie(plat);
                        mapSeries.put(serieCad.getId(), serieCad);
                        plat.escreveArqSerie(serieCad);
                        System.out.println("Serie cadastrado com sucesso");
                        System.out.println(serieCad);
                        System.out.println();
                        break;

                    case 3:
                        System.out.println("Opção 3 selecionada.");
                        entrada.nextLine();
                        filmeCad = cadastrarFilmSerie(plat);
                        mapFilmes.put(filmeCad.getId(), filmeCad);
                        plat.escreveArqFilme(filmeCad);
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
                            System.out.println("Erro no login: usuario nao encontrado");
                            System.out.println();
                            break;
                        }
                        clienteLogado = plat.login(nomeUsuario, senha);
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
                    System.out.println("9 - Deslogar");

                    System.out.println("0 - Sair");
                    op2 = entrada.nextInt();

                    switch (op2) {
                        case 1:
                            break;

                        case 2:

                            entrada.nextLine();
                            System.out.println("Digite o nome da Serie que você deseja adicionar:");
                            String nomeS = entrada.nextLine();
                            Serie s = plat.filtrarSeriePorNome(nomeS);
                            clienteLogado.adicionarSerieVista(s);

                            System.out.println("Você gostaria de avaliar essa serie:");
                            System.out.println("1 - SIM");
                            System.out.println("2 - Não");
                            op3 = entrada.nextInt();
                            switch (op3) {
                                case 1:
                                    float nota;

                                    
                                    if (plat.checkAvaliacaoSerie(nomeS)) {

                                        if (plat.eEspecialista()) {

                                            System.out.println("Qual a sua nota para essa serie:");
                                            nota = entrada.nextFloat();
                                            entrada.nextLine();

                                            System.out.println("Gostaria de deixar um comentário?");                       
                                            System.out.println("1- Sim");
                                            System.out.println("2- Não");
                                            int resposta = entrada.nextInt();

                                            if (resposta == 1) {
                                                System.out.println("Qual é seu comentário para essa serie:");
                                                entrada.nextLine();
                                                String comentario = entrada.nextLine();

                                                plat.setNota(clienteLogado.getNomeDeUsuario(), s.getId(), nota,
                                                        comentario);
                                            } else {
                                                plat.setNota(clienteLogado.getNomeDeUsuario(), s.getId(), nota);
                                            }

                                        } else {

                                            System.out.println("Qual a sua nota para essa serie (0 a 5):");
                                            entrada.nextLine();
                                            nota = entrada.nextFloat();
                                            while (!((nota >= 0) && (nota <= 5))) {
                                                System.out.println("Digite somente valores entre 0 e 5!");
                                                nota = entrada.nextFloat();
                                            }

                                            float teste = plat
                                                    .setNota(clienteLogado.getNomeDeUsuario(), s.getId(), nota)
                                                    .getNota();
                                            System.out.println("Nota média: " + teste);
                                        }

                                        clienteLogado.adicionarSerieVista(s);

                                    } else {
                                        System.out.println("Você já avaliou " + nomeS + "!");
                                    }
                                    break;
                                case 2:
                                    clienteLogado.adicionarSerieVista(s);
                                    break;
                            }
                            break;

                        case 3:
                            break;

                        case 4:
                            break;

                        case 5:
                            break;

                        case 6:
                            System.out.println("Digite o nome do filme que você deseja adicionar:");
                            nomeS = entrada.nextLine();
                            Filme f = plat.filtrarFilmePorNome(nomeS);
                            clienteLogado.adicionarFilmeVisto(f);

                            System.out.println("Você gostaria de avaliar esse filme:");
                            System.out.println("1 - SIM");
                            System.out.println("2 - Não");
                            op3 = entrada.nextInt();
                            switch (op3) {
                                case 1:
                                    float nota;
                                    System.out.println("Digite o nome do filme que você deseja adicionar:");
                                    nomeS = entrada.nextLine();
                                    Filme filme = plat.filtrarFilmePorNome(nomeS);
                                    

                                    if (plat.checkAvaliacaoFilme(nomeS)) {

                                        if (plat.eEspecialista()) {

                                            System.out.println("Qual a sua nota para esse filme:");
                                            nota = entrada.nextFloat();
                                            entrada.nextLine();

                                            System.out.println("Gostaria de deixar um comentário?");
                                            int resposta = entrada.nextInt();
                                            System.out.println("1- Sim");
                                            System.out.println("2- Não");

                                            if (resposta == 1) {
                                                System.out.println("Qual é seu comentário para esse filme:");
                                                entrada.nextLine();
                                                String comentario = entrada.nextLine();

                                                plat.setNota(clienteLogado.getNomeDeUsuario(), filme.getId(), nota,
                                                        comentario);
                                            } else {
                                                plat.setNota(clienteLogado.getNomeDeUsuario(), filme.getId(), nota);
                                            }

                                        } else {
                                            System.out.println("Qual a sua nota para esse filme (0 a 5):");
                                            nota = entrada.nextFloat();
                                            while (!((nota >= 0) && (nota <= 5))) {
                                                System.out.println("Digite somente valores entre 0 e 5!");
                                                nota = entrada.nextFloat();
                                            }

                                            float n = plat
                                                    .setNota(clienteLogado.getNomeDeUsuario(), filme.getId(), nota)
                                                    .getNota();
                                            System.out.println(n);
                                        }

                                        clienteLogado.adicionarFilmeVisto(filme);

                                    } else {
                                        System.out.println("Você já avaliou" + nomeS + "!");
                                    }
                                    break;
                                case 2:
                                    clienteLogado.adicionarFilmeVisto(f);
                                    break;
                            }
                            break;

                        case 7:
                            break;

                        case 8:
                            break;
                        case 9:
                            menu(plat);
                            break;
                        case 10:
                            clienteLogado = plat.getQtdAvaliacoes();
                            System.out.println(plat.eEspecialista());
                        break;
                    }

                } while (op2 != 0);
            }

        } catch (ClienteInvalidoException e1) {
            System.out.println(e1.getMessage());
            System.out.println();
            menu(plat);
        } catch (SerieInvalidaException e2) {
            System.out.println(e2.getMessage());
            System.out.println();
            menu(plat);
        } catch (InputMismatchException e3) {
            System.out.println("Ocorreu um erro no input. Verifique se inseriu a informacao correta e tente novamente");
        } catch (FilmeInvalidoException e4) {
            System.out.println(e4.getMessage());
            System.out.println();
            menu(plat);
        } catch (NomeUsuarioException e5) {
            System.out.println(e5.getMessage());
            System.out.println();
            menu(plat);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Cliente cadastrarCliente(PlataformaStreaming plat) throws ClienteInvalidoException {
        String nomeCompleto, nome, senha;
        entrada.nextLine();

        System.out.println("Digite seu nome completo: ");
        nomeCompleto = entrada.nextLine();

        System.out.println("Digite seu nome de usuario: ");
        nome = entrada.nextLine();
        if (nome.length() < 5) {
            System.out.println("O nome de usuario deve ter pelo menos 5 caracteres");
            throw new ClienteInvalidoException();
        }
        System.out.println("Digite a sua senha: ");
        senha = entrada.nextLine();

        if (!checkString(senha) || senha.length() < 6) {
            System.out.println(
                    "A senha deve possuir pelo menos 6 caracteres, entre letras maiusculas, minusculas e numeros.");
            throw new ClienteInvalidoException();
        }

        Cliente clienteCad = new Cliente(nomeCompleto, nome, senha);
        plat.adicionarCliente(clienteCad);
        return clienteCad;
    }

    private static Serie cadastrarSerie(PlataformaStreaming plat) throws SerieInvalidaException {

        String nome, idioma, genero;
        int qtdEpisodios;
        System.out.println("Digite o nome da série");
        nome = entrada.nextLine();

        System.out.println("Digite o idioma da série");
        idioma = entrada.nextLine();
        if (!idioma.equals("ingles") && !idioma.equals("portugues") && !idioma.equals("espanhol")) {
            System.out.println("O idioma deve ser 'portugues', 'ingles' ou 'espanhol'");
            throw new SerieInvalidaException();
        }

        System.out.println("Digite o genero da série");
        genero = entrada.nextLine();
        if (!genero.equals("comedia") && !genero.equals("terror") && !genero.equals("romance")) {
            System.out.println("O genero deve ser 'comedia', 'terror' ou 'romance'");
            throw new SerieInvalidaException();
        }

        System.out.println("Digite a quantidade de episódios que a série possui");
        qtdEpisodios = entrada.nextInt();
        if (qtdEpisodios <= 0) {
            System.out.println("A serie deve possuir pelo menos 1 episodio");
            throw new SerieInvalidaException();
        }

        Serie serieCad = new Serie(genero, nome, idioma, qtdEpisodios);
        plat.adicionarSerie(serieCad);
        return serieCad;
    }

    private static Filme cadastrarFilmSerie(PlataformaStreaming plat) throws FilmeInvalidoException {
        String nome, idioma, genero;
        int duracao;
        System.out.println("Digite o nome do filme");
        nome = entrada.nextLine();

        System.out.println("Digite o idioma do filme");

        idioma = entrada.nextLine();
        if (!idioma.equals("ingles") && !idioma.equals("portugues") && !idioma.equals("espanhol")) {
            System.out.println("O idioma deve ser 'portugues', 'ingles' ou 'espanhol'");
            throw new FilmeInvalidoException();
        }

        System.out.println("Digite o genero do filme");
        genero = entrada.nextLine();
        if (!genero.equals("comedia") && !genero.equals("terror") && !genero.equals("romance")) {
            System.out.println("O genero deve ser 'comedia', 'terror' ou 'romance'");
            throw new FilmeInvalidoException();
        }

        System.out.println("Digite a duração do filme");
        duracao = entrada.nextInt();
        if (duracao < 1) {
            System.out.println("O filme deve possuir pelo menos 1 minuto");
            throw new FilmeInvalidoException();
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
        for (int i = 0; i < str.length(); i++) {
            ch = str.charAt(i);
            if (Character.isDigit(ch)) {
                num = true;
            } else if (Character.isUpperCase(ch)) {
                letraMaiuscula = true;
            } else if (Character.isLowerCase(ch)) {
                letraMinuscula = true;
            }
            if (num && letraMaiuscula && letraMinuscula)
                return true;
        }
        return false;
    }
}
