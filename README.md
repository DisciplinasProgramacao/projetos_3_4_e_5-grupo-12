[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=10825584)
# Plataforma Streamming - Grupo 12
Nesse projeto temos o objetivo de aprender java ao fazer uma plataforma de streamming contendo series, filmes, espectadores... Com diversos metodos, para salvar, cadastrar, logar, avaliar...

## Nota base: 15

### App 3/6 (5 pontos) = 2,5
	Protótipo de sistema 4 ✔✔✔ (to string)
	Robustez do protótipo 2 ❌
	
### Requisitos principais 19,3/21 + 5/6 (10 pontos) = 9
	Implementação das classes Cliente, Serie, Midia, Filme e PlataformaStreaming 2 ✔✔
	Carga de dados 2 ✔✔
	Cadastro e salvamento 2  ✔✔
	Audiência da mídia 1 ✔
	Implementação do sistema de avaliação de mídias: uma mídia tem sua avaliação média; 2 ✔✔
	Um cliente não pode avaliar a mesma mídia duas vezes; 1 ➗ (lógica para retirar:lista)
	Clientes podem ser especialistas, e estes últimos podem adicionar comentários à avaliação; 3 ✔✔ (if x cast)
	Verificação de especialistas 2  ✔✔
	Os gêneros de mídias devem ser limitados à esta lista 1 ➗➗ (contains)
	Algumas mídias serão marcadas como “Lançamento”. 1 ✔
	Estas mídias só poderão ser assistidas por clientes “profissionais” 2 ✔✔
	Clientes Profissionais também podem escrever comentários para as mídias assistidas. 2 ✔✔
	
	Relatórios 5/6 
	
		Qual cliente assistiu mais mídias, e quantas mídias; ✔
		Qual cliente tem mais avaliações, e quantas avaliações; ✔ 
		Qual a porcentagem dos clientes com pelo menos 15 avaliações; ✔
		Quais são as 10 mídias com a melhor média de avaliações, vistas pelo menos 100 vezes, decrescente; ✔
		Quais são as 10 mídias com mais visualizações, em ordem decrescente; ✔
		Estes mesmos dois últimos relatórios, porém com as mídias separadas por gênero. ❌
	
### Documentação 7/7 (5 pontos) = 5
	Documentação de código 3 ✔✔✔
	Diagrama atualizado    2 ✔✔
	Backlog 			   2 ✔✔
	
### SOLID - Descontos: 
	- Código duplicado em case. (ex, linhas 478-482 plataforma)
	- Código repetido em classe filha (media)
	- Violacao do ISP (trailer retorna 0)
	- ehTrailer x interface/cast
	
### Apresentação - Peso ou desconto

	
## Alunos integrantes da equipe

* Giovanni Bogliolo Sirihal Duarte
* Guilherme Drumond Silva
* Pedro Ramos Vidigal
* Samuel Lincoln de Oliveira Gomes
* Rafael Ferraz Barra

## Professores responsáveis

* João Caram
  

