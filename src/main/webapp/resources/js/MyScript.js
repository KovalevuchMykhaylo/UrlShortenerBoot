window.onload = function () {
    var inp = document.getElementById('urlinput');
    if (inp != null) {
        var chars = document.getElementById('inputCharsLength');
        inp.onkeyup = function () {
            chars.innerHTML = inp.value.length;
        }
    }
};