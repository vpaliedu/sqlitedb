package com.example.sqlite;

/*
Egy egyszerű model osztály.
Ez az osztály tartalmazza az adatokat, amelyeket meg szeretnénk jeleníteni a RecyclerView-ben.
*/

public class ListItem {

    private String number;
    private String name;
    private String email;

    public ListItem(String number, String name, String email) {
        this.number = number;
        this.name = name;
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
