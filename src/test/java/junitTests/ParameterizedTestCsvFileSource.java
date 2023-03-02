package junitTests;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParameterizedTestCsvFileSource {
    @BeforeEach
    void openSiteApple (){
        Configuration.baseUrl = "1920x1080";
        open("https://www.apple.com");
    }

    @CsvFileSource(resources = "/testData.csv")
    @ParameterizedTest (name = "Товар {1} есть в списке по запросу {0}")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})

    void productSiteUrlShouldBePresentOnResultOfSearchInAppleByProductName (
            String letters,
            String product
    )
    {
        $("#globalnav-menubutton-link-search").click();
        $(".globalnav-searchfield-input").setValue(letters).pressEnter();
        $("#exploreCurated").shouldHave(Condition.text(product));
    }
}
