document.querySelectorAll("nav a").forEach(link=>{

link.onclick=function(e){

e.preventDefault();

document.querySelector(

this.getAttribute("href")

).scrollIntoView({

behavior:"smooth"

});

}

});