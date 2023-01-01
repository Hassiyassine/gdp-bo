package com.auth.detailes.service.mailer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.activation.DataHandler;
import java.io.File;
import java.util.Map;

/**
 * @author 	: Yassin OUICHOU | Ouichou.IT@gmail.com
 */

@Data @Builder
@AllArgsConstructor @NoArgsConstructor
public class MailData {

    private String from;
    private String to;
    private String subject;
    private String content;
    private Map<String, DataHandler> attachments;
}
