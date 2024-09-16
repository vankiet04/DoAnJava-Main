package DTO;

public class DTO_Imei {
    long imei;
    int tinhtrang;

    public DTO_Imei(long imei, int tinhtrang) {
        this.imei = imei;
        this.tinhtrang = tinhtrang;
    }

    public long getImei() {
        return imei;
    }

    public void setImei(long imei) {
        this.imei = imei;
    }

    public int getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(int tinhtrang) {
        this.tinhtrang = tinhtrang;
    }
}
