[![Open in Codespaces](https://classroom.github.com/assets/launch-codespace-7f7980b617ed060a017424585567c406b6ee15c891e84e1186181d67ecf80aa0.svg)](https://classroom.github.com/open-in-codespaces?assignment_repo_id=10825584)
# Plataforma Streamming - Grupo 12
Nesse projeto temos o objetivo de aprender java ao fazer uma plataforma de streamming contendo series, filmes, espectadores... Com diversos metodos, para salvar, cadastrar, logar, avaliar...

----

## Correção Projeto 3 (branch de 02/05)

### Nota base: 13,2

### Comentários
- **ATENÇÃO**: todos os erros marcados com alerta 🚨 precisam ser corrigidos nas versões futuras. Eles ferem gravemente a modularidade e podem acarretar uma nota 0 em outras etapas.
- 🚨 **diversos sets e get sem necessidade e desprotegidos em praticamente todas as classes. Isso foi o que gerou para vocês a total desmodularidade (e um trabalho imenso que não precisava) no Main e em outras partes** 🚨
- diagrama não precisa incluir herança de exceções
- exceções não devem herdar de Exception diretamente
- 🚨 **ERRO DE POLIMORFISMO**: plataforma deve ter coleções de mídias, não separadas 🚨
- 🚨 **ERRO DE POLIMORFISMO**: cliente deve ter coleções de mídias, não separadas. Isto está gerando métodos duplicados para todas as ações (e, portanto, trabalho desnecessário que vocês tiveram) 🚨
- 🚨 **ERRO DE ENCAPSULAMENTO**: cliente não deve nem precisa devolver suas listas para ninguém 🚨
- 🚨 **ERRO DE POLIMORFISMO**: plataforma deve ter um único método adicionar mídia, não separados (trabalho desnecessário que vocês tiveram) 🚨
- carregarAudiência pode ser feito junto com clientes (e depois de mídias)
- cuidado com try/catch gigantes.

1. Aderência às classes do diagrama: 1,2/2 pontos
  - Diagrama sem relacionamento entre as classes
  - Métodos filtrar da Plataforma não estão no diagrama

2. Requisitos de corretamente implementados: 6,8/12 pontos
  - Carga de dados					1,8/2 pontos
  - Cadastro + salvar dados			2/2 pontos
  - Robustez básica					1/1 ponto
  - Clientes							0,5/2 pontos
	Listas, audiência sem repet
  - Séries							0,25/1 ponto
	- audiência
  - Filme/Herança de mídia			0,25/1 ponto
  - Buscas 							1/3 pontos
	- nome, gênero, idioma

3. Documentação de código: 4/4 pontos

4. Implementação na aula inicial: 1,2/2 pontos (cliente e série testados)
  - Registrar audiência do cliente: é só chamar o método da série
  - Série só pode guardar audiência pelo método
  - Testes não cobriram sets (que nem deviam existir)

----

## Alunos integrantes da equipe

* Guilherme Drumond Silva
* Giovanni Bogliolo Sirihal Duarte
* Pedro Ramos Vidigal
* Samuel Lincoln de Oliveira Gomes
* Rafael Ferraz Barra

## Professores responsáveis

* Nome completo do professor 1
* Nome completo do professor 2

