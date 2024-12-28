package dut.udn.PGP.model;

import java.security.PrivateKey;
import java.security.PublicKey;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(length = 4096)
    private PublicKey publickey;

    @Column(length = 4096)
    private PrivateKey privatekey;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PublicKey getPublickey() {
        return publickey;
    }

    public void setPublickey(PublicKey userpubKey) {
        this.publickey = userpubKey;
    }

    public PrivateKey getPrivatekey() {
        return privatekey;
    }

    public void setPrivatekey(PrivateKey userprivateKey) {
        this.privatekey = userprivateKey;
    }
}
