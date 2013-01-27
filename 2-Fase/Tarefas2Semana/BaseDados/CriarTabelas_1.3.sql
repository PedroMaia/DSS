--funçao que vai buscvar o top 3 produtos que mais froma adicionados à lista dos favoritos--
select np, refs
from(select idp as ids ,val as refs
      from(
          select favorito.idp , count(idp) val
          from favorito
          group by favorito.idp
        )
     order by refs desc
     ),produto
where rownum <3 and produto.idp=ids;

--Faz a media da classificação e dá o top--

select *
from(select classificado, avg(va) as top
      from classificacao
      group by classificado
      order by top desc)
where rownum<10;


--tabela com as vendas inseridas 7 dias antes!--
select usr, count(vd) as top
from venda, utilizador
where venda.vd=utilizador.usr
and venda.div>data_anterior(7)
and venda.cp is null 
group by usr
order by top desc;

--tabela com o nr de favoritos de cada user--
select favorito.usr, count (favorito.idp) as top
from favorito , produto
where favorito.idp=produto.idp 
group by favorito.usr
order by top desc;


--função que altera a data para os dias que entra--
--para testar:> select data_anterior(7)from dual--
create or replace
function data_anterior(dias integer)
return date is begin
return sysdate-dias;
end data_anterior;

--Procedimento para mudar a password do utilizador--
--para testar em sql : > execute del_password('username')--
create or replace
PROCEDURE del_password (usrname string) is
BEGIN
UPDATE utilizador set pw='ZZZZZZZZZZZZZZZZ'
WHERE usr=usrname;
END del_password;



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
  primary key(idl,vl)
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

create or replace function leilao_fechado(idleilao int)
return boolean is
  max_lit real;
  tecto real;
  data_fecho date;
begin
  select nvl(max(vl),0) into max_lit from licitacao where idl=idleilao;
  select pml,df into tecto, data_fecho from leilao where idl=idleilao;
  if(max_lit>tecto or sysdate>data_fecho) then
    return true;
  else
    return false;
  end if;
end leilao_fechado;

create or replace trigger valida_licitacao
before insert on licitacao
for each row
declare
  leilaoFechado exception;
  licitacaoBaixa exception;
  max_lit real;
begin
  select max(vl) into max_lit from licitacao where idl=:new.idl;
  if(leilao_fechado(:NEW.idl)) then
    raise leilaoFechado;
  else
    if(:new.vl<=max_lit) then
      raise licitacaoBaixa;
    end if;
  end if;
  exception
  when leilaoFechado then raise_application_error(-20001,'Leilao já fechado.');
  when licitacaoBaixa then raise_application_error(-20002,'Licitaçao demasiado baixa.');
end;

CREATE OR REPLACE TRIGGER valida_compra
BEFORE UPDATE ON VENDA 
FOR EACH ROW 
WHEN (new.cp!=old.cp and old.cp is not null) 
BEGIN
  raise_application_error(-20003, 'Produto já comprado.');
END;

