package ru.netology.test;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.data.DbHelper;
import ru.netology.page.OrderPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ByinCreditTest {
    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    void setUp() {
        open("http://localhost:8080/");
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }
    @Test
    void shouldGetNotificationInvalidCard() {
        var cardInfo = new DataHelper().getInvalidCardInfo("approved");
        var creditPage = new OrderPage().goToCredit();
        creditPage.credit(cardInfo);
        creditPage.invalidCardNotification();
    }

    @Test
    void shouldGetNotificationWrongFormatCard() {
        var cardInfo = new DataHelper().getInvalidFormatCardInfo("4444");
        var creditPage = new OrderPage().goToCredit();
        creditPage.credit(cardInfo);
        creditPage.wrongFormatNotification();
    }
    @Test
    void shouldCreditApprovedCard() {
        var cardInfo = new DataHelper().getValidCardInfo("approved");
        var creditPage = new OrderPage().goToCredit();
        creditPage.credit(cardInfo);
        creditPage.approved();
        assertEquals("APPROVED", new DbHelper().getCreditRequestStatus());
        assertNull(new DbHelper().getCreditId());
    }

    @Test
    void shouldPaymentDeclinedCard() {
        var cardInfo = new DataHelper().getValidCardInfo("declined");
        var creditPage = new OrderPage().goToCredit();
        creditPage.credit(cardInfo);
        creditPage.declined();
        assertEquals("DECLINED", new DbHelper().getCreditRequestStatus());
        assertNull(new DbHelper().getCreditId());
    }
    @Test
    void shouldGetNotificationEmptyFields() {
        var creditPage = new OrderPage().goToCredit();
        creditPage.emptyFieldNotification();
    }
}
