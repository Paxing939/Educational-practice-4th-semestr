var menuForm = document.getElementById('form');
var menuMap = document.getElementById('map');
var menuTable = document.getElementById('table');


menuForm.onclick = function () {
    window.open("form.html", "form", "width=670,height=495");
}

menuTable.onclick = function () {
    window.open("table.html", "table", "width=1000,height=650");
}

menuMap.onclick = function () {
    window.open("map.html", "map", "width=590,height=345");
}
