package business;

import java.util.HashMap;



public final class App {

    public static void main(String[] args) throws Exception {
        PlataformaStreaming plat = new PlataformaStreaming("Netflix");
        HashMap<String, Cliente> mapClientes = plat.carregarClientes();
        System.out.println(mapClientes); 

        HashMap<Integer, Serie> mapSeries = plat.carregarSeries();
        System.out.println(mapSeries);

        /* 

            Cliente c = new Cliente("Pedro", "123dfdasfas");
            Cliente c2 = new Cliente("bb", "123dsasagfas");
            Cliente c3 = new Cliente("aa", "123aaa");
            Serie s1 = new Serie("Terror", "Go", "P", 1);
            Serie s2 = new Serie("Terror", "Goss", "b", 2);
            Serie s3 = new Serie("Terror", "Gott", "a", 3);
            PlataformaStreaming pS = new PlataformaStreaming("Netflix", c);
            pS.adicionarSerie(s1);
            pS.adicionarSerie(s2);
            pS.adicionarSerie(s3);
            pS.adicionarCliente(c);
            pS.adicionarCliente(c2);
            pS.adicionarCliente(c3);

            escreveArqEspectadores(pS);
            escreveArqSeries(pS);
            escreveArqAudiencia(pS);

        */
       
    }
    
    /*
        public static void escreveArqEspectadores(PlataformaStreaming pS) {
        String caminho = "Espectadores.csv";
        for (int i = 0; i < pS.getClientes().size(); i++) {
            try {
                FileWriter fw = new FileWriter(caminho, true);
                BufferedWriter bw = new BufferedWriter(fw);
                String line = pS.getClientes().get(i).getNomeDeUsuario() + ";" + pS.login() + ";" + pS.getClientes().get(i).getSenha();
                bw.write(line);
                bw.newLine();
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void escreveArqSeries(PlataformaStreaming pS) {
        String caminho = "Séries.csv";
        for (int i = 0; i < pS.getSeries().size(); i++) {
            try {
                FileWriter fw = new FileWriter(caminho, true);
                BufferedWriter bw = new BufferedWriter(fw);
                String line = pS.getSeries().get(i).getId() + ";" + pS.getSeries().get(i).getNome() + ";" + pS.getSeries().get(i).getdata();
                bw.write(line);
                bw.newLine();
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void escreveArqAudiencia(PlataformaStreaming pS) {
        String caminho = "Audiência.csv";
        for (int i = 0; i < pS.getSeries().size(); i++) {
            try {
                FileWriter fw = new FileWriter(caminho, true);
                BufferedWriter bw = new BufferedWriter(fw);
                Cliente cliente = pS.getClienteAtual();
                String tipo = tipoAssistir(cliente, pS.getSeries().get(i).getId(), pS);
                String line = pS.login() + ";" + tipo + ";" + pS.getSeries().get(i).getId();
                bw.write(line);
                bw.newLine();
                bw.close();
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String tipoAssistir(Cliente cliente, int id, PlataformaStreaming pS){
        Serie serie = pS.getSeries().get(id);
        if(cliente.getListaParaVer().contains(serie)){
            return "F";
        }else if(cliente.getListaJaVista().contains(serie)){
            return "A";
        }else{
            return "Série não encontrada";
        }
    }

    */

    

}
