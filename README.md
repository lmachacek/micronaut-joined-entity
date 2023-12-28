# Micronaut Data Regression Example Project

This is only an extraction of much bigger project to show problem when upgrading from Micronaut 3.6.6 to more recent
version.

## The Problem

There is a relation defined by column `organization_id` in table `tenants`, which references record in
table `organizations` with primary key `id`.

The last version of Micronaut where the example is working is 3.6.6 and in any other higher version it fails, where the
problem is that joined entities are not loaded.

## Steps to Reproduce

Branch _main_ uses Micronaut 3.6.6 and branch _upgrade_ uses Micronaut 3.10.1.

1. Execute test by `./gradlew clean test --info` and the test is successful.
2. Switch branch `git checkout upgrade` to use more recent Micronaut.
3. Execute the test again `./gradlew clean test --info` and it fails.

### Micronaut 3.6.6

Log contains SELECT query from `tenants` table.

```
Hibernate: create table organizations (id binary not null, name varchar(255), primary key (id))
Hibernate: create table tenants (id binary not null, alias varchar(255), organization_id binary, primary key (id))
Hibernate: alter table tenants add constraint FKpkatup2hidrwkgs4yuuodf6bt foreign key (organization_id) references organizations
Hibernate: insert into organizations (name, id) values (?, ?)
Hibernate: insert into tenants (alias, organization_id, id) values (?, ?, ?)
Hibernate: select organizati0_.id as id1_0_, organizati0_.name as name2_0_ from organizations organizati0_ where organizati0_.id=? limit ?
Hibernate: select tenants0_.organization_id as organiza3_1_0_, tenants0_.id as id1_1_0_, tenants0_.id as id1_1_1_, tenants0_.alias as alias2_1_1_, tenants0_.organization_id as organiza3_1_1_ from tenants tenants0_ where tenants0_.organization_id=?
```

### Micronaut 3.7 and higher

Log does not contain SELECT query from `tenants` table when using Micronaut 3.7 or any other higher version. Version
4.2.2 was also tested with the same behavior.

```
Hibernate: create table organizations (id binary not null, name varchar(255), primary key (id))
Hibernate: create table tenants (id binary not null, alias varchar(255), organization_id binary, primary key (id))
Hibernate: alter table tenants add constraint FKpkatup2hidrwkgs4yuuodf6bt foreign key (organization_id) references organizations
Hibernate: insert into organizations (name, id) values (?, ?)
Hibernate: insert into tenants (alias, organization_id, id) values (?, ?, ?)
Hibernate: select organizati0_.id as id1_0_, organizati0_.name as name2_0_ from organizations organizati0_ where organizati0_.id=? limit ?
```
