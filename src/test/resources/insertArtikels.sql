insert into artikels(naam, prijs, artikelGroepId) values
('playmobil', 9.95, (select id from artikelgroepen where naam = 'speelgoed')),
('kreeft', 20, (select id from artikelgroepen where naam = 'voedsel')),
('drone', 35, (select id from artikelgroepen where naam = 'speelgoed'));