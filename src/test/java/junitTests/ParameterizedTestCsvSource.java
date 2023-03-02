package junitTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.codeborne.selenide.Selenide.*;

public class ParameterizedTestCsvSource {
    @BeforeEach
    void openSite (){
        Configuration.baseUrl = "1920x1080";
        open("https://www.apple.com");
    }

    @CsvSource({
        "air, AirPods",
        "mac, MacBook",
    })

    @ParameterizedTest (name = "Товар {1} есть в списке по запросу {0}")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})

    void productSiteUrlShouldBePresentOnResultOfSearchInAppleByProductNameQuery (
        String letters,
        String product
    )
    {
        $("#globalnav-menubutton-link-search").click();
        $(".globalnav-searchfield-input").setValue(letters).pressEnter();
        $("#exploreCurated").shouldHave(Condition.text(product));
    }
}
