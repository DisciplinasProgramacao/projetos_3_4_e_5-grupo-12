package business;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
public class AppV2 {
    public static void main(String[] args) throws Exception{
        List<Cliente> listaClientes = cadastrarClientes();
        // Printando clientes cadastrados na tela
        for (int i=0; i<listaClientes.size(); i++) {
            System.out.println("Nome de usuario: " + listaClientes.get(i).getNomeDeUsuario() +" | Senha: " + listaClientes.get(i).getSenha());
        }
    }

    // Le o arquivo "POO_Espectadores.csv" e retorna uma lista de clientes cadastrados com o nome e a senha definidos no arquivo
    public static List<Cliente> cadastrarClientes() throws Exception { 
        List<Cliente> listaClientes = new LinkedList<>();
        BufferedReader reader = new BufferedReader(new FileReader("POO_Espectadores.csv"));
        String linha;
        reader.read();
        
        while ((linha = reader.readLine()) != null) {
            StringTokenizer str = new StringTokenizer(linha, ";");
            str.nextToken();
            Cliente cliente = new Cliente(str.nextToken(), str.nextToken());
            listaClientes.add(cliente);   
        }

        reader.close();
        return listaClientes;
    }
}
