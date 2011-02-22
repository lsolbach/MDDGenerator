-- create tables for component org.soulspace.address.domain
-- Template:  domain/persistence/create-tables
-- Imports:   lib,model/lib,persistence/db/lib,domain/model/lib,domain/persistence/lib,domain/persistence/ddl
-- Timestamp: Wed Feb 02 21:35:29 CET 2011


create table email_address {
	prefix  varchar(254) not null,
	domain  varchar(254) not null,
	
}

create table phone_number {
	number  varchar(254) not null,
	
}

create table postal_address {
	street  varchar(254) not null,
	house_number  varchar(254) not null,
	zip  varchar(254) not null,
	city  varchar(254) not null,
	
}


