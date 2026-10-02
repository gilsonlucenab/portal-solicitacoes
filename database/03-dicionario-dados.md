Tabela: usuario

Campo

	

Tipo

	

Descrição




id

	

BIGINT

	

Identificador único




nome

	

VARCHAR(100)

	

Nome do usuário




usuario

	

VARCHAR(50)

	

Login único




senha

	

VARCHAR(255)

	

Senha criptografada

Tabela: solicitacao

Campo

	

Tipo

	

Descrição




id

	

BIGINT

	

Código da solicitação




titulo

	

VARCHAR(150)

	

Título da demanda




descricao

	

TEXT

	

Detalhamento




categoria

	

VARCHAR(30)

	

Categoria da demanda




status

	

VARCHAR(20)

	

Situação atual




data_criacao

	

TIMESTAMP

	

Data e hora de abertura




usuario_id

	

BIGINT

	

Referência ao solicitante