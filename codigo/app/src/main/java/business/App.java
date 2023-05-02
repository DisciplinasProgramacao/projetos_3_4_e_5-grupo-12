package business;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
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
                        plat.escreveArqCliente(clienteCad);
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
                        plat.escreveArqSerie(serieCad);
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
        String nomeCompleto, nome, senha;
        entrada.nextLine();

        System.out.println("Digite seu nome completo: ");
        nomeCompleto = entrada.nextLine();
        
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

        Cliente clienteCad = new Cliente(nomeCompleto,nome,senha); 
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


    public static String tipoAssistir(Cliente cliente, int id,
            PlataformaStreaming pS) {
        Serie serie = pS.getSeries().get(id);
        if (cliente.getListaParaVer().contains(serie)) {
            return "F";
        } else if (cliente.getListaJaVista().contains(serie)) {
            return "A";
        } else {
            return "Série não encontrada";
        }
    }

}


  