package be.vdab.artikelsenartikelgroepen.repositories;

import be.vdab.artikelsenartikelgroepen.domain.Artikel;
import be.vdab.artikelsenartikelgroepen.domain.Artikelgroep;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest(showSql = false)
@Sql({"/insertArtikelgroepen.sql", "/insertArtikels.sql"})
public class ArtikelRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String ARTIKELS = "artikels";
    private final ArtikelRepository repository;

    public ArtikelRepositoryTest(ArtikelRepository repository) {
        this.repository = repository;
    }

    @Test
    void findByPrijsBetween() {
        var van = BigDecimal.TEN;
        var tot = BigDecimal.valueOf(20);
        assertThat(repository.findByPrijsBetween(van, tot))
                .hasSize(countRowsInTableWhere(ARTIKELS, "prijs between 10 and 20"))
                .extracting(Artikel::getPrijs)
                .allSatisfy(prijs -> assertThat(prijs).isBetween(van, tot));
    }
    @Test
    void findMaxPrijs() {
        assertThat(repository.findMaxPrijs())
                .isEqualByComparingTo(jdbcTemplate.queryForObject(
                        "select max(prijs) from artikels", BigDecimal.class
                ));
    }

    @Test
    void findByArtikelgroepNaam() {
        var speelgoed = "speelgoed";
        assertThat(repository.findByArtikelgroepNaam(speelgoed))
                .hasSize(countRowsInTableWhere(ARTIKELS,
                        "artikelgroepId = (select id from artikelgroepen where naam = 'speelgoed')"))
                .first()
                .extracting(Artikel::getArtikelgroep)
                .extracting(Artikelgroep::getNaam)
                .isEqualTo(speelgoed);
    }
}
