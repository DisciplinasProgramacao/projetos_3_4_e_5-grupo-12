package business;

public interface IRelatorio {
    public String clienteComMaisMidias();
    public String clienteComMaisAvaliacoes();
    public double porcentagemClientesComPeloMenos15Avaliacoes();
    public String top10MidiasMelhorAvaliacao();
    public String top10MidiasMaisVisualizacoes();
    public String top10MidiasMelhorAvaliacaoPorGenero();
    public String top10MidiasMaisVisualizacoesPorGenero();
}