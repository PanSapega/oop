function encrypt() {
    document.getElementById("isEncrypting").value = "true";
}

function browse() {
    document.getElementById('selectedFile').click();
    document.getElementById("data").required = false;
    document.getElementById("data").readOnly = true;
}

function loadFile(input) {
    let file = input.files[0];

    let reader = new FileReader();

    reader.readAsText(file);

    reader.onload = function() {
        document.getElementById("data").innerHTML = reader.result;
    };
}