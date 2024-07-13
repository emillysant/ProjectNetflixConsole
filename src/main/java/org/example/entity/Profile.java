package org.example.entity;


//@Table(name = "account_profiles")
public class Profile {

    //    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int account;
    private String name;

    public Profile(int id, int account, String name) {
        this.id = id;
        this.account = account;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
