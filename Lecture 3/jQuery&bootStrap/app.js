// document.getElementById("btn-add")
//     .addEventListener("click", () => {
//         const listNode = document.createElement("li");
//         listNode.innerHTML = "New Technology";
//
//         document.getElementById("technologies-list")
//             .appendChild(listNode);
//     });

$("#btn-add")
    .on("click", () => {
        $("#technologies-list")
            .append($("<li>New Technologies</li>"));
    });

// $('#technologies-list').on('click', 'li', function () {
//     $(this).remove();
// });

$('#technologies-list').on('click', 'li', function () {
    $("#technologies-list.li")
        .css('background', '#DDD');
});

$("li").on("mouseover", function() {
    $(this).css( "background", "grey" );
});

$("li").on("mouseleave", function() {
    $(this).css( "background", "white" );
});

// $("button").mouseenter(function () {
//     alert("You entered button!");
// });
//
// $("button").mouseleave(function () {
//     alert("You left button!");
// });

// $("h1").mouseenter(function () {
//     $("p").css( "background", "red" );
// });
//
// $("h1").mouseleave(function () {
//     $("p").css( "background", "white" );
// });

$("h1").hover(function () {
    $("p").css( "background", "red" );
}, function () {
    $("p").css( "background", "white" );
});

$("input").click(function(){
    $(this).hide("slow");
});

$("#btn-show-hide").click(function () {
    $("label").toggle("slow");
});

$("#btn-show-hide").click(function(){
    $("p").fadeToggle(1000);
});

$("#btn-slide").click(function(){
    $("p").slideToggle();
});

$("#btn-anime").click(function () {
    $("img").animate({left: "250px"});
});



