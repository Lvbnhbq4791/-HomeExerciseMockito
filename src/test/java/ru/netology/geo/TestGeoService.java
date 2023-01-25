package ru.netology.geo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import java.util.HashMap;
import java.util.Map;

public class TestGeoService {
    public static final String russianID = "172.123.12.19";
    public static final String englishID = "96.44.183.149";
    Map<String, Country> idCity = new HashMap<>();;
    GeoServiceImpl geoService1;

    public TestGeoService() {
        idCity.put(russianID,Country.RUSSIA);
        idCity.put(englishID,Country.USA);
    }

    @BeforeEach
    public void startTest(){
        System.out.println("Начало теста");
        geoService1 = new GeoServiceImpl();
    }
    @AfterEach
    public void stopTest(){
        System.out.println("Тест закончен");
    }

    @ParameterizedTest
    @ValueSource (strings = {russianID,englishID})
    public void geoServiceTest(String arg){
        System.out.println("Тестируем ID:"+arg);
        String result = String.valueOf(geoService1.byIp(arg).getCountry());
        Assertions.assertEquals(String.valueOf(idCity.get(arg)),result);
        System.out.println(result);
    }
}
