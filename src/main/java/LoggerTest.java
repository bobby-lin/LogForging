import org.owasp.esapi.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerTest {
    private static final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    public static String encode(String message) {
        message = message.replace( '\n' ,  '_' ).replace( '\r' , '_' )
                .replace( '\t' , '_' );
        message = ESAPI.encoder().encodeForHTML(message);
        return message;
    }

    public static void main(String[] args) {
        String account_number = "1111-1111-1111-1111";
        logger.info("Money is transferred successfully for account: " + account_number);
        
        String malicious_payload = "\n2019-06-12 17:47:08 [main] INFO Transfer is reversed successfully";
        logger.info("Money is transferred successfully " + malicious_payload);

        String malicious_payload2 = "\n17-06-2019 23:13:58 [main] INFO Transfer is reversed successfully";
        logger.info("Money is transferred successfully " + malicious_payload2);

        String malicious_payload3 = "\n17-07-2019 23:13:58 [main] INFO Transfer is reversed successfully";
        logger.info("ENCODED THIS -> " + encode(malicious_payload3));

        String malicious_payload4 = "\n17-07-2029 23:13:58 \n[main] INFO Transfer is reversed successfully";
        logger.info("ENCODED THIS -> " + encode(malicious_payload4));
    }
}
