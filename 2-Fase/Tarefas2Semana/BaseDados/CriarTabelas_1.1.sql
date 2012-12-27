



--Falta os auto incrementos!


-- ---
-- Table 'Utilizador'
-- 
-- ---

		
CREATE TABLE Utilizador(
  usr VARCHAR2(20) NOT NULL,--user name
  pw VARCHAR2(20) NOT NULL,--password
  e VARCHAR2(30) NOT NULL,--email
  lc VARCHAR2(100) NOT NULL,--localidade
  cp VARCHAR2(10) NOT NULL,--cod-Postal
  ddr DATE NOT NULL,--cod-Postal
  fu BLOB,--foto user
  PRIMARY KEY (usr)
);


-- ---
-- Table 'Classificação'
-- 
-- ---


		
CREATE TABLE Classificacao(
   cdono VARCHAR2(20) REFERENCES Utilizador(usr),--user name classificado dono
   cuser VARCHAR2(20) REFERENCES Utilizador(usr),--user name class classificador
   va NUMBER(5),--valor atribuido
   dc DATE, --data classificação
  PRIMARY KEY (cdono,cuser)
);


-- ---
-- Table 'Produto'
-- 
-- ---


		--Falta o suporte a comentários?
CREATE TABLE Produto(
  idp NUMBER(10),--IdProduto ver como fazer para auto-icrementar
  np VARCHAR2(50),--NomeProduto
  imp BLOB NOT NULL,--imagemProduto
  dp VARCHAR2(20) REFERENCES Utilizador(usr),--username: dono produto
  dsp VARCHAR2(150),--descrição produto
  ctg VARCHAR2(20) ,--categoria
  di DATE,--data de inserção
  PRIMARY KEY (idp)
);

-- ---
-- Table 'Vendas'
-- 
-- ---

		
CREATE TABLE Vendas(
   idv NUMBER(10), --idvendas
   idp NUMBER(10) REFERENCES Produto(idp),--idproduto
   pr number(6,2),--preço produto
   cp VARCHAR2(20) DEFAULT NULL REFERENCES Utilizador(usr),--comprador 
   dlp DATE,--DataLimitePagamento
   dp DATE,--DataPagamento
   div DATE,--DataInseridoAvenda
   vd VARCHAR2(20) REFERENCES Utilizador(usr),--vendedor
  PRIMARY KEY (idv)
);

-- ---
-- Table 'Favoritos'
-- 
-- ---


		
CREATE TABLE Favoritos(
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
  cp VARCHAR2(20) REFERENCES Utilizador(usr),--Comprador
  dp DATE,--DataPagamento
  df DATE,--DataFecho
  di DATE,--DataInsercao
  pb number(6,2),--Preço Base
  pml number(6,2),--PMLecitacao
  PRIMARY KEY (idl)
)

-- ---
-- Table 'Lecitacao'
-- 
-- ---

		
CREATE TABLE Lecitacao (
  idl number(10) REFERENCES Leilao(idl),--idleilão
  ul VARCHAR2(20) REFERENCES Utilizador(usr),--UserLecitou
  dl DATE ,--DataLecitacao
  vl number(6,2),--Valor Lecitação
  PRIMARY KEY (idl,ul,dl)
);

-- ---
-- Table 'PTroca'
-- 
-- ---


		
CREATE TABLE PTroca(
  usr1 VARCHAR2(20) REFERENCES Utilizador(usr),--user1
  idp1 number(10) REFERENCES Produto(idp),--idproduto1
  usr2 VARCHAR2(20) REFERENCES Utilizador(usr),
  idp2 number(10) REFERENCES Produto(idp),
   ct number(2),--`ConfirmaTroca`
  tcs number(2),--TrocaCSucesso
  dlt DATE,--DataLimiteParaTroca
  dpt DATE,--DataPropostaDeTroca
  PRIMARY KEY (usr1,idp1,usr2,idp2)
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

--que incrementa as IDvendas
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




-- Test Data estou a fazer!!! não tirar de comentário!
-- ---


--Inserir um utilizador  
-- INSERT INTO Utilizador(usr,pw,e,lc,cp,ddr,fu) VALUES('maiarib','1234','maiarib@gmail.com','Ribeirao rua das Escolas','4702-407','');
-- INSERT INTO Utilizador(usr,pw,e,lc,cp,ddr,fu) VALUES('pfaria','12345','pfaria@gmail.com','Cu do mundo','4702-407','');

--inserir uma classificação
-- INSERT INTO Classificacao(cdono,cuser,va,dc) VALUES('maiarib','pfaria',4,to_date('2010-01-23','yyyy-mm-dd'));
  
  
-- INSERT INTO Produto (idp,np,imp,dp,dsp,ctg,di) VALUES(Sidp.nextval,'Telemovel','','maiarib','Em bom estado','COMUNICACOES',to_date('2010-01-23','yyyy-mm-dd'));
-- INSERT INTO `Vendas` (`idVenda`,`idProduto`,`Preço`,`Comprador`,`DataLimitePagamento`,`DataPagamento`,`DataInseridoAvenda`,`Vendedor`) VALUES
-- ('','','','','','','','');
-- INSERT INTO `Favoritos` (`UseName`,`IdProduto`) VALUES
-- ('','');
-- INSERT INTO `Leilão` (`IdLeilao`,`IdProduto`,`Leiloador`,`DataFecho`,`DataInsercao`,`PBase`,`PMLecitacao`,`Comprador`,`DataPagamento`) VALUES
-- ('','','','','','','','','');
-- INSERT INTO `Lecitacao` (`IdLeilao`,`UserLecitou`,`DataLecitacao`,`Valor`) VALUES
-- ('','','','');
-- INSERT INTO `PTroca` (`User1`,`IdProduto1`,`User2`,`IdProduto2`,`ConfirmaTroca`,`TrocaCSucesso`,`DataLimiteParaTroca`,`DataPropostaDeTroca`) VALUES
-- ('','','','','','','','');
-- INSERT INTO `CasoSuspeito` (`UserName`,`idProduto`,`JustificaSusp`) VALUES
-- ('','','');

