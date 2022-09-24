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

const updateCart = () => {
    let items = localStorage.getItem("cart");
    if (items == undefined) {
        items = new Map();
    } else {
        items = new Map(Object.entries(JSON.parse(items)));
    }

    const cartBadge = document.getElementById("cart-count");
    let total = 0;
    if (items.size > 0) {
        total = Array.from(items)
            .map(([key, value]) => value)
            .reduce((a, b) => a + b);
    }
    console.log("total", total);
    cartBadge.innerText = total;
}

updateCart();

const formatter = new Intl.NumberFormat('en-US', { style: 'currency', currency: 'USD' });

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
                `<div id="renglon" class="row mb-2">`,
                `    <div class="col-2">`,
                `        <span class="media-heading">${movie.name}</span>`,
                `    </div>`,
                `    <div class="col">`,
                `        <input type="number" min="1" class="form-control" id="item-${key}" value="${value}" onchange="updateTotalItem('${key}')" />`,
                `    </div>`,
                `    <div class="col"><strong id="price-${key}">${parseInt(movie.price)}</strong></div>`,
                `    <div class="col"><strong id="total-${key}">${parseInt(totalPrice)}</strong></div>`,
                `    <div class="col">`,
                `        <button type="button" class="btn btn-danger"><i class="fa-solid fa-trash"></i></button>`,
                `    </div>`,
                `</div>`
            ].join('\n');
            detail.innerHTML = detail.innerHTML + item;
            totalElem.innerText = total;
        });
};

const updateTotalItem = (key) => {
    const totalElem = document.getElementById("total");

    let totalItemElem = document.getElementById("total-" + key);
    let itemValue = document.getElementById("item-" + key).value;
    let priceValue = document.getElementById("price-" + key).innerText;

    let total = totalElem.innerText - totalItemElem.innerText;

    console.log(totalItemElem.innerText);

    totalItemElem.innerText = parseInt(itemValue) * parseInt(priceValue);
    total += parseInt(totalItemElem.innerText);
    console.log(totalItemElem.innerText);

    totalElem.innerText = total;
};