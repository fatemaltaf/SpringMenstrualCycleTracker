package Main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {
    private final PayleGatewayHelper payleGatewayHelper;

    @Autowired
    public PaymentService(PayleGatewayHelper payleGatewayHelper) {
        this.payleGatewayHelper = payleGatewayHelper;
    }
    
    public String constructAndEncryptRequestData(String accountId, String accountPassword, String amount, String fullAmount, String paymentType, String businessCode, String invoiceKey, String returnUrl, String buyerMobile, String buyerEmail, String buyerName, String paymentMedia) {
        String ReqTranportalId = "tpid=" + accountId + "&";
        String ReqTranportalPassword = "tpkey=" + accountPassword + "&";
        String ReqAmount = "amount=" + amount + "&";
        String ReqFullAmount = "fullamount=" + fullAmount + "&";
        String ReqPaymentType = "paymenttype=" + paymentType + "&";
        String ReqBusinesscode = "businesscode=" + businessCode + "&";
        String ReqInvoiceKey = "invoicekey=" + invoiceKey + "&";
        String ReqUdf1 = "udf1=" + returnUrl + "&";
        String ReqUdf2 = "udf2=" + "&"; 
        String ReqUdf3 = "udf3=" + "&"; 
        String ReqPaymentMedia = "paymentmedia=" + paymentMedia + "&";
        String ReqPaymentBuyerMobile = "buyermobile=" + buyerMobile + "&";
        String ReqPaymentBuyerEmail = "buyeremail=" + buyerEmail + "&";
        String ReqPaymentBuyerName = "buyername=" + buyerName + "&";
    
        String transactionRequestData = ReqPaymentMedia + ReqAmount + ReqFullAmount
                + ReqPaymentType + ReqBusinesscode + ReqInvoiceKey + ReqUdf1 + ReqUdf2 + ReqUdf3
                + ReqTranportalId + ReqTranportalPassword + ReqPaymentBuyerMobile
                + ReqPaymentBuyerEmail + ReqPaymentBuyerName;
    
        try {
            String encryptedData = payleGatewayHelper.encryptAES(transactionRequestData);
            return encryptedData;
        } catch (Exception e) {
            e.printStackTrace();
            return null; 
        }

    }

    public String buildPaymentUrl(String encriptData, String accountId) {
            return "https://sandpay.payleq8.com/payinit/en/" + encriptData + "/" + accountId + "/0";
    }

    public void processPaymentResponse(String encryptedResponse) {
        try {
            // Decrypt the encryptedResponse using decryptAES method from PayleGatewayHelper
            String decryptedData = payleGatewayHelper.decryptAES(encryptedResponse);
        } catch (Exception e) {
            // Handle decryption or processing errors
        }
    }

}
