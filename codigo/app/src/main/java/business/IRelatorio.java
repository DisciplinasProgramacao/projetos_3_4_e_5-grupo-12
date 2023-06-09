package business;

public interface IRelatorio {
    public String clienteComMaisMidias();

    public String clienteComMaisAvaliacoes();

    public String porcentagemClientesComPeloMenos15Avaliacoes();

    public String top10MidiasMelhorAvaliacao();

    public String top10MidiasMaisVisualizacoes();

    public String top10MidiasMelhorAvaliacaoPorGenero(String genero);

    public String top10MidiasMaisVisualizacoesPorGenero(String genero);
}