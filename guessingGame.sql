drop database if exists guessingGame;

create database guessingGame;

use guessingGame;

create table Game (

	gameId int primary key auto_increment,
    gameDone bit not null,
    targetNum int(4) not null
	);
    
    
    
  create table  Round (
	roundId int primary key auto_increment,
    gameId int not null,
    guessNum varchar(10) not null,
    guessTime TimeStamp not null,
    partialMatch int not null,
    exactMatch int not null,
	foreign key fk_Round_GameId (GameId)
    references Game(GameId)
    );  
