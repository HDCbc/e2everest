package com.jujaga.e2e;

import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.ClinicalDocument;

import com.jujaga.e2e.director.Creator;

public class Main {

	public static void main(String[] args) {
		ClinicalDocument clinicaldocument = new Creator().CreateEmrConversionDocument();
		System.out.print("hello world\n");
	}

}