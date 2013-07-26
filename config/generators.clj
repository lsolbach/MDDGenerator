{:standard-generators
 [
  {:element "class"
   :template "generator-class"
   :includes ["lib" "model/lib" "java/lib" "common"]
   :suffix "Generator"
   :subdir "src"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.generator"
   :namespaceIncludes "org.soulspace.modelling.repository.elements"
   :generationFilterPattern "^\\s*$"
   :stereotypes "NONE"
   }
  {:element "Package"
   :template "generator-group"
   :includes ["lib" "model/lib" "java/lib" "common"]
   :basename "GeneratorGroup"
   :subdir "src"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.generator"
   :useNameAsNamespace "true"
   :stereotypes "model elements"
		}
  {:element "Package"
   :template "generation-context"
   :includes ["lib" "model/lib" "java/lib" "common"]
   :basename "AbstractGenerationContext"
   :subdir "src"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.generator"
   :useNameAsNamespace "true"
   :stereotypes "model elements"
   }
  {:element "Package"
   :template "generator-task"
   :includes ["lib" "model/lib" "java/lib" "common"]
   :basename "AbstractGeneratorTask"
   :subdir "src"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.generator.ant"
   :useNameAsNamespace "true"
   :stereotypes "model elements"
   }
  ]
 }