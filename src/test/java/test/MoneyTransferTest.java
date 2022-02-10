package test;

import com.codeborne.selenide.Configuration;
import data.DataHelper;
import lombok.val;
import org.junit.jupiter.api.Test;
import page.LoginPage;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MoneyTransferTest {

    @Test
    void shouldTransferMoneyBetweenOwnCardsV1() {
        Configuration.holdBrowserOpen = true;
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationPage = loginPage.validLogin(authInfo);
        val verifyInfo = DataHelper.getVerificationCodeFor(authInfo);
        val dashboardPage = verificationPage.validVerify(verifyInfo);
        val transferInfo = DataHelper.getTransferInfo();
        dashboardPage.topUpCardBalance();
        dashboardPage.transferOperation(transferInfo);
        val balance = dashboardPage.getFirstCardBalance();
        int expected = 10505;
        int actual = balance;
        assertEquals(expected, actual);
    }
}
