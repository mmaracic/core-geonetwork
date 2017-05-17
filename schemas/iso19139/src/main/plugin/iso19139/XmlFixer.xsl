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

	<!-- Fixes xml problems before storing into database -->
	<!-- Fixes empty Boolean tags in pass section -->
	<xsl:template match="gmd:MD_Metadata">
		<gmd:MD_Metadata xmlns:gmd="http://www.isotc211.org/2005/gmd" xmlns:gco="http://www.isotc211.org/2005/gco" xmlns:xlink="http://www.w3.org/1999/xlink" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:gml="http://www.opengis.net/gml" xmlns:geonet="http://www.fao.org/geonetwork" xmlns:srv="http://www.isotc211.org/2005/srv" xmlns:rdf="http://www.w3.org/1999/02/22-rdf-syntax-ns#" xmlns:skos="http://www.w3.org/2004/02/skos/core#">
		<xsl:copy-of select="gmd:fileIdentifier"/>
		<xsl:copy-of select="gmd:language"/>
		<xsl:copy-of select="gmd:hierarchyLevel"/>
		<xsl:copy-of select="gmd:contact"/>
		<xsl:copy-of select="gmd:dateStamp"/>
		<xsl:copy-of select="gmd:referenceSystemInfo"/>
		<xsl:copy-of select="gmd:metadataExtensionInfo"/>
		<xsl:copy-of select="gmd:identificationInfo"/>
		<xsl:copy-of select="gmd:distributionInfo"/>
		<gmd:dataQualityInfo>
			<gmd:DQ_DataQuality>
				<xsl:copy-of select="gmd:dataQualityInfo/gmd:DQ_DataQuality/gmd:scope"/>
				<xsl:for-each select="gmd:dataQualityInfo/gmd:DQ_DataQuality/gmd:report">
					<gmd:report>
						<gmd:DQ_DomainConsistency xsi:type="gmd:DQ_DomainConsistency_Type">
							<gmd:result>
								<gmd:DQ_ConformanceResult>
									<xsl:copy-of select="gmd:DQ_DomainConsistency/gmd:result/gmd:DQ_ConformanceResult/gmd:specification"/>
									<xsl:copy-of select="gmd:DQ_DomainConsistency/gmd:result/gmd:DQ_ConformanceResult/gmd:explanation"/>
									<xsl:choose>
										<xsl:when test="gmd:DQ_DomainConsistency/gmd:result/gmd:DQ_ConformanceResult/gmd:pass[gco:Boolean and normalize-space(string(gco:Boolean))]">
											<xsl:copy-of select="gmd:DQ_DomainConsistency/gmd:result/gmd:DQ_ConformanceResult/gmd:pass"/>
											<!--<xsl:message>
												#Copy#<xsl:value-of select="gmd:DQ_DomainConsistency/gmd:result/gmd:DQ_ConformanceResult/gmd:pass"/>#
											</xsl:message>-->
										</xsl:when>
										<xsl:otherwise>
											<gmd:pass gco:nilReason="unknown"/>
											<!--<xsl:message>
												#NOT Copy#<xsl:value-of select="gmd:DQ_ConformanceResult/gmd:pass"/>#
											</xsl:message>-->
										</xsl:otherwise>					
									</xsl:choose>
								</gmd:DQ_ConformanceResult>
							</gmd:result>
						</gmd:DQ_DomainConsistency>
					</gmd:report>
				</xsl:for-each>
				<xsl:copy-of select="gmd:dataQualityInfo/gmd:DQ_DataQuality/gmd:lineage"/>
			</gmd:DQ_DataQuality>
		</gmd:dataQualityInfo>
		</gmd:MD_Metadata>
	</xsl:template>  
</xsl:stylesheet>
