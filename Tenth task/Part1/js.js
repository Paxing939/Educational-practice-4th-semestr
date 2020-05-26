document.body.onmouseover = document.body.onmouseout = handler;

function handler(event) {
    if (event.type === 'mouseover' && event.target.className ==='circle') {
        event.target.style.background = 'red'
    }
    if (event.type === 'mouseout') {
        event.target.style.background = ''
    }
}