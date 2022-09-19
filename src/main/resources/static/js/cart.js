let items = new Map();

const addToCart = (movie) => {
    let value = 1;
    console.log(items);
    if (items.get(movie) !== undefined) {
        value = items.get(movie) + 1;
        console.log("Value", value);
    }
    items.set(movie, value);
    updateCart();
}

const updateCart = () => {
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