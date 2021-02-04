package dev.th0m4s.labelshackathon.labels;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public abstract class ProductLabel {
    public abstract String[] getCodes();
    public abstract String getDisplayName();
    public abstract int getImage();
    public abstract int getTrust();
    public abstract String getDesc();

    private static ProductLabel[] labels = new ProductLabel[] {new Label_AB(), new Label_AgriConfiance(), new Label_AOC(), new Label_AOP(), new Label_BioCoherence(), new Label_BioPartenaire(),
            new Label_Demeter(), new Label_Ecocert(), new Label_FRBIO01(), new Label_IGP(), new Label_LabelRouge(), new Label_PecheMSC()};

    public static HashMap<String, ProductLabel> getAllLabels() {
        HashMap<String, ProductLabel> map = new HashMap<>();
        for(ProductLabel label : labels) {
            for(String code : label.getCodes()) {
                map.put(code, label);
            }
        }

        return map;
    }
}
