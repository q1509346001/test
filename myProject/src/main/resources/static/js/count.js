
var num_jia = document.getElementById("num-jia");
var num_jian = document.getElementById("num-jian");
var input_num = document.getElementById("input-num");

num_jia.onclick = function() {

    input_num.value = parseInt(input_num.value) + 1;
}

num_jian.onclick = function() {

    if(input_num.value <= 0) {
        input_num.value = 0;
    } else {

        input_num.value = parseInt(input_num.value) - 1;
    }
    $("body").on("click",".num-jian",function (m) {
        var obj = $(this).closest("ul").find(".input-num");
        if (obj.val() <= 0) {
            obj.val(0);
        } else {
            obj.val(parseInt(obj.val()) - 1);
        }
        obj.change();
    });
    $("body").on("click",".num-jia",function (m) {
        var obj = $(this).closest("ul").find(".input-num");
        obj.val(parseInt(obj.val()) + 1);
        obj.change();
    });
}