package ru.example.java.demo.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
class Main {
    static  ObjectMapper mapper = new ObjectMapper();
    static String PAGE_URL = "https://krisha.kz/prodazha/kvartiry/semej/?page=";//1
    static String CART_URL = "https://krisha.kz/a/show/";//675007842
    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

    }

    public static void main(String[] args) throws UnirestException, IOException, InterruptedException {

        List<String> carts = new ArrayList<>(500);
        int page = 1;
        int i = 1;
        String[] ids;
        try {
        do {
            ids = getIds(PAGE_URL + page++);
            for (String id:ids) {
                Cart cart = getCart(getJsonCart(CART_URL+id));
                String s = cart.toString();
                log.info(i++ + s);
                carts.add(s);
                if (carts.size() > 499){
                    write(carts,"result.csv");
                    carts.clear();
                }

            }

        }while (ids.length>0);
    }catch (UnirestException e){
            log.error(e.toString());

    }finally {
            write(carts,"result1.csv");
        }



    }

    private static void write(List<String> carts,String file) throws IOException {
        try (FileWriter fw = new FileWriter(file, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            carts.forEach(out::println);
        }
    }

    private static String getJsonCart(String url) throws UnirestException {
        Matcher m = Pattern.compile("(?<=(adverts\":\\[))(\\{.+\\})(?=(,\"nbPhotos))").matcher(getResponse(url));
        m.find();
        return m.group() + "}";
    }
    private static Cart getCart(String json) throws JsonProcessingException, UnirestException {
        Cart cart = mapper.readValue(json, Cart.class);
        cart.setPhones(getPhones(cart.getId()));
        return cart;
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
    public static String getPhones(Integer id) throws UnirestException, JsonProcessingException {

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Unirest.setTimeouts(0, 0);
        HttpResponse<String> response = Unirest.get("https://krisha.kz/a/ajaxPhones?id=" + id)
                .header("authority", "krisha.kz")
                .header("accept", "application/json, text/plain, */*")
                .header("x-requested-with", "XMLHttpRequest")
                .header("user-agent", "")
                .header("referer", "https://krisha.kz/a/show/" + id)
                .asString();


        Map<String, List<String>> phones = mapper.readValue(response.getBody(), Map.class);
     return   phones.get("phones").stream().collect(Collectors.joining(" "));


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