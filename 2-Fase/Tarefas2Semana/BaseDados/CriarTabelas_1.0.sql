



--Falta os auto incrementos!


-- ---
-- Table 'Utilizador'
-- 
-- ---

DROP TABLE IF EXISTS `Utilizador`;
		
CREATE TABLE Utilizador(
  usrnm VARCHAR2(20) NOT NULL,--user name
  pw VARCHAR2(20) NOT NULL,--password
  email VARCHAR2(30) NOT NULL,
  localidade VARCHAR2(100) NOT NULL,
  cod-Postal VARCHAR2(10) NOT NULL,
  dataDeRegisto DATE NOT NULL,
  fotoUser BLOB,
  PRIMARY KEY (usrnm)
);


-- ---
-- Table 'Classificação'
-- 
-- ---
DROP TABLE IF EXISTS `Classificação`;
		
CREATE TABLE Classificacao(
   cdono VARCHAR2(20) REFERENCES Utilizador(usrnm),--user name classificado dono
   cuser VARCHAR2(20) REFERENCES Utilizador(usrnm),--user name class
   va NUMBER(5),--valor atribuido
   dc DATE, --data classificação
  PRIMARY KEY (cdono,cuser)
);


-- ---
-- Table 'Produtos'
-- 
-- ---

DROP TABLE IF EXISTS Produtos`;
		--Falta o suporte a comentários?
CREATE TABLE Produtos(
  idp VARCHAR2(20),--IdProduto ver como fazer para auto-icrementar
  np VARCHAR2(50),--NomeProduto
  imp BLOB NOT NULL,--imagemProduto
  dp VARCHAR2(20) REFERENCES Utilizador(usrnm),--username: dono produto
  dsp VARCHAR2(150),--descrição produto
  ctg VARCHAR2(20) ,--categoria
  di DATE,--data de inserção
  PRIMARY KEY (idp),
);

-- ---
-- Table 'Vendas'
-- 
-- ---

DROP TABLE IF EXISTS Vendas`;
		
CREATE TABLE Vendas(
   idv VARCHAR2(20), --idvendas
   idp VARCHAR2(20) REFERENCES Produtos(idp),--idproduto
   pr number(6,2),--preço produto
   cp VARCHAR2(20) DEFAULT NULL REFERENCES Utilizador(usrnm),--comprador 
   dlp DATE,--DataLimitePagamento
   dp DATE,--DataPagamento
   div DATE,--DataInseridoAvenda
   vd VARCHAR2(20) REFERENCES Utilizador(usrnm),--vendedor
  PRIMARY KEY (idv)
);

-- ---
-- Table 'Favoritos'
-- 
-- ---

DROP TABLE IF EXISTS `Favoritos`;
		
CREATE TABLE Favoritos(
  usrnm VARCHAR2(20) REFERENCES Utilizador(usrnm),--username
  idp VARCHAR2(20) REFERENCES Produto(idp);--IDproduto 
  PRIMARY KEY (usrnm,idp)
);

-- ---
-- Table 'Leilão'
-- 
-- ---

DROP TABLE IF EXISTS Leilao;
		
CREATE TABLE Leilao(
  idl VARCHAR2(20),--idleilão
  idp VARCHAR2(20) REFERENCES Produto(idp),--idproduto
  ul VARCHAR2(20) REFERENCES Utilizador(usrnm),--Leiloador
  df DATE,--DataFecho
  di DATE NULL DEFAULT NULL,--DataInsercao
  pb number(6,2),--Preço Base
  pml number(6,2),--PMLecitacao
  cp VARCHAR(20), REFERENCES Utilizador(usrnm)--Comprador
  dp DATA,--DataPagamento
  PRIMARY KEY (idl)
);

-- ---
-- Table 'Lecitacao'
-- 
-- ---

DROP TABLE IF EXISTS Lecitacao;
		
CREATE TABLE Lecitacao (
  idl VARCHAR2(20) REFERENCES Leilao(idl),--idleilão
  ul VARCHAR2(20) REFERENCES Utilizador(usrnm),--UserLecitou
  dl DATE ,--DataLecitacao
  vl number(6,2),--Valor Lecitação
  PRIMARY KEY (idl,ul,dl)
);

-- ---
-- Table 'PTroca'
-- 
-- ---

DROP TABLE IF EXISTS PTroca;
		
CREATE TABLE PTroca(
  usr1 VARCHAR2(20) REFERENCES Utilizador(usrnm),--user1
  idp1 VARCHAR2(20) REFERENCES Produto(idp),--idproduto1
  usr2 VARCHAR2(20) REFERENCES Utilizador(usrnm),
  idp2 VARCHAR2(20) REFERENCES Produto(idp),
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

DROP TABLE IF EXISTS CasoSuspeito;
		
CREATE TABLE CasoSuspeito(
  uas VARCHAR2(20) REFERENCES Utilizador(usrnm) ,--userAcusouSuspeita
  idp VARCHAR2(20)  REFERENCES Produto(idp) ,--idprodutosuspeito
  js VARCHAR2(150),--Justificação de suspeita
  PRIMARY KEY (uas,idp)
);



-- Test Data
-- ---

-- INSERT INTO Utilizador() VALUES
-- ('','','','','','');
-- INSERT INTO Classificação() VALUES
-- ('','','','');
-- INSERT INTO Produtos () VALUES
-- ('','','','','','','');
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

