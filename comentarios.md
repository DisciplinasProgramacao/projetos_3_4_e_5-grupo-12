## PROCESSOS E REQUISITOS

1. Implementação dos métodos para carga de dados de mídias, clientes e audiência (incluindo avaliações) em funcionamento;
	- devem ser chamados no construtor da plataforma: primeiro mídias, depois clientes, depois audiências. Neste último, vai localizar o cliente para inserir como visto. Depois, avaliações. Aqui localiza as mídias para inserir as avaliações.
	
2.  Salvar dados de clientes, filmes e séries;
	- pode ter um método da plataforma para isso, tipo "encerrarServico"
	
3. Implementação do sistema de avaliação de mídias: uma mídia tem sua avaliação média. Um cliente não pode avaliar a mesma mídia duas vezes;
	- um cliente quer avaliar uma mídia. No app, pergunta-se qual o nome da mídia e a nota. O app chama o método da plataforma com estes parâmetros. A plataforma conhece o cliente atual e chama o método do cliente com estes parâmetros. O cliente cria uma nova avaliação e tenta adicionar na mídia. O método de adicionar da mídia só deixa inserir se não for duplicado.	
	- a mídia pode calcular sua média fazendo um stream "average" na suas avaliações.
4. Clientes podem ser regulares ou especialistas, e estes últimos podem adicionar comentários à avaliação;
	- Cada vez que a plataforma for iniciada, o cliente deve ser verificado como especialista ou não, usando um stream contador na data em que assistiu/avaliou séries. Os que forem ganharão um objeto comentarista no atributo que vocês criaram.
	- Se o cliente quiser avaliar, no menu do app, nada demais (item anterior).
	- Se o cliente pedir para comentar, várias alternativas: uma delas é ler o comentário e o nome da mídia, passar isso para a plataforma, e a plataforma tenta executar o método do cliente. O cliente tenta executar o método do seu objeto "meuTipo". Se ele não existir, irá causar uma exceção, que a plataforma ou o app devem capturar.
	
5.	Implementação do protótipo de sistema cobrindo os requisitos; Garantir a robustez do sistema principal.
	- lembrem-se que o app deve tratar as exceções que pode receber a cada execução de método da plataforma.
	- para os outros requisitos: filtro, adicionar etc, app deve sempre chamar um método da plataforma, que aí resolve a questão chamando seus métodos junto ao cliente atual. 

6.	algumas mídias serão marcadas como “Lançamento”. 
	- aqui me parece fácil criar um enumerador para mídias, "Regular" e "Lançamento"
	
7.	Estas mídias só poderão ser assistidas por clientes “profissionais” – aqueles que, de alguma maneira, trabalham diretamente com mídias – sejam como jornalistas, diretores, atores etc. Estes clientes também podem escrever comentários para as mídias assistidas.
	- este requisito poderia complicar bem as coisas, mas a interface de cliente ajuda muito. Dá para criar um tipo Profissional que implementa aquela interface e já resolvemos a parte do comentário. Para a parte do lançamento, eu colocaria um método booleano no enumerador da mídia, ou um método na interface do cliente: este método retornaria true ou false para o cliente indicando se pode ver a mídia. Se não puder, não pode inserir nas suas listas (ou seja, lá na inserção das listas passa a chamar esta verificação antes de inserir)

8. Os gestores, ainda pediram que a plataforma possa, em algum momento, produzir os seguintes relatórios:
	- todos os relatórios podem/devem ser gerados com stream nas respectivas coleções.

9. Gêneros válidos para mídias: Ação, Anime, Aventura, Comédia, Documentário, Drama, Policial, Romance e Suspense.
	- aqui também fácil: criar um enumerador simples para gêneros; a mídia pode conter um enumerador ou uma lista deste enumerador, e o construtor de mídia recebe um enumerador ou uma lista de enumeradores como parâmetro.


