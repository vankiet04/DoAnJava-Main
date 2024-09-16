package DTO;

class DTO_ChiTietNhomQuyen {
    private String machucnang;
    private String tenchucnang;
    private String hanhdong;

    public DTO_ChiTietNhomQuyen() {
    }

    public DTO_ChiTietNhomQuyen(String machucnang, String tenchucnang, String hanhdong) {
        this.machucnang = machucnang;
        this.tenchucnang = tenchucnang;
        this.hanhdong = hanhdong;
    }

    public String getMachucnang() {
        return machucnang;
    }

    public void setMachucnang(String machucnang) {
        this.machucnang = machucnang;
    }

    public String getTenchucnang() {
        return tenchucnang;
    }

    public void setTenchucnang(String tenchucnang) {
        this.tenchucnang = tenchucnang;
    }

    public String getHanhdong() {
        return hanhdong;
    }

    public void setHanhdong(String hanhdong) {
        this.hanhdong = hanhdong;
    }
    

}