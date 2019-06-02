document.querySelector("#calcular").addEventListener("click",
    function () {
        var numero1 = document.querySelector("#numero1").value;
        var numero2 = document.querySelector("#numero2").value;
        
        var operacao = document.querySelector("#operacoes").selectedIndex;
        
        if(operacao == "1"){
            var resultado = parseFloat(numero1) + parseFloat(numero2);

        }else if(operacao == "2"){
            var resultado = parseFloat(numero1) - parseFloat(numero2);

        }else if(operacao == "3"){
            var resultado = parseFloat(numero1) * parseFloat(numero2);

        }else if(operacao == "4"){
            if(parseFloat(numero2) == 0){
                alert("Divisão por zero não é permitida!");
                return;
            }

            var resultado = parseFloat(numero1) / parseFloat(numero2);

        }else{
            alert("Essa operação não existe!");
            return;
        }

        document.querySelector("#resultado").value = resultado;
    }
);