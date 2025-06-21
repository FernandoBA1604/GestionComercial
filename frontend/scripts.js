
window.addEventListener("scroll", function() {
  const header = document.querySelector("header");
  header.classList.toggle("down", this.window.scrollY>0);
});

document.querySelectorAll('nav a.nav-link').forEach(link => {
  link.addEventListener('click', function(e) {
    e.preventDefault();
    const targetId = this.getAttribute('href');
    const targetElement = document.querySelector(targetId);
    const headerHeight = document.querySelector('header').offsetHeight;
    const extraPadding = 35;
    
    if(targetElement) {
      let offset = targetElement.getBoundingClientRect().top + window.pageYOffset - headerHeight - extraPadding;
      
      // Ajuste especial solo para el sponsor
      if(targetId === '#sponsor') {
        offset -= 80; // 80px más arriba
      }
      
      window.scrollTo({
        top: offset,
        behavior: 'smooth'
      });
    }
  });
});

document.getElementById("btnSubir").addEventListener("click", function(){
  window.location.href="subir.html";
});
