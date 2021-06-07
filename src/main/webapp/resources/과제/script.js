output_tag = document.getElementById("output");

function forClear() {
    document.getElementById("output").innerHTML = "0";
}
function forDisplayNum(target) {
    if (output_tag.innerHTML == 0) {
        output_tag.innerHTML = "";
    }
    var num = target.innerHTML;
    num = document.createTextNode(num);
    output_tag.appendChild(num);
}
function forDisplaySign1(target) {
    
    lastChar = output_tag.innerHTML.substr(output_tag.innerHTML.length - 1);
    if ( (lastChar != "X") & (lastChar != "-") & (lastChar != "+") & (lastChar != "/") & (lastChar != "%") ) {
        var sign = target.innerHTML;
        sign = document.createTextNode(sign);
        output_tag.appendChild(sign);    
    }
}
function forDisplayDot(target) {
    lastChar = output_tag.innerHTML.substr(output_tag.innerHTML.length - 1);
    if ( lastChar != "." ) {
        var dot = target.innerHTML;
        dot = document.createTextNode(dot);
        output_tag.appendChild(dot);
    }
}
function forDisplayRes(target) {
    changedText = output_tag.innerHTML.replace("X", "*");
    output_tag.innerHTML = eval(changedText)
    
}