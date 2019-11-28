create table "customer_types"
(
    id      serial,
    caption varchar(60) not null,
    constraint pk_type primary key (id),
    constraint idx_caption_unique unique (caption)
);

create table "customers"
(
    id                   serial,
    title                varchar(3)  not null,
    first_name           varchar(50) not null,
    first_name_metaphone varchar(40) not null,
    last_name            varchar(50) not null,
    last_name_metaphone  varchar(40) not null,
    modified_when        timestamp   not null,
    customer_types       int       not null,
    constraint pk_customer primary key (id),
    constraint customer_types_id_fk foreign key (customer_types)
        references customer_types (id) match simple
        on delete cascade on update cascade
);

