package com.example.findme;

public class Position {
    int id;
    String numero,latituden,longitude;

    public Position(int id, String numero, String latituden, String longitude) {
        this.id = id;
        this.numero = numero;
        this.latituden = latituden;
        this.longitude = longitude;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", numero='" + numero + '\'' +
                ", latituden='" + latituden + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
