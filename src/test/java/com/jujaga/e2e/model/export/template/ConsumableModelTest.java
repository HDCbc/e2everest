package com.jujaga.e2e.model.export.template;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.marc.everest.datatypes.EN;
import org.marc.everest.datatypes.NullFlavor;
import org.marc.everest.datatypes.ST;
import org.marc.everest.datatypes.generic.CE;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.Consumable;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.LabeledDrug;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.ManufacturedProduct;
import org.marc.everest.rmim.uv.cdar2.pocd_mt000040uv.Material;
import org.marc.everest.rmim.uv.cdar2.vocabulary.DrugEntity;
import org.marc.everest.rmim.uv.cdar2.vocabulary.EntityDeterminerDetermined;
import org.marc.everest.rmim.uv.cdar2.vocabulary.RoleClassManufacturedProduct;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.jujaga.e2e.constant.Constants;
import com.jujaga.e2e.util.EverestUtils;
import com.jujaga.emr.PatientExport.Immunization;
import com.jujaga.emr.dao.DrugDao;
import com.jujaga.emr.dao.PreventionDao;
import com.jujaga.emr.dao.PreventionExtDao;
import com.jujaga.emr.model.Drug;
import com.jujaga.emr.model.Prevention;
import com.jujaga.emr.model.PreventionExt;

public class ConsumableModelTest {
	private static ApplicationContext context;
	private static PreventionDao preventionDao;
	private static PreventionExtDao preventionExtDao;
	private static Immunization immunization;

	private static DrugDao drugDao;
	private static Drug drug;
	private static ConsumableModel consumableModel;

	@BeforeClass
	public static void beforeClass() {
		context = new ClassPathXmlApplicationContext(Constants.Runtime.SPRING_APPLICATION_CONTEXT);
		preventionDao = context.getBean(PreventionDao.class);
		preventionExtDao = context.getBean(PreventionExtDao.class);
		drugDao = context.getBean(DrugDao.class);
		consumableModel = new ConsumableModel();

		Prevention prevention = preventionDao.findNotDeletedByDemographicId(Constants.Runtime.VALID_DEMOGRAPHIC).get(0);
		List<PreventionExt> preventionExt = preventionExtDao.findByPreventionId(Constants.Runtime.VALID_PREVENTION);
		immunization = new Immunization(prevention, preventionExt);
	}

	@Before
	public void before() {
		drug = drugDao.findByDemographicId(Constants.Runtime.VALID_DEMOGRAPHIC).get(0);
	}

	@Test
	public void consumableImmunizationStructureTest() {
		Consumable consumable = consumableModel.getConsumable(immunization);
		assertNotNull(consumable);

		ManufacturedProduct manufacturedProduct = consumable.getManufacturedProduct();
		assertNotNull(manufacturedProduct);
		assertEquals(RoleClassManufacturedProduct.ManufacturedProduct, manufacturedProduct.getClassCode().getCode());

		Material material = manufacturedProduct.getManufacturedDrugOrOtherMaterialIfManufacturedMaterial();
		assertNotNull(material);
		assertEquals(EntityDeterminerDetermined.Described, material.getDeterminerCode().getCode());
		assertNotNull(material.getCode());
		assertNotNull(material.getName());
	}

	@Test
	public void consumableImmunizationNullTest() {
		Immunization immunization = null;
		Consumable consumable = consumableModel.getConsumable(immunization);
		assertNotNull(consumable);
	}

	@Test
	public void consumableImmunizationCodeTest() {
		Material material = materialHelper(immunization);

		CE<String> code = material.getCode();
		assertNotNull(code);
		assertEquals(EverestUtils.getPreventionType(immunization.getPrevention().getPreventionType()), code.getCode());
		assertEquals(Constants.CodeSystems.ATC_OID, code.getCodeSystem());
		assertEquals(Constants.CodeSystems.ATC_NAME, code.getCodeSystemName());
	}

	@Test
	public void consumableImmunizationCodeNullTest() {
		Material material = materialHelper(null);

		CE<String> code = material.getCode();
		assertNotNull(code);
		assertTrue(code.isNull());
		assertEquals(NullFlavor.NoInformation, code.getNullFlavor().getCode());
	}

	@Test
	public void consumableImmunizationNameTest() {
		Material material = materialHelper(immunization);

		EN name = material.getName();
		assertNotNull(name);
		assertNotNull(name.getParts());
		assertEquals(1, name.getParts().size());
		assertEquals(immunization.getPrevention().getPreventionType(), name.getPart(0).getValue());
	}

	@Test
	public void consumableImmunizationNameNullTest() {
		Material material = materialHelper(null);

		EN name = material.getName();
		assertNotNull(name);
		assertTrue(name.isNull());
		assertEquals(NullFlavor.NoInformation, name.getNullFlavor().getCode());
	}

	@Test
	public void consumableImmunizationLotNumberTest() {
		Material material = materialHelper(immunization);

		ST lot = material.getLotNumberText();
		assertNotNull(lot);
		assertEquals(immunization.getPreventionMap().get(Constants.PreventionExtKeys.lot.toString()), lot.getValue());
	}

	@Test
	public void consumableImmunizationLotNumberNullTest() {
		Material material = materialHelper(null);

		ST lot = material.getLotNumberText();
		assertNull(lot);
	}

	private Material materialHelper(Immunization immunization) {
		Consumable consumable = consumableModel.getConsumable(immunization);
		return consumable.getManufacturedProduct().getManufacturedDrugOrOtherMaterialIfManufacturedMaterial();
	}

	@Test
	public void consumableDrugStructureTest() {
		Consumable consumable = consumableModel.getConsumable(drug);
		assertNotNull(consumable);
		assertNotNull(consumable.getTemplateId());
		assertEquals(1, consumable.getTemplateId().size());
		assertEquals(Constants.TemplateOids.MEDICATION_IDENTIFICATION_TEMPLATE_ID, consumable.getTemplateId().get(0).getRoot());

		ManufacturedProduct manufacturedProduct = consumable.getManufacturedProduct();
		assertNotNull(manufacturedProduct);
		assertEquals(RoleClassManufacturedProduct.ManufacturedProduct, manufacturedProduct.getClassCode().getCode());

		LabeledDrug labeledDrug = manufacturedProduct.getManufacturedDrugOrOtherMaterialIfManufacturedLabeledDrug();
		assertNotNull(labeledDrug);
		assertEquals(EntityDeterminerDetermined.Described, labeledDrug.getDeterminerCode().getCode());
		assertNotNull(labeledDrug.getCode());
		assertNotNull(labeledDrug.getName());
	}

	@Test
	public void consumableDrugNullTest() {
		Drug drug = null;
		Consumable consumable = consumableModel.getConsumable(drug);
		assertNotNull(consumable);
	}

	@Test
	public void consumableDrugDINCodeTest() {
		LabeledDrug labeledDrug = labeledDrugHelper(drug);

		CE<DrugEntity> code = labeledDrug.getCode();
		assertNotNull(code);
		assertEquals(drug.getRegionalIdentifier().trim(), code.getCode().getCode());
		assertEquals(Constants.CodeSystems.DIN_OID, code.getCodeSystem());
		assertEquals(Constants.CodeSystems.DIN_NAME, code.getCodeSystemName());
	}

	@Test
	public void consumableDrugATCCodeTest() {
		drug.setRegionalIdentifier(null);
		LabeledDrug labeledDrug = labeledDrugHelper(drug);

		CE<DrugEntity> code = labeledDrug.getCode();
		assertNotNull(code);
		assertEquals(drug.getAtc().trim(), code.getCode().getCode());
		assertEquals(Constants.CodeSystems.ATC_OID, code.getCodeSystem());
		assertEquals(Constants.CodeSystems.ATC_NAME, code.getCodeSystemName());
	}

	@Test
	public void consumableDrugCodeNullTest() {
		LabeledDrug labeledDrug = labeledDrugHelper(null);

		CE<DrugEntity> code = labeledDrug.getCode();
		assertNotNull(code);
		assertTrue(code.isNull());
		assertEquals(NullFlavor.NoInformation, code.getNullFlavor().getCode());
	}

	@Test
	public void consumableDrugGenericNameTest() {
		LabeledDrug labeledDrug = labeledDrugHelper(drug);

		EN name = labeledDrug.getName();
		assertNotNull(name);
		assertNotNull(name.getParts());
		assertEquals(1, name.getParts().size());
		assertEquals(drug.getGenericName(), name.getPart(0).getValue());
	}

	@Test
	public void consumableDrugBrandNameTest() {
		drug.setGenericName(null);
		LabeledDrug labeledDrug = labeledDrugHelper(drug);

		EN name = labeledDrug.getName();
		assertNotNull(name);
		assertNotNull(name.getParts());
		assertEquals(1, name.getParts().size());
		assertEquals(drug.getBrandName(), name.getPart(0).getValue());
	}

	@Test
	public void consumableDrugNameNullTest() {
		LabeledDrug labeledDrug = labeledDrugHelper(null);

		EN name = labeledDrug.getName();
		assertNotNull(name);
		assertTrue(name.isNull());
		assertEquals(NullFlavor.NoInformation, name.getNullFlavor().getCode());
	}

	private LabeledDrug labeledDrugHelper(Drug drug) {
		Consumable consumable = consumableModel.getConsumable(drug);
		return consumable.getManufacturedProduct().getManufacturedDrugOrOtherMaterialIfManufacturedLabeledDrug();
	}
}
