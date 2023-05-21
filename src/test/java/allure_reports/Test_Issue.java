package allure_reports;

import io.qameta.allure.Owner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;


@Owner("Vatsook1996")
public class Test_Issue extends TestBase {
        private static final String REPOSITORY_NAME = "eroshenkoam/allure-example",
                ISSUE_NUMBER = "81",
                ISSUE_NAME = "issue_to_test_allure_report";

        @Test
        @DisplayName("Проверка названия issue - чистый Selenide с Listener")
        void checkIssueName() {
            open("/");
            $(".header-search-input").setValue(REPOSITORY_NAME).submit();
            $(linkText("eroshenkoam/allure-example")).click();
            $("#issues-tab").click();
            $(String.format("#issue_%s_link", ISSUE_NUMBER)).shouldHave(text(ISSUE_NAME));
        }

        @Test
        @DisplayName("Проверка названия issue - лямбда шаги")
        void checkIssueNameLambdaStepsTest() {
            step("Открываем главную страницу", () -> {
                open("/");
            });
            step("Ищем репозиторий " + REPOSITORY_NAME, () -> {
                $(".header-search-input").setValue(REPOSITORY_NAME).submit();
            });
            step("Кликаем по ссылке репозитория " + REPOSITORY_NAME, () -> {
                $(linkText(REPOSITORY_NAME)).click();
            });
            step("Открываем таб Issues", () -> {
                $("#issues-tab").click();
            });
            step("Проверяем, что для issue#" + ISSUE_NUMBER + " отображается название: " + ISSUE_NAME, () -> {
                $(String.format("#issue_%s_link", ISSUE_NUMBER)).shouldHave(text(ISSUE_NAME));
            });
        }

        @Test
        @DisplayName("Проверка названия Issue - Шаги с аннотацией @Step")
        void checkIssueNameAnnotatedStepsTest(){
            WebSteps steps = new WebSteps();

            steps.openMainPage();
            steps.searchForRepository(REPOSITORY_NAME);
            steps.clickOnRepositoryLink(REPOSITORY_NAME);
            steps.openIssuesTab();
            steps.checkNameForIssueWithNumber(ISSUE_NUMBER, ISSUE_NAME);
            steps.takeScreenshot();
        }
    }
