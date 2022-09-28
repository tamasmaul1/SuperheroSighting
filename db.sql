DROP DATABASE if exists HeroSightingsDB;
CREATE DATABASE HeroSightingsDB;
USE HeroSightingsDB;

CREATE TABLE Hero(
	HeroId SERIAL PRIMARY KEY,
    AttrHero boolean NOT NULL,
    Name VARCHAR(50) NOT NULL,
    Description VARCHAR(255)
);

CREATE TABLE Superpower(
	SuperpowerId SERIAL PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Description VARCHAR(255)
);

CREATE TABLE HeroSuperpower(
	HeroId INT NOT NULL,
    SuperpowerId INT NOT NULL,
    PRIMARY KEY (HeroId, SuperpowerId),
    FOREIGN KEY (HeroId) REFERENCES Hero(HeroId),
	FOREIGN KEY (SuperpowerId) REFERENCES Superpower(SuperpowerId)
);

CREATE TABLE Organization(
	OrganizationId SERIAL PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    AttrHero BOOLEAN NOT NULL,
    Description VARCHAR(255),
    Address VARCHAR(255),
    Contact VARCHAR(255)
);

CREATE TABLE HeroOrganization(
	HeroId INT NOT NULL,
    OrganizationId INT NOT NULL,
    PRIMARY KEY (HeroId, OrganizationId),
    FOREIGN KEY (HeroId) REFERENCES Hero(HeroId),
	FOREIGN KEY (OrganizationId) REFERENCES Organization(OrganizationId)
);

CREATE TABLE Location(
	LocationId SERIAL PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Latitude DECIMAL(10,8) NOT NULL,
    Longitude DECIMAL(11,8) NOT NULL,
    Description VARCHAR(255),
    AddressInformation VARCHAR(255)
);

CREATE TABLE Sighting(
	SightingId SERIAL PRIMARY KEY,
	HeroId INT NOT NULL,
    LocationId INT NOT NULL,
    Date DATE NOT NULL,
    FOREIGN KEY (HeroId) REFERENCES Hero(HeroId),
	FOREIGN KEY (LocationId) REFERENCES Location(LocationId)
);