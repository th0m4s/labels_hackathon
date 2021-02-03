package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_LabelRouge extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"label-rouge"};
    }

    @Override
    public String getDisplayName() {
        return "Label Rouge";
    }

    @Override
    public int getImage() {
        return R.drawable.label_rouge;
    }
}
