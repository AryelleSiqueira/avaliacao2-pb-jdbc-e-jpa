# avaliacao2-pb-java

* mysql script que gera tabela da questão 9:

create table tbproduct (
    id int unsigned auto_increment,
    name varchar(150),
    description varchar(300),
    price float,
    discount float,
    start_date date,
    primary key (id)
);
