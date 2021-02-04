package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_IGP extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"igp"};
    }

    @Override
    public String getDisplayName() {
        return "IGP";
    }

    @Override
    public int getImage() {
        return R.drawable.igp;
    }

    @Override
    public int getTrust() {
        return 1;
    }

    @Override
    public String getDesc() {
        return "Label européen moins strict que l'AOP : seule une des étapes de fabrication doit avoir lieu dans le territoire d'origine du produit.";
    }
}
