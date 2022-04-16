package ru.example.java.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.Data;


import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


class Main {
    static  ObjectMapper mapper = new ObjectMapper();
    static String pageUrl = "https://krisha.kz/prodazha/kvartiry/semej/?page=";//1
    static String url = "https://krisha.kz/a/show/";//675007842
    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    public static void main(String[] args) throws UnirestException, JsonProcessingException {


        String json = "{\"id\":670469356,\"tempId\":675007842,\"storage\":\"live\",\"uuid\":\"ae578e0c-b0ec-48e1-8af2-27b86d871152\",\"url\":\"/a/show/675007842\",\"title\":\"4-комнатная квартира, 82 м², 7/10 этаж\",\"titleWithPrice\":\"4-комнатная квартира, 82 м², 7/10 этаж за 30&nbsp;млн&nbsp;<span class=\\\"currency-sign offer__currency\\\">〒</span>\",\"address\":\"Достоевского 186\",\"fullAddress\":\"Семей, Достоевского 186\",\"price\":\"30&nbsp;000&nbsp;000&nbsp;<span class=\\\"currency-sign offer__currency\\\">〒</span>\",\"priceM2\":365853,\"priceM2Text\":null,\"daysInLive\":5,\"description\":\"кирпичный дом, 1993 г.п., состояние: хорошее, потолки 2.7м., санузел совмещенный, телефон: отдельный, интернет ADSL, частично меблирована, Продается 4-комнатная квартира новой планировки в кирпичном доме 1993 года район стадиона Спартак. Развитая инфраструктура. Рядом расположены: школа № 17 школа…\",\"color\":\"green\",\"isFlatLayout\":false,\"isHouseLayout\":false,\"isLayout\":false,\"category\":{\"id\":1,\"name\":\"sell.flat\",\"label\":\"Продажа квартир\",\"isDisabled\":false},\"owner\":{\"isPro\":true,\"isComplex\":false,\"isBuilder\":false,\"isOwner\":false,\"title\":\"Осинцев Валентин, компания «ТОО ЭТАЖИ СЕМЕЙ»\",\"label\":{\"title\":\"Специалист\",\"name\":\"identified-specialist\",\"color\":\"transparent\"},\"isChecked\":false,\"isCurrentUser\":false}}";
        Cart cart = mapper.readValue(json, Main.Cart.class);
        cart.setPhones(getJsonPhones(cart.getId()));
        System.out.println(cart.toString());
    }

    private static String getJson(String url) throws UnirestException {
        Matcher m = Pattern.compile("(?<=(adverts\":\\[))(\\{.+\\})(?=(,\"nbPhotos))").matcher(getResponse(url));
        m.find();
        return m.group() + "}";
    }
    private static String[] getIds(String url) throws UnirestException {
        Matcher m = Pattern.compile("(?<=(ids\":\\[))(\\d|,)*(?=(\\]))").matcher(getResponse(url));
        return m.find() ? m.group().split(",") : new String[0];
    }
    public static String getResponse(String url) throws UnirestException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get(url)
                .header("authority", "krisha.kz")
                .header("accept", "application/json, text/plain, */*")
                .header("x-requested-with", "XMLHttpRequest")
                .asString();
        return response.getBody();
    }
    public static String getJsonPhones(Integer id) throws UnirestException, JsonProcessingException {
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://krisha.kz/a/ajaxPhones?id=" + id)
                .header("authority", "krisha.kz")
                .header("accept", "application/json, text/plain, */*")
                .header("x-requested-with", "XMLHttpRequest")
                .header("user-agent", "")
                .header("referer", "https://krisha.kz/a/show/" + id)
                .asString();


        Map<String, List<String>> phones = mapper.readValue(response.getBody(), Map.class);
        return phones.get("phones").stream().collect(Collectors.joining(","));

    }
    @Data
    public static class Cart {
        Integer id;
        String title;
        String price;
        String address;
        String url;
        String description;
        Cart owner;
        String phones;


        public String getFullUrl() {
            return "https://krisha.kz" + url;
        }

        public String getPrice() {

            return price.split("<")[0].replaceAll("&nbsp;","");
        }

        public String getTitle() {
            return title;
        }

        @Override
        public String toString() {
            return
                    address + '\t' +
                            this.getPrice() + '\t' +
                            this.getFullUrl() + '\t' +
                            phones + '\t' +
                            owner.getTitle() + '\t' +
                            title + '\t' +
                            description;
        }
    }


}