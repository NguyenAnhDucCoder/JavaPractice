/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function KeepHightlight() {
    var list = document.getElementById("menuBar").getElementsByTagName("a");
    var defaultPage = document.location.origin + "/labJavaWeb/";
    for (var i = 0; i < list.length; i++) {
        if (document.location.href === defaultPage) {
          list[0].style.fontWeight = "Bold";
          list[0].style.fontSize = "110%";
      }
        if (list[i].href === window.location.href) {
            list[i].style.fontWeight = "Bold";
            list[i].style.fontSize = "110%";
        }
    }
}
function DisabledLink() {
    var list = document.getElementById("page").getElementsByTagName("a");
    //var productList = window.location.href;
    for (var i = 0; i < list.length; i++) {
//        if (window.location.href === productList) {
//            list[0].style.color = "gray";
//            list[0].style.pointerEvents = "none";
//            list[0].style.border = "none";
//        }
        if (list[i].href === window.location.href) {
            list[i].style.color = "gray";
            list[i].style.pointerEvents = "none";
            list[i].style.border = "none";
        }
    }
}



