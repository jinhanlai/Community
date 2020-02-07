/**
 * 提交回复
 */
function post() {
    const questionId = $("#question_id").val();
    const comment_content = $("#comment_content").val();
    comment2Target(questionId, comment_content, 1);
}


function comment2Target(questionId, comment_content, type) {
    if (!comment_content) {
        alert("不能回复空内容！！");
    }
    $.ajax({
        type: "POST",
        dataType: "json",
        contentType: 'application/json',
        url: "/comment",
        data: JSON.stringify({
            "parent_id": questionId,
            "content": comment_content,
            "type": type
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    if (confirm(response.message)) {
                        window.open("https://github.com/login/oauth/authorize?client_id=3ff1b6b3e84c6f89364e&redirect_uri=http://localhost:8080/callback&scope=user&state==1");
                        window.localStorage.setItem("closable", "true");
                    }
                } else {
                    alert(response.message);

                }
            }
        },

    });
}

/**
 * 回复评论
 */
function comment(e) {
    const commentId = e.getAttribute("data-id");
    const content = $("#input-" + commentId).val();
    comment2Target(commentId, content, 2);
}

/**
 * 展开二级评论
 */
function collapsecomment(e) {
    const id = e.getAttribute("data-id");
    const comment = $("#comment-" + id);
    comment.toggleClass("in");
    e.classList.toggle("active");
    if (comment.hasClass("in")) {
        $.getJSON("/comment/" + id, function (data) {
            console.log(data);
            const commenBody = $("#comment-body-" + id);
            const items = [];
            $.each(data.data, function (comment) {
                const c=$("<div/>", {
                    "class": "col-lg-12 col_md_12 col_sm_12 col_xs_12 comments",
                    html: comment.content,
                });
                items.push(c);
            });
            commenBody.appendChild($("<div/>", {
                "class": "col-lg-12 col_md_12 col_sm_12 col_xs_12 collapse sub-comments",
                "id": "comment" + id,
            }));
        })
    } else {

    }
}