const abrirCalculadora = () => {
    const main = document.getElementsByTagName("main").item(0);

    let body = "<h1>Calculadora</h1>";
    body += "<form>";
    body += "<div class='row'>";
    body += "<div class='col'><input name='num1' type='number' class='form-control' placeholder='Numero 1' required></div>";
    body += "<select name='op' class='col form-control'>";
    body += "   <option value='+'>Suma</option>";
    body += "   <option value='-'>Resta</option>";
    body += "   <option value='*'>Multiplicación</option>";
    body += "   <option value='/'>Division</option>";
    body += "   <option value='%'>Residuo de division</option>";
    body += "</select>";
    body += "<div class='col'><input name='num2' type='number' class='form-control' placeholder='Numero 2' required></div>";
    body += "</div>";
    body += "<div class='row'>";
    body += "<button type='button' class='btn btn-primary' onclick='realizarOperacion()'>Calcular</button>";

    body += "</div>";
    body += "<div id='resultado' class='row' ></div>";
    body += "</form>";

    main.innerHTML = body;
}

const realizarOperacion = () => {
    const alertPlaceholder = document.getElementById('liveAlertPlaceholder');

    const num1 = document.getElementsByName("num1").item(0).value;
    const num2 = document.getElementsByName("num2").item(0).value;
    const op = document.getElementsByName("op").item(0).value;
    const tagResultado = document.getElementById("resultado");

    const alert = (message, type) => {
        const wrapper = document.createElement('div')
        wrapper.innerHTML = [
            `<div class="alert alert-${type} alert-dismissible" role="alert">`,
            `   <div>${message}</div>`,
            '   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>',
            '</div>'
        ].join('')

        alertPlaceholder.append(wrapper)
    }


    if (num1 === '') {
        alert("El numero 1 no puede estar vacio", "danger");
        return;
    }
    if (num2 === '') {
        alert("El numero 2 no puede estar vacio", "danger");
        return;
    }

    let resultado = ejecutarOperacionRemoto(num1, op, num2, tagResultado);

    tagResultado.innerText = resultado;
}

const alertTrigger = document.getElementById('liveAlertBtn')
if (alertTrigger) {
    alertTrigger.addEventListener('click', () => {
        alert('Nice, you triggered this alert message!', 'success')
    })
}

const ejecutarOperacionRemoto = async (num1, op, num2, tagResultado) => {
    op = op == '+' ? '%2B' : op;
    op = op == '%' ? '%25' : op;
    const url = `api/calculator?num2=${num2}&op=${op}&num1=${num1}`;

    fetch(url)
        .then(response => response.text())
        .then(respuesta => {
            tagResultado.innerHTML = respuesta;
        });
}

const ejecutarOperacionLocal = (num1, op, num2, tagResultado) => {
    console.log(num1, op, num2);

    let resultado = 0;
    switch (op) {
        case '+':
            resultado = parseInt(num1) + parseInt(num2);
            break;
        case '-':
            resultado = num1 - num2;
            break;
        case '*':
            resultado = num1 * num2;
            break;
        case '/':
            resultado = num1 / num2;
            break;
        case '%':
            resultado = num1 % num2;
            break;
    }
    tagResultado.innerHTML = resultado;
}


abrirCalculadora();