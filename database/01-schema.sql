CREATE TABLE usuario (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    usuario VARCHAR(50) NOT NULL UNIQUE,
    senha VARCHAR(255) NOT NULL
);

CREATE TABLE solicitacao (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    titulo VARCHAR(150) NOT NULL,
    descricao TEXT NOT NULL,
    categoria VARCHAR(30) NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'ABERTO',
    data_criacao TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    usuario_id BIGINT NOT NULL,

    CONSTRAINT fk_solicitacao_usuario
        FOREIGN KEY (usuario_id)
        REFERENCES usuario(id)
        ON DELETE RESTRICT,

    CONSTRAINT chk_solicitacao_categoria
        CHECK (categoria IN (
            'TI',
            'RH',
            'COMPRAS',
            'FINANCEIRO',
            'INFRAESTRUTURA'
        )),

    CONSTRAINT chk_solicitacao_status
        CHECK (status IN (
            'ABERTO',
            'EM_ATENDIMENTO',
            'CONCLUIDO'
        )),

    CONSTRAINT chk_solicitacao_titulo
        CHECK (LENGTH(TRIM(titulo)) > 0),

    CONSTRAINT chk_solicitacao_descricao
        CHECK (LENGTH(TRIM(descricao)) > 0)
);

CREATE INDEX idx_solicitacao_usuario
    ON solicitacao(usuario_id);

CREATE INDEX idx_solicitacao_status
    ON solicitacao(status);

CREATE INDEX idx_solicitacao_categoria
    ON solicitacao(categoria);

CREATE INDEX idx_solicitacao_data
    ON solicitacao(data_criacao);