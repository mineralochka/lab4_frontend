function drawCanvas(r){
    var canvas = document.getElementById("canvas");
    var context = canvas.getContext("2d");
    //очистка
    context.clearRect(0, 0, canvas.width, canvas.height);
    if (r !== 0) {
        //прямоугольник
        context.beginPath();
        context.rect(150, 150, 130, 65);
        context.closePath();
        context.strokeStyle = "#5c99ED";
        context.fillStyle = "#5c99ED";
        context.fill();
        context.stroke();

        // сектор
        context.beginPath();
        context.moveTo(150, 150);
        context.arc(150, 150, 65, Math.PI * 3 / 2, Math.PI, true);
        context.closePath();
        context.strokeStyle = "#5c99ED";
        context.fillStyle = "#5c99ED";
        context.fill();
        context.stroke();

        //треугольник
        context.beginPath();
        context.moveTo(85, 150);
        context.lineTo(150, 280);
        context.lineTo(150, 150);
        context.lineTo(85, 150);
        context.closePath();
        context.strokeStyle = "#5c99ED";
        context.fillStyle = "#5c99ED";
        context.fill();
        context.stroke();
    }
    else{
        r = 2;
    }
    context.strokeStyle = "#5c99ED";
    context.fillStyle = "#5c99ED";
    context.fill();
    context.stroke();
    //отрисовка осей
    context.beginPath();
    context.font = "10px Verdana";
    context.moveTo(150, 0);
    context.lineTo(150, 300);
    context.moveTo(150, 0);
    context.lineTo(145, 15);
    context.moveTo(150, 0);
    context.lineTo(155, 15);
    context.fillText("Y", 160, 10);
    context.moveTo(0, 150);
    context.lineTo(300, 150);
    context.moveTo(300, 150);
    context.lineTo(285, 145);
    context.moveTo(300, 150);
    context.lineTo(285, 155);
    context.fillText("X", 290, 135);

    // деления X
    context.moveTo(145, 20);
    context.lineTo(155, 20);
    context.fillText(r, 160, 20);
    context.moveTo(145, 85);
    context.lineTo(155, 85);
    context.fillText((r / 2), 160, 78);
    context.moveTo(145, 215);
    context.lineTo(155, 215);
    context.fillText(-(r / 2), 160, 215);
    context.moveTo(145, 280);
    context.lineTo(155, 280);
    context.fillText(-r, 160, 280);
    // деления Y
    context.moveTo(20, 145);
    context.lineTo(20, 155);
    context.fillText(-r, 20, 170);
    context.moveTo(85, 145);
    context.lineTo(85, 155);
    context.fillText(-(r / 2), 70, 170);
    context.moveTo(215, 145);
    context.lineTo(215, 155);
    context.fillText((r / 2), 215, 170);
    context.moveTo(280, 145);
    context.lineTo(280, 155);
    context.fillText(r, 280, 170);

    context.closePath();
    context.strokeStyle = "black";
    context.fillStyle = "black";
    context.stroke();
}
function clickCanvas(event) {
    var r = Number(document.getElementById("r").firstChild.value);
    var br = document.getElementById("canvas").getBoundingClientRect();
    var x = event.clientX - br.left;
    var y = event.clientY - br.top;
    if (r === 0){
        drawPoint("canvas", x, y, false);
    }
    else {
        var boolArea = isArea(x, y);
        drawPoint("canvas", x, y, boolArea);
    }
}

function isArea(x, y) {
    x = (x - 150) / 130;
    y = (150 - y) / 130;
    if (x >= 0 && y <= 0 && x <= 1 && y >= -0.5)
        return true;
    if (x <= 0 && y >= 0 && x * x + y * y <= 0.25)
        return true;
    if (x <= 0 && y <= 0 && -2 * x - 1 <= y)
        return true;
    return false;
}

function drawPoint(id, x, y, isArea){
    var canvas = document.getElementById(id),
        context = canvas.getContext("2d");

    if(isArea === true){
        context.strokeStyle = "#52ff00";
        context.fillStyle = "#52FF00";
    } else {
        context.strokeStyle = "red";
        context.fillStyle = "red";
    }
    context.beginPath();
    context.fillRect(x - 1, y - 1, 2, 2);
    context.closePath();
}