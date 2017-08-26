package stud11314053.itdel.screeningtest;

/**
 * Created by Satya on 25-Aug-17.
 */

public class DaftarEvent {
    private String gambar;
    private String nama;
    private String tanggal;

    public DaftarEvent(String gambar, String nama, String tanggal) {
        this.gambar = gambar;
        this.nama = nama;
        this.tanggal = tanggal;
    }

    public void setGambar(String gambar) {
        this.gambar = gambar;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getGambar() {
        return gambar;
    }

    public String getNama() {
        return nama;
    }

    public String getTanggal() {
        return tanggal;
    }
}
