package club.wlqzz.blog.service;

import java.io.File;
import java.util.List;

public interface MailService {
    void sendMail(String to, String subject, String verifyCode);

    void sendAttachmentsMail(String to, String title, String cotent, List<File> fileList);
}
