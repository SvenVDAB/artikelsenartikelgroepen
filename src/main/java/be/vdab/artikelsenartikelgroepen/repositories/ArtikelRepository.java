package be.vdab.artikelsenartikelgroepen.repositories;

import be.vdab.artikelsenartikelgroepen.domain.Artikel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ArtikelRepository extends JpaRepository<Artikel, Long> {
    List<Artikel> findByPrijsBetween(BigDecimal van, BigDecimal tot);
    @Query("select max(a.prijs) from Artikel a")
    BigDecimal findMaxPrijs();

    List<Artikel> findByArtikelgroepNaam(String naam);
}
