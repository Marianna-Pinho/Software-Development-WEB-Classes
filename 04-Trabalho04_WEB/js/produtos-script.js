document.querySelector("#cadastrar").addEventListener("click",
    function () {

        // This part reads the fields of the form
        var prodNome = document.querySelector("#nome").value;
        var prodPreco = document.querySelector("#preco").value;
        var prodQtd = document.querySelector("#qtd").value;

        if(isNaN(prodPreco)){
            alert("Por favor, escreva um número válido para o preço!");
            return;
        }

        if(parseInt(prodQtd) < 0){
            alert("Por favor, digite uma quantidade válida. Quantidades negativas não são permitidas!");
            return;
        }
        // This part computes the total value of a product
        var resultado = parseFloat(prodPreco) * parseInt(prodQtd);

        // This part creates the table and fill its lines
        var linhaTR = document.createElement("tr");
        var linhaTD1 = document.createElement("td");
        var linhaTD2 = document.createElement("td");

        var linhaText1 = document.createTextNode(prodNome);
        var linhaText2 = document.createTextNode(resultado);

        linhaTD1.appendChild(linhaText1);
        linhaTD2.appendChild(linhaText2);
        linhaTR.appendChild(linhaTD1);
        linhaTR.appendChild(linhaTD2);

        document.querySelector("#tabela").appendChild(linhaTR);

        //This part computes the total value using all the products
        var total = document.querySelector("#total").value;

        if (total == "NaN") {
            total = 0;
        }

        document.querySelector("#total").value = parseFloat(total) + resultado;
    }
)