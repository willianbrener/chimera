
-----------------------------------------------------------------------
CREATE TABLE departamento
(
  iddepartamento serial NOT NULL,
  nome character varying(120) NOT NULL,
  responsavel character varying(120),
  nivel character varying(250) NOT NULL,
  ativo boolean,
  CONSTRAINT departamento_pkey PRIMARY KEY (iddepartamento)
);
 
 
CREATE TABLE cargo
(
  idcargo serial NOT NULL,
  nome character varying(120) NOT NULL,
  iddepartamento integer,
  descricao character varying(250) NOT NULL,
  ativo boolean,
  CONSTRAINT cargo_pkey PRIMARY KEY (idcargo),
  CONSTRAINT cargo_iddepartamento_fkey FOREIGN KEY (iddepartamento)
      REFERENCES departamento (iddepartamento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
 
 
------------------------------------------------------------------------
CREATE TABLE usuario
(
  idusuario serial NOT NULL,
  nome character varying(120) NOT NULL,
  account character varying(50) NOT NULL,
  password character varying(50) NOT NULL,
  email character varying(100) NOT NULL,
  permissao character varying(50) NOT NULL,
  ativo boolean,
  idcargo integer,
  CONSTRAINT usuario_pkey PRIMARY KEY (idusuario),
  CONSTRAINT usuario_idcargo_fkey FOREIGN KEY (idcargo)
      REFERENCES cargo (idcargo) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
------------------------------------------------------------------------
 
CREATE TABLE recurso
(
  idrecurso serial NOT NULL,
  nome character varying(120) NOT NULL,
  descricao character varying(120),
  iddepartamento integer,
  ativo boolean,
  CONSTRAINT recurso_pkey PRIMARY KEY (idrecurso),
  CONSTRAINT recurso_iddepartamento_fkey FOREIGN KEY (iddepartamento)
      REFERENCES departamento (iddepartamento) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
 
------------------------------------------------------------------------
 
CREATE TABLE solicitacoes
(
  idsolicitacoes serial NOT NULL,
  titulo character varying(50) NOT NULL,
  descricao character varying(120) NOT NULL,
  data character varying(30) NOT NULL,
  hora character varying(10) NOT NULL,
  situacao character varying(30),
  idusuario integer,
  idrecurso integer,
  ativo boolean,
  CONSTRAINT solicitacoes_pkey PRIMARY KEY (idsolicitacoes),
  CONSTRAINT solicitacoes_idrecurso_fkey FOREIGN KEY (idrecurso)
      REFERENCES recurso (idrecurso) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);
------------------------------------------------------------------------
 
------------------------------------------------------------------------
CREATE TABLE historico_solicitacoes
(
  idhistorico serial NOT NULL,
  idsolicitacoes integer,
  titulo character varying(50) NOT NULL,
  descricao character varying(120) NOT NULL,
  data character varying(30) NOT NULL,
  hora character varying(10) NOT NULL,
  situacao character varying(30),
  idusuario integer,
  idrecurso integer,
  ativo boolean,
  CONSTRAINT historico_solicitacoes_pkey PRIMARY KEY (idhistorico)
);
CREATE TABLE motivo
(
  idmotivo serial NOT NULL,
  descricao character varying(120) NOT NULL,
  idsolicitacoes bigint NOT NULL,
  ativo boolean,
  CONSTRAINT motivo_pkey PRIMARY KEY (idmotivo),
  CONSTRAINT solicitacao_motivo_idsolicitacoes_fkey FOREIGN KEY (idsolicitacoes)
      REFERENCES solicitacoes (idsolicitacoes) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
)
 
 
----------------------INSERTS-------------------------------------------
 
 
 
INSERT INTO departamento(
nome, responsavel, nivel, ativo)
VALUES ('ADMINISTRACAO', 'ADMINISTRADOR','0', true);
 
INSERT INTO cargo(
nome, iddepartamento, descricao, ativo)
VALUES ('ADMNISTRACAO',1, 'ADMINISTRACAO DO SISTEMA', true);
 
INSERT INTO usuario(idusuario,
 nome, account, password, email, permissao, ativo,idcargo 
)
VALUES (99999,'ADMINISTRADOR', 'admin', '1234', 'sistemachimera@gmail.com','TOTAL',true
,1);
--------------------------------TRIGGER E FUNCTION----------------------
CREATE OR REPLACE FUNCTION backup_solicitacoes_update()
RETURNS TRIGGER AS
$$
  BEGIN
   INSERT INTO historico_solicitacoes(idsolicitacoes, titulo, descricao, data, hora, situacao, idusuario,idrecurso, ativo) 
values (NEW.idsolicitacoes, NEW.titulo, NEW.descricao, NEW.data, NEW.hora, NEW.situacao, NEW.idusuario,NEW.idrecurso, NEW.ativo);
    RETURN NEW;
  END;
$$ LANGUAGE plpgsql;
 
---- OU
CREATE OR REPLACE FUNCTION backup_solicitacoes_update()
RETURNS TRIGGER AS
$$
  BEGIN
   INSERT INTO historico_solicitacoes(idsolicitacoes, titulo, descricao, data, hora, situacao, idusuario,idrecurso, ativo) 
values (OLD.idsolicitacoes, OLD.titulo, OLD.descricao, OLD.data, OLD.hora, OLD.situacao, OLD.idusuario,OLD.idrecurso, OLD.ativo);
    RETURN NEW;
  END;
$$ 
LANGUAGE plpgsql;
 
 
CREATE TRIGGER trigger_solicitacoes_update BEFORE UPDATE
    ON solicitacoes FOR EACH ROW
    EXECUTE PROCEDURE backup_solicitacoes_update();