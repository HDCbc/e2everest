package com.jujaga.e2e.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.ClinicalDocument;

import com.jujaga.e2e.constant.Constants;

public class EverestUtilsTest {
	@BeforeClass
	public static void beforeClass() {
		Logger.getRootLogger().setLevel(Level.FATAL);
	}

	@Test
	public void isNullorEmptyorWhitespaceTest() {
		assertTrue(EverestUtils.isNullorEmptyorWhitespace(null));
		assertTrue(EverestUtils.isNullorEmptyorWhitespace(""));
		assertTrue(EverestUtils.isNullorEmptyorWhitespace(" "));
		assertFalse(EverestUtils.isNullorEmptyorWhitespace("test"));
	}

	@Test
	public void generateDocumentToStringTest() {
		ClinicalDocument clinicalDocument = new ClinicalDocument();
		assertNotNull(EverestUtils.generateDocumentToString(clinicalDocument, true));
		assertNull(EverestUtils.generateDocumentToString(null, true));
	}

	@Test
	public void prettyFormatXMLTest() {
		assertNotNull(EverestUtils.prettyFormatXML("<test/>", Constants.XML.INDENT));
		assertNull(EverestUtils.prettyFormatXML(null, Constants.XML.INDENT));
	}
}
