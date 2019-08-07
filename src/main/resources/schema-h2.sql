
CREATE TABLE TIP_ORGANIZACIJSKE_JEDINICE (
	ID int NOT NULL,
	NAZIV varchar(100) NOT NULL,
	AKTIVAN boolean NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE VELICINA_GRADA (
	ID int NOT NULL,
	NAZIV varchar(100) NOT NULL,
	AKTIVAN boolean NOT NULL,
	PRIMARY KEY (ID)
);

CREATE TABLE ORGANIZACIJSKA_JEDINICA (
	ID int NOT NULL,
	NAZIV varchar(100) NOT NULL,
	OPIS varchar(200),
	ORG_JED_ID int,
	TIP_ORG_JED_ID int,
	PRIMARY KEY (ID),
	FOREIGN KEY (ORG_JED_ID) REFERENCES ORGANIZACIJSKA_JEDINICA(ID),
	FOREIGN KEY (TIP_ORG_JED_ID) REFERENCES TIP_ORGANIZACIJSKE_JEDINICE(ID)	
);

CREATE TABLE GRAD (
	ID int NOT NULL,
	NAZIV varchar(100) NOT NULL,
	VELICINA_GRADA_ID int NOT NULL,
	ORG_JED_ID int NOT NULL,
	PRIMARY KEY (ID),
	FOREIGN KEY (VELICINA_GRADA_ID) REFERENCES VELICINA_GRADA(ID),
	FOREIGN KEY (ORG_JED_ID) REFERENCES ORGANIZACIJSKA_JEDINICA(ID),
	
);

CREATE TABLE EVENT (
	ID int NOT NULL,
	NAZIV varchar(100) NOT NULL,
	VRIJEME_OD timestamp NOT NULL,
	VRIJEME_DO timestamp,
	SLOBODAN_ULAZ boolean NOT NULL,
	GRAD_ID int,
	PRIMARY KEY (ID),
	FOREIGN KEY (GRAD_ID) REFERENCES GRAD(ID)
);
