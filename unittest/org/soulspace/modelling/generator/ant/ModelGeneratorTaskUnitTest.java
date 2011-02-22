package org.soulspace.modelling.generator.ant;

import org.apache.tools.ant.BuildFileTest;

public class ModelGeneratorTaskUnitTest extends BuildFileTest {
	
	public ModelGeneratorTaskUnitTest() {
		super();
	}
	
	public void setUp() {
		configureProject("data/unittest/build.xml");
	}
	
	public void testGenerate() {
		executeTarget("generate");
	}

	public void testClean() {
		executeTarget("clean");
	}
}
