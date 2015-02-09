package com.jujaga.e2e.model.export.header;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import org.marc.everest.datatypes.AD;
import org.marc.everest.datatypes.ADXP;
import org.marc.everest.datatypes.AddressPartType;
import org.marc.everest.datatypes.EntityNameUse;
import org.marc.everest.datatypes.II;
import org.marc.everest.datatypes.NullFlavor;
import org.marc.everest.datatypes.PN;
import org.marc.everest.datatypes.PostalAddressUse;
import org.marc.everest.datatypes.TEL;
import org.marc.everest.datatypes.TS;
import org.marc.everest.datatypes.TelecommunicationsAddressUse;
import org.marc.everest.datatypes.generic.CE;
import org.marc.everest.datatypes.generic.CS;
import org.marc.everest.datatypes.generic.SET;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.LanguageCommunication;
import org.marc.everest.rmim.uv.cdar2.vocabulary.AdministrativeGender;

import com.jujaga.e2e.constant.Constants;
import com.jujaga.e2e.constant.Constants.TelecomType;
import com.jujaga.e2e.constant.Mappings;
import com.jujaga.e2e.util.EverestUtils;
import com.jujaga.emr.model.Demographic;

public class RecordTargetModel {
	private Demographic demographic;

	private SET<II> ids;
	private SET<AD> addresses;
	private SET<TEL> telecoms;
	private SET<PN> names;
	private CE<AdministrativeGender> gender;
	private TS birthDate;
	private ArrayList<LanguageCommunication> languages;

	public RecordTargetModel(Demographic demographic) {
		this.demographic = demographic;
		if(this.demographic != null) {
			setIds();
			setAddresses();
			setTelecoms();
			setNames();
			setGender();
			setBirthDate();
			setLanguages();
		}
	}

	public SET<II> getIds() {
		return ids;
	}

	private void setIds() {
		II id = new II();
		if(!EverestUtils.isNullorEmptyorWhitespace(demographic.getHin())) {
			id.setRoot(Constants.DocumentHeader.BC_PHN_OID);
			id.setAssigningAuthorityName(Constants.DocumentHeader.BC_PHN_OID_ASSIGNING_AUTHORITY_NAME);
			id.setExtension(demographic.getHin());
		} else {
			id.setNullFlavor(NullFlavor.NoInformation);
		}
		this.ids = new SET<II>(id);
	}

	public SET<AD> getAddresses() {
		return addresses;
	}

	private void setAddresses() {
		ArrayList<ADXP> addrParts = new ArrayList<ADXP>();
		HeaderUtil.addAddressPart(addrParts, demographic.getAddress(), AddressPartType.Delimiter);
		HeaderUtil.addAddressPart(addrParts, demographic.getCity(), AddressPartType.City);
		HeaderUtil.addAddressPart(addrParts, demographic.getProvince(), AddressPartType.State);
		HeaderUtil.addAddressPart(addrParts, demographic.getPostal(), AddressPartType.PostalCode);
		if(!addrParts.isEmpty()) {
			CS<PostalAddressUse> use = new CS<PostalAddressUse>(PostalAddressUse.HomeAddress);
			AD addr = new AD(use, addrParts);
			this.addresses = new SET<AD>(addr);
		}
		else {
			this.addresses = null;
		}
	}

	public SET<TEL> getTelecoms() {
		return telecoms;
	}

	private void setTelecoms() {
		SET<TEL> telecoms = new SET<TEL>();
		HeaderUtil.addTelecomPart(telecoms, demographic.getPhone(), TelecommunicationsAddressUse.Home, TelecomType.TELEPHONE);
		HeaderUtil.addTelecomPart(telecoms, demographic.getPhone2(), TelecommunicationsAddressUse.WorkPlace, TelecomType.TELEPHONE);
		HeaderUtil.addTelecomPart(telecoms, demographic.getEmail(), TelecommunicationsAddressUse.Home, TelecomType.EMAIL);
		if(!telecoms.isEmpty()) {
			this.telecoms = telecoms;
		}
		else {
			this.telecoms = null;
		}
	}

	public SET<PN> getNames() {
		return names;
	}

	private void setNames() {
		SET<PN> names = new SET<PN>();
		HeaderUtil.addNamePart(names, demographic.getFirstName(), demographic.getLastName(), EntityNameUse.OfficialRecord);
		if(!names.isEmpty()) {
			this.names = names;
		}
		else {
			this.names = null;
		}
	}

	public CE<AdministrativeGender> getGender() {
		return gender;
	}

	private void setGender() {
		CE<AdministrativeGender> gender = new CE<AdministrativeGender>();
		if(EverestUtils.isNullorEmptyorWhitespace(demographic.getSex())) {
			gender.setNullFlavor(NullFlavor.NoInformation);
		}
		else {
			String sexCode = demographic.getSex().toUpperCase().replace("U", "UN");
			if(Mappings.genderCode.containsKey(sexCode)) {
				gender.setCodeEx(Mappings.genderCode.get(sexCode));
				gender.setDisplayName(Mappings.genderDescription.get(sexCode));
			}
			else {
				gender.setNullFlavor(NullFlavor.NoInformation);
			}
		}
		this.gender = gender;
	}

	public TS getBirthDate() {
		return birthDate;
	}

	private void setBirthDate() {
		String dob = demographic.getYearOfBirth() + demographic.getMonthOfBirth() + demographic.getDateOfBirth();
		TS birthDate = new TS();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		try {
			cal.setTime(sdf.parse(dob));
			birthDate.setDateValue(cal);
			birthDate.setDateValuePrecision(TS.DAY);
		} catch (ParseException e) {
			birthDate.setNullFlavor(NullFlavor.NoInformation);
		}

		this.birthDate = birthDate;
	}

	public ArrayList<LanguageCommunication> getLanguages() {
		return languages;
	}

	private void setLanguages() {
		ArrayList<LanguageCommunication> languages = new ArrayList<LanguageCommunication>();
		HeaderUtil.addLanguagePart(languages, demographic.getOfficialLanguage());
		if(!languages.isEmpty()) {
			this.languages = languages;
		}
		else {
			this.languages = null;
		}
	}
}
