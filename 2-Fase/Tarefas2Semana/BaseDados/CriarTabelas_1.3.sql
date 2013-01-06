



--Falta os auto incrementos!


-- ---
-- Table 'Utilizador'
-- 
-- ---

		
CREATE TABLE Utilizador(
  usr VARCHAR2(20),--user name
  pw VARCHAR2(32) NOT NULL,--password
  e VARCHAR2(30) NOT NULL UNIQUE,--email
  lcp VARCHAR2(100) NOT NULL,--localidade e codPostal
  dn DATE NOt NULL,--data nascimento
  ddr DATE NOT NULL,--data de registo
  fu BLOB,--foto user
  PRIMARY KEY (usr)
);


-- ---
-- Table 'Classificação'
-- 
-- ---


		
CREATE TABLE Classificacao(

   classificado VARCHAR2(20) REFERENCES Utilizador(usr),--user name classificado dono
   classificador VARCHAR2(20) REFERENCES Utilizador(usr),--user name class classificador
   va NUMBER(5),--valor atribuido
   dc DATE, --data classificação
  PRIMARY KEY (classificado,classificador)
);


-- ---
-- Table 'Produto'
-- 
-- ---


		--Falta o suporte a comentários?
CREATE TABLE Produto(
  idp NUMBER(10),--IdProduto ver como fazer para auto-icrementar
  np VARCHAR2(50),--NomeProduto
  imp BLOB,--imagemProduto
  dp VARCHAR2(20) REFERENCES Utilizador(usr),--username: dono produto
  dsp VARCHAR2(150),--descrição produto
  ctg VARCHAR2(20) ,--categoria
  PRIMARY KEY (idp)
);

-- ---
-- Table 'Venda'
-- 
-- ---

		
CREATE TABLE Venda(
   idv NUMBER(10), --idVenda
   idp NUMBER(10) REFERENCES Produto(idp),--idproduto
   pr number(10,3),--preço produto
   cp VARCHAR2(20) DEFAULT NULL REFERENCES Utilizador(usr),--comprador 
   dlv DATE,--DataLimiteVenda
   dp DATE,--DataPagamento
   dep DATE, --DataEnvioProduto
   div DATE,--DataInseridoAvenda
   vd VARCHAR2(20) REFERENCES Utilizador(usr),--vendedor
  PRIMARY KEY (idv)
);

-- ---
-- Table 'Favorito'
-- 
-- ---


		
CREATE TABLE Favorito(
  usr varchar2(20) REFERENCES Utilizador(usr),--username
  idp NUMBER(10) REFERENCES Produto(idp),--IDproduto 
  PRIMARY KEY (usr,idp)
);

-- ---
-- Table 'Leilão'
-- 
-- ---


		
CREATE TABLE Leilao(
  idl number(10),--idleilão
  idp number(10) REFERENCES Produto(idp),--idproduto
  ul VARCHAR2(20) REFERENCES Utilizador(usr),--Leiloador
  dll DATE, --DataLimiteLeilao
  dp DATE,--DataPagamento
  dep DATE, --DataEnvioProduto
  df DATE,--DataFecho
  di DATE,--DataInsercao
  pb number(10,3),--Preço Base
  pml number(10,3),--PMLecitacao
  PRIMARY KEY (idl)
);

-- ---
-- Table 'Lecitacao'
-- 
-- ---

		
CREATE TABLE Licitacao (
  idl number(10) REFERENCES Leilao(idl),--idleilão
  ul VARCHAR2(20) REFERENCES Utilizador(usr),--UserLecitou
  dl DATE ,--DataLecitacao
  vl number(10,3),--Valor Lecitação
  PRIMARY KEY (idl,ul,dl),
  UNIQUE(idl,vl)
);

-- ---
-- Table 'PTroca'
-- 
-- ---


		
CREATE TABLE PTroca(
  idt number(10), --idTroca
  usr1 VARCHAR2(20) REFERENCES Utilizador(usr),--propositor
  idp1 number(10) REFERENCES Produto(idp),--oferta
  usr2 VARCHAR2(20) REFERENCES Utilizador(usr),--convidado
  idp2 number(10) REFERENCES Produto(idp),--desejado
  dlt DATE,--DataLimiteParaTroca
  dct DATE, --DataConfirmacaoTroca
  dconlt DATE, --DataConclusaoTroca
  dpt DATE,--DataPropostaDeTroca
  PRIMARY KEY (idt)
);



-- ---
-- Table 'CasoSuspeito'
-- 
-- ---


		
CREATE TABLE CasoSuspeito(
  uas VARCHAR2(20) REFERENCES Utilizador(usr) ,--userAcusouSuspeita
  idp number(10)  REFERENCES Produto(idp) ,--idProdutouspeito
  js VARCHAR2(150),--Justificação de suspeita
  PRIMARY KEY (uas,idp)
);

--- AutoIncrementar ID's---
--que incrementa as IDProduto

Create sequence Sidp
start with 1
increment by 1
minvalue 1
maxvalue 10000;

--que incrementa as IDVenda
Create sequence Sidv
start with 1
increment by 1
minvalue 1
maxvalue 10000;


--que incrementa o IDleilão
Create sequence Sidl
start with 1
increment by 1
minvalue 1
maxvalue 10000;


Create sequence Sidt
start with 1
increment by 1
minvalue 1
nomaxvalue;
---Inserir



-- Test Data estou a fazer!!! não tirar de comentário!
-- ---


--Inserir um utilizador  
INSERT INTO Utilizador VALUES('maiarib','1234','maiarib@gmail.com','Ribeirao rua das Escolas',to_date('2010-01-23','yyyy-mm-dd'),to_date('1991-02-23','yyyy-mm-dd'),null);
INSERT INTO Utilizador VALUES('pfaria','12345','pfaria@gmail.com','Vizela 4702-407',to_date('2010-01-23','yyyy-mm-dd'),to_date('1991-01-23','yyyy-mm-dd'),null);
INSERT INTO Utilizador VALUES('cesar','123456','cesar.perdigao92@gmail.com','Santa Marta de Penaguião', to_date('06-07-1992','dd-mm-yyyy'), sysdate, null);

--inserir uma classificação de 0 a 5 
INSERT INTO Classificacao VALUES('maiarib','pfaria',5,to_date('2010-01-23','yyyy-mm-dd'));
INSERT INTO Classificacao VALUES('pfaria','maiarib',5,to_date('2010-01-24','yyyy-mm-dd'));  


--Inserir produtos  
INSERT INTO Produto VALUES(Sidp.nextval,'Telemovel Samsung',null,'maiarib','Em bom estado','COMUNICACOES');

INSERT INTO Produto VALUES(Sidp.nextval,'Telemovel Samsung 2',null,'pfaria','Não Em bom estado','COMUNICACOES');

INSERT INTO Produto VALUES(Sidp.nextval,'Telemovel Da Vodafone',null,'pfaria','Em bom Optimo estado para Troca','COMUNICACOES');

INSERT INTO Produto VALUES(Sidp.nextval,'Telemovel Da Huawei',null,'maiarib','Em bom Optimo estado para troca','COMUNICACOES');



--Inserir nas Venda
INSERT INTO Venda VALUES
(Sidv.nextval,1,23.3,null,to_date('2010-01-24','yyyy-mm-dd'),to_date('2012-01-23 14:33','yyyy-mm-dd hh24:mi')
,to_date('2012-01-22 14:34','yyyy-mm-dd hh24:mi'),'maiarib');


--Inserir favorito
INSERT INTO Favorito VALUES('pfaria',1);



INSERT INTO Leilão VALUES
 (Sidl.nextval,1,'pfaria',null,to_date('2013-01-22 14:34','yyyy-mm-dd hh24:mi'),
 to_date('2012-01-22 14:35','yyyy-mm-dd hh24:mi'),'',3.00,5.00);




INSERT INTO Lecitacao VALUES(1,'maiarib',to_date('2012-01-22 14:35','yyyy-mm-dd hh24:mi'),3.66);



INSERT INTO PTroca VALUES
(3,'pfaria',4,'maiarib',1,0,to_date('2012-01-22 14:35','yyyy-mm-dd hh24:mi'),to_date('2012-01-22 14:35','yyyy-mm-dd hh24:mi'));



INSERT INTO CasoSuspeito VALUES('pfaria',1,'Nao é real!');


