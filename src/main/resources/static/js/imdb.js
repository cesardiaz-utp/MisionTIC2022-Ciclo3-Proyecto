const apiKey = "k_fca6vsn8";
const baseUrl = "https://imdb-api.com";
const lang = "en";

const movieDetail = async (id) => {
    const response = await fetch(baseUrl + "/" + lang + "/API/Title/" + apiKey + "/" + id);
    if (response.ok) {
        const movieInfo = await response.json();

        movieTrailer(id);

        console.log(movieInfo);

        document.getElementById("detail-title").innerText = movieInfo.title;
        document.getElementById("detail-year").innerText = movieInfo.year;
        document.getElementById("detail-rating").innerText = movieInfo.contentRating;
        document.getElementById("detail-duration").innerText = movieInfo.runtimeStr;
        document.getElementById("detail-img").innerHTML = `<img src="${movieInfo.image}" alt="Movie image">`;

        let categoriesElement = document.getElementById("detail-categories");
        let genres = movieInfo.genres.split(",");
        let content = [];
        genres.forEach((genre) => {
            content.push(`<span class="badge text-bg-dark">${genre}</span>`);
        });
        categoriesElement.innerHTML = content.join(" - ");

        document.getElementById("detail-description").innerText = movieInfo.plot;
    } else {
        console.error("La pelicula no existe")
    }
};

const movieTrailer = async (id) => {
    const response = await fetch(baseUrl + '/API/Trailer/' + apiKey + "/" + id);
    if (response.ok) {
        const movieInfo = await response.json();

        const video = document.getElementById("detail-video");
        const videoWidth = video.offsetWidth - 10;
        video.src = movieInfo.linkEmbed + "?width=" + videoWidth;
    } else {
        console.error("La pelicula no existe")
    }
};