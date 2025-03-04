# Integrador de Pagamentos Online

Este projeto é uma API de integração de pagamentos online desenvolvida em **Spring Boot**, utilizando **RabbitMQ** para processamento assíncrono e **PostgreSQL** como banco de dados.

## 📌 **Funcionalidades**
- Criar pagamentos para um usuário.
- Processar pagamentos utilizando RabbitMQ.
- Registrar transações e logs de pagamento.
- Consultar logs de um pagamento.

## 🚀 **Tecnologias Utilizadas**
- **Spring Boot** (JPA, Web, RabbitMQ)
- **PostgreSQL** (Banco de Dados)
- **RabbitMQ** (Mensageria)
- **Docker & Docker Compose** (Containerização)
- **Maven** (Gerenciador de Dependências)

## 📂 **Estrutura do Projeto**
```
📂 integrador-pagamentos
 ├── 📂 src/main/java/com/exemplo/pagamentos
 │    ├── 📂 action           # Exposição de APIs REST
 │    ├── 📂 domain           # Regras de negócio
 │    ├── 📂 infra            # Serviços externos
 │    ├── 📂 application      # Serviços da aplicação
 │    ├── 📂 config           # Configurações
 │    ├── 📄 Application.java # Classe principal do Spring Boot
 ├── 📂 src/main/resources
 │    ├── 📄 application.yml  # Configurações do Spring Boot
 ├── 📄 pom.xml
 ├── 📄 Dockerfile
 ├── 📄 docker-compose.yml
```

## 🛠 **Configuração do Banco de Dados e RabbitMQ (`application.yml`)
- No caminho **src/main/resources** possui um arquivo chamado **application-example.yaml**, é só mudar o nome para **application.yaml**

### Pode rodar o comando abaixo pelo terminal para realizar a mudança:
```shell
sudo mv src/main/resources/application-example.yaml src/main/resources/application.yaml
```

## 🔧 **Como Rodar o Projeto**
### 🔹 **Passo 1: Clonar o repositório**
```sh
git clone https://github.com/seuusuario/payment-integrator.git
cd payment-integrator
```

### 🔹 **Passo 2: Gerar o JAR**
```sh
mvn clean package -DskipTests
```

### 🔹 **Passo 3: Subir os containers**
```sh
docker-compose up -d
```

### 🔹 **Passo 4: Testar a API com curl**
Criar um pagamento:
```sh
curl -X POST "http://localhost:8080/payments/1/create?amount=100.00"
```

Consultar logs de pagamento:
```sh
curl -X GET "http://localhost:8080/payments/10/logs"
```

### 🔹 Passo 5: Testando a API com Swagger

Após iniciar sua aplicação, você pode testar os endpoints diretamente pelo **Swagger UI**.

📌 **Acesse a interface do Swagger:**  
🔗 [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

### 🔍 Como testar:
1. **Abra o link acima no navegador.**
2. **Explore os endpoints disponíveis.**
3. **Clique em um endpoint para ver os detalhes.**
4. **Preencha os parâmetros necessários (se houver).**
5. **Clique em "Execute" para enviar a requisição e ver a resposta.**

💡 O Swagger facilita a visualização e testes da API diretamente no navegador, sem a necessidade de ferramentas externas como **Insomnia**.


## 🔄 **Fluxo do Sistema**
1️⃣ O **usuário** solicita um pagamento.
2️⃣ O pagamento é salvo no **banco de dados** com status **PENDING**.
3️⃣ O pagamento é enviado para o **RabbitMQ**.
4️⃣ O **RabbitMQ** processa a mensagem e atualiza o status para **APPROVED** ou **REJECTED**.
5️⃣ Os logs do pagamento ficam registrados no sistema.

## 📌 **Próximos Passos**
✅ Adicionar autenticação com JWT  
✅ Criar um webhook para atualizar clientes externos  
✅ Implementar retries para pagamentos rejeitados

---

💡 **Sugestão:** Caso precise de melhorias ou novas funcionalidades, contribua enviando um PR! 🚀

