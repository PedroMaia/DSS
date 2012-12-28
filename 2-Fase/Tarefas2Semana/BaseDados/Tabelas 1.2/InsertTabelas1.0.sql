

-- Test Data estou a fazer!!! não tirar de comentário!
-- ---


--Inserir um utilizador  
INSERT INTO Utilizador(usr,pw,e,lc,cp,ddr,dn,fu) VALUES('maiarib','1234','maiarib@gmail.com','Ribeirao rua das Escolas','4702-407',to_date('2010-01-23','yyyy-mm-dd'),to_date('1991-02-23','yyyy-mm-dd'),null);
INSERT INTO Utilizador(usr,pw,e,lc,cp,ddr,dn,fu) VALUES('pfaria','12345','pfaria@gmail.com','Vizela','4702-407',to_date('2010-01-23','yyyy-mm-dd'),to_date('1991-01-23','yyyy-mm-dd'),null);

--inserir uma classificação de 0 a 5 
INSERT INTO Classificacao(cdono,cuser,va,dc) VALUES('maiarib','pfaria',5,to_date('2010-01-23','yyyy-mm-dd'));
INSERT INTO Classificacao(cdono,cuser,va,dc) VALUES('pfaria','maiarib',5,to_date('2010-01-24','yyyy-mm-dd'));  


--Inserir produtos  
INSERT INTO Produto (idp,np,imp,dp,dsp,ctg,di) VALUES(Sidp.nextval,'Telemovel Samsung',null,'maiarib','Em bom estado','COMUNICACOES',to_date('2012-01-23','yyyy-mm-dd'));

INSERT INTO Produto (idp,np,imp,dp,dsp,ctg,di) VALUES(Sidp.nextval,'Telemovel Samsung 2',null,'pfaria','Não Em bom estado','COMUNICACOES',to_date('2012-01-24','yyyy-mm-dd'));

INSERT INTO Produto (idp,np,imp,dp,dsp,ctg,di) VALUES(Sidp.nextval,'Telemovel Da Vodafone',null,'pfaria','Em bom Optimo estado para Troca','COMUNICACOES',to_date('2012-01-25','yyyy-mm-dd'));

INSERT INTO Produto (idp,np,imp,dp,dsp,ctg,di) VALUES(Sidp.nextval,'Telemovel Da Huawei',null,'maiarib','Em bom Optimo estado para troca','COMUNICACOES',to_date('2012-01-25','yyyy-mm-dd'));



--Inserir nas Venda
INSERT INTO Venda(idv,idp,pr,cp,dlp,dp,div,vd) VALUES
(Sidv.nextval,1,23.3,null,to_date('2010-01-24','yyyy-mm-dd'),to_date('2012-01-23 14:33','yyyy-mm-dd hh24:mi')
,to_date('2012-01-22 14:34','yyyy-mm-dd hh24:mi'),'maiarib');


--Inserir favorito
INSERT INTO Favorito(usr,idp,daf) VALUES('pfaria',1,to_date('2012-01-21 15:33','yyyy-mm-dd hh24:mi'));



INSERT INTO Leilão(idl,idp,ul,cp,dp,df,di,pb,pml) VALUES
 (Sidl.nextval,1,'pfaria',null,to_date('2013-01-22 14:34','yyyy-mm-dd hh24:mi'),
 to_date('2012-01-22 14:35','yyyy-mm-dd hh24:mi'),'',3.00,5.00);




INSERT INTO Lecitacao(idl,ul,dl,vl) VALUES(1,'maiarib',to_date('2012-01-22 14:35','yyyy-mm-dd hh24:mi'),3.66);



INSERT INTO PTroca(usr1,idp1,usr2,idp2,ct,tcs,dlt,dpt) VALUES
(3,'pfaria',4,'maiarib',1,0,to_date('2012-01-22 14:35','yyyy-mm-dd hh24:mi'),to_date('2012-01-22 14:35','yyyy-mm-dd hh24:mi'));



INSERT INTO CasoSuspeito(uas,idp,js) VALUES('pfaria',1,'Nao é real!');

