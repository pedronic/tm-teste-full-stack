# tm-teste-full-stack
Teste para vaga Desenvolvedor Full Stack - Tokio Marine


## Instruções para teste do código
1. Criar um Banco de Dados (local ou em memória) e alterar as configurações do arquivo [application.properties](/backend/src/main/resources/application.properties) para as configurações do banco em utilização.
2. Adicionar as dependências necessárias no arquivo [pom.xml](/backend/pom.xml) caso não opte por utilizar uma base em PostgreSQL (utilizado neste projeto para facilitar e acelerar o desenvolvimento).
3. Popular o Banco de Dados. Há 3 arquivos disponíveis para auxiliar este processo:
    * Um arquivo contendo [Scripts SQL](/db/Script.sql) para adicionar usuários e conseguir logar na API
    * Um arquivo JSON com os [exemplos de utilização dos endpoints](/db/Insomnia_EndPoints.json)
    * Um arquivo JSON com as [Tarifas (Fees) utilizadas ao longo do exercício](/db/Fees.json), para serem cadastradas no Banco de Dados **` utilizando do endpoint criado específicamente para isso`**

Após preparar o banco de dados e as configurações do [Backend](/backend/), rodar a aplicação em Java e o [Frontend (em Angular)](/frontend/frontend/) na mesma máquina, acessar os links locais nas respectivas portas, já configuradas corretamente para a aplicação e nas configurações de CORS da API e testar.


### **Observação importante sobre o desenvolvimento:**
O teste deveria durar/ser entregue em 48h, tempo suficiente para o desenvolvimento da aplicação completa. Porém, me foi ofertada a vaga e o teste durante a realização de um trabalho freelance de outra área (pesquisa de mercado, onde atuei por mais de 2 anos), que foi atrasada por fatores externos de coleta de campo e processamento de dados, gerando atraso no início deste teste.

Não bastante, ainda houve um problema técnico após a finalização do trabalho citado, enquanto já estava desenvolvendo o teste, numa ocasião de falha/surto na rede elétrica da minha residência, perdi quase que completamente a partição do meu HD que utilizo para trabalhos e projetos de desenvolvimento de software. 

Após longos dias de tentaiva de recuperação dos backups e da partição corrompida do HD, retomei o trabalho com o objetivo de entregá-lo dentro dos requisitos do teste (banco de dados em memória e Vue), apenas para ter que recomeçar a etapa de frontend duas vezes por questões técnicas que achei por bem não continuar tentando resolver e optei por utilizar o framework Angular ao invés do Vue. Sobre o Banco de Dados, mesmo tipo de problema encontrado e solução aplicada, utilizando PostgreSQL ao invés de H2, como tentado.

---

# Arquitetura da Solução
A solução foi arquitetada de forma que o projeto fosse primeiramente estruturado no seu backend para em seguida partir para o front, já com o maior esforço de manipulação, tratamento e entrega dos dados para consumo e troca com o frontend.

Partindo desse princípio, primeiramente foram mapeadas todas as ações possíveis e necessárias para que a aplicação se tornasse funcional. Com todas as entidades envolvidas na jornada do usuário mapeadas, demonstrou-se pouca ou baixíssima necessidade de fracionar a aplicação em microsserviços no backend, afinal o usuário só iria consumir dados de duas tabelas distintas.

Abaixo, um exemplo de como foi arquitetado o Banco de Dados:

![Arquitetura do Banco de Dados](/db_arch.png)

Com o usuário consumindo dados apenas das tabelas *tb_transaction* e *tb_account*, podendo adicionar dados apenas na tabela *tb_transaction*, a quantidade de endpoints necessários para a aplicação funcionar corretamente, contando com o login na aplicação para aquisição de token, é de somente 7 (6 definidos manualmente e mais 1 automático, o de login).

Definido e programado o backend, foi momento de partir para o frontend e também notar que a aplicação toda caberia em duas telas simples, uma para login e outra para a execução da tarefa em si. Logo, uma arquitetura monolítica bastou para resolver o problema. Porém, tanto o framework Angular quanto o Spring Boot possibilitam uma versatilidade de desenvolvimento e arquitetura de códigos que permitem escalonar a aplicação e fazer ajustes em sua arquitetura, modificando os paradigmas iniciais, de tal forma que o projeto seja de fácil manutenção e permitindo refatorações mais concisas de código.
