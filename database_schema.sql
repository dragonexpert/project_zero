create table accounts (
	id serial primary key,
	account_type varchar(50) default 'checking',
	account_number int unique not null,
	userid int not null references users(userid),
	balance money,
	first_name varchar(50),
	last_name varchar(50)
);

-- Seed account data here --

insert into accounts (id, account_type, account_number, userid, balance, first_name, last_name)
values 
(1, 'checking', 123456789, 1, 3777, 'Mario', 'Plumber'),
(2, 'checking', 987654321, 2, 527.36, 'Jane', 'Doe'),
(3, 'savings', 567812345, 1, 317.36, 'Mario', 'Plumber'),
(6, 'checking', 100198, 5, 500, 'Miranda', 'Palmer'),
(7, 'checking', 100844, 2, 367.89, 'Jane', 'Doe');

insert into accounts(account_type, account_number, userid)
values ('savings', 567812345, 1);

create table account_applications (
id serial primary key,
userid int references users(userid),
account_type varchar(50) default 'checking',
deposit int not null default 0,
approved int not null default 0,
first_name varchar(50),
last_name varchar(50)
);

-- Seed account_applications
insert into account_applications
(userid, deposit)values
(4, 350.00);



create table employees (
employeeid serial primary key,
userid int not null references users(userid),
last_name varchar(50),
first_name varchar(50),
admin int not null default 0
);

-- Seed employees

insert into employees (userid, first_name, last_name, admin)
Values(7, 'Mark', 'Janssen', true),
(3, 'Travis', 'Gonzales', false);


create table users (
userid serial primary key,
username varchar(50),
password varchar(255),
email varchar(255)
);

-- Seed user table
insert into users
(userid, username, password, email)
values
(1, 'user1', 'pass1', 'email1@example.com'),
(2, 'user2', 'pass2', 'email2@example.com'),
(3, 'user3', 'pass3', 'email3@example.com'),
(4, 'TreeWaster', 'pass4', 'treewaster@gmail'),
(5, 'account1', 'pass5', 'email5@example.com')
(6, 'account6', 'accountpass6', 'email12345@xample.com')
(7, 'user6', 'pass6', 'email6@example.com'),
(8, 'user77', 'pass77', 'email7@example.com');

create view applications as
select aa.id, aa.userid, aa.deposit, aa.account_type, aa.approved, aa.first_name, aa.last_name, u.username  from account_applications aa , users u where aa.userid = u.userid; 
