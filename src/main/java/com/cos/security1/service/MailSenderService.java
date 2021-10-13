package com.cos.security1.service;

import com.cos.security1.domain.Article;
import com.cos.security1.domain.Copyright;

public interface MailSenderService {
    void gmailSendArticle(Article article, String email, String signedPath);
    void gmailSendCopyright(Copyright copyright, String email, String signedPath);
    String sendAuthMail(String email);
}
