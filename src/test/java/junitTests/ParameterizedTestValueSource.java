package junitTests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Selenide.*;

public class ParameterizedTestValueSource {
    @BeforeEach
    void openSiteByApple (){
        Configuration.baseUrl = "1920x1080";
        open("https://www.apple.com");
    }

    @ValueSource(
            strings = {"air", "mac"}
    )
    @ParameterizedTest(name = "По запросу есть минимум 3 подходящих значения")
    @Tags({@Tag("BLOCKER"), @Tag("UI_TEST")})

    void searchResultCountTest (String letters) {
        $("#globalnav-menubutton-link-search").click();
        $(".globalnav-searchfield-input").setValue(letters).pressEnter();
        $$(".rf-serp-productdescription").shouldHave(CollectionCondition.sizeGreaterThan(3));
    }
}
