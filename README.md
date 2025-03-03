# 🚀 TaskFlow - Board de Tarefas com Gamificação e Colaboração em Tempo Real

Bem-vindo ao **TaskFlow**, um projeto inovador desenvolvido com Spring Boot que combina gerenciamento de tarefas, gamificação e colaboração em tempo real via WebSocket. Este aplicativo permite criar, gerenciar e atualizar tarefas, atribuindo pontos aos usuários como parte de um sistema de gamificação, enquanto oferece atualizações instantâneas por meio de WebSocket para uma experiência colaborativa.

## 🌟 Sobre o Projeto

O **TaskFlow** é uma aplicação backend projetada para ajudar equipes a gerenciar tarefas de maneira eficiente, incentivando a produtividade com um sistema de pontos (gamificação) e permitindo colaboração em tempo real. As principais funcionalidades incluem:

- **Criação e Atualização de Tarefas**: Crie tarefas com títulos, pontos e associações a usuários, e atualize seus status (ex.: "To Do", "Done").
- **Gamificação**: Ganhe pontos ao concluir tarefas, que são acumulados pelos usuários.
- **Colaboração em Tempo Real**: Use WebSocket para receber atualizações instantâneas sobre mudanças nas tarefas.
- **Persistência de Dados**: Integração com MySQL para armazenar tarefas e usuários, gerenciada por Liquibase para migrações de banco de dados.

## 🛠 Tecnologias Utilizadas

| Tecnologia               | Versão       | Descrição                                      |
|--------------------------|--------------|------------------------------------------------|
| Java                     | 21.0.6       | Linguagem principal                            |
| Spring Boot              | 3.4.3        | Framework para o backend (REST, WebSocket, JPA) |
| MySQL                    | 9.2.0        | Banco de dados relacional                      |
| Liquibase                | Última       | Ferramenta para gerenciamento de migrações SQL |
| Maven                    | Última       | Gerenciador de dependências e build            |
| Lombok                   | Última       | Redução de boilerplate em Java                 |

## 🚀 Como Usar

### Pré-requisitos
- **JDK 21** instalado.
- **MySQL Server 9.2** rodando localmente.
- **Eclipse** ou outra IDE compatível com Spring Boot.
- **Postman** (ou similar) para testar os endpoints.
- **wscat** (instalável via `npm install -g wscat`) para testar o WebSocket.

### Configuração
1. **Clone o Repositório:**
   ```bash
   git clone https://github.com/seu-usuario/TaskFlow.git
   cd TaskFlow
   ```

2. **Configure o MySQL:**
   Crie o banco de dados taskflow:
   ```sql
   CREATE DATABASE taskflow;
   ```
   Certifique-se de que o serviço MySQL (MySQL92) está rodando:
   ```bash
   net start MySQL92
   ```

3. **Configure o application.properties:**
   Edite src/main/resources/application.properties com suas credenciais do MySQL:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/taskflow?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=sua-senha
   spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
   spring.jpa.hibernate.ddl-auto=none
   spring.jpa.show-sql=true
   spring.liquibase.change-log=classpath:db/changelog/db.changelog-master.yaml
   server.port=8080
   ```

4. **Rode o Projeto:**
   No Eclipse, clique com o botão direito em TaskFlowApplication.java > Run As > Spring Boot App.
   Alternativamente, use o Maven:
   ```bash
   mvn spring-boot:run
   ```

### Testes
1. **Endpoints REST:**
   Use o Postman para testar:
   - Criar uma tarefa:
     ```
     POST http://localhost:8080/api/tasks?title=Estudar Java&points=10&userId=1
     ```
   - Atualizar o status:
     ```
     PUT http://localhost:8080/api/tasks/1/status?status=Done
     ```

2. **WebSocket:**
   Conecte-se para atualizações em tempo real:
   ```bash
   wscat -c ws://localhost:8080/task-updates
   ```
   Crie ou atualize tarefas via Postman e veja as mensagens no terminal wscat.

## 📋 Estrutura do Projeto
```
TaskFlow/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/taskflow/
│   │   │       ├── controller/        # Controladores REST
│   │   │       ├── model/            # Entidades JPA (User, Task)
│   │   │       ├── repository/       # Repositórios JPA
│   │   │       ├── service/          # Serviços de negócios
│   │   │       └── websocket/        # Configurações WebSocket
│   │   └── resources/
│   │       ├── application.properties # Configurações do Spring Boot
│   │       └── db/changelog/         # Arquivos de migração do Liquibase
│   └── test/
│       └── java/                     # Testes unitários e de integração
├── pom.xml                           # Dependências Maven
└── README.md                         # Este arquivo
```


## 📧 Contato
Email: leonardomedd@gmail.com  
LinkedIn: https://www.linkedin.com/in/leonardo-medeiros-de-almeida-996302254/

Sinta-se à vontade para entrar em contato para colaborações, dúvidas ou sugestões! 😊

## 🎉 Agradecimentos
Agradeço à comunidade Spring, ao suporte do MySQL e às ferramentas como Eclipse e Maven que tornaram este projeto possível. Obrigado também ao Grok 3 da xAI por ajudar no desenvolvimento! 🚀
