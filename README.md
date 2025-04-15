# Integrador de Pagamentos Online

Este projeto é uma API de gateway de pagamento online, desenvolvida com Spring Boot, que permite a integração entre sistemas e serviços de pagamento externos (como Mercado Pago, PagSeguro, etc.).

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
curl -X POST "http://localhost:8080/api/invoice"
```

Consultar logs de pagamento:
```sh
curl -X GET "http://localhost:8080/invoice/10/logs"
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
1️⃣ O usuário realiza uma compra ou serviço e escolhe a forma de pagamento (ex: cartão ou Pix).

2️⃣ O sistema cria o pagamento no banco de dados com status PENDING (pendente), aguardando a resposta do gateway.

3️⃣ O sistema envia os dados do pagamento para o gateway de pagamento (ex: Mercado Pago, Stripe, PagSeguro).

4️⃣ O gateway de pagamento verifica os dados: se tem saldo, se o cartão é válido, se o Pix pode ser gerado, etc.

5️⃣ O gateway responde para o sistema se o pagamento foi:

    ✅ APPROVED (aprovado)
    
    ❌ REJECTED (rejeitado)
    
    🕒 IN_PROCESS (em análise)

6️⃣ O sistema atualiza o status do pagamento no banco de dados com base na resposta do gateway.

7️⃣ Todas as ações são registradas nos logs, garantindo rastreabilidade.

## 📌 **Próximos Passos**
✅ Adicionar autenticação com Api Key

✅ Criar um webhook para atualizar os dados da fatura


✅ Implementar retries para pagamentos rejeitados

---

💡 **Sugestão:** Caso precise de melhorias ou novas funcionalidades, contribua enviando um PR! 🚀

