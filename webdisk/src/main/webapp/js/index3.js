//console.log()   是打印日志的可以删除

//需要compare方法
function getObjectInArray(obj, array) {
	for ( var i = 0; i < array.length; i++) {
		if (typeof array[i] == 'object') {
			if (compare(array[i], obj)) {
				return {
					index : i,
					data : array[i]
				};
			}
		}
	}
	return null;
}
// 比较两个对象已存在的属性的值是否相等
// var a = {id:100,name:'abc'}
// var b = {id:101,name:'abc'}
// var flg = compare(a,b);
// 输入false
// var a = {id:100,name:'abc'}
// var b = {id:100}
// var flg = compare(a,b);
// 输入true
function compare(a, b) {
	if (a == b) {
		return true;
	}
	for ( var _key in a) {
		var _value = a[_key];
		if (b[_key] && b[_key] != _value) {
			return false;
		}
	}
	return true;
}
//
Array.prototype.indexOf = function(o) {
	if (o == null) {
		for ( var i = 0; i < this.length; i++)
			if (this[i] == null)
				return i;
	} else {
		for ( var i = 0; i < this.length; i++)
			if (o.equals(this[i]))
				return i;
	}
	return -1;
};
//
Array.prototype.lastIndexOf = function(o) {
	if (o == null) {
		for ( var i = this.length - 1; i >= 0; i--)
			if (this[i] == null)
				return i;
	} else {
		for ( var i = this.length - 1; i >= 0; i--)
			if (o.equals(this[i]))
				return i;
	}
	return -1;
};
// 包含
Array.prototype.contains = function(o) {
	return this.indexOf(o) >= 0;
};
// 比较
Array.prototype.compare = function(array) {
	if (this == array) {
		return true;
	}
	for ( var _key in this) {
		var _value = this[_key];
		if (array[_key] && array[_key] != _value) {
			return false;
		}
	}
	return true;
};
// 追加
Array.prototype.add = function(arrays) {
	this.push(arrays);
};
// 浅克隆
Array.prototype.clone = function() {
	return this.concat([]);
};
// 删除数组指定索引处的元素
Array.prototype.remove = function(index) {
	if (isNaN(index) || index) {
		return false;
	} else {
		this.splice(index, 1);
		return true;
	}
};

// 暂时只能比较简单的对象 如
// {a:1,b:'abc',c:123}支持
// {a:[1,2,3]}不支持
// {a:{x:1,y:2:z:3}}不支持
function equals(o) {
	if (this == o) {
		return true;
	}
	for ( var _key in this) {
		var _value = this[_key];
		if (typeof _value == 'function') {
			continue;
		}
		if (typeof _value == 'object') {
			// if (o[_key].equals && o.equals(_value)) {
			// continue;
			// }
			// if (_value.equals && _value.equals(o[_key])) {
			// continue;
			// }
			continue;
		}
		if (o[_key] || o[_key] != _value) {
			return false;
		}
	}
}
function toString() {
	var array = [];
	array.add('{');
	for ( var _key in this) {
		var _value = this[_key];
		if (typeof _value != 'function' && typeof _value != 'object') {
			if (array.length != 1) {
				array.add(',');
			}
			array.add(_key + ':' + _value);
		}
	}
	array.add("}");
	return array.join('');
}
//入口
window.onload = function() {
	var so = {
		id : 102,
		equals : equals,
		toString : toString
	};

	var os = [ {
		a : 1,
		b : 2,
		c : 3,
		id : 101,
		equals : equals,
		toString : toString
	}, {
		a : 1,
		b : 2,
		c : 3,
		id : 102,
		equals : equals,
		toString : toString
	} ];

	// equals test
	console.log('equals test');
	var flag = so.equals(os[0]);
	console.log(so + '.equals(' + os[0] + ') = ' + flag);

	console.log('');
	// clone test
	console.log('clone test');
	var os2 = os.clone();
	console.log('os2 = os.clone()');
	console.log('os.length = ' + os.length);
	console.log('os2.length = ' + os2.length);
	console.log('os2.remove(0)');
	os2.remove(0);
	console.log('os.length = ' + os.length);
	console.log('os2.length = ' + os2.length);

	console.log('');
	// getObjectInArray test
	console.log('getObjectInArray test');
	var obj = {
		id : 100,
		name : 'abc',
		toString : toString
	};
	var array = [ {
		id : 101,
		name : 'abc',
		age : 18,
		equals : equals,// 引用equals方法
		toString : toString
	}, {
		id : 100,
		name : 'abc',
		age : 18,
		equals : equals,// 引用equals方法
		toString : toString
	} ];
	console.log('obj = ' + obj);
	console.log('array = ' + array);
	var newObj = getObjectInArray(obj, array);
	var index = newObj.index;
	var id = newObj.data.id;
	console.log('index = ' + index + ' id = ' + id);

	console.log('');
	// indexOf test 需要在对象里面加equals方法
	console.log('indexOf test');
	index = array.indexOf(array[1]);
	console.log('array.indexOf(array[1]) = ' + index);
};