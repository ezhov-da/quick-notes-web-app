// пока красивого решения убрать скролл body я не вижу. Могу предложить в input добавить обработчик событий JavaScript 
function   showDialog() {
    var inp = document.getElementsByClassName('lightbox1');
    for (var i = 0; i < inp.length; i++) {
        inp[i].onclick = function () {
            document.documentElement.style.overflow = (this.checked ? 'hidden' : 'auto');
            document.documentElement.style.marginRight = (this.checked ? '17px' : '');
        }
    }
}