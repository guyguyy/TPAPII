package fr.ensim.tp5.data.Adresse;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AdressFeature {

    private AdressData properties;
    private GeometryData geometry;

    public AdressFeature() {

    }

    public AdressData getProperties() {
        return properties;
    }

    public void setProperties(AdressData properties) {
        this.properties = properties;
    }

    public GeometryData getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryData geometry) {
        this.geometry = geometry;
    }
}