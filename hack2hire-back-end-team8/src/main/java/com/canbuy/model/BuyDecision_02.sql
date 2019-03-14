create table BuyDecision(decision varchar(100), suggAccType varchar(100) NOT NULL, success varchar(100), category varchar(100), custId varchar(100));
insert into BuyDecision values('YES', 'CREDIT', 'true', 'Travel', '123');
insert into BuyDecision values('NO', '', 'true', 'Fixed Deposit', '123');
insert into BuyDecision values('YES', 'DEBIT', 'true', 'Investment', '123');
insert into BuyDecision values('YES', 'CREDIT', 'true', 'Fixed Deposit', '234');
insert into BuyDecision values('NO', '', 'true', 'Travel', '234');