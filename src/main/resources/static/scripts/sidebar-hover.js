//sidebar hover
document.addEventListener("DOMContentLoaded", function(){
    const sidebarMenu = document.getElementById("sidebar-menu");
    const menuItems = sidebarMenu.getElementsByClassName("nav-link");

    for (let i = 0; i < menuItems.length; i++) {
        menuItems[i].addEventListener("click",function(){
            //Delete class active from all file
            for (let j = 0; j < menuItems.length; j++) {
                menuItems[j].classList.remove("active");
            }
            //Add class active to clicked file
            this.classList.add("active");
        });
    }
});
