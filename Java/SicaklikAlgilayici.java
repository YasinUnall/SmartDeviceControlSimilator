package com.basepckg;

import java.util.Random;

public class SicaklikAlgilayici implements ISicaklikAlgilayici {
    private static float sicaklik;

    public SicaklikAlgilayici()
    {
        Random rand = new Random();
        sicaklik = rand.nextInt(46) - 10;
    }

    public float sicaklikOku(IEyleyici eyleyici){
        Random rand = new Random();

        if (eyleyici.sogutucuAcikmi()) {
            sicaklik -= 0.5;
        } else {
            sicaklik += (float)(rand.nextInt(3) - 1);
        }

        return sicaklik;
    }

    public void bildir(){

    }
}
