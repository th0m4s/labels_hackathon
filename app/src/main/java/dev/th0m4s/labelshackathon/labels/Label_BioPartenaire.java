package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_BioPartenaire extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"biopartenaire", "bio-partenaire"};
    }

    @Override
    public String getDisplayName() {
        return "Bio Partenaire";
    }

    @Override
    public int getImage() {
        return R.drawable.bio_partenaire;
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
