package commentsender;

import android.content.Context;
import android.util.Log;

import org.acra.ReportField;
import org.acra.collector.CrashReportData;
import org.acra.sender.ReportSender;
import org.acra.sender.ReportSenderException;

public class ACRAReportSender implements ReportSender {

    private String emailUsername;
    private String emailPassword;


    public ACRAReportSender(String emailUsername, String emailPassword) {
        super();
        this.emailUsername = emailUsername;
        this.emailPassword = emailPassword;
    }


    @Override
    public void send(Context context, CrashReportData report)
            throws ReportSenderException {

        String reportBody = createCrashReport(report);


        GMailSender gMailSender = new GMailSender(emailUsername, emailPassword);

        try {

            gMailSender.sendMail("CRASH REPORT", reportBody, emailUsername, "somnath.n005@gmail.com");
        } catch (Exception e) {
            Log.d("Error Sending email", e.toString());
        }
    }


    private String createCrashReport(CrashReportData report) {


        StringBuilder body = new StringBuilder();
        body
                .append("Device : " + report.getProperty(ReportField.BRAND) + "-" + report.getProperty(ReportField.PHONE_MODEL))
                .append("\n")
                .append("Android Version :" + report.getProperty(ReportField.ANDROID_VERSION))
                .append("\n")
                .append("App Version : " + report.getProperty(ReportField.APP_VERSION_CODE))
                .append("\n")
                .append("STACK TRACE : \n" + report.getProperty(ReportField.STACK_TRACE));


        return body.toString();
    }
}

