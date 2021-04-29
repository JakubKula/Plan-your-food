document.addEventListener('DOMContentLoaded', function() {

    const input = document.querySelector("input");
    const images = document.querySelectorAll("tbody");


    images.forEach(el => {

        el.firstElementChild.getAttributeNames()
        console.log(el.firstElementChild.firstElementChild.nextElementSibling.innerHTML)

    })



    input.addEventListener("input", e => {
        const val = input.value;
        console.log(val);

        images.forEach(el => {

            const name = el.firstElementChild.firstElementChild.nextElementSibling.dataset.tag;
            const desc = el.firstElementChild.firstElementChild.nextElementSibling.nextElementSibling.dataset.tag;

            if (name.includes(val) || desc.includes(val)) {
                el.style.display = "";
            } else {
                el.style.display = "none";
            }
        })
    })

});