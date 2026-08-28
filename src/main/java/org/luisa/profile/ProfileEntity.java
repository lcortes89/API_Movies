package org.factoriaf5.profile;

import org.factoriaf5.country.CountryEntity;
import org.factoriaf5.user.UserEntity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "profiles")
public class ProfileEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_profile")
    private Long id;

    private String email;

    // https://www.baeldung.com/jpa-one-to-one
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id_user")
    private UserEntity user;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "country_id")
    private CountryEntity country;

    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public UserEntity getUser() {
        return user;
    }

    public CountryEntity getCountry() {
        return country;
    }

}
