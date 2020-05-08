package com.basepckg;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.SimpleTimeZone;

public class AgArayuzu implements IAgArayuzu{
    enum Islemler {SOGUTUCU_AC, SOGUTUCU_KAPAT, ALGILAMA_BASLA, DURUM_GORUNTULE, CIKIS, ALGILAMA_BITIR}

    private static IAnaAgSistemi anaAgSistemi;
    private static IMerkeziIslemBirimi merkeziIslemBirimi;

    public AgArayuzu(IAnaAgSistemi anaAgSistemi){
        this.anaAgSistemi = anaAgSistemi;
        this.anaAgSistemi.setAgArayuzu(this);
    }

    public void ilkDurumAl()
    {
        merkeziIslemBirimi.ilkDurumAl();
    }

    public void islemAl(Islemler islem)
    {
        merkeziIslemBirimi.islemAl(islem);
    }

    public void sicaklikGonder(float sicaklik){
        anaAgSistemi.sicaklikGuncelle(sicaklik);
    }

    public void setMerkeziIslemBirimi(IMerkeziIslemBirimi merkeziIslemBirimi) {
        this.merkeziIslemBirimi = merkeziIslemBirimi;
    }

    public Durum.YoneticiDurumlar dbDurumAl() { return anaAgSistemi.dbDurumAl(); }

    public void durumGuncelle(Durum.YoneticiDurumlar durum)
    {
        anaAgSistemi.durumGuncelle(durum);
    }

    public void kullaniciDurumGonder(Durum.KullaniciDurumlar durum)
    {
        anaAgSistemi.kullaniciDurumAl(durum);
    }

    public void mesajGonder(String mesaj)
    {
        anaAgSistemi.mesajGonder(mesaj);
    }
}
