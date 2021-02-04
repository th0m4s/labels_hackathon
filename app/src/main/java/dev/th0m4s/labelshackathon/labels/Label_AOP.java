package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_AOP extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"aop", "aop-martinique"};
    }

    @Override
    public String getDisplayName() {
        return "AOP";
    }

    @Override
    public int getImage() {
        return R.drawable.aop;
    }

    @Override
    public int getTrust() {
        return 2;
    }

    @Override
    public String getDesc() {
        return "Version européenne du label AOC attestant la provenance du produit d'un territoire spécifique (et du savoir-faire utilisé).\n\nEn France, l'accréditation est délivrée par l'INAO.";
    }
}
