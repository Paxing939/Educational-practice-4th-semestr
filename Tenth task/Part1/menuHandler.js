var menuForm = document.getElementById('form');
var menuMap = document.getElementById('map');
var menuTable = document.getElementById('table');


menuForm.onclick = function () {
    window.open("form.html", "form", "width=670,height=495");
}

menuMap.onclick = function () {
    window.open("map.html", "map", "width=470,height=550");
}

menuTable.onclick = function () {
    window.open("table.html", "table", "width=650,height=689");
}
