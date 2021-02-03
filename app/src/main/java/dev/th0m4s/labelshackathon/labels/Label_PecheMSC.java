package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_PecheMSC extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"peche-durable-msc"};
    }

    @Override
    public String getDisplayName() {
        return "Pêche Durable MSC";
    }

    @Override
    public int getImage() {
        return R.drawable.peche_msc;
    }
}
