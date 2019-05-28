
Create view artist_no_of_sculpture (artist_names,no_of_sculptures)
as Select artist_name, count(*)
From Sculpture_view
Group by artist_name
having count(*) > 2
;

select * from artist_no_of_sculpture;