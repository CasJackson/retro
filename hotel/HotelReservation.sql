drop database if exists HotelReservation;

Create database HotelReservation;

use HotelReservation;



    


create table Guest (

	GuestId char(6) primary key,
    FirstName varchar(50) not null,
    LastName varchar(50) not null,
    Address varchar(256) not null,
    City varchar(100) not null,
    StateAbbr char(3) not null,
    Zip char(10) not null,
    Phone char(13) null
    
);


 create table Reservation (
 
	ReservationNumber char(6)primary key,
	GuestId  char(6) not null,
    StartDate date not null,
    EndDate   date not null,
    FOREIGN KEY fk_Reservation_GuestId(GuestId)
        REFERENCES Guest (GuestId)
 );   											
  
    create table RoomType (
    RoomTypeId char(10) primary key,
    RoomSize  varChar(12) not null,
    Occupancy  int not null,
    MaxOccupancy int not null,
    BasePrice decimal(5,2) not null
    );
create table Rooms (
	RoomNumber char(6) primary key,
    RoomTypeId  Char(10) not null,
	ExtraPerson char(5) null,
    ADAAccessible bit null,
    FOREIGN KEY fk_Rooms_RoomTypeId(RoomTypeId)
    REFERENCES RoomType (RoomTypeId)
    );
  
    create table Amenities (
    AmenityId char(25) Primary key,
	AmenityType  varChar(35) not null,
		ExtraCharge decimal(4,2) not null
    );

          


CREATE TABLE RoomReservation (
    RoomNumber CHAR(6) NOT NULL,
    ReservationNumber CHAR(6) NOT NULL,
    Adults int NOT NULL,
    Children int NULL,
    TotalRoomCost decimal(6,2) NOT NULL,
    PRIMARY KEY (RoomNumber , ReservationNumber),
    FOREIGN KEY (RoomNumber)
        REFERENCES Rooms (RoomNumber),
    FOREIGN KEY (ReservationNumber)
        REFERENCES Reservation (ReservationNumber)
);        



 
create table RoomAmenites (
	RoomNumber char(6) not null,
	AmenityId char(250) not null,
	Primary Key (RoomNumber,AmenityId),
    foreign Key fk_RoomAmenities_RoomNumber(RoomNumber)
    references Rooms(RoomNumber),
    foreign Key fk_RoomAmenities_AmenityId(AmenityId)
    references Amenities(AmenityId)
    );
    
    
    
    
            
    