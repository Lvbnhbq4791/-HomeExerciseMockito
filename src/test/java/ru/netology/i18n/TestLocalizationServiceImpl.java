package ru.netology.i18n;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

public class TestLocalizationServiceImpl {
    LocalizationServiceImpl localizationService;
    @BeforeEach
    public void startTest(){
        System.out.println("Начало теста");
     localizationService = new LocalizationServiceImpl();
    }
    @AfterEach
    public void stopTest(){
        System.out.println("Тест закончен");
    }
    @Test
    public void test1(){
        System.out.println("Тест возврата русского текста");
        String result = localizationService.locale(Country.RUSSIA);
        Assertions.assertEquals(result,"Добро пожаловать");
        System.out.println(result);
    }
    @Test
    public void test2(){
        System.out.println("Тест возврата английского  текста");
        String result = localizationService.locale(Country.USA);
        Assertions.assertEquals(result,"Welcome");
        System.out.println(result);
    }
}
