package business;

/**
 * Uma interface que define métodos para gerar relatórios relacionados às mídias e aos clientes.
 */
public interface IRelatorio {
    
     /**
     * Gera um relatório contendo informações sobre o cliente com mais mídias assistidas.
     *
     * @return o relatório do cliente com mais mídias assistidas.
     */
    public String clienteComMaisMidias();

     /**
     * Gera um relatório contendo informações sobre o cliente com mais avaliações realizadas.
     *
     * @return o relatório do cliente com mais avaliações realizadas.
     */
    public String clienteComMaisAvaliacoes();

     /**
     * Gera um relatório contendo a porcentagem de clientes que possuem pelo menos 15 avaliações.
     *
     * @return o relatório da porcentagem de clientes com pelo menos 15 avaliações.
     */
    public String porcentagemClientesComPeloMenos15Avaliacoes();

     /**
     * Gera um relatório com as 10 melhores mídias com base em suas avaliações.
     *
     * @return o relatório das 10 melhores mídias com base nas avaliações.
     */
    public String top10MidiasMelhorAvaliacao();

     /**
     * Gera um relatório com as 10 mídias mais visualizadas.
     *
     * @return o relatório das 10 mídias mais visualizadas.
     */
    public String top10MidiasMaisVisualizacoes();

     /**
     * Gera um relatório com as 10 melhores mídias de um gênero específico com base em suas avaliações.
     *
     * @param genero o gênero das mídias a serem consideradas no relatório.
     * @return o relatório das 10 melhores mídias do gênero especificado.
     */
    public String top10MidiasMelhorAvaliacaoPorGenero(String genero);

     /**
     * Gera um relatório com as 10 mídias mais visualizadas de um gênero específico.
     *
     * @param genero o gênero das mídias a serem consideradas no relatório.
     * @return o relatório das 10 mídias mais visualizadas do gênero especificado.
     */
    public String top10MidiasMaisVisualizacoesPorGenero(String genero);
}