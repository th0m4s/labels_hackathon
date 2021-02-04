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

    @Override
    public int getTrust() {
        return 1;
    }

    @Override
    public String getDesc() {
        return "Label français attestant d'un niveau de qualité gustative supérieure spécifique à chaque produit établie via des cahiers des charges.";
    }
}
