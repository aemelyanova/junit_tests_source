package junitTests;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import junitTests.data.Locale;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;


public class ParameterizedTestMethodSource {

    static Stream<Arguments> SiteShouldContainAllOfButtonForGivenLocale() {
        return Stream.of(
                Arguments.of(Locale.Italiano,List.of(
                        "Strumenti", "I nostri dati", "Risorse", "Prezzi", "Enterprise")),
                Arguments.of(Locale.English,List.of(
                        "Tools","Our data","Resources","Pricing","Enterprise"))
       );
    }
    @MethodSource
    @ParameterizedTest (name = "Для локали {0} отображаются кнопки {1}")
    @Tag("BLOCKER")
    void SiteShouldContainAllOfButtonForGivenLocale(
            Locale locale,
            List <String> buttons
    ) {
        Selenide.open("https://ahrefs.com/");
        Selenide.$(".css-7uw272-selectWrapper").click();
        Selenide.$$(".css-kssd3c-innerSelect option").find(Condition.text(locale.name())).click();
        Selenide.$$(".css-1ovir3p-menuItem").shouldHave(CollectionCondition.texts(buttons));
    }
}
