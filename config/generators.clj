{:standard-generators
 [
  {:element "Class"
   :template "generator-class"
   :includes ["lib" "model/lib" "java/lib" "common"]
   :suffix "Generator"
   :subdir "src"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.generator"
   :namespaceIncludes "org.soulspace.modelling.repository.elements"
   :generationFilterPattern "^\\s*$"
   :stereotypes "NONE"
   }
  {:element "Package"
   :template "generator-group"
   :includes ["lib" "model/lib" "java/lib" "common"]
   :baseName "GeneratorGroup"
   :subdir "src"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.generator"
   :stereotypes "model elements"
		}
  {:element "Package"
   :template "generation-context"
   :includes ["lib" "model/lib" "java/lib" "common"]
   :baseName "AbstractGenerationContext"
   :subdir "src"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.generator"
   :stereotypes "model elements"
   }
  {:element "Package"
   :template "generator-task"
   :includes ["lib" "model/lib" "java/lib" "common"]
   :baseName "AbstractGeneratorTask"
   :subdir "src"
   :extension "java"
   :baseNamespace "org.soulspace.modelling.generator.ant"
   :stereotypes "model elements"
   }
  ]
 }