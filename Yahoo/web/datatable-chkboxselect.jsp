<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<title>Insert title here</title>
<%@include file="include/mainTemplate.jsp"%>
<script type="text/javascript">
<!--
	var ports = [
             { port:20,  pname:'FTP_data',ptitle:'File Transfer Process Data' },
             { port:21,  pname:'FTP',     ptitle:'File Transfer Process' },
             { port:22,  pname:'SSH',     ptitle:'Secure Shell' },
             { port:23,  pname:'TELNET',  ptitle:'Telnet remote communications' },
             { port:25,  pname:'SMTP',    ptitle:'Simple Mail Transfer Protocol' },
             { port:43,  pname:'WHOIS',   ptitle:'whois Identification' },
             { port:53,  pname:'DNS',     ptitle:'Domain Name Service' },
             { port:68,  pname:'DHCP',    ptitle:'Dynamic Host Control Protocol' },
             { port:79,  pname:'FINGER',  ptitle:'Finger Identification' },
             { port:80,  pname:'HTTP',    ptitle:'HyperText Transfer Protocol' },
             { port:110, pname:'POP3',   ptitle:'Post Office Protocol v3' },
             { port:115, pname:'SFTP',   ptitle:'Secure File Transfer Protocol' },
             { port:119, pname:'NNTP',   ptitle:'Network New Transfer Protocol' },
             { port:123, pname:'NTP',    ptitle:'Network Time Protocol' },
             { port:139, pname:'NetBIOS',ptitle:'NetBIOS Communication' },
             { port:143, pname:'IMAP',   ptitle:'Internet Message Access Protocol' },
             { port:161, pname:'SNMP',   ptitle:'Simple Network Management Protocol' },
             { port:194, pname:'IRC',    ptitle:'Internet Relay Chat' },
             { port:220, pname:'IMAP3',  ptitle:'Internet Message Access Protocol v3' },
             { port:389, pname:'LDAP',   ptitle:'Lightweight Directory Access Protocol' },
             { port:443, pname:'SSL',    ptitle:'Secure Socket Layer' },
             { port:445, pname:'SMB',    ptitle:'NetBIOS over TCP' },
             { port:993, pname:'SIMAP',  ptitle:'Secure IMAP Mail' },
             { port:995, pname:'SPOP',   ptitle:'Secure POP Mail' }
         ];

	var columnsMeta = [
			{
				key : "select",
				allowHTML : true,
				label : "<input type='checkbox' class='protocol-select-all' title='Toggle All records'",
				formatter : "<input type='checkbox' checked='checked'>",
				emptyCellValue : "<input type='checkbox' />",
			}, {
				key : "port",
				label : "Port No."
			}, {
				key : "pname",
				label : "Protocol"
			}, {
				key : "ptitle",
				label : "Common Name"
			} ]
	var table;
	YUI().use("datatable-sort", "datatable-scroll", function(Y) {
		table = new Y.DataTable({
			columns : columnsMeta,
			data : ports,
			scrollable : "y",
			height : "250px",
			sortable : [ "port", "pname" ],
			sortBy : "port",
			recordType : [ "select", "port", "pname", "ptitle" ]
		}).reader("#dtable");
		
	});
//-->
</script>
<jsp:include page="include/global/footer.xhtml"></jsp:include>
</body>
</html>