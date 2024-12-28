package dut.udn.PGP.model;

import java.security.PublicKey;

import jakarta.persistence.*;

@Entity
@Table(name = "keymanagement")
public class Key {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;


    @Column(length = 4096)
    private PublicKey publickey;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public PublicKey getPublickey() {
        return publickey;
    }

    public void setPublickey(PublicKey userpubKey) {
        this.publickey = userpubKey;
    }

  
}
