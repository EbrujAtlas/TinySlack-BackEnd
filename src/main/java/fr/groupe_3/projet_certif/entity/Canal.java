package fr.groupe_3.projet_certif.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "canaux")
public class Canal {

    // Attributs
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID idCanal;

    @Column(name = "nom_du_canal")
    private String nomCanal;

    @Column(name = "description")
    private String descriptionCanal;

    @Column(name = "date_de_création")
    private LocalDate dateCreation;

    // 0 = libre, 1 = verrouillé
    @Column(name = "protection")
    private Integer locked;

    // Contructeur
    public Canal(String nomCanal, String descriptionCanal, LocalDate dateCreation, Integer locked) {
        this.nomCanal = nomCanal;
        this.descriptionCanal = descriptionCanal;
        this.dateCreation = dateCreation;
        this.locked = locked;
    }

    // Constructeur vide
    public Canal() {
    }

    // Getters
    public UUID getIdCanal() {
        return idCanal;
    }

    public String getNomCanal() {
        return nomCanal;
    }

    public String getDescriptionCanal() {
        return descriptionCanal;
    }

    public LocalDate getDateCreation() {
        return dateCreation;
    }

    public Integer getLocked() {
        return locked;
    }

    // Setters
    public void setIdCanal(UUID idCanal) {
        this.idCanal = idCanal;
    }

    public void setNomCanal(String nomCanal) {
        this.nomCanal = nomCanal;
    }

    public void setDescriptionCanal(String descriptionCanal) {
        this.descriptionCanal = descriptionCanal;
    }

    public void setDateCreation(LocalDate dateCreation) {
        this.dateCreation = dateCreation;
    }

    public void setLocked(Integer locked) {
        this.locked = locked;
    }

    // To string
    @Override
    public String toString() {
        return "Canal [idCanal=" + idCanal + ", nomCanal=" + nomCanal + ", descriptionCanal=" + descriptionCanal
                + ", dateCreation=" + dateCreation + ", locked=" + locked + "]";
    }

}
