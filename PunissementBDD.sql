#------------------------------------------------------------
#        Script MySQL.
#------------------------------------------------------------


#------------------------------------------------------------
# Table: session
#------------------------------------------------------------

CREATE TABLE session(
        session_id Int  Auto_increment  NOT NULL ,
        nom        Varchar (10) NOT NULL
	,CONSTRAINT session_PK PRIMARY KEY (session_id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: stagiaire
#------------------------------------------------------------

CREATE TABLE stagiaire(
        stagiaire_id Int  Auto_increment  NOT NULL ,
        nom          Varchar (25) NOT NULL ,
        prenom       Varchar (25) NOT NULL ,
        photo        Blob NOT NULL ,
        email        Varchar (50) NOT NULL ,
        telephone    Varchar (10) NOT NULL ,
        session_id   Int NOT NULL
	,CONSTRAINT stagiaire_PK PRIMARY KEY (stagiaire_id)

	,CONSTRAINT stagiaire_session_FK FOREIGN KEY (session_id) REFERENCES session(session_id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: formateur
#------------------------------------------------------------

CREATE TABLE formateur(
        formateur_id Int  Auto_increment  NOT NULL ,
        nom          Varchar (25) NOT NULL ,
        prenom       Varchar (25) NOT NULL ,
        motdepasse   Varchar (12) NOT NULL
	,CONSTRAINT formateur_PK PRIMARY KEY (formateur_id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: punissement
#------------------------------------------------------------

CREATE TABLE punissement(
        punissement_id Int  Auto_increment  NOT NULL ,
        type           Varchar (25) NOT NULL ,
        date           Date NOT NULL ,
        lieu           Varchar (50) NOT NULL ,
        description    Text NOT NULL ,
        formateur_id   Int NOT NULL
	,CONSTRAINT punissement_PK PRIMARY KEY (punissement_id)

	,CONSTRAINT punissement_formateur_FK FOREIGN KEY (formateur_id) REFERENCES formateur(formateur_id)
)ENGINE=InnoDB;


#------------------------------------------------------------
# Table: Recoit
#------------------------------------------------------------

CREATE TABLE Recoit(
        punissement_id Int NOT NULL ,
        stagiaire_id   Int NOT NULL
	,CONSTRAINT Recoit_PK PRIMARY KEY (punissement_id,stagiaire_id)

	,CONSTRAINT Recoit_punissement_FK FOREIGN KEY (punissement_id) REFERENCES punissement(punissement_id)
	,CONSTRAINT Recoit_stagiaire0_FK FOREIGN KEY (stagiaire_id) REFERENCES stagiaire(stagiaire_id)
)ENGINE=InnoDB;

