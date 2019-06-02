/* ======= (Pagina Inicial): Carrossel de imagens ======== */

var slides = $('.mySlides');
slides.eq(0).show();
var slideIndex = 0;

$('.next').click(function () {
    slideIndex += 1;

    if (slideIndex > (slides.length - 1)) {
        slideIndex = 0;
    }

    slides.hide();
    slides.eq(slideIndex).fadeIn('slow');

});

$('.prev').click(function () {
    slideIndex -= 1;

    if (slideIndex < 0) {
        slideIndex = slides.length - 1;
    }

    slides.hide();
    slides.eq(slideIndex).fadeIn('slow');

});


//============ (Galeria de Pratos): Botão para ver mais pratos ================

var hidden_plates = $('.toHide');
hidden_plates.hide();

$('#buttonPratos').click(function () {
    if ($('#buttonPratos').attr('value') == 'Mostrar mais') {
        $('#buttonPratos').html('Mostrar menos');
        $('#buttonPratos').attr('value', 'Mostrar menos');

        hidden_plates.slideDown('slow').show();

    } else {
        $('#buttonPratos').html('Mostrar mais');
        $('#buttonPratos').attr('value', 'Mostrar mais');

        hidden_plates.slideUp('slow');
    }
});

/*============= (Contato): Máscara de Telefone ===============*/
$(document).ready(function () {
    $('#telefone').mask('(00)90000-0000');
});

/* ========== (Contato): Submeter =========================*/ 

$('#botaoContato').click(function(){
    if($('#formulario #nome').val() == ""){
        alert('Por favor, preencha o campo nome.');
    }else if($('#formulario #email').val() == ""){
        alert('Por favor, preencha o campo email.');
    }else if($('#formulario #telefone').val() == ""){
        alert('Por favor, preencha o campo telefone.');
    }else if($('#formulario #mensagem').val() == ""){
        alert('Por favor, preencha o campo mensagem.');
    }else{
        alert('Mensagem enviada com sucesso!');
    }
   
});