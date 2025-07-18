import java.util.ArrayList;
import model.Feedback;

public class FeedbackController {
    public static void themFeedback(Feedback fb) {
        Feedback.Create(fb);
    }

    public static void xemThongTinFeedback(String maFeedback) {
        Feedback.Read(maFeedback);
    }

    public static ArrayList<Feedback> layTatCaFeedback() {
        return Feedback.Read();
    }

    public static void capNhatFeedback(String maFeedback, Feedback fbMoi) {
        Feedback.Update(maFeedback, fbMoi);
    }

    public static void xoaFeedback(String maFeedback) {
        Feedback.Delete(maFeedback);
    }
} 