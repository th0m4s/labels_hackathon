package dev.th0m4s.labelshackathon.labels;

import dev.th0m4s.labelshackathon.R;

class Label_AOC extends ProductLabel {
    @Override
    public String[] getCodes() {
        return new String[] {"aoc", "aoc-martinique", "aoc-suisse"};
    }

    @Override
    public String getDisplayName() {
        return "AOC";
    }

    @Override
    public int getImage() {
        return R.drawable.aoc;
    }

    @Override
    public int getTrust() {
        return 2;
    }

    @Override
    public String getDesc() {
        return "Label français attestant que le produit et toutes les étapes nécessaires à sa fabrication sont spécifiques à son territoire d'origine.\n\nAccréditation délivrée par l'INAO.";
    }
}
