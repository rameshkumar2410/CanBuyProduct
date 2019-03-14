create table AccountDetail(typeAccount varchar(100), accountNo varchar(100) NOT NULL, balance varchar(100), description varchar(100), custId varchar(100) NOT NULL) ;

insert into AccountDetail(typeAccount, accountNo, balance, description, custId) values('CREDIT', 'AB123', '5545', 'Test1' ,'123');
insert into AccountDetail(typeAccount, accountNo, balance, description, custId) values('DEBIT', 'AB234', '32400', 'Test2' ,'234');


