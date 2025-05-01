package com.sprta.walkstudent.replycomment;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reply-comments")
@RequiredArgsConstructor
public class ReplyCommentController {

    private final ReplyCommentService replyCommentService;
}
