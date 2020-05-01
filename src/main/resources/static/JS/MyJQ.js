function getRoles(html) {
    $.ajax({
        type:"post",
        url:"/getRoles",
        contentType: 'application/json;charset=utf-8',
        success:function (data) {
            if (data.code > 200){
                window.location.href = html
            }
            else {
                alert(data.message);
            }
        }
    })
}