<h1 align="center" >Netflix Console Application</h1>

<p align="center" >
     <img src="https://github.com/user-attachments/assets/cc11f144-07fd-4992-b728-0a033c2489ea">
</p>

![Badge em Desenvolvimento](http://img.shields.io/static/v1?label=STATUS&message=EM%20DESENVOLVIMENTO&color=GREEN&style=for-the-badge)

## Descrição do Projeto 🚀
Este projeto faz parte da discilplina de Java Avançado da T-Academy. A Netflix Console Application é uma aplicação em Java que simula um sistema básico de gerenciamento de usuários, perfis e conteúdo (filmes e séries) similar ao da Netflix. Esta aplicação é executada via console e permite a interação do usuário com o sistema para realizar operações básicas de CRUD (Criar, Ler, Atualizar, Excluir) em diferentes entidades.

## Como usar
- Clone a main do projeto
- Suba o container
- Usuário para logar
  - Nome:leovano@example.com
  - Senha:1234
### Subindo container
```docker-compose up ```

### Parando o container
```docker-compose down ```

## Funcionalidades
- **Usuários**
  - Registro de novos usuários
  - Login de usuários existentes
  - Gerenciamento de perfis associados a cada usuário
  - Senha salva com Hash Argon2
 
- **Perfis**
  - Criação, edição e exclusão de perfis
 
- **Filmes**
  - Adição de novos filmes
  - Remoção de filmes existentes
  - Busca de filmes por categoria

- **Séries**
  - Adição de novas séries
  - Remoção de séries existentes
  - Busca de séries por categoria, nome ou ID

- **Temporadas**
  - Adição de novas temporadas a uma série
  - Busca de temporadas por série

- **Episódios**
  - Adição de novos episódios a uma temporada
  - Busca de episódios por temporada

- **Categorias**
  - Criação de novas categorias
  - Busca de categorias

- **Assistidos**
  - Marcar filmes e séries como assistidos
  - Visualizar lista de filmes e séries assistidos

## Estrutura do Projeto
- **Entity**: Contém as classes de entidade que representam os dados do domínio.
- **Repository**: Contém as classes que gerenciam a persistência de dados.
- **Factory**: Contém a classe que fornece uma interface para criar objetos.
- **UI.TUI**: Contém as classes que interagem com o usuário através da linha de comando.

## Relatório Diário de Desenvolvimento

### Quinta-Feira 
- Definidos os requisitos do sistema.
- Criados os diagramas UML para a estrutura do sistema.
- Feita a configuração inicial do banco de dados.

### Sexta-Feira
- Definição da arquitetura do projeto, estrutura dos diretórios.
- Criação de um esqueleto básico do projeto com classes vazias para as entidades principais.
- Configuração do Hibernate.
- Continuação do desenvolvimento do banco de dados.
- Atualização do README.md com a descrição inicial do projeto e as funcionalidades básicas.

### Sábado
- Implementação das entidades com seus atributos e métodos básicos.
- Descrição de uma história de usuário.
- Reestruturação para um aplicativo de console simples. 
- Implementação deste console.
- Reestruturação dos diretórios, foram excluídas as classes services não utilizadas
- Implementação de um loop básico para o funcionamento do sistema.
- Implementação de login, registro e seleção de perfil

### Domingo
- Implementada a interface do usuário (TUI) para interagir via console.
- Implementação de métodos para buscar por categoria e buscar por título.
- Implementação de métodos para criar, editar e excluir perfis 
- Implementação recursos do menu principal, categorias e descrições de séries
- Inserção de amostra de dados
- Testada a aplicação manualmente para garantir a funcionalidade.

### Segunda-Feira
- Implementar a funcionalidade de marcar filmes e séries como assistidos.
- Refatorar o código para melhorar a organização, legibilidade e eficiência.
- Implementação de séries e filmes assistidos.
- Garantir que todas as classes e métodos estejam bem documentados.
- Implementação do Hash com Argon2
- Como usar no Readme.
- Implementação de mais dados no BD.

## Tecnologias Utilizadas 🖥
- Java
- InteliJ IDEA
- Maven
- Hibernate
- PostgreSQL
- Docker
- Docker Compose

## Desenvolvedores 👩‍💻
| [<img src="https://avatars.githubusercontent.com/u/13321466?v=4" width=115><br><sub>Eleuvano</sub>](https://github.com/leovano)  |  [<img src="https://avatars.githubusercontent.com/u/70452464?v=4" width=115><br><sub>Emilly Santiago</sub>](https://github.com/emillysant)  |  [<img src="https://user-images.githubusercontent.com/95758069/189210989-7918de13-6172-4c19-8a59-8a2b66e64e83.jpg" width=115><br><sub>Igor Monteiro</sub>](https://github.com/igorperonico)  |  [<img src="https://avatars.githubusercontent.com/u/98565751?v=4" width=115><br><sub>Douglas Queiroz</sub>](https://github.com/douglasliman)  |
| :---: | :---: | :---: | :---: |
