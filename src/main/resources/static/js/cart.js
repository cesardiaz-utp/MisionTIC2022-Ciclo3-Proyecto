const addToCart = (movie) => {
    let items = localStorage.getItem("cart");
    if (items == undefined) {
        items = new Map();
    } else {
        items = new Map(Object.entries(JSON.parse(items)));
    }

    let value = 1;
    console.log(items);
    if (items.get(movie) != undefined) {
        value = items.get(movie) + 1;
        console.log("Value", value);
    }
    items.set(movie, value);

    localStorage.setItem("cart", JSON.stringify(Object.fromEntries(items)));
    updateCart();
}



const formatter = new Intl.NumberFormat('en-US');

const loadCartDetails = () => {
    let items = localStorage.getItem("cart");
    if (items == undefined) {
        return;
    }

    const totalElem = document.getElementById("total");
    let total = 0;
    let detail = document.getElementById("detail");
    detail.innerText = "";
    items = new Map(Object.entries(JSON.parse(items)));
    Array.from(items)
        .forEach(async ([key, value]) => {

            let response = await fetch('/api/movie/' + key);
            let movie = await response.json();

            const totalPrice = movie.price * value;
            total += totalPrice;

            let item = [
                `<div id="movie-${key}" class="row item">`,
                `    <div class="col-4">`,
                `        <span class="media-heading">${movie.name}</span>`,
                `    </div>`,
                `    <div class="col">`,
                `        <input type="number" min="1" class="form-control text-end" id="item-${key}" value="${value}" onchange="updateTotalItem('${key}')" />`,
                `    </div>`,
                `    <div class="col text-end"><strong id="price-${key}">${formatter.format(parseFloat(movie.price))}</strong></div>`,
                `    <div class="col text-end"><strong id="total-${key}">${formatter.format(parseFloat(totalPrice))}</strong></div>`,
                `    <div class="col text-center">`,
                `        <button type="button" class="btn btn-danger" onclick="removeItem('${key}')"><i class="fa-solid fa-trash"></i></button>`,
                `    </div>`,
                `    <hr>`,
                `</div>`
            ].join('\n');
            detail.innerHTML = detail.innerHTML + item;
            totalElem.innerText = formatter.format(total);
        });
};

const updateTotalItem = (key) => {
    const totalElem = document.getElementById("total");

    let totalItemElem = document.getElementById("total-" + key);
    let itemValue = document.getElementById("item-" + key).value;
    let priceValue = document.getElementById("price-" + key).innerText;

    let total = parseIntlNumber(totalElem.innerText, 'en-US') - parseIntlNumber(totalItemElem.innerText, 'en-US');

    totalItemElem.innerText = formatter.format(parseIntlNumber(itemValue, 'en-US') * parseIntlNumber(priceValue, 'en-US'));
    total += parseIntlNumber(totalItemElem.innerText, 'en-US');

    totalElem.innerText = formatter.format(total);
};

const parseIntlNumber = (stringNumber, locale) => {
    var thousandSeparator = Intl.NumberFormat(locale).format(11111).replace(/\p{Number}/gu, '');
    var decimalSeparator = Intl.NumberFormat(locale).format(1.1).replace(/\p{Number}/gu, '');

    return parseInt(stringNumber
        .replace(new RegExp('\\' + thousandSeparator, 'g'), '')
        .replace(new RegExp('\\' + decimalSeparator), '.')
    );
};

const removeItem = (key) => {
    let items = localStorage.getItem("cart");
    items = new Map(Object.entries(JSON.parse(items)));
    items.delete(key);

    const totalItemValue = parseIntlNumber(document.getElementById("total-" + key).innerText,'en-US');
    const totalElem = document.getElementById("total");

    totalElem.innerText = formatter.format(parseIntlNumber(totalElem.innerText,'en-US') - totalItemValue);

    console.log(items, JSON.stringify(Object.fromEntries(items)))
    localStorage.setItem("cart", JSON.stringify(Object.fromEntries(items)));
    console.log(JSON.stringify(items))

    const movieElm = document.getElementById("movie-"+key);
    movieElm.remove();
}