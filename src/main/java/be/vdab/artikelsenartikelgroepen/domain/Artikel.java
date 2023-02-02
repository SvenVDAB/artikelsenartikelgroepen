package be.vdab.artikelsenartikelgroepen.domain;

import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "artikels")
public class Artikel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String naam;
    private BigDecimal prijs;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "artikelGroepId")
    private Artikelgroep artikelgroep;

    public long getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public BigDecimal getPrijs() {
        return prijs;
    }

    public Artikelgroep getArtikelgroep() {
        return artikelgroep;
    }
}
