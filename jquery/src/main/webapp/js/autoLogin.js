app = {}
app.login = {
    loginDialogName:'app.logic.dialog',
    loginDialog:null,
    loginSelect:null,
    init:function () {
        this.initDialog();
        this.scanInput();
    },
    $:function (el) {
        return document.getElementById(el);
    },
    scanInput:function () {
        var inputList = document.body.getElementsByTagName('input');
        for (var i = 0; i < inputList.length; i++) {
            var input = inputList[i];
            if (input.getAttribute('type') == 'text') {
                console.log('scan a input ')
                input.addEventListener('click', function () {
                    app.login.showDialog(this);
                });
            }
        }
    },
    initDialog:function () {
        var div = document.createElement(this.loginDialogName);
        with (div.style) {
            position = 'absolute';
            display = 'none';
            minWidth = '50px';
            minHeight = '22px';
            zIndex = 1000;
        }
        var _select = document.createElement('select');
        _select
        var _option = document.createElement('option');
        _option.value = '123';
        _option.innerHTML = 'text';
        _select.appendChild(_option);
        this.loginSelect = _select;
        this.loginSelect.addEventListener('blur', function () {
            app.login.hideDialog();
        });
        div.appendChild(_select);
        this.loginDialog = div;
        document.body.appendChild(this.loginDialog);
    },
    showDialog:function (el) {
        var border = 3;
        var offset = this.getOffset(el);
        var l = offset.left, t = offset.top, h = el.offsetHeight, w = el.offsetWidth;
        console.log('offsetLeft = ' + l + ' offsetTop = ' + t + ' el.offsetHeight' + h);
        t = t + h + border;
        l = l + border;
        w = w - border;
        this.loginDialog.style.left = l + 'px';
        this.loginDialog.style.top = t + 'px';
        this.loginDialog.style.width = w + 'px';
        this.loginDialog.style.display = 'block';
        this.loginSelect.style.width = this.loginDialog.style.width;
        this.loginDialog.focus();
        this.loginSelect.focus();
    },
    hideDialog:function () {
        this.loginDialog.style.display = 'none';
    },
    getOffset:function (el) {
        var _x = 0;
        var _y = 0;
        while (el && !isNaN(el.offsetLeft) && !isNaN(el.offsetTop)) {
            if (el.tagName !== 'TR') {
                _x += el.offsetLeft - el.scrollLeft;
            }
            if (el.tagName !== 'TD') {
                _y += el.offsetTop - el.scrollTop;
            }
            el = el.parentNode;
        }
        return { top:_y, left:_x };
    }
}


