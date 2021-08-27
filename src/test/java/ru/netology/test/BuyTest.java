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

public class BuyTest {
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
        var paymentPage = new OrderPage().goToPayment();
        paymentPage.payment(cardInfo);
        paymentPage.invalidCardNotification();
    }

    @Test
    void shouldGetNotificationWrongFormatCard() {
        var cardInfo = new DataHelper().getInvalidFormatCardInfo("4444");
        var paymentPage = new OrderPage().goToPayment();
        paymentPage.payment(cardInfo);
        paymentPage.wrongFormatNotification();
    }
    @Test
    void shouldPaymentApprovedCard() {
        var cardInfo = new DataHelper().getValidCardInfo("approved");
        var paymentPage = new OrderPage().goToPayment();
        paymentPage.payment(cardInfo);
        paymentPage.approved();
        assertEquals("APPROVED",new DbHelper().getPaymentStatus());
        assertEquals(4500000, new DbHelper().getPaymentAmount());
        assertNull(new DbHelper().getCreditId());
    }

    @Test
    void shouldPaymentDeclinedCard() {
        var cardInfo = new DataHelper().getValidCardInfo("declined");
        var paymentPage = new OrderPage().goToPayment();
        paymentPage.payment(cardInfo);
        paymentPage.declined();
        assertEquals("DECLINED", new DbHelper().getPaymentStatus());
        assertNull(new DbHelper().getCreditId());
    }
    @Test
    void shouldGetNotificationEmptyFields() {
        var paymentPage = new OrderPage().goToPayment();
        paymentPage.emptyFieldNotification();
    }
}
