
create table Artist (
name varchar(10) not NULL PRIMARY KEY,
date_born varchar(10), 
date_died varchar(10),
description varchar(10), 
country_of_origin varchar(10));

create table Art_object( 
id_no int not NULL, 
year int, 
title varchar(10), 
description varchar(10), 
artist_name varchar(10), 
primary key(id_no),
FOREIGN KEY (artist_name) references Artist(name)
);

create table Others( 
id_no int, 
type varchar(10), 
style varchar(10), 
foreign key (id_no) references  Art_object(id_no), 
primary key(id_no));

create table Painting(
id_no int, 
type varchar(10), 
style varchar(10), 
drawn_on varchar(10), 
foreign key(id_no) references  Art_object(id_no),
primary key(id_no));

create table Sculpture(
id_no int, 
material varchar(10), 
weight varchar(10), 
height int, 
foreign key(id_no) references Art_object(id_no),
 primary key (id_no));

create table Exhibition( 
name varchar(60),
 start_date varchar(10), 
end_date varchar(10), 
primary key(name));

create table Shown_at( 
art int, 
exhibition_name varchar(60), 
foreign key(art) references Art_object(id_no), 
foreign key (exhibition_name) references Exhibition(name), 
primary key (art, exhibition_name)
);

Create view Others_view as
Select a.id_no, a.year, a.title, a.description, o.type, o.style, a.artist_name
From Art_object a, Others o
Where a.id_no = o.id_no;

Create view Painting_view as
Select   a.id_no, a.year, a.title, a.description, p.type, p.style, p.drawn_on, a.artist_name
From Art_object a, Painting p
Where a.id_no=p.id_no;

Create view Sculpture_view as
Select  a.id_no, a.year, a.title, a.description, s.material, s.weight, s.height, a.artist_name
From Art_object a, Sculpture s
Where a.id_no = s.id_no;







############## function and view



Create view artist_no_of_sculpture (artist_names,no_of_sculptures)
as Select artist_name, count(*)
From Sculpture_view
Group by artist_name
having count(*) > 2
;



Delimiter $

Create function No_of_painting_exhibited(exhibition_name varchar(60))
RETURNS INT
BEGIN

DECLARE no_of_paintings INT;
Set no_of_paintings= (
Select count(*)   
FROM Shown_at as a, Painting_view as b
WHERE a.exhibition_name = exhibition_name and a.art = b.id_no );
return no_of_paintings;
END$
Delimiter ;

