package com.basepckg;

public class AkilliCihaz {
    IEyleyici eyleyici;
    ISicaklikAlgilayici sicaklikAlgılayici;
    IAgArayuzu agArayuzu;
    IMerkeziIslemBirimi merkeziIslemBirimi;

    AkilliCihaz(IAnaAgSistemi anaAgSistemi)
    {
        eyleyici = new Eyleyici();
        sicaklikAlgılayici = new SicaklikAlgilayici();
        agArayuzu = new AgArayuzu(anaAgSistemi);
        merkeziIslemBirimi = new MerkeziIslemBirimi(eyleyici, sicaklikAlgılayici, agArayuzu);
    }


}
