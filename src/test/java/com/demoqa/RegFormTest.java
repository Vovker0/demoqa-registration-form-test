package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegFormTest {

    //переменные для теста
    String testFirstName = "Brandon";
    String testLastName = "Lee";
    String testEmail = "brandonl@gmail.com";
    String testPhone = "8506209933";
    String yearOfBirth = "1985";
    String monthOfBirth = "March";
    String dayOfBirth = "10";
    String gender = "Male";
    String subject01 = "English";
    String subject02 = "Computer science";
    String hobby01 = "Reading";
    String hobby02 = "Music";
    String filePath = "src/test/";
    String fileName = "under-hood.png";
    String testAddress = "1618 Main St, Dallas, TX, 75201";
    String testState = "Rajasthan";
    String testCity = "Jaipur";


    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1920x1080";
        Configuration.pageLoadStrategy = "eager";
        //Configuration.holdBrowserOpen = true;
    }

    @Test
    void fillFormTest() {
        open("/automation-practice-form");

        //убираем мешающие банеры
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");

        // Вносим данные
        $("#firstName").setValue(testFirstName);
        $("#lastName").setValue(testLastName);
        $("#userEmail").setValue(testEmail);
        $("#genterWrapper").$(byText(gender)).click();
        $("#userNumber").setValue(testPhone);

        $("#dateOfBirthInput").click();
        $(".react-datepicker__year-select").selectOption(yearOfBirth);
        $(".react-datepicker__month-select").selectOption(monthOfBirth);
        $(".react-datepicker__month-container").$(byText(dayOfBirth)).click();

        $("#subjectsInput").setValue(subject01).pressEnter();
        $("#subjectsInput").setValue(subject02).pressEnter();

        $("#hobbiesWrapper").$(byText(hobby01)).click();
        $("#hobbiesWrapper").$(byText(hobby02)).click();

        $("#uploadPicture").uploadFile(new File(filePath + fileName));

        $("#currentAddress").setValue(testAddress);

        $("#state").click();
        $("#state").$(byText(testState)).click();
        $("#city").click();
        $("#city").$(byText(testCity)).click();

        $("#submit").click();

        //Проверяем данные
        $(".table-responsive").$(byText("Student Name")).sibling(0)
                .shouldHave(text(testFirstName + " " + testLastName));
        $(".table-responsive").$(byText("Student Email")).sibling(0)
                .shouldHave(text(testEmail));
        $(".table-responsive").$(byText("Gender")).sibling(0)
                .shouldHave(text(gender));
        $(".table-responsive").$(byText("Mobile")).sibling(0)
                .shouldHave(text(testPhone));
        $(".table-responsive").$(byText("Date of Birth")).sibling(0)
                .shouldHave(text(dayOfBirth + " " + monthOfBirth + "," + yearOfBirth));
        $(".table-responsive").$(byText("Subjects")).sibling(0)
                .shouldHave(text(subject01 + ", " + subject02));
        $(".table-responsive").$(byText("Hobbies")).sibling(0)
                .shouldHave(text(hobby01 + ", " + hobby02));
        $(".table-responsive").$(byText("Picture")).sibling(0)
                .shouldHave(text(fileName));
        $(".table-responsive").$(byText("Address")).sibling(0)
                .shouldHave(text(testAddress));
        $(".table-responsive").$(byText("State and City")).sibling(0)
                .shouldHave(text(testState + " " + testCity));

        $("#closeLargeModal").click();
    }
}
