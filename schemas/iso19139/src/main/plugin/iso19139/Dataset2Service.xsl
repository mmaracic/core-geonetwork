<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="2.0"
				xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#"
				xmlns:skos="http://www.w3.org/2004/02/skos/core#"
				xmlns:gmd="http://www.isotc211.org/2005/gmd"
				xmlns:gco="http://www.isotc211.org/2005/gco"
				xmlns:xlink="http://www.w3.org/1999/xlink"
				xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
				xmlns:gml="http://www.opengis.net/gml"
				xmlns:geonet="http://www.fao.org/geonetwork"
				xmlns:srv="http://www.isotc211.org/2005/srv">

	<!-- Dataset into service -->
	<xsl:template match="gmd:MD_Metadata">
		<gmd:MD_Metadata xmlns:gmd="http://www.isotc211.org/2005/gmd" xmlns:gco="http://www.isotc211.org/2005/gco" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:gml="http://www.opengis.net/gml" xmlns:geonet="http://www.fao.org/geonetwork" xmlns:srv="http://www.isotc211.org/2005/srv" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:skos="http://www.w3.org/2004/02/skos/core#">
		<xsl:copy-of select="gmd:language"/>
		<gmd:hierarchyLevel>
			<gmd:MD_ScopeCode codeList="http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/Codelist/ML_gmxCodelists.xml#MD_ScopeCode" codeListValue="service"/>
		</gmd:hierarchyLevel>
		<xsl:copy-of select="gmd:contact"/>
		<xsl:copy-of select="gmd:dateStamp"/>
		<xsl:copy-of select="gmd:referenceSystemInfo"/>
		<xsl:copy-of select="gmd:metadataExtensionInfo"/>
		<gmd:identificationInfo>
			<srv:SV_ServiceIdentification>
				<xsl:copy-of select="gmd:identificationInfo/gmd:MD_DataIdentification/gmd:citation"/>
				<xsl:copy-of select="gmd:identificationInfo/gmd:MD_DataIdentification/gmd:abstract"/>
				<xsl:copy-of select="gmd:identificationInfo/gmd:MD_DataIdentification/gmd:pointOfContact"/>
				<xsl:copy-of select="gmd:identificationInfo/gmd:MD_DataIdentification/gmd:descriptiveKeywords[gmd:MD_Keywords/gmd:thesaurusName/gmd:CI_Citation/gmd:title/gco:CharacterString = 'GEMET - Concepts, version 2.4']"/>
				<gmd:descriptiveKeywords>
					<gmd:MD_Keywords xmlns:gmx="http://www.isotc211.org/2005/gmx">
						<gmd:keyword>
							<gco:CharacterString/>
						</gmd:keyword>
						<gmd:type>
							<gmd:MD_KeywordTypeCode codeList="http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/Codelist/ML_gmxCodelists.xml#MD_KeywordTypeCode" codeListValue="theme"/>
						</gmd:type>
						<gmd:thesaurusName>
							<gmd:CI_Citation>
								<gmd:title>
									<gco:CharacterString>ISO - 19119 geographic services taxonomy</gco:CharacterString>
								</gmd:title>
								<gmd:date>
									<gmd:CI_Date>
										<gmd:date>
											<gco:Date>2008-12-03</gco:Date>
										</gmd:date>
										<gmd:dateType>
											<gmd:CI_DateTypeCode codeList="http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/Codelist/ML_gmxCodelists.xml#CI_DateTypeCode" codeListValue="publication"/>
										</gmd:dateType>
									</gmd:CI_Date>
								</gmd:date>
								<gmd:identifier>
									<gmd:MD_Identifier>
										<gmd:code>
											<gmx:Anchor xlink:href="http://geoportal.nipp.hr/geonetwork/srv/en/thesaurus.download?ref=external.theme.inspire-service-taxonomy">geonetwork.thesaurus.external.theme.inspire-service-taxonomy</gmx:Anchor>
										</gmd:code>
									</gmd:MD_Identifier>
								</gmd:identifier>
							</gmd:CI_Citation>
						</gmd:thesaurusName>
					</gmd:MD_Keywords>
				</gmd:descriptiveKeywords>
				<xsl:copy-of select="gmd:identificationInfo/gmd:MD_DataIdentification/gmd:resourceConstraints"/>
				<srv:serviceType>
					<gco:LocalName codeSpace="www.w3c.org"/>
				</srv:serviceType>
				<srv:extent>
					<xsl:copy-of select="gmd:identificationInfo/gmd:MD_DataIdentification/gmd:extent/gmd:EX_Extent"/>
				</srv:extent>
				<srv:coupledResource/>
				<srv:couplingType/>
				<srv:containsOperations/>
				<srv:operatesOn/>
			</srv:SV_ServiceIdentification>
		</gmd:identificationInfo>
		<gmd:distributionInfo>
			<gmd:MD_Distribution>
				<xsl:copy-of select="gmd:distributionInfo/gmd:MD_Distribution/gmd:transferOptions"/>
			</gmd:MD_Distribution>
		</gmd:distributionInfo>
		<gmd:dataQualityInfo>
			<gmd:DQ_DataQuality>
				<gmd:scope>
					<gmd:DQ_Scope>
						<gmd:level>
							<gmd:MD_ScopeCode codeListValue="service" codeList="http://standards.iso.org/ittf/PubliclyAvailableStandards/ISO_19139_Schemas/resources/Codelist/ML_gmxCodelists.xml#MD_ScopeCode"/>
						</gmd:level>
					</gmd:DQ_Scope>
				</gmd:scope>
				<xsl:copy-of select="gmd:dataQualityInfo/gmd:DQ_DataQuality/gmd:report"/>
				<xsl:copy-of select="gmd:dataQualityInfo/gmd:DQ_DataQuality/gmd:lineage"/>
			</gmd:DQ_DataQuality>
		</gmd:dataQualityInfo>
		</gmd:MD_Metadata>
	</xsl:template>  
</xsl:stylesheet>
