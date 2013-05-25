-- Ishag Alexanian   wiki database   Creating and inserting the tables users, wikipages, and revisions.

use mysql;

-- 1. create a new database wiki

create database wiki;

-- 2. create a user ialexan_wiki, with password 3Z!xRqZ*, and grant the user
--    all privileges on the database wiki. 

grant all privileges on *.* to 'ialexan'@'%'
identified by '***' with grant option;

flush privileges;

-- 3. create a tables.

use wiki;


create table users (
    id              integer auto_increment primary key,
    user_name       varchar(255),    
    password        varchar(255),
    first_name      varchar(255),    
    last_name       varchar(255),
    email           varchar(255)      
);

create table wikipages (
    id              integer auto_increment primary key,
    path            varchar(255),
    view_counter    integer     
);

create table revisions (
    id              integer auto_increment primary key,
    wikipage_id     integer references wikipages(id),    
    content         varchar(10255),
    author_id       integer references users(id),
    time_stamp      timestamp
);



-- Inserting some values into the tables

insert into users values (1, 'ialexan', 'abcd', 'Ishag', 'Alexanian', 'ishagalexanian@gmail.com');

insert into users (user_name, password, first_name, last_name, email)
values ('jdoe', 'abcd', 'John', 'Doe', 'jdoe@gmail.com');

-- 

insert into wikipages (path,view_counter) values ('index',0);

-- 

insert into revisions values (1, 1, '<h3>Welcome to ialexan Wikipages. To see all the pages currently hosted on the wiki, please click on <a href="/wikipages/PageList">Page List</a>.</h3><br /><br/>', 2, now());

