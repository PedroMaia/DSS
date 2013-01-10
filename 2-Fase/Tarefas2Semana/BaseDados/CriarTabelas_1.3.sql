



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
   pr number(20,3),--preço produto
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
  pb number(20,3),--Preço Base
  pml number(20,3),--PMLecitacao
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
  vl number(20,3),--Valor Lecitação
  PRIMARY KEY (idl,ul,dl),
  UNIQUE(idl,vl)
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



create index classificacao_classificado
on classificacao (classificado);

create index classificacao_classificador
on classificacao (classificador);

create index favorito_usr
on favorito (usr);

create index favorito_idp
on favorito(idp);

create index leilao_dp
on leilao(dp);

create index leilao_dep
on leilao(dep);

create index leilao_df
on leilao(df);

create index leilao_ul
on leilao(ul);

create index produto_dp
on produto(dp);

create index venda_cp
on venda(cp);

create index venda_vd
on venda(vd);

create index venda_idp
on venda(idp);

create index venda_dp
on venda(dp);

create index venda_dep
on venda(dep);