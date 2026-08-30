package org.luisa.user;

import java.util.Set;

import org.luisa.group.GroupEntity;
import org.luisa.profile.ProfileEntity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;
    private String username;

    // One-To-One
    @JsonIgnore
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private ProfileEntity profile;

    @ManyToMany
    @JoinTable(name = "groups_users", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "group_id"))
    private Set<GroupEntity> groups;

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public ProfileEntity getProfile() {
        return profile;
    }

    public Set<GroupEntity> getGroups() {
        return groups;
    }

}
