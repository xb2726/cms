create database  b_cms;
create table if not exists b_cms.sys_user
(
    id               varchar(20)  not null
        primary key,
    name             varchar(60)  null comment '姓名',
    code             varchar(20)  null comment '工号',
    login_name       varchar(60)  not null,
    password         varchar(20)  not null,
    gender           int(1)       null,
    birth_day        date         null,
    phone            varchar(10)  null,
    address          varchar(100) null,
    education        varchar(20)  null,
    graduated_school varchar(60)  null,
    photo_url        varchar(100) null,
    org_id           varchar(20)  null,
    org_name         varchar(30)  null,
    entry_time       date         null,
    resign_time      date         null,
    locked           int(1)       null,
    remark           varchar(200) null,
    create_date      date         null,
    update_date      date         null,
    reserved1        varchar(100) null,
    reserved2        varchar(100) null,
    deleted          int          null,
    creator_id       varchar(20)  null,
    updater_id       varchar(20)  null
)
    comment '用户';
create index sys_user_login_name_index on sys_user (login_name);



create table if not exists b_cms.tb_industry
(
    id              varchar(20)  not null
        primary key,
    em_rating_code  varchar(100) null,
    em_rating_name  varchar(100) null,
    em_rating_value varchar(100) null,
    industry_code   varchar(20)  null,
    industry_name   varchar(20)  null,
    info_code       varchar(100) null,
    org_code        varchar(100) null,
    org_name        varchar(100) null,
    org_s_name      varchar(100) null,
    pdf_url         varchar(100) null,
    publish_date    datetime     null,
    title           varchar(100) null,
    creator_id      varchar(20)  null,
    create_date     date         null,
    updater_id      varchar(20)  null,
    update_date     date         null,
    reserved1       varchar(100) null,
    reserved2       varchar(100) null,
    deleted         int          null
);
create index tb_industry_info_code_index on tb_industry (info_code);

create table if not exists b_cms.tb_industry_record
(
    id          varchar(20) not null,
    user_id     varchar(20) not null,
    industry_id varchar(20) not null,
    creator_id      varchar(20)  null,
    create_date     date         null,
    updater_id      varchar(20)  null,
    update_date     date         null,
    reserved1       varchar(100) null,
    reserved2       varchar(100) null,
    deleted         int          null
)
    comment '行业研报浏览记录';
create index tb_industry_record_id_index
    on tb_industry_record (id);

create index tb_industry_record_industry_id_index
    on tb_industry_record (industry_id);

create index tb_industry_record_user_id_index
    on tb_industry_record (user_id);
