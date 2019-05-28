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

