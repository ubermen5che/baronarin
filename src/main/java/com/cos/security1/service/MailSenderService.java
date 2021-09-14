package com.cos.security1.service;

import com.cos.security1.domain.Article;

public interface MailSenderService {
    void gmailSend(Article article, String email, String signedPath);
}
