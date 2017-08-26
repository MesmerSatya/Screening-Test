package stud11314053.itdel.screeningtest;

/**
 * Created by Satya on 26-Aug-17.
 */

public class DaftarGuest {
    private String nama;
    private String birthday;

    public DaftarGuest(String nama, String birthday) {
        this.nama = nama;
        this.birthday = birthday;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }
}
