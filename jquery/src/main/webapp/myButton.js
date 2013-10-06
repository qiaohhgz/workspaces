/**
 * Created with IntelliJ IDEA.
 * User: Jim_qiao
 * Date: 7/11/13
 * Time: 5:03 PM
 * To change this template use File | Settings | File Templates.
 */
function myButton(value, click, parent, option) {
    var _value = value;
    var _click = click;
    var _parent = parent;
    var _option = option;
    var _button;

    function _init() {
        _button = document.createElement("button");
        if (_click) {
            addEventListener("click", _click);
        }
        with (_button) {
            innerHTML = _value;

            with (style) {
                backgroundColor = "#aaa";
                minWidth = "100px";
                height = "25px";
                marginLeft = "5px"
                marginTop = "5px"
            }
        }
        if (_parent) {
            _parent.appendChild(_button);
        }
    }

    this.getValue = function () {
        return _value;
    }

    this.getButton = function () {
        return _button;
    }
    _init();
}


window.onload = function () {
    new myButton("A", null, document.body);
    new myButton("B", function(){alert(this.getValue())}, document.body);
    for (var i = 0; i < 100; i++) {
        new myButton(new Date(), null, document.body);
    }
}