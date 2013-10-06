package com.util;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class GeoCoder {
    private String latitude;
    private String longitude;
    private String warning;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getWarning() {
        return warning;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZIP() {
        return zip;
    }

    public String getCountry() {
        return country;
    }

    public GeoCoder(String location) {
        // String geoCodeURL = "http://local.yahooapis.com/MapsService/V1/geocode?appid=M1qheVzV34GjAVpzSq39el4JLcC2HyttgC2R4WKr.NIvI.yeKcp62EUb1vTNpzgLIEE-";
        // Using Yahoo placefinder api
        String geoCodeURL = "http://where.yahooapis.com/geocode?appid=M1qheVzV34GjAVpzSq39el4JLcC2HyttgC2R4WKr.NIvI.yeKcp62EUb1vTNpzgLIEE-&q=";


        try {
        	// Yahoo Geo Code API doesn't support International State Code: US-NY
        	// So "US-" must be removed from address
    		Boolean isInternationalGeo = false;
    		if(location.contains("US-")){
        		location = location.replace("US-", "");
        		isInternationalGeo = true;
    		}
    		
            geoCodeURL += URLEncoder.encode(location, "UTF-8");
            URL url = new URL(geoCodeURL);
            HttpURLConnection httpurlConn = (HttpURLConnection) url
                    .openConnection();
            InputStream in = new BufferedInputStream(httpurlConn
                    .getInputStream());
            BufferedInputStream bis = new BufferedInputStream((in));
            StringBuffer buf = new StringBuffer();
            byte data[] = new byte[1024];
            int count = 0;
            while ( (count = bis.read(data, 0, 1024)) > 0) {
                buf.append(new String(data,0,count));
            }
            in.close();
            String geoXML = buf.toString();

            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new InputSource(new StringReader(geoXML)));
            if (doc.getDocumentElement() != null) {
                NodeList chNodes = doc.getDocumentElement().getChildNodes();
                if (chNodes != null && chNodes.getLength() > 0) {
                    NamedNodeMap nmp = chNodes.item(0).getAttributes();
                    if (nmp != null && nmp.getLength() > 0) {
                        Node warningnode = nmp.getNamedItem("warning");
                        if (warningnode != null) {
                            warning = warningnode.getNodeValue();
                        }
                    }
                }
            }
            NodeList nodes = doc.getElementsByTagName("street");
            if (nodes != null) {
                address = nodes.item(0).getTextContent();
            }
            nodes = doc.getElementsByTagName("city");
            if (nodes != null) {
                city = nodes.item(0).getTextContent();
            }
            nodes = doc.getElementsByTagName("statecode");
            if (nodes != null) {
                state = nodes.item(0).getTextContent();
                if (isInternationalGeo) {
					state = "US-" + state;
				}
            }
            nodes = doc.getElementsByTagName("uzip");
            if (nodes != null) {
                zip = nodes.item(0).getTextContent();
            }
            nodes = doc.getElementsByTagName("country");
            if (nodes != null) {
                country = nodes.item(0).getTextContent();
            }

            nodes = doc.getElementsByTagName("latitude");

            if (nodes != null) {
                latitude = nodes.item(0).getTextContent();
            }


            nodes = doc.getElementsByTagName("longitude");
            if (nodes != null) {
                longitude = nodes.item(0).getTextContent();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
