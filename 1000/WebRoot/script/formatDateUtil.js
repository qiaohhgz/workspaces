function formatDate(pattern, date) {
	function formatNumber(data, format) {
		format = format.length;
		data = data || 0;
		return format == 1 ? data
				: (data = String(Math.pow(10, format) + data))
						.substr(data.length - format);
	}
	return pattern.replace(/([YMDhsm])\1*/g, function(format) {
		switch (format.charAt()) {
		case 'Y':
			return formatNumber(date.getFullYear(), format);
		case 'M':
			return formatNumber(date.getMonth() + 1, format);
		case 'D':
			return formatNumber(date.getDate(), format);
		case 'w':
			return date.getDay() + 1;
		case 'h':
			return formatNumber(date.getHours(), format);
		case 'm':
			return formatNumber(date.getMinutes(), format);
		case 's':
			return formatNumber(date.getSeconds(), format);
		}
	});
}
