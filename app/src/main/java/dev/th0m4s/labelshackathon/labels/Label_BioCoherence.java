package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_BioCoherence extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"bio-coherence"};
    }

    @Override
    public String getDisplayName() {
        return "Bio Cohérence";
    }

    @Override
    public int getImage() {
        return R.drawable.bio_coherence;
    }

    @Override
    public int getTrust() {
        return 2;
    }

    @Override
    public String getDesc() {
        return "Label français privé créé après l'assouplissement des règles du label AB : productions 100% BIO, 100% d'ingrédients français, prise en compte du bien-être animal et moins de 0.1% d'OGM.";
    }
}
