function mostrarMensaje(mensaje) {
    alert(mensaje);
}

function incrementarContador() {
    let valor = document.getElementById("contador").value;

    /*
     console.log("El valor obtenido es: ", valor);

    if (valor > 5) {
        alert("El numero es mayor a 5");
    } else if (valor >= 0) {
        alert("El numero es positivo");
    } else {
        alert("El numero es negativo");
    }

    for(i=0; i < 5; i++) {
        console.log(i);
    }
 */
    valor++;
    document.getElementById("contador").value = valor;

    let modificador = 'b';
    let color = 'black';
    if(valor % 5 == 0) {
        color = "yellow";
    }
    if(valor % 3 == 0) {
        modificador = 'i';
    }
    document.getElementById("salida").innerHTML = '<'+modificador+'>'+valor+"</"+modificador+">";
    document.getElementById("salida").style.color = color;
}