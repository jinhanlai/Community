function post() {
    const questionId=$("#question_id").val();
    const comment_content=$("#comment_content").val();
    $.ajax({
        type:"POST",
        dataType:"json",
        contentType:'application/json',
        url:"/comment",
        data:JSON.stringify({
            "parent_id":questionId,
            "content":comment_content,
            "type":1
        }),
        success:function (response) {
            if (response.code==200){
                $("#comment_section").hide();
            }else {
                alert(response.message);
            }
        },

    });
}