use HotelReservation;

-- Write a query that returns a list of reservations that end in July 2023, 
 -- including the name of the guest, the room number(s), and the reservation dates.

select Guest.FirstName, Guest.LastName, R.startDate, R.endDate,RR.RoomNumber
from Guest
inner join Reservation R on Guest.GuestId = R.GuestId
inner join RoomReservation RR on R.ReservationNumber = RR.ReservationNumber
where R.endDate  between '2023-07-01' and '2023-07-30';

-- Write a query that returns a list of all reservations for rooms with a jacuzzi,
-- displaying the guest's name, the room number, and the dates of the reservation.

select Guest.FirstName, Guest.LastName, R.startDate, R.endDate,RR.RoomNumber
from Guest
inner join Reservation R on Guest.GuestId = R.GuestId
inner join RoomReservation RR on R.ReservationNumber = RR.ReservationNumber
left outer join RoomAmenites RA on RR.RoomNumber = RA.RoomNumber
where RA.AmenityId = '1003';

-- Write a query that returns all the rooms reserved for a specific guest, 
-- including the guest's name, the room(s) reserved, the starting date of the reservation, 
-- and how many people were included in the reservation. (Choose a guest's name from the existing data.)

select G.FirstName, G.LastName, R.startDate, R.endDate,RR.RoomNumber, RR.Adults, RR.Children
from Guest G
inner join Reservation R on G.GuestId = R.GuestId
inner join RoomReservation RR on R.ReservationNumber = RR.ReservationNumber
where G.LastName = 'vise';

-- Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. 
-- The results should include all rooms, whether or not there is a reservation associated with the room.

select  Rs.RoomNumber, RR.ReservationNumber, RR.TotalRoomCost
from Rooms Rs
inner join RoomReservation RR  on Rs.RoomNumber = RR.RoomNumber;
 
-- Write a query that returns all the rooms accommodating at least three guests
--  and that are reserved on any date in April 2023.
select RR.RoomNumber,
 sum(adults+children) 
from Reservation R 
inner join RoomReservation RR on R.ReservationNumber = RR.ReservationNumber
where R.startDate  between '2023-04-01' and '2023-04-30' 
Group by RR.RoomNumber
Having sum(adults+children) > 3;
-- Write a query that returns a list of all guest names and the number of reservations per guest, 
-- sorted starting with the guest with the most reservations and then by the guest's last name.

select  G.LastName, count(RR.ReservationNumber)ReservationNumber
from Guest G
inner join  Reservation R on G.GuestId = R.GuestId
inner join RoomReservation RR on R.ReservationNumber = RR.ReservationNumber
group by G.LastName 
order by ReservationNumber desc;







-- Write a query that displays the name, address, and phone number of a guest
-- based on their phone number. (Choose a phone number from the existing data.)

select G.FirstName, G.LastName, G.Address, G.Phone
from Guest G
where G.Phone = '(291)553-0508';

