<?xml version="1.0" encoding="utf-8"?>
<!--
	Generator for XMI registry builder
	Ludger Solbach
	-->
<xsl:stylesheet version ="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
	xmlns:xs="http://www.w3.org/2001/XMLSchema">
	<xsl:output method="text"/>
	<xsl:template match="xs:schema">
package org.soulspace.xmi.repository;

import java.util.Enumeration;
import org.soulspace.xmi.marshal.*;
import org.soulspace.xmi.marshal.types.*;
import org.soulspace.xmi.util.XmiHelper;

/**
 * XMIBuilder
 * Generated from schema
 */
public class XMIBuilder {
	
	XMIRepository xmi;
	
	/**
	 * Constructor
	 */
	public XMIBuilder(XMIRepository xmi) {
		super();
		this.xmi = xmi;
	}
		<xsl:apply-templates/>
}
  </xsl:template>
	
	<xsl:template match="xs:element[@name and not(starts-with(@name, 'XMI'))]">
  /**
   * Traverse <xsl:value-of select="translate(./@name, '.', '_')"/> element.
   */
  void traverse<xsl:value-of select="translate(./@name, '.', '_')"/>(
	               String namespace,
								 org.soulspace.xmi.marshal.<xsl:value-of select="translate(./@name, '.', '_')"/> element) {
		String childNamespace = namespace;
		<xsl:apply-templates select="." mode="namespace"/>
		<xsl:apply-templates mode="register"/><xsl:apply-templates mode="descend"/>
  }
	</xsl:template>

	<!-- choose correct namespace for child elements -->
  <!-- According to XmiReader -->
	<!--
		Qualified Name for Package, Class, Interface, Operation, Attribute, Method,
		TagDefinition, Stereotype, Association, DataType, Dependency, Abstraction, Generalization
		-->
	<!-- Traverse with Qualified Name at Package, Class, Interface, Method?, DataType -->
  <!--
		Missing in XmiReader Parameter, Exception, AssociationEnd, AssociationClass,
		Actor, UseCase, EventOccurance, ExecutionOccurance, Message, MessageEnd,
		OpaqueExpression, Lifeline, ...
		-->
	
	<!-- Rule: Set Namespace for Elements in Namespace_ownedElements? -->
	<!-- Rule: Set Qualified Name for Named Elements? -->
	<!-- Rule: Traverse with Qualified Name for all Named Elements? -->
	<xsl:template match="xs:element[@name]" mode="namespace">
		<xsl:if test="./@name='Class'
									or ./@name='Package'
									or ./@name='Interface'
									or ./@name='Operation'
									or ./@name='Method'
									or ./@name='TagDefinition'
									or ./@name='Stereotype'
									or ./@name='UseCase'
									or ./@name='Actor'
									or ./@name='StateMachine'
									or ./@name='State'
									or ./@name='SimpleState'
									or ./@name='SubmachineState'
									or ./@name='Pseudostate'
									or ./@name='FinalState'
									or ./@name='DataType'">
		if(element.getXmi_idref() != null) {
			// just a reference
			return;
		}
		</xsl:if>
		<xsl:if test="./@name='Package'
									or ./@name='Class'
									or ./@name='Interface'
									or ./@name='Operation'
									or ./@name='Attribute'
									or ./@name='Method'
									or ./@name='TagDefinition'
									or ./@name='Stereotype'
									or ./@name='Association'
									or ./@name='UseCase'
									or ./@name='Actor'
									or ./@name='StateMachine'
									or ./@name='State'
									or ./@name='SimpleState'
									or ./@name='SubmachineState'
									or ./@name='Pseudostate'
									or ./@name='FinalState'
									or ./@name='DataType'">
		<!-- TODO make reporting more elegant than console output -->
		if(element.getName() == null) {
			System.out.println("INFO: Name of <xsl:value-of select="translate(./@name, '.', '_')"/> with XmiId " + element.getXmi_id() + " is not set");
		}
		String qualifiedName = XmiHelper.appendNamespace(namespace, element.getName());
		element.setNamespace(namespace);
		element.setQualifiedName(qualifiedName);
		element.setParent(xmi.lookupByQualifiedName(namespace));
		</xsl:if>
		<xsl:if test="./@name='Package'
									or ./@name='Class'
									or ./@name='Interface'
									or ./@name='DataType'
		">
		childNamespace = qualifiedName;
		</xsl:if>
	</xsl:template>

	<xsl:template match="xs:attribute[@ref='xmi.id']" mode="register">
		xmi.register<xsl:value-of select="translate(../../@name, '.', '_')"/>(element.getXmi_id(), element);
	</xsl:template>

	<xsl:template match="xs:choice[@maxOccurs='unbounded']" mode="descend">
    Enumeration e = element.enumerate<xsl:value-of select="translate(../../@name, '.', '_')"/>Item();
    while(e.hasMoreElements()) {
      <xsl:value-of select="translate(../../@name, '.', '_')"/>Item item = (<xsl:value-of select="translate(../../@name, '.', '_')"/>Item) e.nextElement();
      <xsl:apply-templates mode="descend"/>
    }
	</xsl:template>

	<xsl:template match="xs:element/xs:complexType/xs:choice[@maxOccurs='unbounded']/xs:element[@ref]" mode="descend">
		<xsl:choose>
			<xsl:when test="@ref='Class'">
      if(item.getClazz() != null) {
        traverseClass(childNamespace, item.getClazz());
      }
		  </xsl:when>
		  <xsl:otherwise>
      if(item.get<xsl:value-of select="translate(@ref, '.', '_')"/>() != null) {
        traverse<xsl:value-of select="translate(@ref, '.', '_')"/>(childNamespace, item.get<xsl:value-of select="translate(@ref, '.', '_')"/>());
      }
		  </xsl:otherwise>
		</xsl:choose>
	</xsl:template>

	<xsl:template
		match="xs:element/xs:complexType/xs:choice[not(@maxOccurs='unbounded')]/xs:element[@ref]
					|xs:element/xs:complexType/xs:element[@ref]
					|xs:sequence/xs:element[@ref]" mode="descend">
		<xsl:choose>
			<xsl:when test="@ref='Class'">
      if(element.getClazz() != null) {
        traverseClass(childNamespace, element.getClazz());
      }
		  </xsl:when>
		  <xsl:otherwise>
      if(element.get<xsl:value-of select="translate(@ref, '.', '_')"/>() != null) {
        traverse<xsl:value-of select="translate(@ref, '.', '_')"/>(childNamespace, element.get<xsl:value-of select="translate(@ref, '.', '_')"/>());
      }
		  </xsl:otherwise>
		</xsl:choose>
	</xsl:template>
	
	<!-- supress text -->
	<xsl:template match="text()"/>
	<!--xsl:template match="comment()">
		/*
		<xsl:value-of select="comment()"/>
		*/
	</xsl:template-->
</xsl:stylesheet>
