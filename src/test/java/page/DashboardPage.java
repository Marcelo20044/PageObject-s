package page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import data.DataHelper;
import lombok.val;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement topUpButton = $("[data-test-id='action-deposit']");
    private SelenideElement amountField = $("[data-test-id='amount'] input");
    private SelenideElement cardTransferFrom = $("[data-test-id='from'] input");
    private SelenideElement actionTransferButton = $("[data-test-id='action-transfer']");



    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public void topUpCardBalance() {
        topUpButton.click();
    }


    public DashboardPage transferOperation(DataHelper.TransferInfo info) {
        amountField.setValue(info.getAmount());
        cardTransferFrom.setValue(info.getSecondCard());
        actionTransferButton.click();
        return new DashboardPage();
    }

    private ElementsCollection cards = $$(".list__item");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";


    public int getFirstCardBalance() {
        val text = cards.first().text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}
