package fr.ensim.tp5.controller;


import fr.ensim.tp5.data.Adresse.AdressQuery;
import fr.ensim.tp5.data.meteo.MeteoQuery;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

@Controller
public class MeteoCibleControleur {
    @PostMapping(path = "/meteocible")
    public String MeteoCibleControleur(@RequestParam String address, Model model) {
        System.out.println(address);
        model.addAttribute("address", address);
        RestTemplateBuilder builder = new RestTemplateBuilder();
        RestTemplate restTemplate = builder.build();


        double[] coordinate = getAddressResult(restTemplate, model, address);
        getMeteoResult(restTemplate, model, coordinate[1], coordinate[0]);

        return "meteocible";
    }

    private double[] getAddressResult(RestTemplate restTemplate, Model model, String addressMeteo){

        String uri = "https://api-adresse.data.gouv.fr/search/?limit=1&q=" + addressMeteo.replace(" ", "+");

        AdressQuery result = restTemplate.getForObject(uri, AdressQuery.class);
         String name = result.getFeatures()[0].getProperties().getName();
        String city = result.getFeatures()[0].getProperties().getCity() + ", " +result.getFeatures()[0].getProperties().getContext();

        double lon = result.getFeatures()[0].getGeometry().getCoordinates()[0];
        double lat = result.getFeatures()[0].getGeometry().getCoordinates()[1];

        model.addAttribute("name", name);
        model.addAttribute("city", city);

        model.addAttribute("lon", lon);
        model.addAttribute("lat", lat);

        return new double[]{lon, lat};
    }

    private void getMeteoResult(RestTemplate restTemplate, Model model, double lat, double lon) {
        // Préparation de la requête
        String uri = "https://api.meteo-concept.com/api/forecast/daily?" +
                "token=08c9d0a82c1eb23fdd59cb4c8d61743dc3dcee51ef8d83bca0b9060c36923e9f&" +
                "latlng="+ lat +","+ lon;
        MeteoQuery result = restTemplate.getForObject(uri, MeteoQuery.class);
        int probarain = result.getForecast()[0].getProbarain();
        int tmin = result.getForecast()[0].getTmin();
        int tmax = result.getForecast()[0].getTmax();
        int weather = result.getForecast()[0].getWeather();
        model.addAttribute("probarain", probarain);
        model.addAttribute("tmin", tmin);
        model.addAttribute("tmax", tmax);
        model.addAttribute("weather", weather);
    }
}