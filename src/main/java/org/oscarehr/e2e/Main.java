package org.oscarehr.e2e;

import org.oscarehr.common.dao.DemographicDaoExport;

import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.ClinicalDocument;
import org.oscarehr.e2e.constant.Constants;
import org.oscarehr.e2e.director.E2ECreator;
import org.oscarehr.e2e.util.EverestUtils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ByteArrayBody;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	Main() {
		throw new UnsupportedOperationException();
	}

	public static void main(String[] args) {

		ApplicationContext context = new ClassPathXmlApplicationContext(Constants.Runtime.SPRING_APPLICATION_CONTEXT);
		DemographicDaoExport demographicsDaoExport = context.getBean(DemographicDaoExport.class);

		// Patient demographic numbers and two
		List<Integer> demographicNoList = demographicsDaoExport.getDemographicNumbers();
		int responseSuccess = 0;
		int responseFail = 0;

		for (Integer demographicNo : demographicNoList) {

			// Populate Clinical Document
			ClinicalDocument clinicalDocument = E2ECreator.createEmrConversionDocument(demographicNo);

			// Output Clinical Document as String
			String output = EverestUtils.generateDocumentToString(clinicalDocument, true);
			if(!EverestUtils.isNullorEmptyorWhitespace(output)) {

				final String e2eUrl = "http://localhost:3001/records/create";

				HttpClient httpClient = new DefaultHttpClient();
				HttpPost httpPost = new HttpPost( e2eUrl );

				StringBuilder sbFile = new StringBuilder( 255 );
				sbFile.append( "Record_1234567890" ).append( ".xml" );
				ByteArrayBody body = new ByteArrayBody( output.getBytes(), "text/xml", sbFile.toString());
				MultipartEntity reqEntity = new MultipartEntity();
				reqEntity.addPart( "content", body );
				httpPost.setEntity( reqEntity );

				try{
					HttpResponse response = httpClient.execute( httpPost );
					if( response != null ){
						int responseCode = response.getStatusLine().getStatusCode();

						if( responseCode == 201 ){
							responseSuccess++;
							System.out.print( "." );
						}
						else{
							responseFail++;
							System.out.println( response );
						}
					}
				}
				catch( Exception ex ){
					System.out.println( ex );
				}
			}
		}
		System.out.println( "" );
		System.out.println( "Pass:  "+ responseSuccess );
		System.out.println( "Fail:  "+ responseFail );
		System.out.println( "Total: "+ demographicNoList.size() );
	}
}
