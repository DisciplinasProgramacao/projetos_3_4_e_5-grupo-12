package business;

import java.util.*;

public final class App {

    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        PlataformaStreaming plat = new PlataformaStreaming("Netflix");
        HashMap<String, Cliente> mapClientes = plat.carregarClientes();
        HashMap<Integer, Serie> mapSeries = plat.carregarSeries();

        plat.setClientes(mapClientes);
        plat.setSeries(mapSeries);

        Cliente clienteLogado = plat.login("Ada2", "AArr25751");
        System.out.println("Logado como: " + clienteLogado);
        System.out.println();

        clienteLogado.adicionarSerieVista(mapSeries.get(3459));
        clienteLogado.adicionarSerieVista(mapSeries.get(3460));
        clienteLogado.adicionarSerieVista(mapSeries.get(3462));
        clienteLogado.adicionarSerieVista(mapSeries.get(3465));

        clienteLogado.adicionarNaLista(mapSeries.get(3514));
        clienteLogado.adicionarNaLista(mapSeries.get(3560));
        clienteLogado.adicionarNaLista(mapSeries.get(3518));
        clienteLogado.adicionarNaLista(mapSeries.get(3533));

        System.out.println("Series já vistas: " + clienteLogado.getListaJaVista().toString());
        System.out.println();
        System.out.println("Series para ver: " + clienteLogado.getListaParaVer().toString());

        List<Serie> comediaVisto = new LinkedList<>();
        comediaVisto = clienteLogado.filtrarPorGenero("comedia");
        System.out.println();
        System.out.println("Series de comedia vistas: " + comediaVisto);

        List<Serie> portuguesVisto = new LinkedList<>();
        portuguesVisto = clienteLogado.filtrarPorIdioma("portugues");
        System.out.println();
        System.out.println("Series em portugues vistas: " + portuguesVisto);

        menu(plat);

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

    // Metodo para testar carregamento de filmes
    public static void teste() throws Exception {

        PlataformaStreaming p = new PlataformaStreaming("Netflix");

        HashMap<Integer, Filme> mapFilmes = p.carregarFilmes();

        for (Integer name : mapFilmes.keySet()) {
            String key = name.toString();
            String value = mapFilmes.get(name).toString();
            System.out.println(key + " " + value);
        }

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

    public static void menu(PlataformaStreaming plat) {

        int op;
        do {
            Cliente clienteCad;
            Filme filmeCad;
            Serie serieCad;
            System.out.println("Selecione uma opção:");
            System.out.println("1 - Cadastrar um cliente");
            System.out.println("2 - Cadastrar uma série");
            System.out.println("3 - Cadastrar um filme");
            System.out.println("0 - Sair");
            op = entrada.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Opção 1 selecionada.");
                    clienteCad = cadastrarCliente(plat);
                    break;

                case 2:
                    System.out.println("Opção 2 selecionada.");
                    plat = cadastrarSerie(plat);
                    break;

                case 3:
                    System.out.println("Opção 3 selecionada.");
                    plat = cadastrarFilmSerie(plat);
                    break;

                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        } while (op != 0);
    }

    private static Cliente cadastrarCliente(PlataformaStreaming plat) {
        String nome, senha;
        entrada.nextLine();
        System.out.println("Digite seu nome de usuario: ");
        nome = entrada.nextLine();
        System.out.println("Digite a sua senha: ");
        senha = entrada.nextLine();

        Cliente clienteCad = new Cliente(nome,senha);
        return clienteCad;
    }

    private static PlataformaStreaming cadastrarSerie(PlataformaStreaming plat) {

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

        Serie serieCad = new Serie(genero, nome, idioma, qtdEpisodios);
        plat.adicionarSerie(serieCad);
        return plat;
    }

    private static PlataformaStreaming cadastrarFilmSerie(PlataformaStreaming plat) {

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

        Filme filmeCad = new Filme(genero, nome, idioma, duracao);
        plat.adicionarFilme(filmeCad);

        return plat;
    }

}
