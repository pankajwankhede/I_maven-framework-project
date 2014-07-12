
    alter table t_user 
        drop 
        foreign key FKCB63CCB6CEAB0634;

    drop table if exists t_group;

    drop table if exists t_user;

    create table t_group (
        groupId varchar(255) not null,
        groupName varchar(255),
        primary key (groupId)
    );

    create table t_user (
        userId varchar(255) not null,
        userName varchar(255),
        groupId varchar(255),
        primary key (userId)
    );

    alter table t_user 
        add index FKCB63CCB6CEAB0634 (groupId), 
        add constraint FKCB63CCB6CEAB0634 
        foreign key (groupId) 
        references t_group (groupId);
