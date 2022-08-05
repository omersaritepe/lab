function showAlert() {
    alert("The button was clicked!");
}
function updateTextInput(val) {
    document.getElementById('textInput').value=val;
}

function xxx(val) {
    return "@{'/labWorkers/page/2/' + ${" + val + "}}"
}