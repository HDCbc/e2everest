package com.jujaga.e2e.util;

import static org.junit.Assert.assertFalse;

import java.io.IOException;
import java.io.OutputStream;

import org.junit.Test;
import org.marc.everest.formatters.interfaces.IFormatterGraphResult;
import org.marc.everest.formatters.xml.datatypes.r1.DatatypeFormatter;
import org.marc.everest.formatters.xml.its1.XmlIts1Formatter;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.ClinicalDocument;

public class E2EEverestValidatorTest {
	@Test
	public void isValidCDATest() {
		XmlIts1Formatter fmtr = new XmlIts1Formatter();
		fmtr.setValidateConformance(true);
		fmtr.getGraphAides().add(new DatatypeFormatter());
		IFormatterGraphResult details = fmtr.graph(new NullOutputStream(), new ClinicalDocument());

		assertFalse(E2EEverestValidator.isValidCDA(details, true));
	}

	// Creates an OutputStream that does nothing
	public class NullOutputStream extends OutputStream {
		@Override
		public void write(int b) throws IOException {
		}
	}
}