function post() {
    const questionId = $("#question_id").val();
    const comment_content = $("#comment_content").val();
    if(!comment_content){
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
            "type": 1
        }),
        success: function (response) {
            if (response.code == 200) {
                window.location.reload();
            } else {
                if (response.code == 2003) {
                    if (confirm(response.message)){
                        window.open("https://github.com/login/oauth/authorize?client_id=3ff1b6b3e84c6f89364e&redirect_uri=http://localhost:8080/callback&scope=user&state==1");
                        window.localStorage.setItem("closable","true");
                    }
                } else {
                    alert(response.message);

                }
            }
        },

    });
}