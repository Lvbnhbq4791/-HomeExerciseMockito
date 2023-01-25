package ru.netology.sender;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;

public class TestMessageSender {
    public static final String russianID = "172.123.12.19";
    public static final String englishID = "96.44.183.149";
    Map<Country, Location> locationID;
    MessageSenderImpl messageSender;
    GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
    LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);


    public TestMessageSender() {
        this.locationID = new HashMap<>();
        GeoServiceImpl geoService1 = new GeoServiceImpl();
        locationID.put(Country.RUSSIA, geoService1.byIp(russianID));
        locationID.put(Country.USA, geoService1.byIp(englishID));

    }

    @BeforeEach
    public void startTest() {
        messageSender = new MessageSenderImpl(geoService, localizationService);
        System.out.println("Начало теста");
    }
    @AfterEach
    public void stopTest(){
        System.out.println("\n"+"Тест закончен");
    }

    @Test
    public void testRussianText() {
        System.out.println("MessageSenderImpl  отправляет только русский текст");
        String text = "Добро пожаловать";
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "172.123.12.19");
        given(geoService.byIp(russianID)).willReturn(locationID.get(Country.RUSSIA));
        given(localizationService.locale(Country.RUSSIA)).willReturn(text);
        String result = messageSender.send(headers);
        Assertions.assertEquals(result, text);
    }

    @Test
    public void testEnglishText() {
        System.out.println("MessageSenderImpl  отправляет только английский текст");
        String text = "Welcome";
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, "96.44.183.149");
        given(geoService.byIp(englishID)).willReturn(locationID.get(Country.USA));
        given(localizationService.locale(Country.USA)).willReturn(text);
        String result = messageSender.send(headers);
        Assertions.assertEquals(result, text);
    }
}
