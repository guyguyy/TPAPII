package fr.ensim.tp5.data.Adresse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdressQuery {

    @JsonProperty
    private AdressFeature[] features;

    public AdressQuery() {}

    public AdressFeature[] getFeatures() {
        return features;
    }
    public void setFeatures(AdressFeature[] features) {
        this.features = features;
    }


}
