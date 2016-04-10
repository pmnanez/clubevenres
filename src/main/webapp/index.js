mostrarCargando();

var xmlhttp = new XMLHttpRequest();
var url = "webapi/pratos";
var pratoSeleccionado;

xmlhttp.onreadystatechange = function() {
    if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
        var myArr = JSON.parse(xmlhttp.responseText);
        populateSelect(myArr);
    }
};
xmlhttp.open("GET", url, true);
xmlhttp.send();

function populateSelect(arr) {
    var select = document.getElementById('prato');
    for ( var i in arr) {
        var opt = document.createElement("option");
        var id = arr[i].id;
        var descripcion = arr[i].descripcion;
        opt.value = id;
        opt.innerHTML = id + ' ' + descripcion;
        select.appendChild(opt);
    }
    ocultarCargando();
}

function pratoSeleccionado() {
    mostrarCargando();

    var prato = document.getElementById('prato');
    var pratoId = prato.options[prato.options.selectedIndex].value;

    var xmlhttp = new XMLHttpRequest();
    var url = "webapi/pratos/" + pratoId;

    xmlhttp.onreadystatechange = function() {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            var jsonojb = JSON.parse(xmlhttp.responseText);
            datosDePrato(jsonojb);
        }
    };
    xmlhttp.open("GET", url, true);
    xmlhttp.send();

}

function datosDePrato(obj) {

    // {"precio":2.6,"complementos":[{"id":2,"nombre":"queso"},{"id":3,"nombre":"bacon"}],"ingredienteBase":{"id":1,"nombre":"lomo"},"id":1,"nombre":"Bocadillo"}

    var ingredientesBox = document.getElementById('ingredientes');

    // Madre mía, pero que cerdo que soy.
    while (ingredientesBox.lastChild
            && ingredientesBox.lastChild.localName !== "legend") {
        ingredientesBox.removeChild(ingredientesBox.lastChild);
    }

    if (obj.ingredienteBase != null) {
        engadeIngrediente(ingredientesBox, obj.ingredienteBase, true)
    }

    for ( var i in obj.complementos) {

        engadeIngrediente(ingredientesBox, obj.complementos[i], false)

    }
    
    var ingredientes = document.getElementById('ingredientes');
    ingredientes.style.display = "block";

    ocultarCargando();
}

function engadeIngrediente(ingredientesBox, obj, basico) {
    var chkContainer = document.createElement('div');
    chkContainer.style.display = "inline-block";
    
    var checkbox = document.createElement('input');
    checkbox.type = "checkbox";
    checkbox.name = "ingredientes";
    checkbox.value = obj.id;
    checkbox.id = "chk" + obj.id;
    checkbox.checked = true;
    checkbox.disabled = basico;

    var label = document.createElement('label')
    label.htmlFor = "chk" + obj.id;
    label.appendChild(document.createTextNode(obj.name));
    chkContainer.appendChild(checkbox);
    chkContainer.appendChild(label);
    
    ingredientesBox.appendChild(chkContainer);
}

function mostrarCargando() {

    var cargando = document.createElement('div');
    cargando.id = "cargando";
    cargando.innerHTML = "Cargando...";
    cargando.className = "overlay";

    document.getElementsByTagName('body')[0].appendChild(cargando);

}

function ocultarCargando() {
    var element = document.getElementById("cargando");
    element.parentNode.removeChild(element);
}

function queroPatacas() {
    if (document.getElementById("patacas").checked)
        document.getElementById("sauces").style.display = "block";
    else
        document.getElementById("sauces").style.display = "none";


}

document.getElementById("prato").addEventListener("change", pratoSeleccionado);
document.getElementById("patacas").addEventListener("change", queroPatacas);