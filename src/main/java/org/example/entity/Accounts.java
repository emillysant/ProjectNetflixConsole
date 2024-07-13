package org.example.entity;

//@Table(name = "accounts")
public class Accounts {
    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String password_hash;
    private String created_at;

    public Accounts(int id, String email, String password_hash, String created_at) {
        this.id = id;
        this.email = email;
        this.password_hash = password_hash;
        this.created_at = created_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword_hash() {
        return password_hash;
    }

    public void setPassword_hash(String password_hash) {
        this.password_hash = password_hash;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }


}
