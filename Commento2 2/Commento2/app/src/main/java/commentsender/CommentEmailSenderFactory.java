package commentsender;

import android.content.Context;

import org.acra.config.ACRAConfiguration;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderFactory;

public class CommentEmailSenderFactory implements ReportSenderFactory {

    // NB requires a no arg constructor.

    public ReportSender create(Context context, ACRAConfiguration config) {

        return new ACRAReportSender("bravokk437@gmail.com", "idealkk437");
    }
}
