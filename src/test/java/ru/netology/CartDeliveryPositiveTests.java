package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;

public class CartDeliveryPositiveTests {

    int days = 4;
    MeetingData meetingData = new MeetingData();

    @BeforeEach
    void setUp() {

        open( "http://localhost:7777/" );

    }


    @Test
    void shouldSendForm() {


        $( "[data-test-id='city'] input" ).setValue( "Москва" );
        $( "[data-test-id='date'] input" ).doubleClick().sendKeys( Keys.BACK_SPACE );
        $( "[data-test-id='date'] input" ).setValue( meetingData.generateDate( days ));
        $( "[data-test-id='name'] input" ).setValue( "Иванов Иван" );
        $( "[data-test-id='phone'] input" ).setValue( "+79261234567" );
        $( "[data-test-id='agreement']" ).click();
        $$( "button" ).find( exactText( "Забронировать" ) ).click();
        $( "[data-test-id='notification']" ).shouldHave( text( "Встреча успешно забронирована на " + meetingData.generateDate( days ) ), Duration.ofSeconds( 15 ) );

    }

    @Test
    public void shouldSendFormWithDashInName() {

        $( "[data-test-id='city'] input" ).setValue( "Москва" );
        $( "[data-test-id='date'] input" ).doubleClick().sendKeys( Keys.BACK_SPACE );
        $( "[data-test-id='date'] input" ).setValue( meetingData.generateDate( days ) );
        $( "[data-test-id='name'] input" ).setValue( "Иванов-Петров Иван" );
        $( "[data-test-id='phone'] input" ).setValue( "+79261234567" );
        $( "[data-test-id='agreement']" ).click();
        $$( "button" ).find( exactText( "Забронировать" ) ).click();
        $( "[data-test-id='notification']" ).shouldHave( text( "Встреча успешно забронирована на " + meetingData.generateDate( days ) ), Duration.ofSeconds( 15 ) );


    }


}
