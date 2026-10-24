create table author
(
    id          uuid         not null primary key,
    name        varchar(100) not null,
    date_birth  date         not null,
    nationality varchar(50)  not null
);

create table book
(
    id               uuid           not null primary key,
    title            varchar(100)   not null,
    isbn             varchar(20)    not null,
    date_publication date           not null,
    price            numeric(18, 2) not null,
    id_author        uuid           not null references author (id),
    genre            varchar(30)    not null,
    constraint chk_genre check (genre in ('FICCAO',
                                          'FANTASIA',
                                          'ROMANCE',
                                          'MISTERIO',
                                          'BIOGRAFIA',
                                          'CIENCIA'))
);

select *
from author;
select *
from book;