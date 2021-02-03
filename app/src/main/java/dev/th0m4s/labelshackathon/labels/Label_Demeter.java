package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_Demeter extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"demeter"};
    }

    @Override
    public String getDisplayName() {
        return "Demeter";
    }

    @Override
    public int getImage() {
        return R.drawable.demeter;
    }
}
