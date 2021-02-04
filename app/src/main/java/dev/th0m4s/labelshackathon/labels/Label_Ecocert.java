package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_Ecocert extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"equitable-ecocert", "ab-ecocert", "ecocert-france", "ecocert-certificate"};
    }

    @Override
    public String getDisplayName() {
        return "Ecocert";
    }

    @Override
    public int getImage() {
        return R.drawable.ecocert;
    }

    @Override
    public int getTrust() {
        return -1;
    }

    @Override
    public String getDesc() {
        return null;
    }
}
