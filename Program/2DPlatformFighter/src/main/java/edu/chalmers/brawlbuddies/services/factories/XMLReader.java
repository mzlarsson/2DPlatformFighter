package edu.chalmers.brawlbuddies.services.factories;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public class XMLReader {

	/**
	 * Finds and returns the xml file on the path provided.
	 * @param docString The path to the file.
	 * @return A Document made from the xml file.
	 */
	public static Document getDocument(String docString) {
		try {
			// API used to convert XML into a DOM object tree
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			// Ignore all of the comments
			factory.setIgnoringComments(true);
			// Ignore white space in elements
			factory.setIgnoringElementContentWhitespace(true);
			// Validate the XML as it is parsed
			factory.setValidating(true);
			// Provides access to the documents data
			DocumentBuilder builder = factory.newDocumentBuilder();
			new InputSource(docString);
			// Takes the document
			return builder.parse(new InputSource(docString));
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		return null;
	}
}
